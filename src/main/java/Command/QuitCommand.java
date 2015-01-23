public class QuitCommand extends Command{
	
	public QuitCommand(ExpressionTreeContext context, String arg){
		super(context, arg);
	}
	
	public void execute(){
		context.quit(arg);
	}
	
	public void unexecute(){
		
	}
}
