public class SetInputFormatCommand extends Command{
	
	public SetInputFormatCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}
	
	public void execute(){
		context.setInputFormat(arg);	
	}
	
	public void unexecute(){
		
	}
}
