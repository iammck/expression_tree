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
	public void testInitialState() throws Exception{
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
	public void testFormatedStated()  throws Exception{
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
			state.printExpressionTree(context, arg);
			fail("State should have thrown InvalidState exception "
				+ "from printExpressionTree method call.");
		} catch (ExpressionTreeException e) {
			return;
		}
	}
	
	private void assertEvaluateThrowsInvalidStateException(State state, String arg){
		try{
			state.evaluate(context, arg);
			fail("State should have thrown InvalidState exception "
				+ "from evaluate method call.");
		} catch (ExpressionTreeException e) {
			return;
		}
	}
	
	private void assertSetExpressionThrowsInvalidStateException(State state, String arg){
		try{
			state.setExpression(context, arg);
			fail("State should have thrown exception "
				+ "from set Expression method call.");
		} catch (ExpressionTreeException e) {
			return;
		}
	}
	
	private void assertSetInputFormatThrowsIllegalArgumentException(State state, String arg){
		try{
			state.setInputFormat(context, arg);
			fail("State should have thrown illegal arg exception "
				+ "from set Input format method call.");
		} catch (ExpressionTreeException e) {
			return;
		}
		
	}
	
	private void assertSetTreeOrderThrowsIllegalArgumentException(State state, String arg){
		try{
			state.setTreeOrder(context, arg);
			fail("State should have thrown ill arg eception "
				+ "from set tree order method call.");
		} catch (ExpressionTreeException e) {
			return;
		}
	}
	
	private void assertSetExpressionThrowsInvalidInputException(State state, String arg){
		try{
			state.setExpression(context, arg);
			fail("State should have thrown ill arg eception "
				+ "from set tree order method call.");
		} catch (ExpressionTreeException e) {
			return;
		}
	}
	
	@Test
	public void testHasExpressionState() throws Exception{
		// create the state instance as a State interface
		// setting the input format will return a formated state
		context.setInputFormat("infix");
		// setting the expression will return a has expression state
		context.setExpression("3*4");
		// assert that the state is an instance of HasExpressionState
		State curState = context.getCurrentState();
		assertTrue("State should be HasExpressionState state.", 
			curState instanceof HasExpressionState);
		// should be able to printExpressionTree.
		context.printExpressionTree("");		
		// should be able to quit.
		context.quit("");
		// should be able to evaluate while in HasExpressionState
		context.evaluate("");
		// assert that the state is an instance of HasEvaluatedState
		curState = context.getCurrentState();
		assertTrue("State should be HasEvaluatedState state.", 
			curState instanceof EvaluatedState);
		
		// should be able to set the expression 
		context.setExpression("3*4");
		// assert that the state is an instance of HasExpressionState
		curState = context.getCurrentState();
		assertTrue("State should be HasExpressionState state.", 
			curState instanceof HasExpressionState);
		
		
		// Should still have access to setting the Tree order and input format
		curState.setTreeOrder(context, "infix");
		curState.setInputFormat(context, "infix");
		// the resulting state should now be the HasExpressionState
		assertTrue("Context current state should be instanceof HasExpressionState.", 
			context.getCurrentState() instanceof HasExpressionState);
		
		// Setting the treeOrder to something unevaluatable should change state
		curState.setTreeOrder(context, "inorder");
		// the resulting state should now be the HasUnevaluatableExpressionState
		assertTrue("Context current state should be instanceof "
					+ "HasUnevaluatableExpressionState.", 
			context.getCurrentState() instanceof HasUnevaluatableExpressionState);
		
		curState.setTreeOrder(context, "infix");
		
		// formated state should still throw invalid input
		assertSetInputFormatThrowsIllegalArgumentException(curState, "");
		assertSetInputFormatThrowsIllegalArgumentException(curState, "not an input format");
		assertSetTreeOrderThrowsIllegalArgumentException(curState, "");
		assertSetTreeOrderThrowsIllegalArgumentException(curState, "not a tree order");
		assertSetExpressionThrowsInvalidInputException(curState, "3--+6");
		assertSetExpressionThrowsInvalidInputException(curState, "");
	}
	
	@Test
	public void testEvaluatedState() throws Exception{
		// create the state instance as a State interface
		// setting the input format will return a formated state
		context.setInputFormat("infix");
		// setting the expression will return a has expression state
		context.setExpression("3*4");
		// should be able to evaluate while in HasExpressionState
		context.evaluate("");
		// assert that the state is an instance of HasEvaluatedState
		State curState = context.getCurrentState();
		assertTrue("State should be EvaluatedState state.", 
			curState instanceof EvaluatedState);
		
		// should be able to quit.
		context.quit("");
		
		// should be able to set the expression 
		context.setExpression("3*4");
		// assert that the state is an instance of HasExpressionState
		curState = context.getCurrentState();
		assertTrue("State should be HasExpressionState state.", 
			curState instanceof HasExpressionState);
		
		// should be able to evaluate while in HasExpressionState
		context.evaluate("");
		// assert that the state is an instance of HasEvaluatedState
		curState = context.getCurrentState();
		assertTrue("State should be EvaluatedState state.", 
			curState instanceof EvaluatedState);
		
		
		// Should still have access to setting the Tree order and input format
		curState.setTreeOrder(context, "infix");
		curState.setInputFormat(context, "infix");
		// the resulting state should now be the EvaluatedState
		assertTrue("Context current state should be instanceof EvaluatedState.", 
			context.getCurrentState() instanceof EvaluatedState);
		
		// Setting the treeOrder to something unevaluatable should change state
		curState.setTreeOrder(context, "inorder");
		// the resulting state should now be the HasUnevaluatableExpressionState
		assertTrue("Context current state should be instanceof "
					+ "HasUnevaluatableExpressionState.", 
			context.getCurrentState() instanceof HasUnevaluatableExpressionState);
		
		curState.setTreeOrder(context, "infix");
		context.evaluate("");
		// the resulting state should now be the EvaluatedState
		assertTrue("Context current state should be instanceof EvaluatedState!", 
			context.getCurrentState() instanceof EvaluatedState);
		
		
		
		// formated state should still throw invalid input
		assertSetInputFormatThrowsIllegalArgumentException(curState, "");
		assertSetInputFormatThrowsIllegalArgumentException(curState, "not an input format");
		assertSetTreeOrderThrowsIllegalArgumentException(curState, "");
		assertSetTreeOrderThrowsIllegalArgumentException(curState, "not a tree order");
		assertSetExpressionThrowsInvalidInputException(curState, "3--+6");
		assertSetExpressionThrowsInvalidInputException(curState, "");
	}
	

}
