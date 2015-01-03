public class Multiply extends BinaryOperator {
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		if ( (otherSymbol instanceof Parenthesis)
			|| (otherSymbol instanceof Negate)
			|| (otherSymbol instanceof Multiply)
			|| (otherSymbol instanceof Divide)){
			return true;
		}
		return false;
	}
}
