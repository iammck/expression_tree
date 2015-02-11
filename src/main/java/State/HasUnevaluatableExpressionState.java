public class HasUnevaluatableExpressionState extends HasExpressionState{

	public void evaluate(ExpressionTreeContext context, String arg){
		throw new InvalidStateException("The expression is not evaluatable!");		
	}

	public void printExpressionTree(ExpressionTreeContext context, String arg){
		context.printCurrentExpressionTree();
	}
	
	public void quit(ExpressionTreeContext context, String arg){
		System.out.println("Quit!");
	}
	
	public void reset(ExpressionTreeContext context, String arg){
		System.out.println("Reset!");
		context.setCurrentState(new InitialState());
	}
		
	public void setInputFormat(ExpressionTreeContext context, String arg){
		context.setCurrentInputFormat(arg);
	}
	
	public void setTreeOrder(ExpressionTreeContext context, String arg){
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
	
	public void setExpression(ExpressionTreeContext context, String arg){
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
