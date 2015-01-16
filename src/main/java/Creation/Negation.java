public class Negation extends UnaryOperator {
	
	public ComponentNode build(){
		return new NegationCompositeUnaryNode( rightSymbol.build());
	}
	
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		// if parenthesis or negaite
		if ( otherSymbol instanceof Parenthesis
			|| otherSymbol instanceof Negation ){
			return true;
		} else {
			return false;
		}
	}
}
