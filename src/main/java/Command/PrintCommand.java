public class PrintCommand extends Command{
	public PrintCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}
	
	public void execute(){
		context.print(arg);
	}
	
	public void unexecute(){
		
	}
}
