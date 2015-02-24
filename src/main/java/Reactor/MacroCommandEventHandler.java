public class MacroCommandEventHandler 
				extends CommandEventHandler {
	
	ExpressionTreeContext context;

	public MacroCommandEventHandler(){
		this.context = new ExpressionTreeContext();
	}
	
	public void handleEvent(String event, Object data){
		String expression = (String) data;
		CommandFactory factory = context.getCommandFactory();
		
		// try to get a command
		try {
			Command command = 
				factory.makeCommand(
					CommandContract.Macro, expression);
				command.execute();
		} catch (InvalidCommandException e){
			String message = e.getMessage();
			handleInvalidCommandException(message);
		}		
	}
	
	private void handleInvalidCommandException(String message){
		try {
			Reactor.getInstance()
				.handleEvent("output", message);
		} catch (InvalidEventHandlerException e) {
			throw new IllegalStateException(
				"State exception forwarding event "
				+ " by MacroCommandEventHandler."
				+ e.toString());
		}
	}
	
	public void quit(){
		
	}
}
