import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

/**
  * Test the instantiation of each command class. Possible commands:
  * set format, set expression, evaluate, macro, print, quit
  */

public class TestCommand{

	// commands can take a context and arg.
	ExpressionTreeContext context;
	String arg;
	
	Command setFormat;
	Command setExpression;
	Command evaluate;
	Command macro;
	Command print;
	Command  quit;
	
	List<Command> commands;
	
	@Before
	public void instantiateCommands(){
		// commands can take a context and arg.
		ExpressionTreeContext context = new ExpressionTreeContext();
		String arg = "random arg";
		setFormat = new SetFormatCommand(context, arg);
		setExpression = new SetExpressionCommand(context, arg);
		evaluate = new EvaluateCommand(context, arg);
		print = new PrintCommand(context, arg);
		quit = new QuitCommand(context, arg);
		
		commands = new ArrayList<Command>();
		commands.add(setFormat);
		commands.add(setExpression);
		commands.add(evaluate);
		commands.add(quit);
		
		macro = new MacroCommand(context, commands);
	}
	
	@Test
	public void testExecuteCommands(){
		setFormat.execute();
		setExpression.execute();
		evaluate.execute();
		macro.execute();
		print.execute();
		quit.execute();
	}
}
