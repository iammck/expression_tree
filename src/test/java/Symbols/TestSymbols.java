import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestSymbols {
	// some default symbols to be used in tests. setup before 
	Symbol number, negation, addition, subtraction,
		multiplication, division,
		leftParenthesis, rightParenthesis;
	// Lists for parsed and accumulated symbols.
	ArrayList<Symbol> interpretedList;
	ArrayList<Symbol> parsedList;
	
	/*
	 * Having tested for the cretion of nodes, go ahead
	 * and create some defaults.
	 */
	@Before
	public void setUpDefaultSymbols(){
		// create one of each symbol.		
		number = new Number("42.34");
		negation = new Negation();
		addition = new Addition();
		subtraction = new Subtraction();
		multiplication = new Multiplication();
		division = new Division();
		leftParenthesis  = new Parenthesis("(");
		rightParenthesis = new Parenthesis(")");
	
		interpretedList = new ArrayList<Symbol>();
		parsedList = new ArrayList<Symbol>();
	
	
	}

		
	@Test
	public void testCreatingSymbols(){
		Symbol symbol;
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
		assertEquals("Number.compareToSymbol(Number) should result in 0 ",
				0, number.precedenceComparedToSymbol(number));
		
		// numbers should be less than all other symbol
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = number
				.precedenceComparedToSymbol(symbol);
			assertEquals("Number.compareToSymbol() "
				+ "should result in -1.", -1, result);
		}
		
	}
	
	@Test
	public void testAdditionHasLessOrEqualPrecedenceOver(){
		// check that Addition compared to addition is 0.
		assertEquals("Addition.compareToSymbol(Addition) should result in 0 ",
				0, addition.precedenceComparedToSymbol(addition));
		// check that Addition compared to subtraction is 0.
		assertEquals("Addition.compareToSymbol(Subtraction) should result in 0 ",
				0, addition.precedenceComparedToSymbol(subtraction));
		
		
		// addition should be less than these
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(multiplication);
		symbolList.add(division);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = addition
				.precedenceComparedToSymbol(symbol);
			assertEquals("addition compareToSymbol " + symbol.toString()
				+ " should result in -1.", -1, result);
		}	
		
		// check that Addition compared to numbers is 1.
		assertEquals("Addition.compareToSymbol(number) should result in 1 ",
				1, addition.precedenceComparedToSymbol(number));
		
	}
	
	@Test
	public void testSubtractionHasLessOrEqualPrecedenceOver(){
		// check that subtraction compared to addition is 0.
		assertEquals("subtraction.compareToSymbol(subtraction) should result in 0 ",
				0, subtraction.precedenceComparedToSymbol(addition));
		// check that subtraction compared to subtraction is 0.
		assertEquals("subtraction.compareToSymbol(Subtraction) should result in 0 ",
				0, subtraction.precedenceComparedToSymbol(subtraction));
		
		
		// subtraction should be less than these
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(multiplication);
		symbolList.add(division);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = subtraction
				.precedenceComparedToSymbol(symbol);
			assertEquals("subtraction compareToSymbol " + symbol.toString()
				+ " should result in -1.", -1, result);
		}	
		
		// check that subtraction compared to numbers is 1.
		assertEquals("subtraction.compareToSymbol(number) should result in 1 ",
				1, subtraction.precedenceComparedToSymbol(number));
	}
	
	@Test
	public void testMultiplicationHasLessOrEqualPrecedenceOver(){
		// check that multiplication compared to multiplication is 0.
		assertEquals("multiplication.compareToSymbol(multiplication) should result in 0 ",
				0, multiplication.precedenceComparedToSymbol(multiplication));
		// check that multiplication compared to division is 0.
		assertEquals("multiplication.compareToSymbol(division) should result in 0 ",
				0, multiplication.precedenceComparedToSymbol(division));		
		
		// multiplication should be less than these
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = multiplication
				.precedenceComparedToSymbol(symbol);
			assertEquals("multiplication compareToSymbol " + symbol.toString()
				+ " should result in -1.", -1, result);
		}
		
		// multiplication should be greater than these
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		for(Symbol symbol: symbolList){
			int result = multiplication
				.precedenceComparedToSymbol(symbol);
			assertEquals("multiplication compareToSymbol " + symbol.toString()
				+ " should result in 1.", 1, result);
		}
	}
	
	@Test
	public void testDivisionHasLessOrEqualPrecedenceOver(){
		// check that division compared to multiplication is 0.
		assertEquals("division.compareToSymbol(multiplication) should result in 0 ",
				0, division.precedenceComparedToSymbol(multiplication));
		// check that division compared to division is 0.
		assertEquals("division.compareToSymbol(division) should result in 0 ",
				0, division.precedenceComparedToSymbol(division));
		
		
		// division should be less than these
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = division
				.precedenceComparedToSymbol(symbol);
			assertEquals("division compareToSymbol " + symbol.toString()
				+ " should result in -1.", -1, result);
		}
		
		// division should be greater than these
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		for(Symbol symbol: symbolList){
			int result = division
				.precedenceComparedToSymbol(symbol);
			assertEquals("division compareToSymbol " + symbol.toString()
				+ " should result in 1.", 1, result);
		}
	}
	
	
	@Test
	public void testNegationHasLessOrEqualPrecedenceOver(){
		// check that negation compared to negation is 0.
		assertEquals("negation.compareToSymbol(negation) should result in 0 ",
				0, negation.precedenceComparedToSymbol(negation));
		
		// negation should be less than these
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = negation
				.precedenceComparedToSymbol(symbol);
			assertEquals("negation compareToSymbol " + symbol.toString()
				+ " should result in -1.", -1, result);
		}
		
		// negation should be greater than these
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		for(Symbol symbol: symbolList){
			int result = negation
				.precedenceComparedToSymbol(symbol);
			assertEquals("negation compareToSymbol " + symbol.toString()
				+ " should result in 1.", 1, result);
		}
	}

	
	@Test
	public void testParenthesisHasLessOrEqualPrecedenceOver(){
		// check that leftParenthesis compared to leftParenthesis is 0.
		assertEquals("leftParenthesis.compareToSymbol(leftParenthesis) should result in 0 ",
				0, leftParenthesis.precedenceComparedToSymbol(leftParenthesis));
		
		// leftParenthesis should be less than these none
		ArrayList<Symbol> symbolList = null;
		
		// leftParenthesis should be greater than these
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		symbolList.add(negation);
		for(Symbol symbol: symbolList){
			int result = leftParenthesis
				.precedenceComparedToSymbol(symbol);
			assertEquals("leftParenthesis compareToSymbol " + symbol.toString()
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
		Symbol num1 = new Number("1");
		Symbol num2 = new Number("2");
		Symbol num3 = new Number("3");
		Symbol num4 = new Number("4");
		// any binary operator will do since interprete is handled by
		// the abstract BinaryOperator
		boolean result = multiplication.interpret(interpretedList);
		// if there are no or not enough symbols in interpretedList,
		// the operater should have a false result.
		assertFalse("if there are no symbols in "
				+ " interpretedList, the operater should "
				+ " have a false result.",result);
		interpretedList.add(num1);
		result = multiplication.interpret(interpretedList);
		assertFalse("if there are not enough symbols in "
				+ " interpretedList, the operater should "
				+ " have a false result.",result);
		// If there are two, should have grabed last two numbers before
		// inserting itself onto interpretedList
		interpretedList.add(num2);
		result = multiplication.interpret(interpretedList);
		assertTrue("if there are enough symbols in "
				+ " interpretedList, the operater should "
				+ " have a true result.",result);
		// size of interpretedList should be one.
		int size = interpretedList.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove two symbols and add itself "
				+ "to the parsed list.", 1, size);
		
		assertSame("multiplication has wrong left symbol",
				   	num1, ((BinaryOperator) multiplication).getLeftSymbol());		
		assertSame("multiplication has wrong right symbol",
				   	num2, ((BinaryOperator) multiplication).getRightSymbol());
		// should be able to add two more numbers to the parsed symbol,
		// then be able to and interprete another symbol.
		interpretedList.add(num3);
		interpretedList.add(num4);
		division.interpret(interpretedList);
		// size of interpretedList should be two.
		size = interpretedList.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove two symbols and add itself "
				+ "to the list.", 2, size);
		// should be able to interprete the two symbols with a third.
		addition.interpret(interpretedList);
		// size of interpretedList should be one.
		size = interpretedList.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove anytwo symbols and add itself "
				+ "to the list.", 1, size);
		// the final symbol should be addition
		assertSame("the final symbol should be addition",
					addition, interpretedList.get(0));
		// addition should have multiplication as left symbol
		assertSame("addition should have multiplication as left symbol",
				multiplication, 
				((BinaryOperator) addition).getLeftSymbol());		
		// division as right symbol
		assertSame("addition should have division as right symbol",
				division, 
				((BinaryOperator) addition).getRightSymbol());		

	}
	
	@Test
	public void testInterpretUnaryOperators(){
		// will need some numbers for the interpretedList
		Symbol num1 = new Number("1");
		Symbol num2 = new Number("2");
		Symbol num3 = new Number("3");
		Symbol num4 = new Number("4");
		boolean result = negation.interpret(interpretedList);
		// if there are no or not enough symbols in interpretedList,
		// the operater should have a false result.
		assertFalse(" If there are no symbols in "
				+ " interpretedList, negation should "
				+ " have a false result.",result);
		interpretedList.add(num1);
		result = negation.interpret(interpretedList);
		assertTrue(" If there are enough symbols in "
				+ " interpretedList, negation should "
				+ " have a true result.",result);
		int size = interpretedList.size();
		assertEquals("interpreting unary operatoer should "
				+ "remove symbol and add itself "
				+ "to the list.", 1, size);
		assertSame("negation has wrong right side symbol",
				   	num1, ((UnaryOperator) negation).getRightSymbol());
		// should be able to have a negation of a negation
		Symbol neg1 = new  Negation();
		result = neg1.interpret(interpretedList);
		assertTrue(" If there is a negation symbol next in "
				+ " interpretedList, negation should "
				+ " have a true result.",result);
				// size of interpretedList should be one.
		size = interpretedList.size();
		assertEquals("interpreting unary operatoer should "
				+ "remove symbol and add itself "
				+ "to the list.", 1, size);
		assertSame("negation has wrong right side symbol",
				   	negation, ((UnaryOperator) neg1).getRightSymbol());
		
	}
	
	@Test
	public void testInterpretParenthesis(){
		assertFalse("Parenthesis interpret() should always be false.", 
				leftParenthesis.interpret(interpretedList));
	}
	
	
	@Test
	public void testAddToInterpretListsNumber(){
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
	public void testAddToInterpretListsUnaryOperator(){
		negation.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one symbol, negation, in parsedList",
				1, parsedList.size());
		
	}
	
	@Test
	public void testAddToInterpretListsBinaryOperatorWithEmptyAccumSymbols(){
		// if there is nothing in the parsedList list the symbol should just add itself.
		addition.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one symbol in parsedList",
				1, parsedList.size());
	}
	
	@Test
	public void testAddToInterpretListsBinaryOperatorWithEqPrecAccumSymbol(){
		// add the first
		addition.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one symbol, addition, in parsedList",
				1, parsedList.size());
		
		// if another operator is added with equal precedence
		// the first operator should be interpreted. if it fails,
		// both should be left in accumOperatoers.
		subtraction.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be two symbols in parsedList",
				2, parsedList.size());
		// add some numbers to interpretedList
		interpretedList.add(new Number("67.8"));
		interpretedList.add(new Number("67.8"));
		// remove subtraction from parsedList
		parsedList.remove(subtraction);
		assertEquals("subtraction should bave been removed from parsedList",
				1, parsedList.size());
		subtraction.addToInterpreter(interpretedList, parsedList);
		// There should be one symbol in parsed list.
		assertEquals("There should be one symbol in parsedList list.",
				1, parsedList.size());
		assertEquals("There should be one symbol in paresedSymbols: "
				+ interpretedList.toString(),
				1, interpretedList.size());
	}
	
	@Test
	public void testAddToInterpretListsBinaryOperatorWithLessPrecThanLastAccumSymbol(){
		// add the first
		multiplication.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one symbol, multiplication, in parsedList",
				1, parsedList.size());
		
		// if another operator is added with less precedence
		// the first operator should be interpreted. If it fails,
		// both operators are left in in accum operators.
		subtraction.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be two symbols in parsedList",
				2, parsedList.size());
		// add some numbers to interpretedList
		interpretedList.add(new Number("67.8"));
		interpretedList.add(new Number("67.8"));
		// remove subtraction from parsedList
		parsedList.remove(subtraction);
		assertEquals("subtraction should bave been removed from parsedList",
				1, parsedList.size());
		subtraction.addToInterpreter(interpretedList, parsedList);
		// There should be one in accumSybols and paresed symbols.
		assertEquals("There should be 1 symbol in parsedList list.",
				1, parsedList.size());
		assertEquals("There should be 1 symbol in interpretedList: "
				+ interpretedList.toString(),
				1, interpretedList.size());
	}
	
	@Test
	public void testAddToInterpretListsBinaryOperatorWithGreaterPrecAccumSymbol(){
		// add the first
		addition.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one symbol, addition, in parsedList",
				1, parsedList.size());
		
		// if another operator is added with greater precedence
		// the first operator should not interpreted. if it fails,
		// both should be left in accumOperatoers.
		multiplication.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be two symbols in parsedList",
				2, parsedList.size());
		// add some numbers to interpretedList
		interpretedList.add(new Number("67.8"));
		interpretedList.add(new Number("67.8"));
		// remove multiplication from parsedList
		parsedList.remove(multiplication);
		assertEquals("multipliction should bave been removed from parsedList",
				1, parsedList.size());
		multiplication.addToInterpreter(interpretedList, parsedList);
		// There should be two in accumSybols and paresed symbols.
		assertEquals("There should be two symbol in parsedList list.",
				2, parsedList.size());
		assertEquals("There should be two symbol in interpretedList: "
				+ interpretedList.toString(),
				2, interpretedList.size());
	}
	
		
	@Test
	public void testAddToInterpretListsParenthesis(){
		// left parethesis will be put it on the parsedList list.
		leftParenthesis.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be one symbol in parsedList list.",
				1, parsedList.size());
		assertSame("LeftParenthesis should be the only accumulated symbol.", 
					leftParenthesis, parsedList.get(0));
		// adding a right parethesis should remove both from the accum
		rightParenthesis.addToInterpreter(interpretedList, parsedList);
		assertEquals("There should be no symbols in parsedList list.",
				0, parsedList.size());
		// a parenthesis with a sub expression should be evaluated.
		Symbol num1 = new Number("1");
		Symbol num2 = new Number("2");
		leftParenthesis.addToInterpreter(interpretedList,parsedList);
		num1.addToInterpreter(interpretedList,parsedList);
		addition.addToInterpreter(interpretedList,parsedList);
		num2.addToInterpreter(interpretedList,parsedList);
		rightParenthesis.addToInterpreter(interpretedList,parsedList);
		assertEquals("There should be one symbol in interpretedList list.",
				1, interpretedList.size());
		assertEquals("There should be no symbols in parsedList list.",
				0, parsedList.size());
		assertSame("addition should be the only parsed symbol.", 
					addition, interpretedList.get(0));
		
	}
	
}
