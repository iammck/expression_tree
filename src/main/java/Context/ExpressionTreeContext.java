public class ExpressionTreeContext {
	
	public enum TreeOrder {
		preorder, inorder, postorder,
		prefix, infix, postfix 		};
	
	public enum InputFormat {
		prefix, infix, postfix 		};
		
	TreeOrder currentTreeOrder;
	InputFormat currentInputFormat;
	State currentState;
	ExpressionTree currentExpTree;
	
	public ExpressionTreeContext(){
		currentState = new InitialState();
	}
	
	public void setCurrentState(State state){
		this.currentState = state;
	}
	
	public State getCurrentState(){
		return this.currentState;
	}
	
	public void setExpression(String arg){
		currentState.setExpression(this, arg);
	}
	
	public void setInputFormat(String arg){
		currentState.setInputFormat(this, arg);	
	}
	
	public void evaluate(String arg){
		currentState.evaluate(this, arg);
	}
	
	
	public void print(String arg){
		currentState.print(this,arg);
	}
	
	
	public void setTreeOrder(String arg){
		currentState.setTreeOrder(this, arg);
	}

	public void quit(String arg){
		currentState.quit(this,arg);		
	}
	
	
	///////
	///////
	///////
	
	public void setCurrentInputFormat(String arg){
		currentInputFormat =
			Enum.valueOf(InputFormat.class, arg);
	}
	
	public void setCurrentTreeOrder(String arg){
		currentTreeOrder = Enum
			.valueOf(TreeOrder.class, arg);
	}
	
	public TreeOrder getCurrentTreeOrder(){
		return currentTreeOrder;
	}	
	
	public void setCurrentExpression(String expr){
		Interpreter interpreter = getInterpreter();
		currentExpTree = interpreter.interpret(this, expr);
	}
	
	private Interpreter getInterpreter(){
		switch (currentInputFormat){
		case prefix:
			return new PrefixInterpreter();
		case postfix:
			return new PostfixInterpreter();
		default:
			return new InfixInterpreter();
		}
	}

	public void evaluateCurrentExpressionTree(){
		System.out.println("evaluated expression");	
	}
	
	public void printCurrentExpressionTree(){
		System.out.println("printed expression");
	}
	
	public void pleaseQuit(){
		System.out.println("Quit.");
	}
}
