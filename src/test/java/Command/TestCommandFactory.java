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
	public void testMakeCommandExpression(){
		assertCanNotMakeCommand("expression", null);
		assertCanMakeCommand("expression", "bad expr");
		assertCanMakeCommand("expression", "3+1");

	}
	
	@Test
	public void testMakeCommandFormat(){
		assertCanNotMakeCommand("format", null);
		assertCanNotMakeCommand("format", "bad arg");
		assertCanMakeCommand("format", "infix");
	}
	
	
	
	@Test
	public void testMakeCommandReset(){
		assertCanMakeCommand("reset", null);
	}


	private class CommandChecker extends Command{	
		private CommandChecker() throws InvalidCommandException{
			super(null,null);
		}
		
		public  void execute(){}
		public void unexecute(){}
	
		
		public String getArg(Command command){
			return command.arg;
		}
	}
	
	private void assertCommandArgEquals(Command command, String otherArg){
		CommandChecker cc = null;
		try {
			cc = new CommandChecker();
		} catch (Exception e){
			System.out.println("Error making CommandChecker! " 
				+ e.toString());
		}
		assertEquals("\nmade command, but args have changed.", 
				otherArg, cc.getArg(command));		
	}
	
	private void assertCanMakeCommand(String name, String arg){
		try {
			Command command = factory.makeCommand(name, arg);
			assertNotNull("makeCommand returned null for \""
				+ name + "\" command name." , command);
			assertCommandArgEquals(command, arg);
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
