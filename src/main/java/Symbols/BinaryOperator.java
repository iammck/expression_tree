import java.util.List;

public abstract class BinaryOperator extends Operator {	
	protected Symbol leftSymbol;
	protected Symbol rightSymbol;
	
	/*
	 * If there are atleast two symbols in the parseArray, this instance will use
	 * them as the right and left child nodes. It will then be placed in the parse
	 * array. if the parse array does not have two symbols then will return false.
	 */	
	public boolean interpret(List<Symbol> interpretedList){
		// if not enough symbols in parse array, need to wait.
		if ( interpretedList.size() < 2){
			return false;
		} else { // else grab last two digits, add this to parse array
			// order matters.
			rightSymbol = interpretedList
				.remove(interpretedList.size() - 1);
			leftSymbol = interpretedList
				.remove(interpretedList.size() - 1);
			interpretedList.add(this);
			return true;
		}
	}
	
	/*
	 * Adds this operator instance to the pendingList  after attepting to 
	 * interprete preveous symbols of equal or less precedence. 
	 *
	 */
	public void addToInterpreter(List<Symbol> interpretedList, List<Symbol> pendingList){		
		// while there are pendingList items
		while ( pendingList.size() > 0){
			// get last symbol using an index.
			int index = pendingList.size() - 1;
			Symbol lastSymbol = pendingList.get(index);
			// if this has less or equal 
			// precedence over last symbol, interprete.
			if (precedenceComparedToSymbol(lastSymbol) < 1){
				if (lastSymbol.interpret(interpretedList)){
					// remove it from pendingList.
					pendingList.remove(lastSymbol);
				
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
