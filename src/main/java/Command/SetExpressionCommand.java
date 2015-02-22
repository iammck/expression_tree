public class SetExpressionCommand extends Command{
	
	public SetExpressionCommand(	ExpressionTreeContext context, 
					String arg)
					throws InvalidCommandException {
		super(context, arg);
		if (arg == null){
			throw new InvalidCommandException(
				"The expression is invalid.");
		}
	}
	
	public void execute(){
		context.setExpression(arg);
	}
	
	public void unexecute(){
		
	}
}
