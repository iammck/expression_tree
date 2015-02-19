import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class TestEvaluatable {
	
	Evaluatable numberSeven;
	Evaluatable numberEight;
	Evaluatable numberNine;
	Evaluatable addition;
	Evaluatable subtraction;
	Evaluatable multiplication;
	Evaluatable division;
	Evaluatable leftParenthesis;
	Evaluatable rightParenthesis;
	Evaluatable negation;

	@Test
	public void testSymbolsImplementEvaluatable(){
		// All symbols need to be implement the Evaluatable interface
		Symbol number = ((Symbol) new Number("7"));
		assertTrue("All Symbols need to implement Evaluatable.",
				(number instanceof Evaluatable)? true: false);
	}
	
	@Before
	public void beforeEachTest(){
		// instantiate one of each symbol for tests, also extra numbers.
		numberSeven = new Number("7");
		numberEight = new Number("8");
		numberNine = new Number("9");
		addition = new Addition();
		subtraction = new Subtraction();
		multiplication = new Multiplication();
		division = new Division();
		leftParenthesis = new Parenthesis("(");
		rightParenthesis = new Parenthesis(")");
		negation = new Negation();
	}
	
	@Test
	public void testEvaluatablePrecedenceComparedToForSymbols(){
		// All symbols need to be implement the Evaluatable interface
		assertEquals("Number 7 and Number 8 should have equal precedence.",
			0, numberSeven.precedenceComparedTo(numberEight));
		
		assertEquals("Number precedence should be lower than Addition",
			-1, numberSeven.precedenceComparedTo(addition));
		assertEquals("Number precedence should be lower than Subtraction",
			-1, numberSeven.precedenceComparedTo(subtraction));
		assertEquals("Number precedence should be lower than multiplication",
			-1, numberSeven.precedenceComparedTo(multiplication));
		assertEquals("Number precedence should be lower than Division",
			-1, numberSeven.precedenceComparedTo(division));
		assertEquals("Number precedence should be lower than left parenthesis",
			-1, numberSeven.precedenceComparedTo(leftParenthesis));
		assertEquals("Number precedence should be lower than Negation",
			-1, numberSeven.precedenceComparedTo(negation));
		
		
		assertEquals("Parenthesis precedence should be higher than Number 7",
			1, leftParenthesis.precedenceComparedTo(numberSeven));
		assertEquals("Parenthesis precedence should be higher than Addition",
			1, leftParenthesis.precedenceComparedTo(addition));
		assertEquals("Parenthesis precedence should be higher than Subtraction",
			1, leftParenthesis.precedenceComparedTo(subtraction));
		assertEquals("Parenthesis precedence should be higher than multiplication",
			1, leftParenthesis.precedenceComparedTo(multiplication));
		assertEquals("Parenthesis precedence should be higher than Division",
			1, leftParenthesis.precedenceComparedTo(division));
		assertEquals("Parenthesis precedence should be higher than Negation",
			1, leftParenthesis.precedenceComparedTo(negation));
		assertEquals("Parenthesis precedence should equal Parenthesis",
			0, leftParenthesis.precedenceComparedTo(rightParenthesis));
		
		
		assertEquals("Multiplication precedence should be higher than Number 7",
			1, multiplication.precedenceComparedTo(numberSeven));
		assertEquals("Multiplication precedence should be higher than Addition",
			1, multiplication.precedenceComparedTo(addition));
		assertEquals("Multiplication precedence should be higher than Subtraction",
			1, multiplication.precedenceComparedTo(subtraction));
		assertEquals("Multiplication precedence should equal multiplication",
			0, multiplication.precedenceComparedTo(multiplication));
		assertEquals("Multiplication precedence should equal Division",
			0, multiplication.precedenceComparedTo(division));
		assertEquals("Multiplication precedence should be less than Negation",
			-1, multiplication.precedenceComparedTo(negation));
		assertEquals("Multiplication precedence should be less than Parenthesis",
			-1, multiplication.precedenceComparedTo(rightParenthesis));

	}
	
	@Test
	public void testEvaluatableEvaluate() throws Exception{
		// need the evaluated list.
		ArrayList<Evaluatable> evaluatedList; 
		evaluatedList = getInitialList();
		assertTrue("addition should be able to evaluate the list.",
			addition.evaluate(evaluatedList));
		assertEquals("addition of 7 and 8 should be 15", 
			15.0, ((Number) evaluatedList.get(0)).toDouble(), .001);
		
		evaluatedList = getInitialList();
		assertTrue("subtraction should be able to evaluate the list.",
			subtraction.evaluate(evaluatedList));
		assertEquals("subtraction of 7 and 8 should be -1", 
			-1, ((Number) evaluatedList.get(0)).toDouble(), .001);

		evaluatedList = getInitialList();
		assertTrue("multiplication should be able to evaluate the list.",
			multiplication.evaluate(evaluatedList));
		assertEquals("multiplication of 7 and 8 should be 56", 
			56, ((Number) evaluatedList.get(0)).toDouble(), .001);

		evaluatedList = getInitialList();
		assertTrue("division should be able to evaluate the list.",
			division.evaluate(evaluatedList));
		assertEquals("division of 7 and 8 should be 56", 
			0.875, ((Number) evaluatedList.get(0)).toDouble(), .0001);
		
		evaluatedList = getInitialList();
		assertTrue("negation should be able to evaluate the list.",
			negation.evaluate(evaluatedList));
		assertEquals("negation on [7, 8] list should lead to [7, -8]", 
			-8, ((Number) evaluatedList.get(1)).toDouble(), .0001);
		
		evaluatedList = getInitialList();
		assertFalse("parentesis should not be able to evaluate the list.",
			leftParenthesis.evaluate(evaluatedList));
		
	}
	
	private ArrayList<Evaluatable> getInitialList(){
		ArrayList<Evaluatable> evaluatedList = 
				new ArrayList<Evaluatable>();
		// add two numbers to the list
		evaluatedList.add(numberSeven);
		evaluatedList.add(numberEight);
		return evaluatedList;
	}
		
	
	@Test
	public void testEvaluatableAddToEvaluator()  throws Exception{
		// need the evaluated and pending lists.
		ArrayList<Evaluatable> evaluatedList = new ArrayList<Evaluatable>();
		ArrayList<Evaluatable> pendingList = new ArrayList<Evaluatable>();
		
		// if both lists are empty,
		// numbers should be immediately evaluated
		numberSeven.addToEvaluator(evaluatedList, pendingList);
		assertEquals("evaluatedList should have size of 1 after number "
			+ "addToEvaluator method call.",
			1, evaluatedList.size());
		// clear the lists
		evaluatedList.clear();
		pendingList.clear();
		// add some numbers to the evaluated list.
		numberSeven.addToEvaluator(evaluatedList, pendingList);
		numberEight.addToEvaluator(evaluatedList, pendingList);
		// binary opperators should be put onto pending
		((BinaryOperator) addition).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 1 after addition "
			+ "addToEvaluator method call.",
			1, pendingList.size());
		// binary opperators should not try to evaluate preveous evaluators of
		// lesser value
		((BinaryOperator) multiplication).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 2 after multiplication "
			+ "addToEvaluator method call.",
			2, pendingList.size());
		// remove multiplication from pendingList
		pendingList.remove(multiplication);		
		// binary opperators should try to evaluate preveous evaluators of
		// equal value
		((BinaryOperator) subtraction).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 1 after subtraction "
			+ "addToEvaluator method call.",
			1, pendingList.size());
		assertEquals("evaluatedList should have size of 1 after subtraction "
			+ "addToEvaluator method call.",
			1, evaluatedList.size());
		assertEquals("evaluatedList item should be 15 after subtraction "
			+ "addToEvaluator method call.",
			15, ((Number) evaluatedList.get(0)).toDouble(), 0.001);

		// clear the lists
		evaluatedList.clear();
		pendingList.clear();
		// add some numbers to the evaluated list.
		numberSeven.addToEvaluator(evaluatedList, pendingList);
		numberEight.addToEvaluator(evaluatedList, pendingList);
		// binary opperators should not try to evaluate preveous evaluators of
		// greater value
		((BinaryOperator) multiplication).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 1 after multiplication "
			+ "addToEvaluator method call.",
			1, pendingList.size());
		((BinaryOperator) addition).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 1 after addition "
			+ "addToEvaluator method call.",
			1, pendingList.size());
		assertEquals("evaluatedList should have size of 1 after addition "
			+ "addToEvaluator method call.",
			1, evaluatedList.size());
		assertEquals("evaluatedList item should be 56 after addition "
			+ "addToEvaluator method call.",
			56, ((Number) evaluatedList.get(0)).toDouble(), 0.001);

		// clear the lists
		evaluatedList.clear();
		pendingList.clear();
		// add some numbers to the evaluated list.
		numberSeven.addToEvaluator(evaluatedList, pendingList);
		numberEight.addToEvaluator(evaluatedList, pendingList);
		
		// unary operators go to pending list
		((UnaryOperator) negation).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 1 after negation "
			+ "addToEvaluator method call.",
			1, pendingList.size());
		// any next binary operator should change the 8 to a -8 in evaluated
		((BinaryOperator) addition).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 1 after negation "
			+ "addToEvaluator method call.",
			1, pendingList.size());
		assertEquals("second evaluatedList item should be -8 after addition "
			+ "addToEvaluator method call.",
			-8, ((Number) evaluatedList.get(1)).toDouble(), 0.001);
		
		// clear the lists
		evaluatedList.clear();
		pendingList.clear();
		// add some numbers to the evaluated list.
		numberSeven.addToEvaluator(evaluatedList, pendingList);
		numberEight.addToEvaluator(evaluatedList, pendingList);
		
		// left parenthesis should be put on pending
		((Parenthesis) leftParenthesis).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 1 after leftParenthesis "
			+ "addToEvaluator method call.",
			1, pendingList.size());
		// right parenthesis should be evaluated. left parenthesis should be removed. 
		((Parenthesis) rightParenthesis).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 0 after rightParenthesis "
			+ "addToEvaluator method call.",
			0, pendingList.size());
		// put left parenthesis and addition in pending.
		((Parenthesis) leftParenthesis).addToEvaluator(evaluatedList, pendingList);
		((BinaryOperator) addition).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 2 after addition "
			+ "addToEvaluator method call.",
			2, pendingList.size());
		// right parenthesis should be evaluated. left parenthesis should be removed. 
		((Parenthesis) rightParenthesis).addToEvaluator(evaluatedList, pendingList);
		assertEquals("pendingList should have size of 0 after rightParenthesis "
			+ "addToEvaluator method call.",
			0, pendingList.size());
		assertEquals("evaluatedList item should be 15 after rightParenthesis "
			+ "addToEvaluator method call.",
			15, ((Number) evaluatedList.get(0)).toDouble(), 0.001);
	}
	
	
	
}	
