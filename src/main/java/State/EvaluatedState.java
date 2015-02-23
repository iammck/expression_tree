public class EvaluatedState implements State {
	
	public void printExpressionTree(ExpressionTreeContext context, String traversalOrder){
		context.printCurrentExpressionTree();
	}
	
	public void evaluate(ExpressionTreeContext context, String traversalOrder) throws InvalidInputException{
		context.evaluateCurrentExpressionTree(traversalOrder);		
	}
		
	public void quit(ExpressionTreeContext context, String arg){
		context.handleQuit();
	}
	
	public void reset(ExpressionTreeContext context, String arg){
		context.handleReset();
		context.setCurrentState(new InitialState());
	}
		
	public void setFormat(ExpressionTreeContext context, String arg)throws ExpressionTreeException{
		context.setCurrentFormat(arg);
	}
	
	public void setExpression(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		context.setCurrentExpression(arg);
		context.setCurrentState(new HasExpressionState());
	}	
}
