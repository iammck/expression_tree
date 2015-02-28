public class Negation extends UnaryOperator {
	
	public ComponentNode build(){
		return new NegationCompositeUnaryNode( rightSymbol.build());
	}
	
	public int precedenceComparedTo(Symbol otherSymbol){
		if (otherSymbol instanceof Parenthesis){
				return -1;
		} else if (otherSymbol instanceof Negation){
			return 0;
		} else {
			return 1;
		}
	}
	
	protected Number operate(Number firstParam, Number secondParam){
		double value = -1 * secondParam.toDouble();
		return new Number(String.valueOf(value));
	}
}
