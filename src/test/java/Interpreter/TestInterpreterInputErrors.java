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


public class TestInterpreterInputErrors {
	
	// the interpreter
	Interpreter interpreter;	
	// any InterpreterContext should work
	ExpressionTreeContext context;
	
	@Before
	public void beforeEachTest(){
		interpreter = new InfixInterpreter();
		context = new ExpressionTreeContext();
		ExpressionTreeContext ec = new ExpressionTreeContext();
	}
	
	@Test
	public void testEmptyStringInput(){
		assertEmptyStringThrowsError(new InfixInterpreter());	
		assertEmptyStringThrowsError(new PrefixInterpreter());	
		assertEmptyStringThrowsError(new PostfixInterpreter());	
		
	}
	
	private void assertEmptyStringThrowsError(Interpreter interpreter){
		try {
			// empty string
			String testString = "";
			// get the resulting expression tree
			ExpressionTree result = interpreter.interpret(context, testString);
			fail(interpreter.toString() 
				+ " should have thrown an exception for empty string.");
		} catch (InvalidInputException e) {
			return;
		}
	}
	
	@Test
	public void shouldFail(){
	String testString = "  (5 +)( 4)) ";
		assertTrue("Failed to raise exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
	}

	@Test
	public void testAddToSymbolsParenthesis(){		
		String testString = "  ((5 +  4) ";
		assertTrue("Failed to raise exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
		testString = "  (5 +  4)) ";
		assertTrue("Failed to raise exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
		testString = "  (5 +)( ";
		assertTrue("Failed to raise exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
		testString = "  (5 + 5)(  4)) ";
		assertTrue("Failed to raise exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
		testString = "  (5 + 5)*(  4)) ";
		assertTrue("Failed to raise exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
		testString = "  ( (((5) + 5))*(  4) ) ";
		assertFalse("Raised exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
		testString = "  ((5 +()  4)) ";
		assertFalse("Raised exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
				
	}
	

	@Test
	public void testAddToSymbolsWithErroneousOperator(){
		assertAllFail(" 7 8");
		assertAllFail("  5 ++  4 ");
		assertAllFail( "  +5 +  4 ");
		assertAllFail( "  5 +  4 + ");
		assertAllFail( " * 5 +  4 + ");
		
	}
	
	private void assertAllFail(String testString){
		assertTrue("Failed to raise infix exception with " + testString,
			failInfixInterpretWithInvalidInputException(testString));
		assertTrue("Failed to raise prefix exception with " + testString,
			failPrefixInterpretWithInvalidInputException(testString));
		assertTrue("Failed to raise postfix exception with " + testString,
			failPostfixInterpretWithInvalidInputException(testString));
	}
		
	@Test
	public void testAddToSymbolsUnknownOperator(){
		assertAllFail( "  5 p  4 + ");
	}
	
	
	@Test
	public void testAddToSymbolsMissingOperator(){
		assertAllFail("  5  4 ");
		assertAllFail("  3 + 5  4 ");
		assertAllFail("  3 + 5 7 + 4 ");
		assertAllFail("  3 7 + 5 + 4 ");			
	}
	
	
	
	private boolean failInfixInterpretWithInvalidInputException(String testString){
		interpreter = new InfixInterpreter();
		
		try {
			ExpressionTree result = 
			interpreter.interpret( context,testString);
			return false;
		} catch (InvalidInputException exception){
			checkInvalidInputExceptionData(exception);
			return true;
		}
		
	}
	
	private boolean failPrefixInterpretWithInvalidInputException(String testString){
		interpreter = new PrefixInterpreter();
		
		try {
			ExpressionTree result = 
			interpreter.interpret( context,testString);
			return false;
		} catch (InvalidInputException exception){
			checkInvalidInputExceptionData(exception);
			return true;
		}
		
	}
	
	private boolean failPostfixInterpretWithInvalidInputException(String testString){
		interpreter = new PostfixInterpreter();
		
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
