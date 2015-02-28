public interface OutputEventHandler extends EventHandler{
	void handleEvent(String event, Object data);
	void quit();
}
