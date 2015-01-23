public class SetExpressionCommand extends Command{
	
	public SetExpressionCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}
	
	public void execute(){
		context.setExpression(arg);
	}
	
	public void unexecute(){
		
	}
}
