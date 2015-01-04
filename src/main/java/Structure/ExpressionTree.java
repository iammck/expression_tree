public class ExpressionTree {
	
	ComponentNode root;
	
	public ExpressionTree(ComponentNode root){
		this.root = root;
	}
	
	public String getItem(){
		return root.getItem();
	}
	
	ComponentNode getRightChild(){
		return root.getRightChild();
	}
	
	ComponentNode getLeftChild(){
		return root.getLeftChild();
	}
}
