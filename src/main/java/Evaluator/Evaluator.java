public abstract class Evaluator {
	ExpressionTree expressionTree;
	
	// get the expression tree from the context.
	public Double evaluate(ExpressionTree expressionTree)
					throws InvalidInputException{
		if (expressionTree == null)
			throw new InvalidInputException(
				"An ExpressionTree is required.");
		this.expressionTree = expressionTree;
		return evaluate();
	}
	
	protected abstract Double evaluate()throws InvalidInputException;
}
