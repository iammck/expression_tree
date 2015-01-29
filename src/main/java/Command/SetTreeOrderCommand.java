public class SetTreeOrderCommand extends Command{
	
	public SetTreeOrderCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}
	
	public void execute(){
		context.setTreeOrder(arg);	
	}
	
	public void unexecute(){
		
	}
}
