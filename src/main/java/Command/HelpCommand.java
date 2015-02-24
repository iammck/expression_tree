public class HelpCommand extends Command{
	
	public HelpCommand(ExpressionTreeContext context, String arg)
					throws InvalidCommandException {
		super(context, arg);
	}
	
	public void execute(){
		try {
			Reactor.getInstance().handleEvent(CommandContract.Help, arg);
		} catch (InvalidEventHandlerException e) {
			throw new IllegalStateException(
				"State exception forwarding event "
				+ " help by HelpCommand."
				+ e.toString());
		}
	}
	
	public void unexecute(){
		
	}
}
