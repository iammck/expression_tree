import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TestCreation {
	
	// some default symbols to be used in tests. setup before 
	Symbol number, negation, addition, subtraction,
		multiplication, division,
		leftParenthesis, rightParenthesis;
	// Lists for parsed and accumulated symbols.
	ArrayList<Symbol> parsedSymbols;
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
	
		parsedSymbols = new ArrayList<Symbol>();
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
	
	/**
	 * A number should add itself to the parsedSymbols list at the
	 * end.
	 */
	@Test
	public void testInterpretNumber(){
		boolean result = number.interpret(parsedSymbols);
		// result should be true
		assertTrue("number.interpret() should return true.", result);
		int size = parsedSymbols.size();
		// length should be one.
		assertEquals("interpreting number should add "
				+ "it to the parsed list.", 1, size);
		
	}
	
	@Test
	public void testInterpretBinaryOperators(){
		// will need some numbers for the parsedSymbols
		Symbol num1 = new Number("1");
		Symbol num2 = new Number("2");
		Symbol num3 = new Number("3");
		Symbol num4 = new Number("4");
		// any binary operator will do since interprete is handled by
		// the abstract BinaryOperator
		boolean result = multiplication.interpret(parsedSymbols);
		// if there are no or not enough symbols in parsedSymbols,
		// the operater should have a false result.
		assertFalse("if there are no symbols in "
				+ " parsedSymbols, the operater should "
				+ " have a false result.",result);
		parsedSymbols.add(num1);
		result = multiplication.interpret(parsedSymbols);
		assertFalse("if there are not enough symbols in "
				+ " parsedSymbols, the operater should "
				+ " have a false result.",result);
		// If there are two, should have grabed last two numbers before
		// inserting itself onto parsedSymbols
		parsedSymbols.add(num2);
		result = multiplication.interpret(parsedSymbols);
		assertTrue("if there are enough symbols in "
				+ " parsedSymbols, the operater should "
				+ " have a true result.",result);
		// size of parsedSymbols should be one.
		int size = parsedSymbols.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove two symbols and add itself "
				+ "to the parsed list.", 1, size);
		
		assertSame("multiplication has wrong left symbol",
				   	num1, ((BinaryOperator) multiplication).getLeftSymbol());		
		assertSame("multiplication has wrong right symbol",
				   	num2, ((BinaryOperator) multiplication).getRightSymbol());
		// should be able to add two more numbers to the parsed symbol,
		// then be able to and interprete another symbol.
		parsedSymbols.add(num3);
		parsedSymbols.add(num4);
		division.interpret(parsedSymbols);
		// size of parsedSymbols should be two.
		size = parsedSymbols.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove two symbols and add itself "
				+ "to the parsed list.", 2, size);
		// should be able to interprete the two symbols with a third.
		addition.interpret(parsedSymbols);
		// size of parsedSymbols should be one.
		size = parsedSymbols.size();
		assertEquals("interpreting binary operatoer should "
				+ "remove anytwo symbols and add itself "
				+ "to the parsed list.", 1, size);
		// the final symbol should be addition
		assertSame("the final symbol should be addition",
					addition, parsedSymbols.get(0));
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
		// will need some numbers for the parsedSymbols
		Symbol num1 = new Number("1");
		Symbol num2 = new Number("2");
		Symbol num3 = new Number("3");
		Symbol num4 = new Number("4");
		boolean result = negation.interpret(parsedSymbols);
		// if there are no or not enough symbols in parsedSymbols,
		// the operater should have a false result.
		assertFalse(" If there are no symbols in "
				+ " parsedSymbols, negation should "
				+ " have a false result.",result);
		parsedSymbols.add(num1);
		result = negation.interpret(parsedSymbols);
		assertTrue(" If there are enough symbols in "
				+ " parsedSymbols, negation should "
				+ " have a true result.",result);
		int size = parsedSymbols.size();
		assertEquals("interpreting unary operatoer should "
				+ "remove symbol and add itself "
				+ "to the parsed list.", 1, size);
		assertSame("negation has wrong right side symbol",
				   	num1, ((UnaryOperator) negation).getRightSymbol());
		// should be able to have a negation of a negation
		Symbol neg1 = new  Negation();
		result = neg1.interpret(parsedSymbols);
		assertTrue(" If there is a negation symbol next in "
				+ " parsedSymbols, negation should "
				+ " have a true result.",result);
				// size of parsedSymbols should be one.
		size = parsedSymbols.size();
		assertEquals("interpreting unary operatoer should "
				+ "remove symbol and add itself "
				+ "to the parsed list.", 1, size);
		assertSame("negation has wrong right side symbol",
				   	negation, ((UnaryOperator) neg1).getRightSymbol());
		
	}
	
	@Test
	public void testInterpretParenthesis(){
		assertFalse("Parenthesis interpret() should always be false.", 
				leftParenthesis.interpret(parsedSymbols));
	}

}
