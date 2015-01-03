public class Negate extends UnaryOperator {
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		String otherSymbol = otherSymbol.getSymbol;
		// if parenthesis or negaite
		if ( otherSymbol instanceof Parenthesis
			|| otherSymbol instanceof Negate ){
			return true;
		} else {
			return false;
		}
	}
}
