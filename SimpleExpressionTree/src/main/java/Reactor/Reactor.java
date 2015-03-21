import java.util.Hashtable;

public class Reactor {
	// Is a singleton instance.
	private static Reactor instance;
	
	public static synchronized Reactor getInstance(){
		if (instance == null){
			instance = new Reactor();
		}
		return instance;
	}
	// can not be created, but by getInstance method.
	private Reactor(){
		handlerTable = new Hashtable<String, EventHandler>();
		registerDefaultEventHandlers();
	}

	//		///
	//EventHandlers	///
	//		///
	private Hashtable<String, EventHandler> handlerTable;
	
	private void registerDefaultEventHandlers(){
		// create the default quit, reset, and help handlers.	
		EventHandler quitHandler = new EventHandler(){
			public void handleEvent(String event, Object data){
				for(String key: handlerTable.keySet()){
					handlerTable.get(key).quit();
				}
			}
			public void quit(){}
		};		
		registerEventHandler("quit",quitHandler);
		
		EventHandler resetHandler = new EventHandler(){
			public void handleEvent(String event, Object data){}
			public void quit(){}
		};		
		registerEventHandler("reset",resetHandler);		
	}
	
	public void registerEventHandler(String event, EventHandler eventHandler){
		if (event == null || event.equals("") || eventHandler == null){
			throw new IllegalArgumentException(
				"Event, eventHandler are not set up correctly.");
		}
		handlerTable.put(event, eventHandler);
	}

	public void unregisterEventHandler(String event){
		handlerTable.remove(event);
	}
	
	public void unregisterEventHandler(EventHandler eventHandler){
		for(String key : handlerTable.keySet()){
			EventHandler temp = handlerTable.get(key);
			if (temp.equals(eventHandler)){
				handlerTable.remove(key);
				return;
			}
		}
	}
	
	public void unregisterHandlers(){
		handlerTable.clear();
		registerDefaultEventHandlers();
	}
	
	public int handlerCount(){
		return handlerTable.size();
	}
	
	public void handleEvent(String event, Object data) throws InvalidEventHandlerException{
		EventHandler handler;
		handler = handlerTable.get(event);
		if (handler != null)
			handler.handleEvent(event, data);
		else 
			throw new InvalidEventHandlerException(
				"There is no registered event handler for " 
				+ event + " event.");
	}
	
	
	
}
