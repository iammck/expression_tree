public class HasExpressionState implements State {
	public void print(ExpressionTreeContext context, String arg){
		context.printCurrentExpressionTree();
	}
	
	public void quit(ExpressionTreeContext context, String arg){
		context.pleaseQuit();
	}
	
	public void evaluate(ExpressionTreeContext context, String arg){
		context.evaluateCurrentExpressionTree();		
	}
	
	public void setExpression(ExpressionTreeContext context, String arg){
		context.setCurrentExpression(arg);
	}
	
	public void setInputFormat(ExpressionTreeContext context, String arg){
		context.setCurrentInputFormat(arg);
	}
	
	public void setTreeOrder(ExpressionTreeContext context, String arg){
		context.setCurrentTreeOrder(arg);					
	}
}
