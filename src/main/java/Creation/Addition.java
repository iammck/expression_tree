public class Addition extends BinaryOperator {	
	public ComponentNode build(){
		return new AdditionCompositeBinaryNode( 
			leftItem.build(), rightItem.build());
	}
	
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		if ((otherSymbol instanceof Number)){
				return false;
		}
		return true;
	}
}
