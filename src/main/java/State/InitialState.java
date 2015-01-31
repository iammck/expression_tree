public class InitialState implements State {
	public void print(ExpressionTreeContext context, String arg){
		throw new InvalidStateException("There is no expression to print!");
	}
	
	public void quit(ExpressionTreeContext context, String arg){
		context.pleaseQuit();
	}
	
	public void evaluate(ExpressionTreeContext context, String arg){
		throw new InvalidStateException("There is no expression to evaluate!");		
	}
	
	public void setExpression(ExpressionTreeContext context, String arg){
		throw new InvalidStateException("You must specify an input format!");
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
