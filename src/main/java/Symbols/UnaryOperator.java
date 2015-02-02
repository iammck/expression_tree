import java.util.List;

public abstract class UnaryOperator extends Operator {
	protected Symbol rightSymbol;	
	/*
	 * If there is a symbols in the interpretedList, this instance will use it
	 * as the right child item. It will then be placed in the interpretedList
	 * array. if the interpretedList does not have any symbols then will return false.
	 */	
	public boolean interpret(List<Symbol> interpretedList){
		// if not enough symbols in parse array, need to wait.
		if ( interpretedList.size() < 1){
			return false;
		} else { // else grab last interpretedList item then add this to the list.
			rightSymbol = interpretedList.remove(interpretedList.size() - 1);
			interpretedList.add(this);
			// returning true
			return true;
		}
	}
	
	/*
	 * Adds this operator instance to the pendingList.
	 */
	public void addToInterpreter(List<Symbol> interpretedList, 
					      List<Symbol> pendingList){		
		pendingList.add(this);
	}
	
	// used for testing.
	public Symbol getRightSymbol(){
		return rightSymbol;
	}
}
