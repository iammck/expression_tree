public class InitialState implements State {
	public void printExpressionTree(ExpressionTreeContext context, String arg){
		throw new InvalidStateException("There is no expression to print!");
	}
	
	public void quit(ExpressionTreeContext context, String arg){
		System.out.println("Quit!");
	}
	
	public void reset(ExpressionTreeContext context, String arg){
		System.out.println("Reset!");
		context.setCurrentState(new InitialState());
	}
	
	public void evaluate(ExpressionTreeContext context, String arg){
		throw new InvalidStateException("There is no expression to evaluate!");		
	}
	
	public void setExpression(ExpressionTreeContext context, String arg){
		throw new InvalidStateException("You must specify an input format first!");
	}
	
	public void setInputFormat(ExpressionTreeContext context, String arg){
		context.setCurrentInputFormat(arg);
		context.setCurrentTreeOrder(arg);
		context.setCurrentState(new FormatedState());
	}
	
	public void setTreeOrder(ExpressionTreeContext context, String arg){
		context.setCurrentTreeOrder(arg);		
	}
}
