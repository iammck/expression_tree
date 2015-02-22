import java.util.List;

public class MacroCommand extends Command{
	List<Command> commands;
	
	public MacroCommand(	ExpressionTreeContext context, 
				List<Command> commands,
				String arg)
					throws InvalidCommandException {
		
		super(context, arg);
		if (commands == null)
			throw new InvalidCommandException(
				"MacroCommand constructor params must not be null.");
		this.commands = commands;
	}
	
	public void execute(){
		for(Command command: commands){
			command.execute();
		}
	}
	
	public void unexecute(){
		
	}
}
