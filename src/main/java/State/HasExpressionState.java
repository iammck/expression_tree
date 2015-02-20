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
	
	public void setTreeOrder(ExpressionTreeContext context, String arg) throws ExpressionTreeException{
		ExpressionTreeContext.TreeOrder treeOrder = null;
		try {
			treeOrder = Enum.valueOf(
					ExpressionTreeContext.TreeOrder.class, arg);
		} catch (Exception e){
			throw new InvalidInputException("The tree order was not valid.");
		}
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
