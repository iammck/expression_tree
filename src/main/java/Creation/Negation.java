public class Negation extends UnaryOperator {
	
	public ComponentNode build(){
		return new NegationCompositeUnaryNode( rightSymbol.build());
	}
	
	public int comparedToSymbol(Symbol otherSymbol){
		if (otherSymbol instanceof Parenthesis){
				return -1;
		} else if (otherSymbol instanceof Negation){
			return 0;
		} else {
			return 1;
		}
	}
}
