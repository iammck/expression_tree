public class FormatedState implements State {
	public void printExpressionTree(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		throw new InvalidStateException("There is no expression to print!");
	}
	
	public void evaluate(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		throw new InvalidStateException("There is no expression to evaluate!");		
	}
	
	public void quit(ExpressionTreeContext context, String arg){
		context.handleQuit();
	}
	
	public void reset(ExpressionTreeContext context, String arg){
		context.setCurrentState(new InitialState());
		context.handleReset();
	}
	
	public void setFormat(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		context.setCurrentFormat(arg);
	}
	
	public void setExpression(ExpressionTreeContext context, String arg)throws ExpressionTreeException{
		// problems should throw errors. should this be here in this state or in Context?
		context.setCurrentExpression(arg);
		context.setCurrentState(new HasExpressionState());
	}
}
