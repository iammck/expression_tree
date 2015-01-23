public interface State {
	void print(ExpressionTreeContext context, String arg);
	void quit(ExpressionTreeContext context, String arg);
	void evaluate(ExpressionTreeContext context, String arg);
	void setExpression(ExpressionTreeContext context, String arg);
	void setFormat(ExpressionTreeContext context, String arg);
}
