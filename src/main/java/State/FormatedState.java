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
	
	public void setInputFormat(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		context.setCurrentInputFormat(arg);
		context.setCurrentTreeOrder(arg);
	}
	
	public void setTreeOrder(ExpressionTreeContext context, String arg)throws ExpressionTreeException{
		context.setCurrentTreeOrder(arg);		
	}
	
	public void setExpression(ExpressionTreeContext context, String arg)throws ExpressionTreeException{
		// problems should throw errors. should this be here in this state or in Context?
		context.setCurrentExpression(arg);
		// if the expression state is evaluatable
		ExpressionTreeContext.TreeOrder treeOrder 
						= context.getCurrentTreeOrder();
		switch (treeOrder) {
		case infix:
		case prefix:
		case postfix:
			context.setCurrentState(new HasExpressionState());
			break;
		
		default: // else one that is not evaluatable
			context.setCurrentState(new HasUnevaluatableExpressionState());
			break;
		}
	}
}
