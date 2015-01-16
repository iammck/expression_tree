public class Subtraction extends BinaryOperator {
	
	public ComponentNode build(){
		return new SubtractionCompositeBinaryNode( 
			leftSymbol.build(), rightSymbol.build());
	}
	
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		if ((otherSymbol instanceof Number)){
				return false;
		}
		return true;
	}
}
