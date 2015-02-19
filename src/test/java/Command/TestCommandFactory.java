import org.junit.*;
import static org.junit.Assert.*;

/**
  * Test the instantiation of the command factory. 
  * check that the factory can interprete the Possible commands:
  * set input format, set expression, evaluate, macro, print, quit
  * and set tree order.
  */
public class TestCommandFactory{
	CommandFactory factory;
	
	
	@Before
	public void createConcreteCommandFactory(){
		// needs the context to build commands with.
		ExpressionTreeContext context = new ExpressionTreeContext();
		factory = new ConcreteCommandFactory(context);
	}
	
	@Test
	public void testMakeCommandEvaluate(){
		assertCanMakeCommand("evaluate", null);
	}
	
	@Test
	public void testMakeCommandMacro(){
		assertCanNotMakeCommand("macro", null);
		assertCanMakeCommand("macro", "");
		assertCanMakeCommand("macro", "bad expr");
		assertCanMakeCommand("macro", "4*5");
	}
	
	@Test
	public void testMakeCommandPrint(){
		assertCanMakeCommand("print", null);
		assertCanMakeCommand("print", "in-order");
	}
	
	@Test
	public void testMakeCommandQuit(){
		assertCanMakeCommand("quit", null);
	}	

	@Test
	public void testMakeCommandSetExpression(){
		assertCanNotMakeCommand("setexpression", null);
		assertCanMakeCommand("setexpression", "bad expr");
		assertCanMakeCommand("setexpression", "3+1");

	}
	
	@Test
	public void testMakeCommandSetInputFormat(){
		assertCanNotMakeCommand("setinputformat", null);
		assertCanNotMakeCommand("setinputformat", "bad arg");
		assertCanMakeCommand("setinputformat", "infix");
	}
	
	@Test
	public void testMakeCommandSetTreeOrder(){
		assertCanNotMakeCommand("settreeorder", null);
		assertCanNotMakeCommand("settreeorder", "bad arg");
		assertCanMakeCommand("settreeorder", "infix");
	}
	
	@Test
	public void testMakeCommandReset(){
		assertCanMakeCommand("reset", null);
	}


	
	private void assertCanMakeCommand(String name, String arg){
		try {
			Command command = factory.makeCommand(name, arg);
			assertNotNull("makeCommand returned null for \""
				+ name + "\" command name." , command);
		} catch (InvalidCommandException e){
			fail("Failed to make command, " + e.toString());
		}
	}
	
	
	private void assertCanNotMakeCommand(String name, String arg){
		try {
			Command command = factory.makeCommand(name, arg);
			fail("Failed, should not be able to make command "
				+ name + " with arg " + arg);
		} catch (InvalidCommandException e){
			return;
		}
	}
}
