public class Addition extends BinaryOperator {	
	
	public ComponentNode build(){
		return new AdditionCompositeBinaryNode( 
			leftSymbol.build(), rightSymbol.build());
	}
	
	public int precedenceComparedToSymbol(Symbol otherSymbol){
		if ((otherSymbol instanceof Number)){
				return +1;
		} else if (( otherSymbol instanceof Addition)
				|| (otherSymbol instanceof Subtraction)){
			return 0;
		} else {
			return -1;
		}
	}
}
