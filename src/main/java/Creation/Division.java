public class Division extends BinaryOperator {
	public ComponentNode build(){
		return new DivisionCompositeBinaryNode( 
			leftSymbol.build(), rightSymbol.build());
	}
	
	public int comparedToSymbol(Symbol otherSymbol){
		if ((otherSymbol instanceof Multiplication)
			|| (otherSymbol instanceof Division)){
			return 0;
		} else if ( (otherSymbol instanceof Parenthesis)
			|| (otherSymbol instanceof Negation)){
			return -1;
		} else {
			return 1;
		}
	}
	
}
