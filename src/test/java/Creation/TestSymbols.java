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
	ArrayList<Symbol> interpretedSymbols;
	ArrayList<Symbol> accumSymbols;
	
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
	
		interpretedSymbols = new ArrayList<Symbol>();
		accumSymbols = new ArrayList<Symbol>();
	
	
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
		// an array list to hold the symbols
		ArrayList<Symbol> symbolList;
		// numbers should be less or equal to all other symbol 
		// put all the symbols into the the symbolList
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(negation);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			boolean result = number
				.hasLessOrEqualPrecedenceOver(symbol);
			assertTrue("Number failed to have less or "
				+ "equal precedence.", result);
		}
		
	}
	
	@Test
	public void testAdditionHasLessOrEqualPrecedenceOver(){
		// an array list to hold the symbols
		ArrayList<Symbol> symbolList;
		// Addition should be less or equal to itself, subtaction
		// parenthesis, multiplication, and divition. 
		// Put these symbols into the the symbolList
		symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		for(Symbol symbol: symbolList){
			boolean result = addition
				.hasLessOrEqualPrecedenceOver(symbol);
			assertTrue("Addition failed to have less or "
				+ "equal precedence.", result);
		}
		
		// addition should be greater than numbers,
		// put number in the symbol list
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		for(Symbol symbol: symbolList){
			boolean result = addition
				.hasLessOrEqualPrecedenceOver(symbol);
			assertFalse("Addition failed to have greater "
				+ "precedence.", result);
		}
	}
	
	@Test
	public void testSubtractionHasLessOrEqualPrecedenceOver(){
		// an array list to hold the symbols
		ArrayList<Symbol> symbolList;
		// Subtraction should be less or equal to itself, addition
		// parenthesis, multiplication, and divition. 
		// Put these symbols into the the symbolList
		symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		for(Symbol symbol: symbolList){
			boolean result = subtraction
				.hasLessOrEqualPrecedenceOver(symbol);
			assertTrue("Subtraction failed to have less or "
				+ "equal precedence.", result);
		}
		
		// subtraction should be greater than numbers,
		// put number in the symbol list
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		for(Symbol symbol: symbolList){
			boolean result = subtraction
				.hasLessOrEqualPrecedenceOver(symbol);
			assertFalse("Subtraction failed to have greater "
				+ "precedence.", result);
		}
	}
	
	@Test
	public void testMultiplicationHasLessOrEqualPrecedenceOver(){
		// an array list to hold the symbols
		ArrayList<Symbol> symbolList;
		// multiplication should be less or equal to itself
		// parenthesis, negation and divition. 
		// Put these symbols into the the symbolList
		symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		symbolList.add(multiplication);
		symbolList.add(division);
		for(Symbol symbol: symbolList){
			boolean result = multiplication
				.hasLessOrEqualPrecedenceOver(symbol);
			assertTrue("Multiplication failed to have less or "
				+ "equal precedence.", result);
		}
		
		// Multiplication should be greater than numbers,
		// subtraction and addition
		// put number in the symbol list
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		for(Symbol symbol: symbolList){
			boolean result = multiplication
				.hasLessOrEqualPrecedenceOver(symbol);
			assertFalse("Multiplication failed to have greater "
				+ "precedence.", result);
		}
	}
	
	@Test
	public void testDivisionHasLessOrEqualPrecedenceOver(){
		// an array list to hold the symbols
		ArrayList<Symbol> symbolList;
		// division should be less or equal to itself
		// parenthesis, negation and multiplication. 
		// Put these symbols into the the symbolList
		symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		symbolList.add(multiplication);
		symbolList.add(division);
		for(Symbol symbol: symbolList){
			boolean result = division
				.hasLessOrEqualPrecedenceOver(symbol);
			assertTrue("Division failed to have less or "
				+ "equal precedence.", result);
		}
		
		// Division should be greater than numbers,
		// subtraction and addition
		// put number in the symbol list
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		for(Symbol symbol: symbolList){
			boolean result = division
				.hasLessOrEqualPrecedenceOver(symbol);
			assertFalse("Division failed to have greater "
				+ "precedence.", result);
		}
	}
	
	
	@Test
	public void testNegationHasLessOrEqualPrecedenceOver(){
		// an array list to hold the symbols
		ArrayList<Symbol> symbolList;
		// negation should be less or equal to itself and 
		// parenthesis. put these symbols into the the 
		// symbolList
		symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			boolean result = negation
				.hasLessOrEqualPrecedenceOver(symbol);
			assertTrue("Negation failed to have less or "
				+ "equal precedence.", result);
		}
		
		// negation should be greater than all else,
		// put these in the symbol list
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		for(Symbol symbol: symbolList){
			boolean result = negation
				.hasLessOrEqualPrecedenceOver(symbol);
			assertFalse("Negation failed to have greater "
				+ "precedence.", result);
		}
	}

	
	@Test
	public void testParenthesisHasLessOrEqualPrecedenceOver(){
		// an array list to hold the symbols
		ArrayList<Symbol> symbolList;
		// parenthesis should be less or equal to itself 
		// put these symbols into the the symbolList
		symbolList = new ArrayList<Symbol>();
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			boolean result = leftParenthesis
				.hasLessOrEqualPrecedenceOver(symbol);
			assertTrue("Parethesis failed to have less or "
				+ "equal precedence.", result);
		}
		
		// parethesis should be greater than all else,
		// put these in the symbol list
		symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		for(Symbol symbol: symbolList){
			boolean result = leftParenthesis
				.hasLessOrEqualPrecedenceOver(symbol);
			assertFalse("Parethesis failed to have greater "
				+ "precedence.", result);
		}
	}
	
	@Test
	public void testInterpretNumber(){
		boolean result = number.interpret(interpretedSymbols);
		// result should be true
		assertTrue("number.interpret() should return true.", result);
		int size = interpretedSymbols.size();
		// length should be one.
		assertEquals("interpreting number should add "
				+ "it to the parsed list.", 1, size);
		
	}
	
	@Test
	public void testInterpretBinaryOperators(){
		// will need some numbers for the interpretedSymbols
		Symbol num1 = new Number("1");
		Symbol num2 = new Number("2");
		Symbol num3 = new Number("3");
		Symbol num4 = new Number("4");
		// any binary operator will do since interprete is handled by
		// the abstract BinaryOperator
		boolean result = multiplication.interpret(interpretedSymbols);
		// if there are no or not enough symbols in interpretedSymbols,
		// the operater should have a false result.
		assertFalse("if there are no symbols in "
				+ " interpretedSymbols, the operater should "
				+ " have a false result.",result);
		interpretedSymbols.add(num1);
		result = multiplication.interpret(interpretedSymbols);
		assertFalse("if there are not enough symbols in "
				+ " interpretedSymbols, the operater should "
				+ " have a false result.",result);
		// If there are two, should have grabed last two numbers before
		// inserting itself onto interpretedSymbols
		interpretedSymbols.add(num2);
		result = multiplication.interpret(interpretedSymbols);
		assertTrue("if there are enough symbols in "
				+ " interpretedSymbols, the operater should "
				+ " have a true result.",result);
		// size of interpretedSymbols should be one.
		int size = interpretedSymbols.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove two symbols and add itself "
				+ "to the parsed list.", 1, size);
		
		assertSame("multiplication has wrong left symbol",
				   	num1, ((BinaryOperator) multiplication).getLeftSymbol());		
		assertSame("multiplication has wrong right symbol",
				   	num2, ((BinaryOperator) multiplication).getRightSymbol());
		// should be able to add two more numbers to the parsed symbol,
		// then be able to and interprete another symbol.
		interpretedSymbols.add(num3);
		interpretedSymbols.add(num4);
		division.interpret(interpretedSymbols);
		// size of interpretedSymbols should be two.
		size = interpretedSymbols.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove two symbols and add itself "
				+ "to the parsed list.", 2, size);
		// should be able to interprete the two symbols with a third.
		addition.interpret(interpretedSymbols);
		// size of interpretedSymbols should be one.
		size = interpretedSymbols.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove anytwo symbols and add itself "
				+ "to the parsed list.", 1, size);
		// the final symbol should be addition
		assertSame("the final symbol should be addition",
					addition, interpretedSymbols.get(0));
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
		// will need some numbers for the interpretedSymbols
		Symbol num1 = new Number("1");
		Symbol num2 = new Number("2");
		Symbol num3 = new Number("3");
		Symbol num4 = new Number("4");
		boolean result = negation.interpret(interpretedSymbols);
		// if there are no or not enough symbols in interpretedSymbols,
		// the operater should have a false result.
		assertFalse(" If there are no symbols in "
				+ " interpretedSymbols, negation should "
				+ " have a false result.",result);
		interpretedSymbols.add(num1);
		result = negation.interpret(interpretedSymbols);
		assertTrue(" If there are enough symbols in "
				+ " interpretedSymbols, negation should "
				+ " have a true result.",result);
		int size = interpretedSymbols.size();
		assertEquals("interpreting unary operatoer should "
				+ "remove symbol and add itself "
				+ "to the parsed list.", 1, size);
		assertSame("negation has wrong right side symbol",
				   	num1, ((UnaryOperator) negation).getRightSymbol());
		// should be able to have a negation of a negation
		Symbol neg1 = new  Negation();
		result = neg1.interpret(interpretedSymbols);
		assertTrue(" If there is a negation symbol next in "
				+ " interpretedSymbols, negation should "
				+ " have a true result.",result);
				// size of interpretedSymbols should be one.
		size = interpretedSymbols.size();
		assertEquals("interpreting unary operatoer should "
				+ "remove symbol and add itself "
				+ "to the parsed list.", 1, size);
		assertSame("negation has wrong right side symbol",
				   	negation, ((UnaryOperator) neg1).getRightSymbol());
		
	}
	
	@Test
	public void testInterpretParenthesis(){
		assertFalse("Parenthesis interpret() should always be false.", 
				leftParenthesis.interpret(interpretedSymbols));
	}
	
	
	@Test
	public void testAddToSymbolsNumber(){
		// a number gets added to interpretedSymbols right away
		number.addToSymbols(interpretedSymbols, accumSymbols);
		int pSize = interpretedSymbols.size();
		int aSize = accumSymbols.size();
		assertEquals("interpretedSymbols should have size 1 one after a number is inserted.", 
					1, pSize);
		assertEquals("accumSymbols should have size 0  after a number is inserted.", 
					0, aSize);
		assertSame("Number should be the only thing ing symbols.", 
					number, interpretedSymbols.get(0));
	}
	
	@Test
	public void testAddToSymbolsUnaryOperator(){
		negation.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be one symbol, negation, in accumSymbols",
				1, accumSymbols.size());
		
	}
	
	@Test
	public void testAddToSymbolsBinaryOperatorWithEmptyAccumSymbols(){
		// if there is nothing in the accumSymbols list the symbol should just add itself.
		addition.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be one symbol in accumSymbols",
				1, accumSymbols.size());
	}
	
	@Test
	public void testAddToSymbolsBinaryOperatorWithEqPrecAccumSymbol(){
		// add the first
		addition.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be one symbol, addition, in accumSymbols",
				1, accumSymbols.size());
		
		// if another operator is added with equal precedence
		// the first operator should be interpreted. if it fails,
		// both should be left in accumOperatoers.
		subtraction.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be two symbols in accumSymbols",
				2, accumSymbols.size());
		// add some numbers to interpretedSymbols
		interpretedSymbols.add(new Number("67.8"));
		interpretedSymbols.add(new Number("67.8"));
		// remove subtraction from accumSymbols
		accumSymbols.remove(subtraction);
		assertEquals("subtraction should bave been removed from accumSymbols",
				1, accumSymbols.size());
		subtraction.addToSymbols(interpretedSymbols, accumSymbols);
		// There should be two in accumSybols and paresed symbols.
		assertEquals("There should be one symbol in accumSymbols list.",
				1, accumSymbols.size());
		assertEquals("There should be one symbol in paresedSymbols: "
				+ interpretedSymbols.toString(),
				1, interpretedSymbols.size());
	}
	
	@Test
	public void testAddToSymbolsBinaryOperatorWithLessPrecThanLastAccumSymbol(){
		// add the first
		multiplication.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be one symbol, multiplication, in accumSymbols",
				1, accumSymbols.size());
		
		// if another operator is added with less precedence
		// the first operator should be interpreted. If it fails,
		// both operators are left in in accum operators.
		subtraction.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be two symbols in accumSymbols",
				2, accumSymbols.size());
		// add some numbers to interpretedSymbols
		interpretedSymbols.add(new Number("67.8"));
		interpretedSymbols.add(new Number("67.8"));
		// remove subtraction from accumSymbols
		accumSymbols.remove(subtraction);
		assertEquals("subtraction should bave been removed from accumSymbols",
				1, accumSymbols.size());
		subtraction.addToSymbols(interpretedSymbols, accumSymbols);
		// There should be one in accumSybols and paresed symbols.
		assertEquals("There should be 1 symbol in accumSymbols list.",
				1, accumSymbols.size());
		assertEquals("There should be 1 symbol in interpretedSymbols: "
				+ interpretedSymbols.toString(),
				1, interpretedSymbols.size());
	}
	
	@Test
	public void testAddToSymbolsBinaryOperatorWithGreaterPrecAccumSymbol(){
		// add the first
		addition.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be one symbol, addition, in accumSymbols",
				1, accumSymbols.size());
		
		// if another operator is added with greater precedence
		// the first operator should not interpreted. if it fails,
		// both should be left in accumOperatoers.
		multiplication.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be two symbols in accumSymbols",
				2, accumSymbols.size());
		// add some numbers to interpretedSymbols
		interpretedSymbols.add(new Number("67.8"));
		interpretedSymbols.add(new Number("67.8"));
		// remove multiplication from accumSymbols
		accumSymbols.remove(multiplication);
		assertEquals("multipliction should bave been removed from accumSymbols",
				1, accumSymbols.size());
		multiplication.addToSymbols(interpretedSymbols, accumSymbols);
		// There should be two in accumSybols and paresed symbols.
		assertEquals("There should be two symbol in accumSymbols list.",
				2, accumSymbols.size());
		assertEquals("There should be two symbol in interpretedSymbols: "
				+ interpretedSymbols.toString(),
				2, interpretedSymbols.size());
	}
	
		
	@Test
	public void testAddToSymbolsParenthesis(){
		// left parethesis will be put it on the accumSymbols list.
		leftParenthesis.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be one symbol in accumSymbols list.",
				1, accumSymbols.size());
		assertSame("LeftParenthesis should be the only accumulated symbol.", 
					leftParenthesis, accumSymbols.get(0));
		// adding a right parethesis should remove both from the accum
		rightParenthesis.addToSymbols(interpretedSymbols, accumSymbols);
		assertEquals("There should be no symbols in accumSymbols list.",
				0, accumSymbols.size());
		// a parenthesis with a sub expression should be evaluated.
		Symbol num1 = new Number("1");
		Symbol num2 = new Number("2");
		leftParenthesis.addToSymbols(interpretedSymbols,accumSymbols);
		num1.addToSymbols(interpretedSymbols,accumSymbols);
		addition.addToSymbols(interpretedSymbols,accumSymbols);
		num2.addToSymbols(interpretedSymbols,accumSymbols);
		rightParenthesis.addToSymbols(interpretedSymbols,accumSymbols);
		assertEquals("There should be one symbol in interpretedSymbols list.",
				1, interpretedSymbols.size());
		assertEquals("There should be no symbols in interpretedSymbols list.",
				0, accumSymbols.size());
		assertSame("addition should be the only parsed symbol.", 
					addition, interpretedSymbols.get(0));
		
	}
	
}