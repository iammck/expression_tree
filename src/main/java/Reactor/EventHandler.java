public interface EventHandler {
	void handleEvent(String event, Object data);
	void quit();
}
