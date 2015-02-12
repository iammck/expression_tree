public class ConsoleOutputEventHandler implements OutputEventHandler {
	public void handleEvent(String event, Object data){
		System.out.println((String) data);
	}
}
