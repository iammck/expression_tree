public class InitialState implements State {
	
	public void printExpressionTree(ExpressionTreeContext context, String arg)throws ExpressionTreeException{
		throw new InvalidStateException("There is no expression to print!");
	}
	
	public void evaluate(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		throw new InvalidStateException("There is no expression to evaluate!");		
	}
	
	public void setExpression(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		throw new InvalidStateException("You must specify an input format first!");
	}
	
	public void quit(ExpressionTreeContext context, String arg){
		context.handleQuit();
	}
	
	public void reset(ExpressionTreeContext context, String arg){
		context.handleReset();
		context.setCurrentState(new InitialState());
	}
	
	public void setFormat(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		context.setCurrentFormat(arg);
		context.setCurrentState(new FormatedState());
	}	
}
