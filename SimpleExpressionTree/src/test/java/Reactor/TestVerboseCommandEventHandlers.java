import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class TestVerboseCommandEventHandlers {
	
	private class TestCommandEventHandler extends CommandEventHandler{
		boolean hasHandled = false;
		boolean hasQuit = false;
		int eventCount = 0;
		
		public void handleEvent(String event, Object data){
			//System.out.println((String) data);
			hasHandled = true;
			eventCount += 1;
		}	
		
		public void quit(){
			hasQuit = true;
		}
		
		void reset(){
			hasHandled = false;
			hasQuit = false;
			eventCount = 0;
		}
	}
	
	private class TestOutputEventHandler implements OutputEventHandler{
		boolean hasHandled = false;
		boolean hasQuit = false;
		String data;
		int eventCount = 0;
		int resetCount = 0;
		
		public void handleEvent(String event, Object data){
			this.data = (String) data;
			//System.out.println((String) data);
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
	
	private class TestHelpEventHandler implements HelpEventHandler{
		boolean hasHandled = false;
		boolean hasQuit = false;
		String data;
		int eventCount = 0;
		int resetCount = 0;
		
		public void handleEvent(String event, Object data){
			this.data = (String) data;
			//System.out.println((String) data);
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
	
	InputStream originalInputStream;
	PrintStream originalPrintStream;
	OutputEventHandler outputEventHandler;
	
	EventHandler commandEventHandler;
		
	@Before
	public void beforeTesting(){
		originalInputStream = System.in;
		originalPrintStream = System.out;
	
		// create an output event handler to pick up the exprected results.
		outputEventHandler = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", outputEventHandler);
		// and the command event handler.
		commandEventHandler = new VerboseCommandEventHandler();
	}
	
	@After
	public void afterTesting(){
		System.setIn(originalInputStream);
		System.setOut(originalPrintStream);
	}
	
	
	@Test
	public void testWithNonsenseCommand(){
		// send the handler a jiberish command.
		commandEventHandler.handleEvent("command", "nons3nse");
		// outputEventHandler should now have a result.
		String result = ((TestOutputEventHandler)outputEventHandler).data;
		String expected = "The command name " 
				+ "nons3nse does not exist."; 
		assertEquals("\nThe resulting outputEventHandler event did not generate the "
			+ "right output.", expected, result);
	}
	
	@Test
	public void testWithQuitCommand(){
		// send the handler a quit command.
		commandEventHandler.handleEvent("command", "quit");
		// outputEventHandler should now have quit.
		assertTrue("The outputEventHandler event Handler did not quit as expected.",
			((TestOutputEventHandler)outputEventHandler).hasQuit);
	}
	
	@Test
	public void testWithFormatCommand(){
		// send the handler a setInputFormat command.
		commandEventHandler.handleEvent("command", "format infix");
		ExpressionTreeContext context;
		context = ((VerboseCommandEventHandler) commandEventHandler).getContext();
		assertTrue("The state was not updated after setting input.",
			context.getCurrentState() instanceof FormatedState);
	}
	
	@Test
	public void testWithExpressionCommand(){
		// first set the inputformat
		// send the handler a setInputFormat command.
		commandEventHandler.handleEvent("command", "format infix");
		
		// send the handler a expression command with no expression.
		commandEventHandler.handleEvent("command", "expression");
		// outputEventHandler should now have the error as a result.
		String result = ((TestOutputEventHandler)outputEventHandler).data;
		String expected = "The expression is invalid."; 
		assertEquals("\nThe resulting outputEventHandler event did not generate the "
			+ "right output.\n", expected, result);
		ExpressionTreeContext context;
		context = ((VerboseCommandEventHandler) commandEventHandler).getContext();
		assertTrue("The state should not updated after expression with no expression.",
			context.getCurrentState() instanceof FormatedState);
		
		
		
		// send the handler a expression command with erroneous expression.
		commandEventHandler.handleEvent("command", "expression 4++5");
		// outputEventHandler should now have the error as a result.
		result = ((TestOutputEventHandler)outputEventHandler).data;
		expected = "Unable to set expression to 4++5."; 
		assertEquals("\nThe resulting outputEventHandler event did not generate the "
			+ "right output.\n", expected, result);
		context = ((VerboseCommandEventHandler) commandEventHandler).getContext();
		assertTrue("The state should not updated after expression with no expression.",
			context.getCurrentState() instanceof FormatedState);
		
		
		// send the handler a expression command with correct input.
		commandEventHandler.handleEvent("command", "expression 3*4+5");
		context = ((VerboseCommandEventHandler) commandEventHandler).getContext();
		assertTrue("The state was not updated after setting expression.",
			context.getCurrentState() instanceof HasExpressionState);
	}
	
	
	@Test
	public void testWithPrintCommand(){		
		// Print can have possible two outputs depending on state.
		// incorrect state
		// send the handler a print command while incorrect state
		commandEventHandler.handleEvent("command", "print");
		// outputEventHandler should now have a result.
		String result = ((TestOutputEventHandler)outputEventHandler).data;
		String expected = "There is no expression to print!"; 
		assertEquals("\nThe resulting outputEventHandler event did not generate the "
			+ "right output.", expected, result);
		// correct state
		// send the handler a print command while in the right state.
		commandEventHandler.handleEvent("command","format infix");
		commandEventHandler.handleEvent("command","expression 3 + 77-4");
		commandEventHandler.handleEvent("command", "print");
		// outputEventHandler should now have a result.
		result = ((TestOutputEventHandler)outputEventHandler).data;
		expected = "3 + 77 - 4"; 
		assertEquals("\nThe resulting outputEventHandler event did not generate the "
			+ "right output.\n", expected, result);
		
	}
	
	@Test
	public void testWithEvaluateCommand(){
		// Evaluate can have possible two outputs depending on state.
		// wrong state
		// send the handler an evaluate command while context is in wrong state.
		commandEventHandler.handleEvent("command", "evaluate");
		// outputEventHandler should now have a result.
		String result = ((TestOutputEventHandler)outputEventHandler).data;
		String expected = "There is no expression to evaluate!"; 
		assertEquals("\nThe resulting outputEventHandler event did not generate the "
			+ "right output.", expected, result);
		// correct state
		// send the handler a print command while in the right state.
		commandEventHandler.handleEvent("command","format infix");
		commandEventHandler.handleEvent("command","expression 3 + 77-4");
		// send the handler an evaluate command while context is in wrong state.
		commandEventHandler.handleEvent("command", "evaluate");
		// outputEventHandler should now have a result.
		result = ((TestOutputEventHandler)outputEventHandler).data;
		expected = "76.0"; 
		assertEquals("\nThe resulting outputEventHandler event did not generate the "
			+ "right output.\n", expected, result);

		ExpressionTreeContext context;
		context = ((VerboseCommandEventHandler) commandEventHandler).getContext();
		assertTrue("The state was not updated after setting expression.",
			context.getCurrentState() instanceof EvaluatedState);
	}
		
	@Test
	public void testWithResetCommand(){
		// set up to EvaluatedState
		// send the handler a print command while in the right state.
		commandEventHandler.handleEvent("command","format infix");
		commandEventHandler.handleEvent("command","expression 3 + 77-4");
		// send the handler an evaluate command while context is in wrong state.
		commandEventHandler.handleEvent("command", "evaluate");
		commandEventHandler.handleEvent("command", "reset");
		ExpressionTreeContext context;
		context = ((VerboseCommandEventHandler) commandEventHandler).getContext();
		assertTrue("The state was not updated after setting expression.",
			context.getCurrentState() instanceof InitialState);
	}
	
	@Test
	public void testWithHelpCommand(){
		// will need to have a help event listener to pick up the command
		HelpEventHandler helpHandler = new TestHelpEventHandler();
		Reactor.getInstance().registerEventHandler("help", helpHandler);
		commandEventHandler.handleEvent("command", "help");
		// HelpEventHandler should now have a result.
		boolean result = ((TestHelpEventHandler) helpHandler).hasHandled;
		assertTrue("\nThe HelpEventHandler did not "
			+ "revceive the help event.", result);	
	}
}
