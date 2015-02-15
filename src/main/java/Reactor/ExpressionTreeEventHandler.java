public interface ExpressionTreeEventHandler extends EventHandler {
	void handleEvent(String event, Object data);
	void quit();
}
