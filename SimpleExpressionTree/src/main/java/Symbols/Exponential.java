import java.lang.Math;

public class Exponential extends BinaryOperator{
	
	public ComponentNode build(){
		return new ExponentialCompositeBinaryNode( 
			leftSymbol.build(), rightSymbol.build());
	}
	
	public int precedenceComparedTo(Symbol otherSymbol){
		if (otherSymbol instanceof Exponential){
			return 0;
		} else if ( (otherSymbol instanceof Parenthesis)
			|| (otherSymbol instanceof Negation)){
			return -1;
		} else {
			return 1;
		}
	}
	
	protected Number operate(Number firstParam, Number secondParam){
		double value = Math.pow(firstParam.toDouble(), secondParam.toDouble());
		return new Number(String.valueOf(value));
	}
}
