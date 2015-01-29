public class ExpressionTreeContext {
	
	public enum TreeOrder {
		preorder, inorder, postorder,
		prefix, infix, postfix 		};
	
	public enum InputFormat {
		prefix, infix, postfix 		};
		
	TreeOrder currentTreeOrder;
	InputFormat currentInputFormat;
	
	
	public void setExpression(String arg){
		// TODO
	}
	
	public void setInputFormat(String arg){
		currentInputFormat =
			Enum.valueOf(InputFormat.class, arg);
		setTreeOrder(arg);
	}	
	
	public void evaluate(String arg){
		// TODO
	}
	
	public void setTreeOrder(String arg){
		currentTreeOrder = Enum
			.valueOf(TreeOrder.class, arg);
	}
	
	public TreeOrder getCurrentTreeOrder(){
		return currentTreeOrder;
	}
	
	public void print(String arg){
		
	}
	
	public void quit(String arg){
		
	}
}
