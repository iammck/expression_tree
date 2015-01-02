public class Multiply extends Operator {
	public boolean hasLessOrEqualPrecedenceOver(Operator otherOperator){
		String otherSymbol = otherOperator.getSymbol;
		if (otherSymbol.equals("*") || otherSymbol.equals("/")
			|| otherSymbol.equals("+") || otherSymbol.equals("-"){
				return true;
		} else {
			return false;
		}
	}
}
