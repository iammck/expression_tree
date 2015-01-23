import java.util.List;

public class MacroCommand extends Command{
	List<Command> commands;
	
	public MacroCommand(ExpressionTreeContext context, List<Command> commands){
		super(context, null);
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
