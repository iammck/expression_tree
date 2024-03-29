public class Division extends BinaryOperator {
	public ComponentNode build(){
		return new DivisionCompositeBinaryNode( 
			leftSymbol.build(), rightSymbol.build());
	}
	
	public int precedenceComparedTo(Symbol otherSymbol){
		if ((otherSymbol instanceof Multiplication)
			|| (otherSymbol instanceof Division)){
			return 0;
		} else if ( (otherSymbol instanceof Parenthesis)
			|| (otherSymbol instanceof Negation)
			|| (otherSymbol instanceof Exponential)){
			return -1;
		} else {
			return 1;
		}
	}
	
		protected Number operate(Number firstParam, Number secondParam){
		double value = firstParam.toDouble() / secondParam.toDouble();
		return new Number(String.valueOf(value));
	}
	
}
