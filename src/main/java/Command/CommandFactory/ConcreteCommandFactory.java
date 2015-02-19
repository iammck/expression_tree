import java.util.Hashtable;

public class ConcreteCommandFactory implements CommandFactory{
	ExpressionTreeContext context;
	Hashtable<String, CommandMaker> commandTable; 
	
	public ConcreteCommandFactory(ExpressionTreeContext context){
		this.context = context;
		commandTable = new Hashtable<String, CommandMaker>();
		commandTable.put("print", new PrintCommandMaker());
		commandTable.put("evaluate", new EvaluateCommandMaker());
		commandTable.put("macro", new MacroCommandMaker());
		commandTable.put("quit", new QuitCommandMaker());
		commandTable.put("reset", new ResetCommandMaker());
		commandTable.put("setexpression", new SetExpressionCommandMaker());
		commandTable.put("settreeorder", new SetTreeOrderCommandMaker());
		commandTable.put("setinputformat", new SetInputFormatCommandMaker());
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
