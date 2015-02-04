import java.util.List;

public interface Evaluatable {
	int precedenceComparedTo(Evaluatable other);
	boolean evaluate(List<Evaluatable> evaluatedList);
	void addToEvaluater(List<Evaluatable> evaluatedList, 
					List<Evaluatable> pendingList);
}
