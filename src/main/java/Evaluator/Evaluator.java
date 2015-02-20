public abstract class Evaluator {
	ExpressionTree expressionTree;
	ExpressionTreeContext context;
	
	// get the expression tree from the context.
	public ExpressionTree evaluate(ExpressionTreeContext context, 
					ExpressionTree expressionTree)
					throws InvalidInputException{
		if (context == null || expressionTree == null)
			throw new InvalidInputException(
				"An ExpressionTree is required.");
		this.expressionTree = expressionTree;
		this.context = context;
		return evaluate();
	}
	
	protected abstract ExpressionTree evaluate()throws InvalidInputException;
}
