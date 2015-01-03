public class DivideCompositeBinaryNode extends CompositeBinaryNode {
		
	public DivideCompositeBinaryNode(ComponentNode rightNode, ComponentNode leftNode){
		super(rightNode, leftNode);
	}
	
	public int getItem(){
		return '/';
	}
}
