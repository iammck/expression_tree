public class SetFormatCommand extends Command{
	
	public SetFormatCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}
	
	public void execute(){
		context.setFormat(arg);	
	}
	
	public void unexecute(){
		
	}
}
