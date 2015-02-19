import org.junit.*;
import static org.junit.Assert.*;

public class TestExpressionTreeContext {
	
	
	// the context should catch the state exception and
	// send an output event with the reactor.
	private class TestOutputEventHandler implements OutputEventHandler{
		boolean hasHandled = false;
		boolean hasQuit = false;
		String data;
		int eventCount = 0;
		int resetCount = 0;
		
		public void handleEvent(String event, Object data){
			//System.out.println((String) data);
			//System.out.println("");
			
			this.data = (String) data;
			hasHandled = true;
			eventCount += 1;
		}	
		
		public void quit(){
			hasQuit = true;
		}
		
		void reset(){
			resetCount++;
			hasHandled = false;
			hasQuit = false;
			eventCount = 0;
		}
	}
	
	TestOutputEventHandler outputHandler;
	
	@Before
	public void beforeEachTest(){
		outputHandler = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler(
			"output", outputHandler );
	}
	
	@Test
	public void testTestCommandsWithInitialState(){	
		
		// create the context
		ExpressionTreeContext context;
		context = new ExpressionTreeContext();
		
		// State methods that throw exceptions, should
		// be caught and a message should be sent to the
		// recator for print, evaluate, setExpression		
		// for three values each. Any result is fine.
		// a null.
		context.printExpressionTree(null);
		// a treeOrder
		context.printExpressionTree("infix");
		// something unknown.
		context.printExpressionTree("unknown");
		// a null.
		context.evaluate(null);
		// a treeOrder
		context.evaluate("infix");
		// something unknown.
		context.evaluate("unknown");
		// a null.
		context.setExpression(null);
		// a treeOrder
		context.setExpression("55+1");
		// something unknown.
		context.setExpression("unknown");
		
		
		assertEquals("The outputHandler did not receive 9 events.",
			9, outputHandler.eventCount);
		
		// quit, should send a message to the reactor
		
		// to quit, which should send one to the output handler.
		context.quit("um?");
		assertTrue("The outputHandler did not receive a quit message.",
			outputHandler.hasQuit);
		
		// reset should reset the context to starting vals.
		context.reset("rereset");
		assertTrue("the context did not reset to initial state.",
			context.getCurrentState() instanceof InitialState);
		
		// setInputFormat
		context.setInputFormat(null);
		assertTrue("the context should not be set to FormatedState state.",
			!(context.getCurrentState() instanceof FormatedState));
		context.reset("arr");
		// setInputFormat... does not raise out event.
		context.setInputFormat("infix");
		assertTrue("the context did not get set to FormatedState state.",
			context.getCurrentState() instanceof FormatedState);
		context.reset("arr");
		// setInputFormat
		context.setInputFormat("unknown");
		assertTrue("the context should not be set to FormatedState state.",
			!(context.getCurrentState() instanceof FormatedState));
		context.reset("arr");
		
		// the output handler should have reveived the three events, 11 total.
		assertEquals("The outputHandler did not receive 11 events.",
			11, outputHandler.eventCount);
		
		// setTreeOrder should set the tree order.
		// setInputFormat
		context.setTreeOrder(null);
		assertTrue("the context should not be set to FormatedState state.",
			!(context.getCurrentState() instanceof FormatedState));
		context.reset("arr");
		// setTreeOrder does not generate output
		context.setTreeOrder("infix");
		assertTrue("the context should not be set to FormatedState state.",
			!(context.getCurrentState() instanceof FormatedState));
		context.reset("arr");
		// setTreeOrder
		context.setTreeOrder("unknown");
		assertTrue("the context should not be set to FormatedState state.",
			!(context.getCurrentState() instanceof FormatedState));
		context.reset("arr");
		
		// the output handler should have reveived the three events, 13 total.
		assertEquals("The outputHandler did not receive 13 events.",
			13, outputHandler.eventCount);
		
	}
	
	@Test
	public void testTestCommandsWithFormatedState(){
		// create the context
		ExpressionTreeContext context;
		context = new ExpressionTreeContext();
		// set the state by setting the format
		context.setInputFormat("infix");
		
		// State methods that throw exceptions, should
		// be caught and a message should be sent to the
		// recator.
		// print, evaluate
		context.printExpressionTree(null);
		// a treeOrder
		context.printExpressionTree("infix");
		// something unknown.
		context.printExpressionTree("unknown");
		// a null.
		context.evaluate(null);
		// a treeOrder
		context.evaluate("infix");
		// something unknown.
		context.evaluate("unknown");
		assertTrue("the context should still be set to FormatedState state.",
			(context.getCurrentState() instanceof FormatedState));

		// the output handler should have reveived the three events, 6 total.
		assertEquals("The outputHandler did not receive 6 events.",
			6, outputHandler.eventCount);
		
		// erroneous setExpression calls, a null.
		context.setExpression(null);
		// something unknown.
		context.setExpression("unknown");
		assertTrue("the context should still be set to FormatedState state.",
			(context.getCurrentState() instanceof FormatedState));
		
		// the output handler should have reveived the two more events, 8 total.
		assertEquals("The outputHandler did not receive 8 events.",
			8, outputHandler.eventCount);

		
		// setExpression should set the expression and update state
		context.setExpression("55+1");		
		assertTrue("the context should now be set to HasExpressionState state.",
			(context.getCurrentState() instanceof HasExpressionState));

		// to quit, which should send one to the output handler.
		context.quit("um?");
		assertTrue("The outputHandler did not receive a quit message.",
			outputHandler.hasQuit);
	
		// reset should reset the context to starting vals.
		context.reset("rereset");
		assertTrue("the context did not reset to initial state.",
			context.getCurrentState() instanceof InitialState);
		
		// set the state by setting the format
		context.setInputFormat("infix");
		
		
		// setTreeOrder should set the tree order.
		// setInputFormat
		context.setTreeOrder(null);		
		// setTreeOrder does not generate output
		context.setTreeOrder("infix");		
		// setTreeOrder
		context.setTreeOrder("unknown");
		
		// the output handler should have reveived the two events, 10 total.
		assertEquals("The outputHandler did not receive 10 events.",
			10, outputHandler.eventCount);
	}
	
	@Test
	public void testTestCommandsWithHasExpressionState(){	
		
		
		
		// print, evaluate should do just that and send result
		// to the reactor.
		
		// setExpression should set the expression and update state
		
		// quit, should send a message to the reactor
		// to quit.

		// reset should reset the context to starting vals.
		// setInputFormat should set the input format
		// setTreeOrder should set the tree order.
		fail("Method needs implementation.");
	}
	
	@Test
	public void testTestCommandsWithHasUnevaluatableExpressionState(){	
		// evaluate should throw exception, should
		// be caught and a message should be sent to the
		// recator.
		
		// print should send the expression to the reactor.
		
		// setExpression should set the expression and update state
		
		// quit, should send a message to the reactor
		// to quit.

		// reset should reset the context to starting vals.
		// setInputFormat should set the input format
		// setTreeOrder should set the tree order.
		fail("Method needs implementation.");
	}
}
	
