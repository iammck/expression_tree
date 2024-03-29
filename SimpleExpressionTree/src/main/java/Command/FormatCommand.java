public class FormatCommand extends Command{
	
	public FormatCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		super(context, arg);
		try { // Try to get the enum value for the arg.
			Enum.valueOf(ExpressionTreeContext.InputFormat.class, arg);
		} catch ( IllegalArgumentException e){
			throw new InvalidCommandException( this.toString() 
					+ " could not set format to " + arg);
		} catch (NullPointerException e){
			throw new InvalidCommandException( this.toString() 
					+ " could not set format to " + arg);
		}
	}
	
	public void execute(){
		context.setFormat(arg);	
	}
	
	public void unexecute(){
		
	}
}
