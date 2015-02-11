import java.util.List;

public interface Interpretable {
	int precedenceComparedTo(Interpretable other);
	boolean interpret(List<Interpretable> interpretedList);
	void addToInterpreter(List<Interpretable> interpretedList, 
					List<Interpretable> pendingList);
	ComponentNode build();
}
