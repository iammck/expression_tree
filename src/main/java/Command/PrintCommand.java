public class PrintCommand extends Command{
	public PrintCommand(ExpressionTreeContext context, String arg)
					throws InvalidCommandException {
		super(context, arg);
	}
	
	public void execute(){
		context.printExpressionTree(arg);
	}
	
	public void unexecute(){
		
	}
}
