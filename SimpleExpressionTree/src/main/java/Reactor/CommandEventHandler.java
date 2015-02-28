public abstract class CommandEventHandler implements EventHandler {
	public abstract void handleEvent(String event, Object data);
	public abstract void quit();
}
