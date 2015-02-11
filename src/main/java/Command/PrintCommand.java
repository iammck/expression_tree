public class PrintCommand extends Command{
	public PrintCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}
	
	public void execute(){
		context.printExpressionTree(arg);
	}
	
	public void unexecute(){
		
	}
}
