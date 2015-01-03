public class Subtract extends BinaryOperator {
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		if ( (otherSymbol instanceof Add)
			|| (otherSymbol instanceof Subtract)){
				return true;
		}
		return false;
	}
}
