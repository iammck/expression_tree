public class ResetCommand extends Command{
	
	public ResetCommand(ExpressionTreeContext context, String arg)
					throws InvalidCommandException {
		super(context, arg);
	}
	
	public void execute(){
		context.reset(arg);
	}
	
	public void unexecute(){
		
	}
}
