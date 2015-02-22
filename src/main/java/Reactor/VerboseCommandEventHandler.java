public class VerboseCommandEventHandler 
				extends CommandEventHandler {
	ExpressionTreeContext context;
	
	public VerboseCommandEventHandler(){
		if ( context == null){
			context = new ExpressionTreeContext();
		}
	}
	
	public void handleEvent(String event, Object data){
		String input = (String) data;
		// attempt to build the command.
		CommandFactory factory = context.getCommandFactory();
		// turn the object into  the command name and argumet
		String commandName = getCommandName(input);
		String commandArgs = getCommandArgs(input);
		
		// try to get a command
		try {
			Command command = 
				factory.makeCommand(commandName, commandArgs);
				command.execute();
		} catch (InvalidCommandException e){
			String message = e.getMessage();
			Reactor.getInstance().handleEvent("output", message);
		}
	}
	
	private String getCommandName(String input){
		if (input == null)
			return null;
		input = input.trim();
		int index = input.indexOf(' ');
		if (index == -1)
			return input;
		return input.substring(0,index);
	}

	private String getCommandArgs(String input){
		if (input == null)
			return null;
		input = input.trim();
		int index = input.indexOf(' ');
		if (index == -1)
			return null;
		return input.substring( index + 1, input.length());

		
	}
	
	public void quit(){
		
	}
	
	public ExpressionTreeContext getContext(){
		return context;
	}
}
