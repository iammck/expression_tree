import java.util.List;

public abstract class BinaryOperator extends Operator {	
	protected Symbol leftSymbol;
	protected Symbol rightSymbol;
	
	/*
	 * If there are atleast two symbols in the interpretedList, this instance will use
	 * them as the right and left child nodes. It will then be placed in the list
	 * If the interpretedList does not have two symbols then will return false.
	 */	
	public boolean interpret(List<Interpretable> interpretedList){
		// if not enough symbols in parse array, need to wait.
		if ( interpretedList.size() < 2){
			return false;
		} else { // else grab last two digits, add this to parse array
			// order matters.
			rightSymbol = (Symbol) interpretedList
				.remove(interpretedList.size() - 1);
			leftSymbol = (Symbol) interpretedList
				.remove(interpretedList.size() - 1);
			interpretedList.add(this);
			return true;
		}
	}
	
	/*
	 * If there are atleast two symbols in the evaluatedList, this instance will use them
	 * as paremeters when performing the operation. The ans will be placed in the list
	 * If the evaluatedList does not have two symbols then will return false.
	 */	
	public boolean evaluate(List<Evaluatable> evaluatedList){
		// if not enough symbols in parse array, need to wait.
		if ( evaluatedList.size() < 2){
			return false;
		} else { // else grab last two digits, operate
			// order matters.
			Number secondParam = (Number) evaluatedList
				.remove(evaluatedList.size() - 1);
			Number firstParam  = (Number) evaluatedList
				.remove(evaluatedList.size() - 1);
			Evaluatable result = this.operate(firstParam, secondParam);
			evaluatedList.add(result);
			return true;
		}
	}
	
	/*
	 * Adds this operator instance to the pendingList  after attepting to 
	 * interprete preveous symbols of equal or less precedence. 
	 *
	 */
	public void addToInterpreter(List<Interpretable> interpretedList, List<Interpretable> pendingList){		
		// while there are pendingList items
		while ( pendingList.size() > 0){
			// get last symbol using an index.
			int index = pendingList.size() - 1;
			Interpretable lastInterpretable = pendingList.get(index);
			// if this has less or equal 
			// precedence over last symbol, interprete.
			if (precedenceComparedTo(lastInterpretable) < 1){
				// if we are able to interprete
				if (lastInterpretable.interpret(interpretedList)){
					// remove it from pendingList.
					pendingList.remove(lastInterpretable);
				
				} else { // else can not interprete. break for loop.
					break;
				}
			} else { // else this has the greater precedence. break for loop.
				break;
			}			
		}		
		// put this in pendingList.
		pendingList.add(this);
	}
	
	/*
	 * Adds this binary operator instance to the pendingList  after attepting to 
	 * interprete preveous symbols of equal or less precedence. 
	 *
	 */
	public void addToEvaluator(List<Evaluatable> evaluatedList, 
		List<Evaluatable> pendingList) throws InvalidInputException{		
		
		// while there are pendingList items
		while ( pendingList.size() > 0){
			// get last Evaluatable using an index.
			int index = pendingList.size() - 1;
			Evaluatable lastEvaluatable = pendingList.get(index);
			// if this has less or equal
			// precedence over last, evaluate the last.
			if (precedenceComparedTo(lastEvaluatable) < 1){
				if (lastEvaluatable.evaluate(evaluatedList)){
					// remove it from pendingList.
					pendingList.remove(lastEvaluatable);
				
				} else { // else can not evaluate. break for loop.
					break;
				}
			} else { // else this has the greater precedence. break for loop.
				break;
			}			
		}		
		// put this in pendingList.
		pendingList.add(this);
	}
	
	
	// used for testing
	public Symbol getLeftSymbol(){
		return leftSymbol;
	}
	
	// used for testing.
	public Symbol getRightSymbol(){
		return rightSymbol;
	}
	
	public void swapLeftRightSymbols(){
		Symbol temp = leftSymbol;
		leftSymbol = rightSymbol;
		rightSymbol = temp;
	}
}
