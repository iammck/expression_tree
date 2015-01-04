public class MultiplicationCompositeBinaryNode extends CompositeBinaryNode {
		
	public MultiplicationCompositeBinaryNode(ComponentNode leftNode, ComponentNode rightNode){
		super(leftNode, rightNode);
	}
	
	public String getItem(){
		return "*";
	}
}
