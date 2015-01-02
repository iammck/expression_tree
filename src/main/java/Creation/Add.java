public class Add extends Operator {
		
	public boolean interpret(List<Symbol> parseArray){
		// for each accumulated operator
		for (int index = accumOperators.size() - 1; index >= 0; index++){
			Symbol lastOp = accumOperators.get(index);
			if (hasPrecedenceOver(lastOp)){
				if (lastOp.interpret(parseArray)){
					accumOperators.remove(lastOp);
				}
			}
		}		
		if (accumOperators.isEmpty()){
			// if not enough symbols in parse array, need to wait.
			if ( parseArray.size() < 2){
				return false;
			} else {
				rightItem = parseArray.remove(parseArray.size() - 1);
				leftItem = parseArray.remove(parseArray.size() - 1);
				parseArray.add(this);
				return true;
			}
		}	
	}
	
}
