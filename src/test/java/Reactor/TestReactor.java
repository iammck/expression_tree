import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestReactor {

	@Test
	public void testGetInstance(){
		assertNotNull("Reactor failed to get an instance()", 
			Reactor.getInstance());

	}
	
	@Test
	public void testRegisterUnregisterEventHandler(){
		// should fail null or empty event name or null handler
		shouldFailNullEventName();
		shouldFailEmptyEventName();
		shouldFailNullHandler();
		
		ConsoleOutputEventHandler consoleHandler = new ConsoleOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output" , consoleHandler);
		
		// this plus the two default quit and reset handlers.
		assertEquals("There should be 3 registered handler.",
			3,Reactor.getInstance().handlerCount());
		Reactor.getInstance().unregisterEventHandler(consoleHandler);
		assertEquals("There should be 2 registered handler.",
			2,Reactor.getInstance().handlerCount());
	}
	
	@Test
	public void testHandleEvent(){
		ConsoleOutputEventHandler consoleHandler = new ConsoleOutputEventHandler();
		Reactor.getInstance().registerEventHandler("output" , consoleHandler);
		Reactor.getInstance().handleEvent("output","test text");
	}
	
	private void shouldFailNullEventName(){
		try{
			ConsoleOutputEventHandler consoleHandler;
			consoleHandler = new ConsoleOutputEventHandler();
			Reactor.getInstance()
				.registerEventHandler(null,consoleHandler); 
			fail("shouldFailNullEventName");
		} catch (Exception e) {
			 return ;
		}
	}
	private void shouldFailEmptyEventName(){
		try{
			ConsoleOutputEventHandler consoleHandler;
			consoleHandler = new ConsoleOutputEventHandler();
			Reactor.getInstance()
				.registerEventHandler("",consoleHandler);
			fail("shouldFailEmptyEventName");
		} catch (Exception e) {
			 return ;
		}
	}
	private void shouldFailNullHandler(){
		try{
			Reactor.getInstance()
				.registerEventHandler("the", null);
			fail("shouldFailNullHandler");
		} catch (Exception e) {
			 return ;
		}
	}
}
