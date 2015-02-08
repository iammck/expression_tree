public class ExpressionBuilder implements ComponentNodeVisitor {
	StringBuilder result;
	
	public ExpressionBuilder( ExpressionTree expressionTree){
		result = new StringBuilder();
		for(ComponentNode node: expressionTree){
			node.accept(this);	
		}
	}
	
	public String build(){
		return result.toString().trim();
	}
	
	public void visit(ComponentNode responder){
			result.append(responder.getItem());
			result.append(" ");
	}	
}
