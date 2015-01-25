import java.util.List;

public abstract class BinaryOperator extends Operator {	
	protected Symbol leftSymbol;
	protected Symbol rightSymbol;
	
	/*
	 * If there are atleast two symbols in the parseArray, this instance will use
	 * them as the right and left child nodes. It will then be placed in the parse
	 * array. if the parse array does not have two symbols then will return false.
	 */	
	public boolean interpret(List<Symbol> interpretedSymbols){
		// if not enough symbols in parse array, need to wait.
		if ( interpretedSymbols.size() < 2){
			return false;
		} else { // else grab last two digits, add this to parse array
			// order matters.
			rightSymbol = interpretedSymbols
				.remove(interpretedSymbols.size() - 1);
			leftSymbol = interpretedSymbols
				.remove(interpretedSymbols.size() - 1);
			interpretedSymbols.add(this);
			return true;
		}
	}
	
	/*
	 * Adds this operator instance to the accumulated operators list just after
	 * attepting to interprete any preveous operaton of equal or less precedence.
	 *
	 */
	public void addToSymbols(List<Symbol> interpretedSymbols, List<Symbol> accumSymbols){		
		// while there are accumulated opperators
		while ( accumSymbols.size() > 0){
			// get last opperation using an index.
			int index = accumSymbols.size() - 1;
			Symbol lastOp = accumSymbols.get(index);
			// if this has less or equal 
			// precedence over last op, interprete.
			if (comparedToSymbol(lastOp) < 1){
				if (lastOp.interpret(interpretedSymbols)){
					// remove it from the accumulated operators.
					accumSymbols.remove(lastOp);
				
				} else { // else can not interprete. break for loop.
					break;
				}
			} else { // else this has the greater precedence. break for loop.
				break;
			}			
		}		
		// put this on accumOperators.
		accumSymbols.add(this);
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
