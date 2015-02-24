public class ConsoleHelpEventHandler implements HelpEventHandler {
	public void handleEvent(String event, Object data){
		System.out.println("The console help command result.");
	}
	
	public void quit(){
		
	}
}
