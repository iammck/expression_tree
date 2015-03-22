import java.util.List;

public class Parenthesis extends Symbol{
	private String symbol;
	
	public Parenthesis(String symbol){
		this.symbol = symbol;
	}
	
	public ComponentNode build(){
		return null;
	}
	
	public boolean interpret(List<Interpretable> interpretedList){
		return false;
	}
	
	public boolean evaluate(List<Evaluatable> interpretedList){
		return false;
	}

	
	/*
	 * If a '(', will add itself to pendingList. Otherwise, it is a ')' so iterate through
	 * pendingList calling interpret() then remove matching '(' from pendingList.
	 */	 
	public void addToInterpreter(
		List<Interpretable> interpretedList, 
		List<Interpretable> pendingList)
		throws InvalidInputException{		
		// if symbol is (, put into pendingList
		if (symbol.equals("(")){ // here!
			pendingList.add(this);
			return;
		} // Else, this must be a ')'
		// if pendingList is empty, this ) is missing a (
		if (pendingList.size() == 0){
			throw new InvalidInputException(
				"this ) is missing a (" );
		}
		// while there are pendingList items and the last is not (
		Interpretable lastInterpretable = pendingList.get(pendingList.size() - 1);
		while (lastInterpretable.interpret(interpretedList)){
			// remove it from the accumulated operators.
			pendingList.remove(lastInterpretable);
			// if out of symbols, this ) is missing a (
			if (pendingList.size() == 0){
				throw new InvalidInputException(
					"this ) is missing a (");
			}
			// get the next symbol
			lastInterpretable = pendingList.get(pendingList.size() - 1);
		}
		// if the last symbol is a (, then remove it.
		if ( lastInterpretable instanceof Parenthesis){
			if( ((Parenthesis) lastInterpretable).symbol.equals("("))
				pendingList.remove(lastInterpretable);
		}
	}
	
	
	
	
	/*
	 * If a '(', will add itself to pendingList. Otherwise, it is a ')' so iterate through
	 * pendingList calling evaluate() then remove matching '(' from pendingList.
	 */	 
	public void addToEvaluator(
		List<Evaluatable> evaluatedList, 
		List<Evaluatable> pendingList)
		throws InvalidInputException{		
		// if symbol is (, put into pendingList
		if (symbol.equals("(")){ 
			pendingList.add(this);
			return;
		}
		// if pendingList is empty, this ) is missing a (
		if (pendingList.size() == 0){
			throw new InvalidInputException(
				"this ) is missing a (" );
		}
		// while there are pendingList items and the last is not (
		Evaluatable lastEvaluatable = pendingList.get(pendingList.size() - 1);
		while (lastEvaluatable.evaluate(evaluatedList)){
			// remove it from the accumulated operators.
			pendingList.remove(lastEvaluatable);
			// if out of symbols, this ) is missing a (
			if (pendingList.size() == 0){
				throw new InvalidInputException(
					"this ) is missing a (");
			}
			// get the next evaluatable
			lastEvaluatable = pendingList.get(pendingList.size() - 1);
		}
		// if the last symbol is a (, then remove it.
		if ( lastEvaluatable instanceof Parenthesis){
			if( ((Parenthesis) lastEvaluatable).symbol.equals("("))
				pendingList.remove(lastEvaluatable);
		}
	}
	
	
	
		
	public int precedenceComparedTo(Symbol otherSymbol){
		if (otherSymbol instanceof Parenthesis){
			return 0;
		} else {
			return 1;
		}
	}
}
