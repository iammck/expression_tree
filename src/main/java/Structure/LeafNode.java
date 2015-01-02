public class LeafNode implements ComponentNode {
	
	private int item;
	
	public LeafNode(int item){
		this.item = item;
	}
	
	public int getItem(){
		return item;	
	}
	
	ComponentNode getLeftChild(){
		return null;
	}
	
	ComponentNode getRightChild(){
		return null;
	}
}
