public class Multiplication extends BinaryOperator {
	
	public ComponentNode build(){
		return new MultiplicationCompositeBinaryNode( 
			leftSymbol.build(), rightSymbol.build());
	}
	
	public int precedenceComparedToSymbol(Symbol otherSymbol){
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
