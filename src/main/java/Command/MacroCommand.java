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
			if (context.hasMacroCommandError()){
				try{
					// do not quit, just stop executing commands.
					//Command c = new QuitCommand(context, null);
					//c.execute();
					return;
				} catch (Exception e){
					System.out.println(e.toString());
				}
			}
		}
	}
	
	public void unexecute(){
		
	}
}
