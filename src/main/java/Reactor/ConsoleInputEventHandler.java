import java.util.Scanner;

public class ConsoleInputEventHandler implements InputEventHandler {
	public void handleEvent(String event, Object data){
		Scanner in = new Scanner(System.in);
		System.out.println((String) data); // should be an output event
		String input; // input of user console input
		input = in.nextLine();
		String inputEvent = getEvent(input);
		String inputEventData = getEventData(input);
		
		//Reactor.handleEvent(event, data);
		
		System.out.println(input + " should be "
			+ inputEvent + " as event and "
			+ inputEventData + " as data.");
	}
	
	private String getEvent(String input){
		input = input.trim();
		int index = input.indexOf(' ');
		return input.substring(0,index);
	}
		
	private String getEventData(String input){
		input = input.trim();
		int index = input.indexOf(' ');
		return input.substring(index + 1);
	}	
}
