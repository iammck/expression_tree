public class QuitCommand extends Command{
	
	public QuitCommand(ExpressionTreeContext context, String arg)
					throws InvalidCommandException {
		super(context, arg);
	}
	
	public void execute(){
		context.quit(arg);
	}
	
	public void unexecute(){
		
	}
}
