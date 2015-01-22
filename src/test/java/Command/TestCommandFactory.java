import org.junit.*;
import static org.junit.Assert.*;

/**
  * Test the instantiation of the command factory. 
  * check that the factory can interprete the Possible commands:
  * set format, set expression, evaluate, macro, print, quit
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
		assertCanMakeCommand("macro", null);
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
	public void testMakeCommandSetFormat(){
		assertCanMakeCommand("setformat", null);
		assertCanMakeCommand("setformat", "bad arg");
		assertCanMakeCommand("setformat", "in-fix");
	}
	
	private void assertCanMakeCommand(String name, String arg){
		Command command = factory.makeCommand(name, arg);
		assertNotNull("makeCommand returned null for \""
			+ name + "\" command name." , command);
	}
}
