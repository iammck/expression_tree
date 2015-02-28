import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class TestMacroCommandEventHandlers {
		
	private class TestOutputEventHandler implements OutputEventHandler{
		boolean hasHandled = false;
		boolean hasQuit = false;
		String data;
		int eventCount = 0;
		int resetCount = 0;
		
		public void handleEvent(String event, Object data){
			this.data = (String) data;
			System.out.println(data);
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
	
	OutputEventHandler outputEventHandler;
	
	
	@Before
	public void beforeEachTest(){
		outputEventHandler = new TestOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output", outputEventHandler);
		
	}
	
	@Test
	public void testMacroCommandEventHandler(){
		EventHandler eventHandler;
		eventHandler = new MacroCommandEventHandler();
		Reactor.getInstance().registerEventHandler("command", eventHandler);
		
		try{
			// this should fail because the random text is not an expression.
			Reactor.getInstance().handleEvent("command", "some rando text");
			// check for the right input
			String result = ((TestOutputEventHandler) outputEventHandler).data;
			String expected = "Unable to set expression to some rando text.";
			assertEquals("\nExpected a different result!",
				expected, result);
			
			// this should fail because the random text is not an expression.
			Reactor.getInstance().handleEvent("command", "");
			// check for the right input
			result = ((TestOutputEventHandler) outputEventHandler).data;
			expected = "Unable to set expression.";
			assertEquals("\nExpected a different result!",
				expected, result);
			
			// this should pass.
			Reactor.getInstance().handleEvent("command", "(5+4)*4");
			// check for the right input
			result = ((TestOutputEventHandler) outputEventHandler).data;
			expected = "36.0";
			assertEquals("\nExpected a different result!",
				expected, result);
			
			
		} catch (InvalidEventHandlerException e){
			fail("Reactor caught InvalidEventHandlerException");
		}
		
		
	}
	
	
}
