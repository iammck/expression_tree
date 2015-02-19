public class HasUnevaluatableExpressionState implements State{

	public void evaluate(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		throw new InvalidStateException("The expression is not evaluatable!");		
	}

	public void printExpressionTree(ExpressionTreeContext context, String arg){
		context.printCurrentExpressionTree();
	}
	
	public void quit(ExpressionTreeContext context, String arg){
		context.handleQuit();
	}
	
	public void reset(ExpressionTreeContext context, String arg){
		context.handleReset();
		context.setCurrentState(new InitialState());
	}
		
	public void setInputFormat(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		context.setCurrentInputFormat(arg);
	}
	
	public void setTreeOrder(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		// if the expression state is evaluatable
		ExpressionTreeContext.TreeOrder treeOrder = Enum.valueOf(
				ExpressionTreeContext.TreeOrder.class, arg);
		switch (treeOrder) {
		case infix:
		case prefix:
		case postfix:
			context.setCurrentState(new HasExpressionState());
			break;
		default: // else one that is not evaluatable
			context.setCurrentState(new HasUnevaluatableExpressionState());
		}
		context.setCurrentTreeOrder(arg);
	}
	
	public void setExpression(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
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
		}
	}
}
