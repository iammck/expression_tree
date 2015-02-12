import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestReactorEventHandlers {
	
	@Test
	public void testConsoleInputEventHandler(){
		EventHandler eventHandler;
		eventHandler = new ConsoleInputEventHandler();
		
		fail("Must implement");
	}
	
	@Test
	public void testConsoleOutputEventHandler(){
		EventHandler eventHandler;
		eventHandler = new ConsoleOutputEventHandler();
		
		fail("Must implement");
	}
	
	@Test
	public void testConsoleQuitEventHandler(){
		EventHandler eventHandler;
		eventHandler = new ConsoleQuitEventHandler();
		
		fail("Must implement");
	}
	
	@Test
	public void testExpressionTreeMacroEventHandler(){
		EventHandler eventHandler;
		eventHandler = new ExpressionTreeMacroEventHandler();
		
		fail("Must implement");
	}
	
	@Test
	public void testExpressionTreeVerboseEventHandler(){
		EventHandler eventHandler;
		eventHandler = new ExpressionTreeMacroEventHandler();
		
		fail("Must implement");
	}
}
