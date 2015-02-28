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
	TestContext context;
	
	Command format;
	Command expression;
	Command evaluate;
	Command macro;
	Command print;
	Command  quit;
	Command  reset;
	Command help;
	
	boolean reachedSetFormat = false;
	boolean reachedSetExpression = false;
	boolean reachedEvaluate = false;
	boolean reachedMacro = false;
	boolean reachedPrint = false;
	boolean reachedQuit = false;
	boolean reachedReset = false;
	boolean rechedHelp = false;
	
	
	List<Command> commands;
	
	private class TestContext extends ExpressionTreeContext {
		public State getCurrentState(){
			return super.getCurrentState();
		}
		
		public void setCurrentState(State state){
			super.setCurrentState(state);
		}
		
		public void setExpression(String arg){
			reachedSetExpression = true;
		}
		
		
		public void setFormat(String arg){
			reachedSetFormat = true;
		}
		
		public void evaluate(String arg){
			reachedEvaluate = true;
		}	
		
		public void printExpressionTree(String arg){
			reachedPrint = true;
		}	
		
		public void quit(String arg){
			reachedQuit = true;		
		}
		
		public void reset(String arg){
			reachedReset = true;		
		}
		
	}
	
	@Before
	public void instantiateCommands(){
		// commands take a context
		try {
			context = new TestContext();
			format = new FormatCommand(context, "infix");
			expression = new ExpressionCommand(context, "3*4");
			evaluate = new EvaluateCommand(context, "");
			print = new PrintCommand(context, "");
			quit = new QuitCommand(context, "");
			reset = new ResetCommand(context,"");
			help = new HelpCommand(context,"");
			
			// a macro takes a list of commands.
			commands = new ArrayList<Command>();
			commands.add(format);
			commands.add(expression);
			commands.add(evaluate);
			commands.add(quit);
			
			macro = new MacroCommand(context, commands, "67+11" );
		} catch (InvalidCommandException e){
			fail("Unable to instaniate commands. " + e.toString());
		}
	}
	
	@Test
	public void testInstantiateCommandsWithNullArguments(){
		String erroneousArgs = null;
		assertInstantiateFormatCommandFails(erroneousArgs);
		assertInstantiateExpressionCommandFails(erroneousArgs);
		assertInstantiateEvaluateCommand(erroneousArgs);
		assertInstantiatePrintCommand(erroneousArgs);
		assertInstantiateQuitCommand(erroneousArgs);
		assertInstantiateResetCommand(erroneousArgs);	
		assertInstantiateMacroCommandFails();
		
	}
	
	
	@Test
	public void testInstantiateCommandsWithErroneousArguments(){
		String erroneousArgs = "chuckles";
		assertInstantiateFormatCommandFails(erroneousArgs);
		// should not fail here at this point
		// assertInstantiateExpressionCommandFails(erroneousArgs);
		assertInstantiateEvaluateCommand(erroneousArgs);
		assertInstantiatePrintCommand(erroneousArgs);
		assertInstantiateQuitCommand(erroneousArgs);
		assertInstantiateResetCommand(erroneousArgs);	
		assertInstantiateMacroCommandFails();
		
	}
		
	public void assertInstantiateFormatCommandFails(String erroneousArgs){
		try{
			new FormatCommand(context, erroneousArgs);
			fail("Instantiating FormatCommand" 
				+ " should have thrown error from args: " + erroneousArgs);
		} catch (InvalidCommandException e){
			return;
		}		
	}
	
	public void assertInstantiateExpressionCommandFails(String erroneousArgs){
		try{
			new ExpressionCommand(context, erroneousArgs);
			fail("Instantiating ExpressionCommand" 
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
		format.execute();
		expression.execute();
		evaluate.execute();
		print.execute();
		evaluate.execute();
		quit.execute();
		reset.execute();
		macro.execute();
	}
	
	
}
