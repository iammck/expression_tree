import java.util.Scanner;

public class ConsoleInputVerboseEventHandler implements InputEventHandler {	

	private class GetInputThread extends Thread {		
		public void run(){
			while (!isInterrupted()){
				Scanner in = new Scanner(System.in);
				String input = null; // user console input
				if (in.hasNextLine()){
					input = in.nextLine();
					String inputEvent = "command";
					String inputEventData = parseInput(input);
					Reactor.getInstance()
						.handleEvent(inputEvent, inputEventData);
				}
				in.close();
			}
		}
	}	

	private GetInputThread getInputThread;
	
	public void handleEvent(String event, Object data){
		// is there already a thread awaiting input?
		getInputThread = new GetInputThread();
		getInputThread.start();
	}
	
	public void quit(){
		if (getInputThread != null){
			getInputThread.interrupt();
		}
	}
	
	private String parseInput(String input){
		return input;
	}	
}
