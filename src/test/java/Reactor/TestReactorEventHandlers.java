import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class TestReactorEventHandlers {
	
	private class TestCommandEventHandler extends CommandEventHandler{
		boolean hasHandled = false;
		boolean hasQuit = false;
		int eventCount = 0;
		
		public void handleEvent(String event, Object data){
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
	
	@Before
	public void beforeTesting(){
		originalInputStream = System.in;
		originalPrintStream = System.out;
	}
	
	@After
	public void afterTesting(){
		System.setIn(originalInputStream);
		System.setOut(originalPrintStream);
	}
	
	@Test
	public void testConsoleInputMacroEventHandler(){
		// register a command event handler with the Reactor
		TestCommandEventHandler testCommandEventHandler;
		testCommandEventHandler = new TestCommandEventHandler();
		Reactor.getInstance().registerEventHandler(
				"command", testCommandEventHandler);
		
		// Set sysetem in to some test input.
		String testInput = "test command";
		ByteArrayInputStream in = new ByteArrayInputStream(
			testInput.getBytes());
		System.setIn(in);
		
		// instantiate and invoke ConsoleInputMacroEventHandler 
		EventHandler eventHandler;
		eventHandler = new ConsoleInputMacroEventHandler();
		eventHandler.handleEvent("input", "Test input macro, enter something.");
		// check test command event handler		
		assertTrue("testCommandEventHandler should have handled command.",
			testCommandEventHandler.hasHandled);
		eventHandler.quit();
	}
	
	@Test
	public void testConsoleInputVerboseEventHandler(){
		// register a command event handler with the Reactor
		TestCommandEventHandler testCommandEventHandler;
		testCommandEventHandler = new TestCommandEventHandler();
		Reactor.getInstance().registerEventHandler(
				"command", testCommandEventHandler);
		
		// Set sysetem in to some test input.
		String testInput = "test command1\n";
		ByteArrayInputStream in = new ByteArrayInputStream(
						testInput.getBytes());
		System.setIn(in);
		
		// instantiate and invoke ConsoleInputVerboseEventHandler 
		EventHandler eventHandler;
		eventHandler = new ConsoleInputVerboseEventHandler();
		eventHandler.handleEvent("input", "Test input macro, enter something.");
		
		try {
			// sleep for a sec.
			Thread.sleep(100);
		} catch (InterruptedException e){ 
			System.out.print("\n" + e);
		}
		
		// check test command event handler		
		assertTrue("testCommandEventHandler should have handled command.",
			testCommandEventHandler.hasHandled);
		assertEquals("testCommandEventHandler should have 1 handled events.",
			1, testCommandEventHandler.eventCount);
		
		
		// Set sysetem in to some test input.
		testInput = "test command2\n";
		in = new ByteArrayInputStream( testInput.getBytes());
		System.setIn(in);
		
		try {
			// sleep for a sec.
			Thread.sleep(100);
		} catch (InterruptedException e){ 
			System.out.print("\n" + e);
		}
		
		assertEquals("testCommandEventHandler should have 2 handled events.",
			2, testCommandEventHandler.eventCount);
		
		
		// Set sysetem in to some test input.
		testInput = "test command3\n";
		in = new ByteArrayInputStream( testInput.getBytes());
		System.setIn(in);
		
		try {
			// sleep for a sec.
			Thread.sleep(100);
		} catch (InterruptedException e){ 
			System.out.print("\n" + e);
		}
		// must quit to interrupt input listener.
		eventHandler.quit();
		
		assertEquals("testCommandEventHandler should have 3 handled events.",
			3, testCommandEventHandler.eventCount);
		
	}
	
	@Test
	public void testConsoleOutputEventHandler(){
		// create the testPrintStream for System.out.
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream testPrintStream = new PrintStream(out);
		System.setOut(testPrintStream);
		// create the Event handler
		EventHandler eventHandler;
		eventHandler = new ConsoleOutputEventHandler();
		String msg = "OutputEventHandler output.";
		eventHandler.handleEvent("rando", msg);
		
		assertEquals("msg did not equal eventHandler output!",
			msg, out.toString().trim());
	}
	
	@Test
	public void testVerboseCommandEventHandlerWithNonsenseCommand(){
		// create an output event handler to pick up the exprected results.
		OutputEventHandler output = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", output);
		
		EventHandler eventHandler;
		eventHandler = new VerboseCommandEventHandler();
		
		// send the handler a jiberish command.
		eventHandler.handleEvent("command", "nonsense.");
		// output should now have a result.
		assertNotNull("The output event Handler did not generate output as exprected.",
			((TestOutputEventHandler)output).data);
	}
	
	@Test
	public void testVerboseCommandEventHandlerWithQuitCommand(){
		// create an output event handler to pick up the exprected results.
		OutputEventHandler output = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", output);
		
		EventHandler eventHandler;
		eventHandler = new VerboseCommandEventHandler();
		
		// send the handler a quit command.
		eventHandler.handleEvent("command", "quit");
		// output should now have quit.
		assertTrue("The output event Handler did not quit as expected.",
			((TestOutputEventHandler)output).hasQuit);
	}
	
	@Test
	public void testVerboseCommandEventHandlerWithResetCommand(){
		// create an output event handler to pick up the exprected results.
		OutputEventHandler output = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", output);
		
		EventHandler eventHandler;
		eventHandler = new VerboseCommandEventHandler();
		
		// send the handler a reset command.
		eventHandler.handleEvent("command", "reset");
		// output should now have quit.
		assertEquals("The output event Handler did not reset as expected.",
			1, ((TestOutputEventHandler)output).resetCount);
	}
	
	
	@Test
	public void testVerboseCommandEventHandlerWithPrintCommand(){
		// create an output event handler to pick up the exprected results.
		OutputEventHandler output = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", output);
		
		EventHandler eventHandler;
		eventHandler = new VerboseCommandEventHandler();
		
		// send the handler a print command.
		eventHandler.handleEvent("command", "print");
		// output should now have quit.
		assertTrue("The  output event Handler did not print as expected.",
			((TestOutputEventHandler)output).hasHandled);
	}
	
	@Test
	public void testVerboseCommandEventHandlerWithEvaluateCommand(){
		// create an output event handler to pick up the exprected results.
		OutputEventHandler output = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", output);
		
		EventHandler eventHandler;
		eventHandler = new VerboseCommandEventHandler();
		
		// send the handler a print command.
		eventHandler.handleEvent("command", "evaluate");
		// output should now have quit.
		assertTrue("The output event Handler did not print as expected.",
			((TestOutputEventHandler)output).hasHandled);
	}
	
	@Test
	public void testVerboseCommandEventHandlerWithSetExpressionCommand(){
		// create an output event handler to pick up the exprected results.
		OutputEventHandler output = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", output);
		
		EventHandler eventHandler;
		eventHandler = new VerboseCommandEventHandler();
		
		// send the handler a print command.
		eventHandler.handleEvent("command", "setexpression 3*4");
		// output should now have quit.
		assertTrue("The output event Handler did not behave as expected.",
			((TestOutputEventHandler)output).hasHandled);
	}
	
	@Test
	public void testVerboseCommandEventHandlerWithSetInputFormatCommand(){
		// create an output event handler to pick up the exprected results.
		OutputEventHandler output = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", output);
		
		EventHandler eventHandler;
		eventHandler = new VerboseCommandEventHandler();
		
		// send the handler a print command.
		eventHandler.handleEvent("command", "setinputformat infix");
		// output should now have quit.
		assertTrue("The output event Handler did not behave as expected.",
			((TestOutputEventHandler)output).hasHandled);
	}
	
	@Test
	public void testVerboseCommandEventHandlerWithSetTreeOrderCommand(){
		// create an output event handler to pick up the exprected results.
		OutputEventHandler output = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", output);
		
		EventHandler eventHandler;
		eventHandler = new VerboseCommandEventHandler();
		
		// send the handler a print command.
		eventHandler.handleEvent("command", "settreeorder infix");
		// output should now have quit.
		assertTrue("The output event Handler did not behave as expected.",
			((TestOutputEventHandler)output).hasHandled);
	}
	
	@Test
	public void testMacroCommandEventHandler(){
		EventHandler eventHandler;
		eventHandler = new MacroCommandEventHandler();
		
		fail("Must implement");
	}
	
	
}
