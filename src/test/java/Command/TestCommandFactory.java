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
		// a command may be made with any arg. Bad args are
		// exposed when the command is executed.
		assertCanMakeCommand("macro", null);
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
		assertCanMakeCommand("setexpression", null);
		assertCanMakeCommand("setexpression", "bad expr");
		assertCanMakeCommand("setexpression", "3+1");

	}
	
	@Test
	public void testMakeCommandSetInputFormat(){
		assertCanMakeCommand("setinputformat", null);
		assertCanMakeCommand("setinputformat", "bad arg");
		assertCanMakeCommand("setinputformat", "infix");
	}
	
	@Test
	public void testMakeCommandSetTreeOrder(){
		assertCanMakeCommand("settreeorder", null);
		assertCanMakeCommand("settreeorder", "bad arg");
		assertCanMakeCommand("settreeorder", "infix");
	}
	
	@Test
	public void testMakeCommandReset(){
		assertCanMakeCommand("reset", null);
	}


	
	private void assertCanMakeCommand(String name, String arg){
		Command command = factory.makeCommand(name, arg);
		assertNotNull("makeCommand returned null for \""
			+ name + "\" command name." , command);
	}
}
