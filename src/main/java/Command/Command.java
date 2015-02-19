public abstract class Command {
	protected ExpressionTreeContext context;
	protected String arg;

	public Command(ExpressionTreeContext context, String arg)
					throws InvalidCommandException {
		this.context = context;
		this.arg = arg;
	}	
	public abstract void execute();
	public abstract void unexecute();	
}
