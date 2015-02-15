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
		
		assertEquals("There should be 1 registered handler.",
			1,Reactor.getInstance().handlerCount());
		Reactor.getInstance().unregisterEventHandler(consoleHandler);
		assertEquals("There should be 0 registered handler.",
			0,Reactor.getInstance().handlerCount());
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
