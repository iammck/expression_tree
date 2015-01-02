public class ExpressionTree {
	
	ComponentNode root;
	
	public ExpressionTree(ComponentNode root){
		this.root = root;
	}
	
	int getItem(){
		return root.getItem();
	}
	
	ComponentNode getRightChild(){
		return root.getRightChild();
	}
	
	ComponentNode getLeftChild(){
		return root.getLeftChild();
	}
}
