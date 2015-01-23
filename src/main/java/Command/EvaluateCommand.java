public class EvaluateCommand extends Command{
	
	public EvaluateCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}	
	
	public void execute(){
		context.evaluate(arg);
	}
	
	public void unexecute(){
		
	}
}
