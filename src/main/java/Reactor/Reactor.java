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
	// can not be created.
	private Reactor(){
		handlerTable = new Hashtable<String, EventHandler>();
	}

	//		///
	//EventHandlers	///
	//		///
	private Hashtable<String, EventHandler> handlerTable;
	
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
	
	public int handlerCount(){
		return handlerTable.size();
	}
	
	public void handleEvent(String event, Object data){
		handlerTable.get(event).handleEvent(event, data);	
	}
	
}
