import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestSymbols {
	// some default symbols to be used in tests. setup before 
	// Symbols implement Symbol and can be interpreted.
	Symbol number, negation, addition, subtraction,
		multiplication, division, exponential,
		leftParenthesis, rightParenthesis;
	
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
		exponential = new Exponential();
		division = new Division();
		leftParenthesis  = new Parenthesis("(");
		rightParenthesis = new Parenthesis(")");
	
	}

		
	@Test
	public void testCreatingSymbol(){
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
		symbol = new Exponential();
	}
	
	@Test
	public void testNumberHasLessOrEqualPrecedenceOver(){
		// check that numbers compared to numbers are 0.
		assertEquals("Number.precedenceComparedTo(Number) should result in 0 ",
				0, number.precedenceComparedTo(number));
		
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
				.precedenceComparedTo(symbol);
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
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(multiplication);
		symbolList.add(division);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = addition
				.precedenceComparedTo(symbol);
			assertEquals("addition precedenceComparedTo " + symbol.toString()
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
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(multiplication);
		symbolList.add(division);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = subtraction
				.precedenceComparedTo(symbol);
			assertEquals("subtraction precedenceComparedTo " + symbol.toString()
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
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = multiplication
				.precedenceComparedTo(symbol);
			assertEquals("multiplication precedenceComparedTo " + symbol.toString()
				+ " should result in -1.", -1, result);
		}
		
		// multiplication should be greater than these
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		for(Symbol symbol: symbolList){
			int result = multiplication
				.precedenceComparedTo(symbol);
			assertEquals("multiplication precedenceComparedTo " + symbol.toString()
				+ " should result in 1.", 1, result);
		}
	}
	
	
	
	
	@Test
	public void testExponentialHasLessOrEqualPrecedenceOver(){
		// check that exponential compared to exponential is 1.
		assertEquals("exponential.precedenceComparedTo(exponential) should result in 1 ",
				1, exponential.precedenceComparedTo(exponential));
		
		// exponential should be less than these
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = exponential
				.precedenceComparedTo(symbol);
			assertEquals("exponential precedenceComparedTo " + symbol.toString()
				+ " should result in -1.", -1, result);
		}
		
		// exponential should be greater than these
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		symbolList.add(multiplication);
		symbolList.add(division);
		for(Symbol symbol: symbolList){
			int result = exponential
				.precedenceComparedTo(symbol);
			assertEquals("exponential precedenceComparedTo " + symbol.toString()
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
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(negation);
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = division
				.precedenceComparedTo(symbol);
			assertEquals("division precedenceComparedTo " + symbol.toString()
				+ " should result in -1.", -1, result);
		}
		
		// division should be greater than these
		symbolList = new ArrayList<Symbol>();
		symbolList.add(number);
		symbolList.add(addition);
		symbolList.add(subtraction);
		for(Symbol symbol: symbolList){
			int result = division
				.precedenceComparedTo(symbol);
			assertEquals("division precedenceComparedTo " + symbol.toString()
				+ " should result in 1.", 1, result);
		}
	}
	
	
	@Test
	public void testNegationHasLessOrEqualPrecedenceOver(){
		// check that negation compared to negation is 0.
		assertEquals("negation.precedenceComparedTo(negation) should result in 0 ",
				0, negation.precedenceComparedTo(negation));
		
		// negation should be less than these
		ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
		symbolList.add(leftParenthesis);
		symbolList.add(rightParenthesis);
		for(Symbol symbol: symbolList){
			int result = negation
				.precedenceComparedTo(symbol);
			assertEquals("negation precedenceComparedTo " + symbol.toString()
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
				.precedenceComparedTo(symbol);
			assertEquals("negation precedenceComparedTo " + symbol.toString()
				+ " should result in 1.", 1, result);
		}
	}

	
	@Test
	public void testParenthesisHasLessOrEqualPrecedenceOver(){
		// check that leftParenthesis compared to leftParenthesis is 0.
		assertEquals("leftParenthesis.precedenceComparedTo(leftParenthesis) should result in 0 ",
				0, leftParenthesis.precedenceComparedTo(leftParenthesis));
		
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
				.precedenceComparedTo(symbol);
			assertEquals("leftParenthesis precedenceComparedTo " + symbol.toString()
				+ " should result in 1.", 1, result);
		}
	}	
}
