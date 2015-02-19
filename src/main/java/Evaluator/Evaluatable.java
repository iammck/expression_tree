import java.util.List;

public interface Evaluatable {
	int precedenceComparedTo(Evaluatable other);
	
	boolean evaluate(	List<Evaluatable> evaluatedList)
				throws InvalidInputException;
	
	void addToEvaluator(	List<Evaluatable> evaluatedList, 
				List<Evaluatable> pendingList)
				throws InvalidInputException;
}
