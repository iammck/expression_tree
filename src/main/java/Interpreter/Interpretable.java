import java.util.List;

public interface Interpretable {
	boolean interpret(List<Symbol> interpretedList);
	void addToInterpreter(List<Symbol> interpretedList, 
					List<Symbol> pendingList);
}
