import org.junit.*;

import static org.junit.Assert.*;

	/**
	 * TestCreationError is responsible for exposing common errors 
	 * and the development of solutions for those errors. Missing 
	 * parenthesis, erroneous input statements should be caught 
	 * by users of interpret() These errors are returned as 
	 * InvalidInputExceptions to the caller of interpret(). 
	 * A thrown Exception should come with if any current 
	 * symbol when the error was raised well as the interpretedSymbols 
	 * and accumSymbols lists, and a string description of the error.
	 */


public class TestCreationErrors {
	

	@Test
	public void testAddToSymbolsParenthesis(){		
		String testString = "  ((5 +  4) ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  (5 +  4)) ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  (5 +)( ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  (5 + 5)(  4)) ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  (5 + 5)*(  4)) ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		
		
		testString = "  ( (((5) + 5))*(  4) ) ";
		assertFalse("Raised eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  ((5 +()  4)) ";
		assertFalse("Raised eception with " + testString,
			failInterpretWithInvalidInputException(testString));
				
	}

	@Test
	public void testAddToSymbolsOperator(){
		
		String testString = "  5 ++  4 ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  +5 +  4 ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  5 +  4 + ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = " * 5 +  4 + ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
	}
		
	@Test
	public void testAddToSymbolsUnknownOperator(){
		String testString = "  5 p  4 + ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
	}
	
	
	@Test
	public void testAddToSymbolsMissingOperator(){
		String testString = "  5  4 ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  3 + 5  4 ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  3 + 5 7 + 4 ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
		testString = "  3 7 + 5 + 4 ";
		assertTrue("Failed to raise eception with " + testString,
			failInterpretWithInvalidInputException(testString));
			
	}
	
	
	private boolean failInterpretWithInvalidInputException(String testString){
		Interpreter interpreter = new Interpreter();
		InterpreterContext context = new InterpreterContext();

		try {
			ExpressionTree result = 
			interpreter.interpret( context,testString);
			return false;
		} catch (InvalidInputException exception){
			checkInvalidInputExceptionData(exception);
			return true;
		}
		
	}
	
	void checkInvalidInputExceptionData(InvalidInputException exception){
		// exception should have a current item
		// and two lists.
		assertNotNull("", exception.getInterpretedSymbols());
		assertNotNull("", exception.getAccumSymbols());
		
	}


}
