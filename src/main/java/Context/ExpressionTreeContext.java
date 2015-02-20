public class ExpressionTreeContext {	
	// TreeOrder and InputFormat
	public enum TreeOrder {
		preorder, inorder, postorder,
		prefix, infix, postfix 		};
	public enum InputFormat {
		prefix, infix, postfix 		};
	
	//CONTEXT DATA		
	TreeOrder currentTreeOrder;
	InputFormat currentInputFormat;
	State currentState;
	ExpressionTree currentExpTree;
	CommandFactory commandFactory;
	
	
	//CONSTRUCTOR
	public ExpressionTreeContext(){
		currentState = new InitialState();
		commandFactory = new ConcreteCommandFactory(this);
	}

	///////////
	// STATE //
	///////////
	
	public State getCurrentState(){
		return this.currentState;
	}
	
	public void setCurrentState(State state){
		currentState = state;
	}
	
	public void setExpression(String arg){
		try{
			currentState.setExpression(this, arg);
		} catch (ExpressionTreeException e){
			raiseOutputEvent(e.getMessage());
		}
	}
	
	public void setInputFormat(String arg){
		try{
			currentState.setInputFormat(this, arg);
		} catch (ExpressionTreeException e){
			raiseOutputEvent(e.getMessage());
		}
	}
	
	public void evaluate(String arg){
		try{
			currentState.evaluate(this, arg);
		} catch (ExpressionTreeException e){
			raiseOutputEvent(e.getMessage());
		}
	}	
	
	public void printExpressionTree(String arg){
		try{
			currentState.printExpressionTree(this,arg);
		} catch (ExpressionTreeException e){
			raiseOutputEvent(e.getMessage());
		}
	}	
	
	public void setTreeOrder(String arg){
		try {
			currentState.setTreeOrder(this, arg);
		} catch (ExpressionTreeException e){
			raiseOutputEvent(e.getMessage());
		}
	}

	public void quit(String arg){
		currentState.quit(this,arg);		
	}
	
	public void reset(String arg){
		currentState.reset(this,arg);		
	}
			
	// METHODS USED BY STATE
		
	public void setCurrentInputFormat(String arg) 
				throws InvalidInputException{
		try {
			currentInputFormat =
				Enum.valueOf(InputFormat.class, arg);
		} catch (Exception e){
			throw new InvalidInputException("The input format "
				+ arg + " is not valid.");
		}
	}
	
	public void setCurrentTreeOrder(String arg) 
				throws InvalidInputException{
		try {
			currentTreeOrder = Enum
				.valueOf(TreeOrder.class, arg);
		} catch (Exception e){
			throw new InvalidInputException("The tree order "
				+ arg + " is not valid.");
		}		
	}
	
	public TreeOrder getCurrentTreeOrder(){
		return currentTreeOrder;
	}	
	
	public void setCurrentExpression(String expr)
				throws InvalidInputException{
		Interpreter interpreter = getInterpreter();
		currentExpTree = interpreter.interpret(this, expr);
	}
	
	public ExpressionTree getCurrentExpressionTree(){
		return currentExpTree;
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
	
	public CommandFactory getCommandFactory(){
		return commandFactory;
	}

	public void evaluateCurrentExpressionTree(){
		// Evaluate the expression tree to get an ExpressionTree object.
		// Use an expressionBuilder to build up an expression as a String.
		// send and output event to the reactor.
		
		System.out.println("evaluated expression");	
	}
	
	public void printCurrentExpressionTree(){
		System.out.println("printed expression");
	}
	
	public void handleQuit(){
		Reactor.getInstance().handleEvent("quit", null);
	}
	
	public void handleReset(){
		Reactor.getInstance().handleEvent("reset", null);
	}
	
	private void raiseOutputEvent(String output){
		Reactor.getInstance().handleEvent("output", output);
	}
	
}
