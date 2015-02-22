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
		try {
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
			
			macro = new MacroCommand(context, commands, "67+11" );
		} catch (InvalidCommandException e){
			fail("Unable to instaniate commands. " + e.toString());
		}
	}
	
	@Test
	public void testInstantiateCommandsWithNullArguments(){
		String erroneousArgs = null;
		assertInstantiateSetInputFormatCommandFails(erroneousArgs);
		assertInstantiateSetExpressionCommandFails(erroneousArgs);
		assertInstantiateEvaluateCommand(erroneousArgs);
		assertInstantiateSetTreeOrderCommandFails(erroneousArgs);
		assertInstantiatePrintCommand(erroneousArgs);
		assertInstantiateQuitCommand(erroneousArgs);
		assertInstantiateResetCommand(erroneousArgs);	
		assertInstantiateMacroCommandFails();
		
	}
	
	
	@Test
	public void testInstantiateCommandsWithErroneousArguments(){
		String erroneousArgs = "chuckles";
		assertInstantiateSetInputFormatCommandFails(erroneousArgs);
		// should not fail here at this point
		// assertInstantiateSetExpressionCommandFails(erroneousArgs);
		assertInstantiateEvaluateCommand(erroneousArgs);
		assertInstantiateSetTreeOrderCommandFails(erroneousArgs);
		assertInstantiatePrintCommand(erroneousArgs);
		assertInstantiateQuitCommand(erroneousArgs);
		assertInstantiateResetCommand(erroneousArgs);	
		assertInstantiateMacroCommandFails();
		
	}
		
	public void assertInstantiateSetInputFormatCommandFails(String erroneousArgs){
		try{
			new SetInputFormatCommand(context, erroneousArgs);
			fail("Instantiating SetInputFormatCommand" 
				+ " should have thrown error from args: " + erroneousArgs);
		} catch (InvalidCommandException e){
			return;
		}		
	}
	
	public void assertInstantiateSetExpressionCommandFails(String erroneousArgs){
		try{
			new SetExpressionCommand(context, erroneousArgs);
			fail("Instantiating SetExpressionCommand" 
				+ " should have thrown error from args: " + erroneousArgs);
		} catch (InvalidCommandException e){
			return;
		}
	}
	
	public void assertInstantiateEvaluateCommand(String erroneousArgs){
		try{
			new EvaluateCommand(context, erroneousArgs);
		} catch (InvalidCommandException e){
			fail("Instantiating EvaluateCommand" 
				+ " should not have thrown error with args: " + erroneousArgs);
		}
	}
	
	public void assertInstantiateSetTreeOrderCommandFails(String erroneousArgs){
		try{
			new SetTreeOrderCommand(context, erroneousArgs);
			fail("Instantiating SetTreeOrder" 
				+ " should have thrown error from args: " + erroneousArgs);
		} catch (InvalidCommandException e){
			return;
		}
	}
	
	public void assertInstantiatePrintCommand(String args){
		try{
			new PrintCommand(context, args);
		} catch (InvalidCommandException e){
			fail("Instantiating printCommand" 
				+ " should not have thrown error from args: " + args);
		}
	}
	
	public void assertInstantiateQuitCommand(String args){
		try{
			new QuitCommand(context, args);
		} catch (InvalidCommandException e){
			fail("Instantiating QuitCommand" 
				+ " should not have thrown error from args: " + args);
		}
	}
	
	public void assertInstantiateResetCommand(String args){
		try{
			new ResetCommand(context, args);
		} catch (InvalidCommandException e){
			fail("Instantiating ResetCommand" 
				+ " should not have thrown error from args: " + args);
		}
	}
	
	public void assertInstantiateMacroCommandFails(){
		try{
			new MacroCommand(context, null, null);
			fail("Instantiating MacroCommand" 
				+ " should have thrown error from null command List: ");
		} catch (InvalidCommandException e){
			return;
		}
	}
		
	
	@Test
	public void testExecuteCommands(){
		// note that context state is at work here. so order of execution counts.
		setInputFormat.execute();
		setExpression.execute();
		evaluate.execute();
		setTreeOrderToInOrder.execute();
		print.execute();
		setTreeOrderToInfix.execute();
		evaluate.execute();
		quit.execute();
		reset.execute();
		macro.execute();
	}
	
	
}
