public interface State {
	void printExpressionTree(ExpressionTreeContext context, String arg);
	void quit(ExpressionTreeContext context, String arg);
	void reset(ExpressionTreeContext context, String arg);
	void evaluate(ExpressionTreeContext context, String arg);
	void setExpression(ExpressionTreeContext context, String arg);
	void setInputFormat(ExpressionTreeContext context, String arg);
	void setTreeOrder(ExpressionTreeContext context, String arg);
}
