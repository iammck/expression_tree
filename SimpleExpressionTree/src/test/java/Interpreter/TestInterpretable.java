import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestInterpretable {
	// some default interpretables to be used in tests. setup before 
	// Interpretables implement Interpretable and can be interpreted.
	Interpretable number, negation, addition, subtraction,
		multiplication, division,
		leftParenthesis, rightParenthesis;
	// Lists for parsed and accumulated symbols.
	ArrayList<Interpretable> interpretedList;
	ArrayList<Interpretable> parsedList;
	
	/*
	 * Having tested for the cretion of nodes, go ahead
	 * and create some defaults.
	 */
	@Before
	public void setUpDefaultInterpretables(){
		// create one of each symbol.		
		number = new Number("42.34");
		negation = new Negation();
		addition = new Addition();
		subtraction = new Subtraction();
		multiplication = new Multiplication();
		division = new Division();
		leftParenthesis  = new Parenthesis("(");
		rightParenthesis = new Parenthesis(")");
	
		interpretedList = new ArrayList<Interpretable>();
		parsedList = new ArrayList<Interpretable>();
	
	
	}

		
	@Test
	public void testCreatingInterpretables(){
		Interpretable symbol;
		// Number
		symbol = new Number("42.34");
		// Negation
		symbol = new Negation();
		// Addition
		symbol = new Addition();
		// Subtraction
		symbol = new Subtraction();
		// Multiplication
		symbol = new Multiplication();
		// Divide
		symbol = new Division();
		// Parenthesis
		symbol = new Parenthesis("(");
		symbol = new Parenthesis(")");
		
	}
	
	@Test
	public void testNumberHasLessOrEqualPrecedenceOver(){
		// check that numbers compared to numbers are 0.
		assertEquals("Number.precedenceComparedTo(Number) should result in 0 ",
				0, number.precedenceComparedTo(number));
		
		// numbers should be less than all other symbol
		ArrayList<Interpretable> interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(negation);
		interpretableList.add(addition);
		interpretableList.add(subtraction);
		interpretableList.add(multiplication);
		interpretableList.add(division);
		interpretableList.add(leftParenthesis);
		interpretableList.add(rightParenthesis);
		for(Interpretable interpretable: interpretableList){
			int result = number
				.precedenceComparedTo(interpretable);
			assertEquals("Number.precedenceComparedTo() "
				+ "should result in -1.", -1, result);
		}
		
	}
	
	@Test
	public void testAdditionHasLessOrEqualPrecedenceOver(){
		// check that Addition compared to addition is 0.
		assertEquals("Addition.precedenceComparedTo(Addition) should result in 0 ",
				0, addition.precedenceComparedTo(addition));
		// check that Addition compared to subtraction is 0.
		assertEquals("Addition.precedenceComparedTo(Subtraction) should result in 0 ",
				0, addition.precedenceComparedTo(subtraction));
		
		
		// addition should be less than these
		ArrayList<Interpretable> interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(negation);
		interpretableList.add(multiplication);
		interpretableList.add(division);
		interpretableList.add(leftParenthesis);
		interpretableList.add(rightParenthesis);
		for(Interpretable interpretable: interpretableList){
			int result = addition
				.precedenceComparedTo(interpretable);
			assertEquals("addition precedenceComparedTo " + interpretable.toString()
				+ " should result in -1.", -1, result);
		}	
		
		// check that Addition compared to numbers is 1.
		assertEquals("Addition.precedenceComparedTo(number) should result in 1 ",
				1, addition.precedenceComparedTo(number));
		
	}
	
	@Test
	public void testSubtractionHasLessOrEqualPrecedenceOver(){
		// check that subtraction compared to addition is 0.
		assertEquals("subtraction.precedenceComparedTo(subtraction) should result in 0 ",
				0, subtraction.precedenceComparedTo(addition));
		// check that subtraction compared to subtraction is 0.
		assertEquals("subtraction.precedenceComparedTo(Subtraction) should result in 0 ",
				0, subtraction.precedenceComparedTo(subtraction));
		
		
		// subtraction should be less than these
		ArrayList<Interpretable> interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(negation);
		interpretableList.add(multiplication);
		interpretableList.add(division);
		interpretableList.add(leftParenthesis);
		interpretableList.add(rightParenthesis);
		for(Interpretable interpretable: interpretableList){
			int result = subtraction
				.precedenceComparedTo(interpretable);
			assertEquals("subtraction precedenceComparedTo " + interpretable.toString()
				+ " should result in -1.", -1, result);
		}	
		
		// check that subtraction compared to numbers is 1.
		assertEquals("subtraction.precedenceComparedTo(number) should result in 1 ",
				1, subtraction.precedenceComparedTo(number));
	}
	
	@Test
	public void testMultiplicationHasLessOrEqualPrecedenceOver(){
		// check that multiplication compared to multiplication is 0.
		assertEquals("multiplication.precedenceComparedTo(multiplication) should result in 0 ",
				0, multiplication.precedenceComparedTo(multiplication));
		// check that multiplication compared to division is 0.
		assertEquals("multiplication.precedenceComparedTo(division) should result in 0 ",
				0, multiplication.precedenceComparedTo(division));		
		
		// multiplication should be less than these
		ArrayList<Interpretable> interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(negation);
		interpretableList.add(leftParenthesis);
		interpretableList.add(rightParenthesis);
		for(Interpretable interpretable: interpretableList){
			int result = multiplication
				.precedenceComparedTo(interpretable);
			assertEquals("multiplication precedenceComparedTo " + interpretable.toString()
				+ " should result in -1.", -1, result);
		}
		
		// multiplication should be greater than these
		interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(number);
		interpretableList.add(addition);
		interpretableList.add(subtraction);
		for(Interpretable interpretable: interpretableList){
			int result = multiplication
				.precedenceComparedTo(interpretable);
			assertEquals("multiplication precedenceComparedTo " + interpretable.toString()
				+ " should result in 1.", 1, result);
		}
	}
	
	@Test
	public void testDivisionHasLessOrEqualPrecedenceOver(){
		// check that division compared to multiplication is 0.
		assertEquals("division.precedenceComparedTo(multiplication) should result in 0 ",
				0, division.precedenceComparedTo(multiplication));
		// check that division compared to division is 0.
		assertEquals("division.precedenceComparedTo(division) should result in 0 ",
				0, division.precedenceComparedTo(division));
		
		
		// division should be less than these
		ArrayList<Interpretable> interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(negation);
		interpretableList.add(leftParenthesis);
		interpretableList.add(rightParenthesis);
		for(Interpretable interpretable: interpretableList){
			int result = division
				.precedenceComparedTo(interpretable);
			assertEquals("division precedenceComparedTo " + interpretable.toString()
				+ " should result in -1.", -1, result);
		}
		
		// division should be greater than these
		interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(number);
		interpretableList.add(addition);
		interpretableList.add(subtraction);
		for(Interpretable interpretable: interpretableList){
			int result = division
				.precedenceComparedTo(interpretable);
			assertEquals("division precedenceComparedTo " + interpretable.toString()
				+ " should result in 1.", 1, result);
		}
	}
	
	
	@Test
	public void testNegationHasLessOrEqualPrecedenceOver(){
		// check that negation compared to negation is 0.
		assertEquals("negation.precedenceComparedTo(negation) should result in 0 ",
				0, negation.precedenceComparedTo(negation));
		
		// negation should be less than these
		ArrayList<Interpretable> interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(leftParenthesis);
		interpretableList.add(rightParenthesis);
		for(Interpretable interpretable: interpretableList){
			int result = negation
				.precedenceComparedTo(interpretable);
			assertEquals("negation precedenceComparedTo " + interpretable.toString()
				+ " should result in -1.", -1, result);
		}
		
		// negation should be greater than these
		interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(number);
		interpretableList.add(addition);
		interpretableList.add(subtraction);
		interpretableList.add(multiplication);
		interpretableList.add(division);
		for(Interpretable interpretable: interpretableList){
			int result = negation
				.precedenceComparedTo(interpretable);
			assertEquals("negation precedenceComparedTo " + interpretable.toString()
				+ " should result in 1.", 1, result);
		}
	}

	
	@Test
	public void testParenthesisHasLessOrEqualPrecedenceOver(){
		// check that leftParenthesis compared to leftParenthesis is 0.
		assertEquals("leftParenthesis.precedenceComparedTo(leftParenthesis) should result in 0 ",
				0, leftParenthesis.precedenceComparedTo(leftParenthesis));
		
		// leftParenthesis should be less than these none
		ArrayList<Interpretable> interpretableList = null;
		
		// leftParenthesis should be greater than these
		interpretableList = new ArrayList<Interpretable>();
		interpretableList.add(number);
		interpretableList.add(addition);
		interpretableList.add(subtraction);
		interpretableList.add(multiplication);
		interpretableList.add(division);
		interpretableList.add(negation);
		for(Interpretable interpretable: interpretableList){
			int result = leftParenthesis
				.precedenceComparedTo(interpretable);
			assertEquals("leftParenthesis precedenceComparedTo " + interpretable.toString()
				+ " should result in 1.", 1, result);
		}
	}
	
	@Test
	public void testInterpretNumber(){
		boolean result = number.interpret(interpretedList);
		// result should be true
		assertTrue("number.interpret() should return true.", result);
		int size = interpretedList.size();
		// length should be one.
		assertEquals("interpreting number should add "
				+ "it to the parsed list.", 1, size);
		
	}
	
	@Test
	public void testInterpretBinaryOperators(){
		// will need some numbers for the interpretedList
		Interpretable num1 = new Number("1");
		Interpretable num2 = new Number("2");
		Interpretable num3 = new Number("3");
		Interpretable num4 = new Number("4");
		// any binary operator will do since interprete is handled by
		// the abstract BinaryOperator
		boolean result = multiplication.interpret(interpretedList);
		// if there are no or not enough interpretables in interpretedList,
		// the operater should have a false result.
		assertFalse("if there are no interpretables in "
				+ " interpretedList, the operater should "
				+ " have a false result.",result);
		interpretedList.add(num1);
		result = multiplication.interpret(interpretedList);
		assertFalse("if there are not enough interpretables in "
				+ " interpretedList, the operater should "
				+ " have a false result.",result);
		// If there are two, should have grabed last two numbers before
		// inserting itself onto interpretedList
		interpretedList.add(num2);
		result = multiplication.interpret(interpretedList);
		assertTrue("if there are enough interpretables in "
				+ " interpretedList, the operater should "
				+ " have a true result.",result);
		// size of interpretedList should be one.
		int size = interpretedList.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove two interpretables and add itself "
				+ "to the parsed list.", 1, size);
		
		assertSame("multiplication has wrong left interpretable",
				   	num1, ((BinaryOperator) multiplication).getLeftSymbol());		
		assertSame("multiplication has wrong right interpretable",
				   	num2, ((BinaryOperator) multiplication).getRightSymbol());
		// should be able to add two more numbers to the parsed interpretable,
		// then be able to and interprete another interpretable.
		interpretedList.add(num3);
		interpretedList.add(num4);
		division.interpret(interpretedList);
		// size of interpretedList should be two.
		size = interpretedList.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove two interpretables and add itself "
				+ "to the list.", 2, size);
		// should be able to interprete the two interpretables with a third.
		addition.interpret(interpretedList);
		// size of interpretedList should be one.
		size = interpretedList.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove anytwo interpretables and add itself "
				+ "to the list.", 1, size);
		// the final interpretable should be addition
		assertSame("the final interpretable should be addition",
					addition, interpretedList.get(0));
		// addition should have multiplication as left interpretable
		assertSame("addition should have multiplication as left interpretable",
				multiplication, 
				((BinaryOperator) addition).getLeftSymbol());		
		// division as right interpretable
		assertSame("addition should have division as right interpretable",
				division, 
				((BinaryOperator) addition).getRightSymbol());		

	}
	
	@Test
	public void testInterpretUnaryOperators(){
		// will need some numbers for the interpretedList
		Interpretable num1 = new Number("1");
		Interpretable num2 = new Number("2");
		Interpretable num3 = new Number("3");
		Interpretable num4 = new Number("4");
		boolean result = negation.interpret(interpretedList);
		// if there are no or not enough interpretables in interpretedList,
		// the operater should have a false result.
		assertFalse(" If there are no interpretables in "
				+ " interpretedList, negation should "
				+ " have a false result.",result);
		interpretedList.add(num1);
		result = negation.interpret(interpretedList);
		assertTrue(" If there are enough interpretables in "
				+ " interpretedList, negation should "
				+ " have a true result.",result);
		int size = interpretedList.size();
		assertEquals("interpreting unary operatoer should "
				+ "remove interpretable and add itself "
				+ "to the list.", 1, size);
		assertSame("negation has wrong right side interpretable",
				   	num1, ((UnaryOperator) negation).getRightSymbol());
		// should be able to have a negation of a negation
		Interpretable neg1 = new  Negation();
		result = neg1.interpret(interpretedList);
		assertTrue(" If there is a negation interpretable next in "
				+ " interpretedList, negation should "
				+ " have a true result.",result);
				// size of interpretedList should be one.
		size = interpretedList.size();
		assertEquals("interpreting unary operatoer should "
				+ "remove interpretable and add itself "
				+ "to the list.", 1, size);
		assertSame("negation has wrong right side interpretable",
				   	negation, ((UnaryOperator) neg1).getRightSymbol());
		
	}
	
	@Test
	public void testInterpretParenthesis(){
		assertFalse("Parenthesis interpret() should always be false.", 
				leftParenthesis.interpret(interpretedList));
	}
	
	
	@Test
	public void testAddToInterpretListsNumber() throws Exception{
		// a number gets added to interpretedList right away
		number.addToInterpreter(interpretedList, parsedList);
		int iSize = interpretedList.size();
		int pSize = parsedList.size();
		assertEquals("interpretedList should have size 1 one after a number is inserted.", 
					1, iSize);
		assertEquals("parsedList should have size 0  after a number is inserted.", 
					0, pSize);
		assertSame("Number should be the only thing in interpretedList.", 
					number, interpretedList.get(0));
	}
	
	@Test
	public void testAddToInterpretListsUnaryOperator()throws Exception{
		negation.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one interpretable, negation, in parsedList",
				1, parsedList.size());
		
	}
	
	@Test
	public void testAddToInterpretListsBinaryOperatorWithEmptyParsedList()throws Exception{
		// if there is nothing in the parsedList list the interpretable should just add itself.
		addition.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one interpretable in parsedList",
				1, parsedList.size());
	}
	
	@Test
	public void testAddToInterpretListsBinaryOperatorWithEqPrecParsedList()throws Exception{
		// add the first
		addition.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one interpretable, addition, in parsedList",
				1, parsedList.size());
		
		// if another operator is added with equal precedence
		// the first operator should be interpreted. if it fails,
		// both should be left in accumOperatoers.
		subtraction.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be two interpretables in parsedList",
				2, parsedList.size());
		// add some numbers to interpretedList
		interpretedList.add(new Number("67.8"));
		interpretedList.add(new Number("67.8"));
		// remove subtraction from parsedList
		parsedList.remove(subtraction);
		assertEquals("subtraction should bave been removed from parsedList",
				1, parsedList.size());
		subtraction.addToInterpreter(interpretedList, parsedList);
		// There should be one interpretable in parsed list.
		assertEquals("There should be one interpretable in parsedList list.",
				1, parsedList.size());
		assertEquals("There should be one interpretable in paresedList: "
				+ interpretedList.toString(),
				1, interpretedList.size());
	}
	
	@Test
	public void testAddToInterpretListsBinaryOperatorWithLessPrecThanLastParsedList()throws Exception{
		// add the first
		multiplication.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one interpretable, multiplication, in parsedList",
				1, parsedList.size());
		
		// if another operator is added with less precedence
		// the first operator should be interpreted. If it fails,
		// both operators are left in in accum operators.
		subtraction.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be two interpretables in parsedList",
				2, parsedList.size());
		// add some numbers to interpretedList
		interpretedList.add(new Number("67.8"));
		interpretedList.add(new Number("67.8"));
		// remove subtraction from parsedList
		parsedList.remove(subtraction);
		assertEquals("subtraction should bave been removed from parsedList",
				1, parsedList.size());
		subtraction.addToInterpreter(interpretedList, parsedList);
		// There should be one in accumSybols and paresed interpretables.
		assertEquals("There should be 1 interpretable in parsedList list.",
				1, parsedList.size());
		assertEquals("There should be 1 interpretable in interpretedList: "
				+ interpretedList.toString(),
				1, interpretedList.size());
	}
	
	@Test
	public void testAddToInterpretListsBinaryOperatorWithGreaterPrecParsedList()throws Exception{
		// add the first
		addition.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one interpretable, addition, in parsedList",
				1, parsedList.size());
		
		// if another operator is added with greater precedence
		// the first operator should not interpreted. if it fails,
		// both should be left in accumOperatoers.
		multiplication.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be two interpretables in parsedList",
				2, parsedList.size());
		// add some numbers to interpretedList
		interpretedList.add(new Number("67.8"));
		interpretedList.add(new Number("67.8"));
		// remove multiplication from parsedList
		parsedList.remove(multiplication);
		assertEquals("multipliction should bave been removed from parsedList",
				1, parsedList.size());
		multiplication.addToInterpreter(interpretedList, parsedList);
		// There should be two in accumSybols and paresed interpretables.
		assertEquals("There should be two interpretable in parsedList list.",
				2, parsedList.size());
		assertEquals("There should be two interpretable in interpretedList: "
				+ interpretedList.toString(),
				2, interpretedList.size());
	}
	
		
	@Test
	public void testAddToInterpretListsParenthesis()throws Exception{
		// left parethesis will be put it on the parsedList list.
		leftParenthesis.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one interpretable in parsedList list.",
				1, parsedList.size());
		assertSame("LeftParenthesis should be the only accumulated interpretable.", 
					leftParenthesis, parsedList.get(0));
		// adding a right parethesis should remove both from the accum
		rightParenthesis.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be no interpretables in parsedList list.",
				0, parsedList.size());
		// a parenthesis with a sub expression should be evaluated.
		Interpretable num1 = new Number("1");
		Interpretable num2 = new Number("2");
		leftParenthesis.addToInterpreter(interpretedList,parsedList);
		num1.addToInterpreter(interpretedList,parsedList);
		addition.addToInterpreter(interpretedList,parsedList);
		num2.addToInterpreter(interpretedList,parsedList);
		rightParenthesis.addToInterpreter(interpretedList,parsedList);
		assertEquals("There should be one interpretable in interpretedList list.",
				1, interpretedList.size());
		assertEquals("There should be no interpretables in parsedList list.",
				0, parsedList.size());
		assertSame("addition should be the only parsed interpretable.", 
					addition, interpretedList.get(0));
		
	}
	
}
