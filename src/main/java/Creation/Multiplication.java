public class Multiplication extends BinaryOperator {
	
	public ComponentNode build(){
		return new MultiplicationCompositeBinaryNode( 
			leftSymbol.build(), rightSymbol.build());
	}
	
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		if ( (otherSymbol instanceof Parenthesis)
			|| (otherSymbol instanceof Negation)
			|| (otherSymbol instanceof Multiplication)
			|| (otherSymbol instanceof Division)){
			return true;
		}
		return false;
	}
}
