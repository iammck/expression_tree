public class HasExpressionState implements State {
	
	public void printExpressionTree(ExpressionTreeContext context, String treeOrder){
		context.printCurrentExpressionTree();
	}
	
	public void evaluate(ExpressionTreeContext context, String order) throws InvalidInputException{
		context.evaluateCurrentExpressionTree();
		context.setCurrentState(new EvaluatedState());
	}
		
	public void quit(ExpressionTreeContext context, String arg){
		context.handleQuit();
	}
	
	public void reset(ExpressionTreeContext context, String arg){
		context.handleReset();
		context.setCurrentState(new InitialState());
	}
		
	public void setInputFormat(ExpressionTreeContext context, String arg)throws ExpressionTreeException{
		context.setCurrentInputFormat(arg);
	}
	
	public void setExpression(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		context.setCurrentExpression(arg);
	}	
}
