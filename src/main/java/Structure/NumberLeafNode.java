public class NumberLeafNode extends LeafNode {
	public NumberLeafNode(String item){
		super(item);
	}	

	public int compareToNode(ComponentNode node){
		// equals to other leaf nodes
		if ((node instanceof NumberLeafNode)){
			return 0;
		}
		// Is less than any other node.
		return -1;
	}
}
