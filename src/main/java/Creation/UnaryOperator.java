public class UnaryOperator extends Symbol {
	Symbol rightItem;
	
	/*
	 * If there is a symbols in the parseArray, this instance will use it
	 * as the right child item. It will then be placed in the parse
	 * array. if the parse array does not have any symbols then will return false.
	 */	
	public boolean interpret(List<Symbol> parseArray){
		// if not enough symbols in parse array, need to wait.
		if ( parseArray.size() < 1){
			return false;
		} else { // else grab last parsedArray item then add this to parseArray
			rightItem = parseArray.remove(parseArray.size() - 1);
			parseArray.add(this);
			return true;
		}
	}
}
