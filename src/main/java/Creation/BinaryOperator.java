import java.util.List;

public abstract class BinaryOperator extends Operator {	
	protected Symbol leftSymbol;
	protected Symbol rightSymbol;
	
	/*
	 * If there are atleast two symbols in the parseArray, this instance will use
	 * them as the right and left child nodes. It will then be placed in the parse
	 * array. if the parse array does not have two symbols then will return false.
	 */	
	public boolean interpret(List<Symbol> parsedSymbols){
		// if not enough symbols in parse array, need to wait.
		if ( parsedSymbols.size() < 2){
			return false;
		} else { // else grab last two digits, add this to parse array
			rightSymbol = parsedSymbols
				.remove(parsedSymbols.size() - 1);
			leftSymbol = parsedSymbols
				.remove(parsedSymbols.size() - 1);
			parsedSymbols.add(this);
			return true;
		}
	}
	
	// used for testing
	public Symbol getLeftSymbol(){
		return leftSymbol;
	}
	
	// used for testing.
	public Symbol getRightSymbol(){
		return rightSymbol;
	}
}
