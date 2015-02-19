public interface State {
	void printExpressionTree(ExpressionTreeContext context, String arg)
		throws ExpressionTreeException;
	void evaluate(ExpressionTreeContext context, String arg)
		throws ExpressionTreeException;
	void setExpression(ExpressionTreeContext context, String arg)
		throws ExpressionTreeException;
	void setInputFormat(ExpressionTreeContext context, String arg)
		throws ExpressionTreeException;
	void setTreeOrder(ExpressionTreeContext context, String arg)
		throws ExpressionTreeException;

	void quit(ExpressionTreeContext context, String arg);
	void reset(ExpressionTreeContext context, String arg);

}
