import java.util.List;

public interface Interpretable {
	int precedenceComparedTo(Interpretable other);
	boolean interpret(List<Symbol> interpretedList);
	void addToInterpreter(List<Symbol> interpretedList, 
					List<Symbol> pendingList);
}
