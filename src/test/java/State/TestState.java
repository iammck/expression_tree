import org.junit.*;
import static org.junit.Assert.*;

public class TestState{
	
	
	ExpressionTreeContext context;
	
	@Before
	public void beforeEachTest(){
		// create the context.
		context = new ExpressionTreeContext();
	}

	@Test
	public void testInitialState(){
		// create the state instance as a State interface
		State initialState = new InitialState();
		
		assertPrintThrowsInvalidStateException(initialState, "");		
		
		initialState.quit(context, "");
		
		assertEvaluateThrowsInvalidStateException(initialState, "");		
		assertSetExpressionThrowsInvalidStateException(initialState, "");		
		
		
		initialState.setTreeOrder(context, "infix");
		assertTrue("State should be initial state.", 
			context.getCurrentState() instanceof InitialState);
		initialState.setInputFormat(context, "infix");
		assertTrue("State should be initial state.", 
			context.getCurrentState() instanceof FormatedState);
		initialState.setTreeOrder(context, "infix");
		
		assertSetInputFormatThrowsIllegalArgumentException(initialState, "");
		assertSetInputFormatThrowsIllegalArgumentException(initialState, "not an input format");
		assertSetTreeOrderThrowsIllegalArgumentException(initialState, "");
		assertSetTreeOrderThrowsIllegalArgumentException(initialState, "not a tree order");
	}
	
	
	@Test
	public void testFormatedStated(){
		// create the state instance as a State interface
		// setting the input format will return a formated state
		context.setInputFormat("infix");
		State formatedState = context.getCurrentState();
		// assert that the state is an instance of FormatedState
		assertTrue("State should be initial state.", 
			formatedState instanceof FormatedState);
		// should not be able to print yet.
		assertPrintThrowsInvalidStateException(formatedState, "");		
		// should be able to quit.
		formatedState.quit(context, "");
		// can not evaluate while in the FormatedStated
		assertEvaluateThrowsInvalidStateException(formatedState, "");		
		// should be able to set the expression 
		formatedState.setExpression(context, "3+4");
		// the resulting state should now be the HasExpressionState
		assertTrue("State should be expression state.", 
			context.getCurrentState() instanceof HasExpressionState);
		// set context back to formatedState for consistency
		context.setCurrentState(formatedState);
		assertSame("The context state does not match the formated state.",
			formatedState, context.getCurrentState());
		// Should still have access to setting the Tree order and input format
		formatedState.setTreeOrder(context, "infix");
		formatedState.setInputFormat(context, "infix");
		// the state should still be FormateState
		assertTrue("State should be formated state.", 
			context.getCurrentState() instanceof FormatedState);
		// formated state should still throw
		assertSetInputFormatThrowsIllegalArgumentException(formatedState, "");
		assertSetInputFormatThrowsIllegalArgumentException(formatedState, "not an input format");
		assertSetTreeOrderThrowsIllegalArgumentException(formatedState, "");
		assertSetTreeOrderThrowsIllegalArgumentException(formatedState, "not a tree order");
		assertSetExpressionThrowsInvalidInputException(formatedState, "not an expression");

	}	
	
	private void assertPrintThrowsInvalidStateException(State state, String arg){
		try{
			state.print(context, arg);
			fail("State should have thrown InvalidState exception "
				+ "from print method call.");
		} catch (InvalidStateException e) {
			return;
		}
	}
	
	private void assertEvaluateThrowsInvalidStateException(State state, String arg){
		try{
			state.evaluate(context, arg);
			fail("State should have thrown InvalidState exception "
				+ "from evaluate method call.");
		} catch (InvalidStateException e) {
			return;
		}
	}
	
	private void assertSetExpressionThrowsInvalidStateException(State state, String arg){
		try{
			state.setExpression(context, arg);
			fail("State should have thrown exception "
				+ "from set Expression method call.");
		} catch (InvalidStateException e) {
			return;
		}
	}
	
	private void assertSetInputFormatThrowsIllegalArgumentException(State state, String arg){
		try{
			state.setInputFormat(context, arg);
			fail("State should have thrown illegal arg exception "
				+ "from set Input format method call.");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	private void assertSetTreeOrderThrowsIllegalArgumentException(State state, String arg){
		try{
			state.setTreeOrder(context, arg);
			fail("State should have thrown ill arg eception "
				+ "from set tree order method call.");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	private void assertSetExpressionThrowsInvalidInputException(State state, String arg){
		try{
			state.setExpression(context, arg);
			fail("State should have thrown ill arg eception "
				+ "from set tree order method call.");
		} catch (InvalidInputException e) {
			return;
		}
	}
	
	@Test
	public void testHasExpressionState(){
		// create the state instance as a State interface
		// setting the input format will return a formated state
		context.setInputFormat("infix");
		// setting the expression will return a has expression state
		context.setExpression("3*4");
		State hasExpressionState = context.getCurrentState();
		// assert that the state is an instance of HasExpressionState
		assertTrue("State should be HasExpressionState state.", 
			hasExpressionState instanceof HasExpressionState);
		// should be able to print.
		hasExpressionState.print(context, "");		
		// should be able to quit.
		hasExpressionState.quit(context, "");
		// should be able to evaluate while in HasExpressionState
		hasExpressionState.evaluate(context,"");
		// should be able to set the expression 
		hasExpressionState.setExpression(context, "3+4");
		// Should still have access to setting the Tree order and input format
		hasExpressionState.setTreeOrder(context, "infix");
		hasExpressionState.setInputFormat(context, "infix");
		// the resulting state should now be the HasExpressionState
		assertTrue("State should be hasExpressionState state.", 
			context.getCurrentState() instanceof HasExpressionState);
		
		// formated state should still throw invalid input
		assertSetInputFormatThrowsIllegalArgumentException(hasExpressionState, "");
		assertSetInputFormatThrowsIllegalArgumentException(hasExpressionState, "not an input format");
		assertSetTreeOrderThrowsIllegalArgumentException(hasExpressionState, "");
		assertSetTreeOrderThrowsIllegalArgumentException(hasExpressionState, "not a tree order");
		assertSetExpressionThrowsInvalidInputException(hasExpressionState, "3--+6");
		assertSetExpressionThrowsInvalidInputException(hasExpressionState, "");
	}

}
