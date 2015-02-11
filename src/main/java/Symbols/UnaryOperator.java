import java.util.List;

public abstract class UnaryOperator extends Operator {
	protected Symbol rightSymbol;	
	/*
	 * If there is a symbols in the interpretedList, this instance will use it
	 * as the right child item. It will then be placed in the interpretedList
	 * array. if the interpretedList does not have any symbols then will return false.
	 */	
	public boolean interpret(List<Interpretable> interpretedList){
		// if not enough symbols in list, need to wait.
		if ( interpretedList.size() < 1){
			return false;
		} else { // else grab last interpretedList item then add this to the list.
			rightSymbol 
				= (Symbol) interpretedList.remove(interpretedList.size() - 1);
			interpretedList.add(this);
			// returning true
			return true;
		}
	}
	
	/*
	 * If there is an evaluatable in the interpretedList, operate will be called on
	 * the evaluatable instance. It will then be placed last in the interpretedList
	 * If the interpretedList does not have any symbols then will return false.
	 */	
	public boolean evaluate(List<Evaluatable> evaluatedList){
		// if not enough evaluatables in list, need to wait.
		if ( evaluatedList.size() < 1){
			return false;
		} else { // else grab last from evaluatedList cast as a Number. 
			Number param = (Number) evaluatedList.remove(evaluatedList.size() - 1);
			// add the result of operation to list.
			Number result = this.operate(null, param);
			evaluatedList.add(result);
			// returning true
			return true;
		}
	}
	
	/*
	 * Adds this operator instance to the pendingList.
	 */
	public void addToInterpreter(List<Interpretable> interpretedList, 
					      List<Interpretable> pendingList){		
		pendingList.add(this);
	}
	
	/*
	 * Adds this operator instance to the pendingList.
	 */
	public void addToEvaluator(List<Evaluatable> interpretedList, 
					      List<Evaluatable> pendingList){		
		pendingList.add(this);
	}
	
	// used for testing.
	public Interpretable getRightSymbol(){
		return rightSymbol;
	}
}
