import java.util.List;

public abstract class UnaryOperator extends Operator {
	protected Symbol rightSymbol;
	
	/*
	 * If there is a symbols in the parseArray, this instance will use it
	 * as the right child item. It will then be placed in the parse
	 * array. if the parse array does not have any symbols then will return false.
	 */	
	public boolean interpret(List<Symbol> parsedSymbols){
		// if not enough symbols in parse array, need to wait.
		if ( parsedSymbols.size() < 1){
			return false;
		} else { // else grab last parsedSymbols item then add this to parseArray
			rightSymbol = parsedSymbols.remove(parsedSymbols.size() - 1);
			parsedSymbols.add(this);
			// returning true
			return true;
		}
	}
	
	/*
	 * Adds this operator instance to the accumulated operators.
	 *
	 */
	public void addToSymbols(List<Symbol> interpretedSymbols, List<Symbol> accumSymbols){		
				
		// put this on accumOperators.
		accumSymbols.add(this);
	}
	
	
	// used for testing.
	public Symbol getRightSymbol(){
		return rightSymbol;
	}
}
