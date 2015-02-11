public class ResetCommand extends Command{
	
	public ResetCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}
	
	public void execute(){
		context.reset(arg);
	}
	
	public void unexecute(){
		
	}
}
