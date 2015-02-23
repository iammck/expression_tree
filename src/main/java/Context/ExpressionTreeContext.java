public class ExpressionTreeContext {	
	
	public enum InputFormat {
		prefix, infix, postfix 		};
	
	//CONTEXT DATA		
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
			raiseOutputEvent(
				"Unable to set expression to " + arg);
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
	
	private Evaluator getEvaluator(){
		switch (currentInputFormat){
		case prefix:
			return new PrefixEvaluator();
		case postfix:
			return new PostfixEvaluator();
		case infix:
			return new InfixEvaluator();
		default:
			return null;
		}
	}
	
	public CommandFactory getCommandFactory(){
		return commandFactory;
	}

	public void evaluateCurrentExpressionTree()throws InvalidInputException{
		// Evaluate the expression tree to get an ExpressionTree object.
		Evaluator evaluator = getEvaluator();
		ExpressionTree resultTree;
		resultTree = evaluator.evaluate(this, currentExpTree);
		// Use an expressionBuilder to build up an expression as a String.
		ExpressionBuilder expBuilder;
		expBuilder = new ExpressionBuilder(resultTree);
		Reactor.getInstance().handleEvent("output", expBuilder.build());
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
