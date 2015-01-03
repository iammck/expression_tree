public class PlusCompositeBinaryNode extends CompositeBinaryNode {
		
	public PlusCompositeBinaryNode(ComponentNode rightNode, ComponentNode leftNode){
		super(rightNode, leftNode);
	}
	
	public int getItem(){
		return '+';
	}
}
