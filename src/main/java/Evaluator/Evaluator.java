public abstract class Evaluator {
	ExpressionTree expressionTree;
	
	// get the expression tree from the context.
	public float evaluate(ExpressionTree expressionTree){
		if (expressionTree == null)
			throw new NullPointerException(
				"An ExpressionTree is required.");
		this.expressionTree = expressionTree;
		return evaluate();
	}
	
	protected abstract float evaluate();
}
