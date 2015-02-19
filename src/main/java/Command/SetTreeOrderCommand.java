public class SetTreeOrderCommand extends Command{
	
	public SetTreeOrderCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		super(context, arg);
		try { // Try to get the enum value for the arg.
			Enum.valueOf(ExpressionTreeContext.TreeOrder.class, arg);
		} catch ( IllegalArgumentException e){
			throw new InvalidCommandException( this.toString() 
					+ " could not set tree order to " + arg);
		} catch (NullPointerException e){
			throw new InvalidCommandException( this.toString() 
					+ " could not set tree order to " + arg);
		}
	}
	
	public void execute(){
		context.setTreeOrder(arg);	
	}
	
	public void unexecute(){
		
	}
}
