import java.util.Scanner;

public class ConsoleInputMacroEventHandler implements InputEventHandler {
	public void handleEvent(String event, Object data){
		//System.out.println((String) data);
		
		Scanner in = new Scanner(System.in);
		String input; // input of user console input
		input = in.nextLine();
		
		String inputEvent = "command";
		String inputEventData = getEventData(input);
		Reactor.getInstance()
			.handleEvent(inputEvent, inputEventData);		
	}
	
	public void quit(){
		
	}
			
	private String getEventData(String input){
		return input;
	}	
}
