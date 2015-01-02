public abstract class Operator extends Symbol {	
	Symbol leftItem;
	Symbol rightItem;
	public abstract hasLessOr
	/*
	 * Adds this operator instance to the accumulated operators list just after
	 * attepting to interprete any preveous operaton of equal or less precedence.
	 *
	 */
	public void addToAccumOperators(List<Symbol> accumOperators){		
		// while there are accumulated opperators
		while ( accumOperators.size() > 0){
			// get last opperation using an index.
			int index = accumOperators.size() - 1;
			Symbol lastOp = accumOperators.get(index);
			// if this has less or equal precedence over last op, interprete.
			if (hasLessOrEqualPrecedenceOver(lastOp)){
				if (lastOp.interpret(parseArray)){
					// remove it from the accumulated operators.
					accumOperators.remove(lastOp);
				
				} else { // else can not interprete. break for loop.
					break;
				}
			} else { // else this has the greater precedence. break for loop.
				break;
			}			
		}		
		// put this on accumOperators.
		accumOperators.add(this);
	}
	
	/*
	 * If there are atleast two symbols in the parseArray, this instance will use
	 * them as the right and left child nodes. It will then be placed in the parse
	 * array. if the parse array does not have two symbols then will return false.
	 */	
	public boolean interpret(List<Symbol> parseArray){
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
