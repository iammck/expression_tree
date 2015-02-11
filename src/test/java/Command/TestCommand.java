import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

/**
  * Test the instantiation of each command class. Possible commands:
  * set format, set expression, evaluate, macro, print, quit
  */

public class TestCommand{

	// commands can take a context and "".
	ExpressionTreeContext context;
	
	Command setInputFormat;
	Command setExpression;
	Command evaluate;
	Command setTreeOrderToInOrder;
	Command setTreeOrderToInfix;
	Command macro;
	Command print;
	Command  quit;
	Command  reset;
	
	List<Command> commands;
	
	@Before
	public void instantiateCommands(){
		// commands take a context
		context = new ExpressionTreeContext();
		setInputFormat = new SetInputFormatCommand(context, "infix");
		setExpression = new SetExpressionCommand(context, "3*4");
		evaluate = new EvaluateCommand(context, "");
		setTreeOrderToInOrder = new SetTreeOrderCommand(context, "inorder");
		setTreeOrderToInfix = new SetTreeOrderCommand(context, "infix");
		print = new PrintCommand(context, "");
		quit = new QuitCommand(context, "");
		reset = new ResetCommand(context,"");
		// a macro takes a list of commands.
		commands = new ArrayList<Command>();
		commands.add(setInputFormat);
		commands.add(setExpression);
		commands.add(evaluate);
		// commands.add(SetTreeOrder); // should be set already. with input
		commands.add(quit);
		
		macro = new MacroCommand(context, commands);
	}
	
	@Test
	public void testExecuteCommands(){
		// note that context state is at work here. so order of execution counts.
		setInputFormat.execute();
		assertEvaluateThrowsInvalidStateException();
		setExpression.execute();
		evaluate.execute();
		setTreeOrderToInOrder.execute();
		print.execute();
		assertEvaluateThrowsInvalidStateException();
		setTreeOrderToInfix.execute();
		evaluate.execute();
		quit.execute();
		reset.execute();
		macro.execute();
	}
	
	
	private void assertEvaluateThrowsInvalidStateException(){
		try {
			evaluate.execute();
			fail("should not have been able to execute.");
		} catch (InvalidStateException e){
			return;
		}
	}
}
