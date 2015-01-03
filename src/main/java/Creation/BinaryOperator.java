public abstract class BinaryOperator extends Symbol {	
	Symbol leftItem;
	Symbol rightItem;
	
	/*
	 * If there are atleast two symbols in the parseArray, this instance will use
	 * them as the right and left child nodes. It will then be placed in the parse
	 * array. if the parse array does not have two symbols then will return false.
	 */	
	public boolean interpret(List<Symbol> parsedArray){
		// if not enough symbols in parse array, need to wait.
		if ( parseArray.size() < 2){
			return false;
		} else { // else grab last two digits, add this to parse array
			rightItem = parseArray.remove(parseArray.size() - 1);
			leftItem = parseArray.remove(parseArray.size() - 1);
			parseArray.add(this);
			return true;
		}
	}
}
