import java.util.Hashtable;

public class ConcreteCommandFactory implements CommandFactory{
	ExpressionTreeContext context;
	Hashtable<String, CommandMaker> commandTable; 
	
	public ConcreteCommandFactory(ExpressionTreeContext context){
		this.context = context;
		commandTable = new Hashtable<String, CommandMaker>();
		commandTable.put(CommandContract.Print, new PrintCommandMaker());
		commandTable.put(CommandContract.Evaluate, new EvaluateCommandMaker());
		commandTable.put(CommandContract.Macro, new MacroCommandMaker());
		commandTable.put(CommandContract.Quit, new QuitCommandMaker());
		commandTable.put(CommandContract.Reset, new ResetCommandMaker());
		commandTable.put(CommandContract.Expression, new ExpressionCommandMaker());
		commandTable.put(CommandContract.Help, new HelpCommandMaker());
		commandTable.put(CommandContract.Format, new FormatCommandMaker());
	}
	
	public Command makeCommand(String commandName, String arg) 
			throws InvalidCommandException{
		CommandMaker maker = commandTable.get(commandName);
		if (maker != null)
			return maker.makeCommand(context, arg);
		throw new InvalidCommandException("The command name " 
				+ commandName + " does not exist.");
	}
}
