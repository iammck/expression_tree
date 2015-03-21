public interface HelpEventHandler extends EventHandler{
	void handleEvent(String event, Object data);
	void quit();
}
