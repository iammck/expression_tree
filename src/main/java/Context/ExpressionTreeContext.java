public class ExpressionTreeContext {	
	
	public enum InputFormat {
		prefix, infix, postfix 		};
	
	//CONTEXT DATA		
	InputFormat currentFormat;
	State currentState;
	ExpressionTree currentExpTree;
	CommandFactory commandFactory;
	boolean hasMacroCommandError;
	
	//CONSTRUCTOR
	public ExpressionTreeContext(){
		currentState = new InitialState();
		commandFactory = new ConcreteCommandFactory(this);
		hasMacroCommandError = false;
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
	
	public void setExpression(String expression){
		try{
			currentState.setExpression(this, expression);
			hasMacroCommandError = false;
		} catch (ExpressionTreeException e){
			hasMacroCommandError = true;
			if (expression != null && expression == ""){
				raiseOutputEvent("Unable to set expression.");
			} else { // null expression ok here. 
				raiseOutputEvent(
					"Unable to set expression to " + expression + ".");
			}
		}
	}
	
	public void setFormat(String arg){
		try{
			currentState.setFormat(this, arg);
			hasMacroCommandError = false;
		} catch (ExpressionTreeException e){
			hasMacroCommandError = true;
			raiseOutputEvent(e.getMessage());
		}
	}
	
	public void evaluate(String arg){
		try{
			currentState.evaluate(this, arg);
			hasMacroCommandError = false;
		} catch (ExpressionTreeException e){
			hasMacroCommandError = true;
			raiseOutputEvent(e.getMessage());
		}
	}	
	
	public void printExpressionTree(String arg){
		try{
			currentState.printExpressionTree(this,arg);
			hasMacroCommandError = false;
		} catch (ExpressionTreeException e){
			hasMacroCommandError = true;
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
		
	public void setCurrentFormat(String arg) 
				throws InvalidInputException{
		try {
			currentFormat =
				Enum.valueOf(InputFormat.class, arg);
		} catch (Exception e){
			throw new InvalidInputException("The input format "
				+ arg + " is not valid.");
		}
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
		switch (currentFormat){
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

	public void evaluateCurrentExpressionTree(String traversalOrder) throws InvalidInputException{
		
		// Evaluate the expression tree to get an ExpressionTree object.
		Evaluator evaluator = getEvaluator(traversalOrder);
		ExpressionTree resultTree;
		resultTree = evaluator.evaluate(this, currentExpTree);
		// Use an expressionBuilder to build up an expression as a String.
		ExpressionBuilder expBuilder;
		expBuilder = new ExpressionBuilder(resultTree);
		forwardEvent("output", expBuilder.build());
	}
	
	private Evaluator getEvaluator(String traversalOrder) throws InvalidInputException{
		// if no order specified, use the format order
		if (traversalOrder == null || traversalOrder.equals("")){
			traversalOrder = currentFormat.toString();
		}// get the traversal order.
		try {
			ExpressionTree.TraversalOrder order = null;
			order = Enum.valueOf(
					ExpressionTree.TraversalOrder.class, 
					traversalOrder);
			switch (order) {
			case prefix:
				return new PrefixEvaluator();
			case postfix:
				return new PostfixEvaluator();
			case infix:
				return new InfixEvaluator();
			default:
				throw new InvalidInputException(traversalOrder + 
					" is not a valid evaluation traversal order.");
			}		
		} catch (Exception e){
			throw new InvalidInputException(traversalOrder + 
					" is not a valid tree evaluation traversal order.");
		}
	}
	
	public void printCurrentExpressionTree(String traversalOrder) throws InvalidInputException{
		if (traversalOrder == null || traversalOrder.equals(""))
				traversalOrder = currentFormat.toString();
		currentExpTree.setTraversalOrder(traversalOrder);
		ExpressionBuilder builder = null;
		builder = new ExpressionBuilder(currentExpTree);
		String result = builder.build();	
		forwardEvent("output", result);				
	}
	
	public void handleQuit(){
		forwardEvent("quit", null);
	}
	
	public void handleReset(){
		forwardEvent("reset", null);
	}
	
	protected void raiseOutputEvent(String output){
		forwardEvent("output", output);
	}
	
	protected void forwardEvent(String event, Object data){
		try {
			Reactor.getInstance().handleEvent(event, data);
		} catch (InvalidEventHandlerException e) {
			throw new IllegalStateException(
				"State exception forwarding event "
				+ event + " by context."
				+ e.toString());
		}
	}
	
	public boolean hasMacroCommandError(){
		return this.hasMacroCommandError;
	}
	
}
