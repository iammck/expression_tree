public class ExpressionTreeContext {	
	
	public enum InputFormat {
		prefix, infix, postfix 		};
	
	//CONTEXT DATA		
	InputFormat currentFormat;
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
			raiseOutputEvent(
				"Unable to set expression to " + arg);
		}
	}
	
	public void setFormat(String arg){
		try{
			currentState.setFormat(this, arg);
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
		Reactor.getInstance().handleEvent("output", expBuilder.build());
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
	
	public void printCurrentExpressionTree(){
		ExpressionBuilder builder = null;
		builder = new ExpressionBuilder(currentExpTree);
		String result = builder.build();	
		Reactor.getInstance().handleEvent("output", result);				
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
