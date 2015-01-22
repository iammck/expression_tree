import org.junit.*;
import static org.junit.Assert.*;

/**
  * Test the instantiation of each command class. Possible commands:
  * set format, set expression, evaluate, macro, print, quit
  */

public class TestCommand{

	@Test
	public void testInstantiateCommands(){
		
		// commands can take a context and arg.
		ExpressionTreeContext context = new ExpressionTreeContext();
		String arg = "random arg";
		Command command = new SetFormatCommand(context, arg);
		command = new SetExpressionCommand(context, arg);
		command = new EvaluateCommand(context, arg);
		command = new MacroCommand(context, arg);
		command = new PrintCommand(context, arg);
		command = new QuitCommand(context, arg);
	}
	
	@Test
	public void testExecuteCommands(){
		// commands can take a context and arg.
		ExpressionTreeContext context = new ExpressionTreeContext();
		String arg = "random arg";
		
		Command command = new SetFormatCommand(context, arg);
		command.execute();
		command = new SetExpressionCommand(context, arg);
		command.execute();
		command = new EvaluateCommand(context, arg);
		command.execute();
		command = new MacroCommand(context, arg);
		command.execute();
		command = new PrintCommand(context, arg);
		command.execute();
		command = new QuitCommand(context, arg);
		command.execute();
	}
}
