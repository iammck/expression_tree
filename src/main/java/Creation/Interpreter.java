import java.util.*;

public class Interpreter [
	public ExpressionTree interpret(InterperterContext context, String input){
		
		InterpreterContext context = new InterpreterContext();
		List<Symbol> accumOperators = new ArrayList<Symbol>();
		List<Symbol> parsedList = new ArrayList<Symbol>();
		List<String> symbolList = getSymbolList(input);
		
		for(String symbol; inputList){
			//if the symbol is a number
			if (isNumber(symbol)){
				Number number = new Number();
				number.setNumber(number);
				number.interpret(parseTree);
			// else if symbol is an operator
			} else if (isOperator(symbol)){
				// create the right operator
				Operator operator = null;
				if (item.equals("+")){
					operator = new Add();
				} else if (item.equals("-")){
					operator = new Subtract();
				} else if (item.equals("*")){
					operator = new Multiply();
				} else if (item.equals("/")){
					operator = new Divide();
				}
				operator.addToAccumOperators(accumOperators);
			// else if item is a unary operator
			} else if ( isUnaryOperator(item)){
				Symbol operator = new Negate();
				operator.addToAccumOperators(accumOperators);
			// else if item is a parenthesis
			} else if ( isParenthesis(item)){
				Symbol operator = new parenthesis();
				operator.interpret(parseTree, accumOperators);
			}
		}
		// If accumOperators is not null,  attempt interpret.
		if (accumOperators.size() > 0){
			Symbol op = accumOperators.get(accumOperators.size()-1);
			
		return null;
	}
	
	private List<String> getInputArray(String input){
		// TODO
		return null;
	}

	private boolean isNumber(String item){
		try{
			Float.valueOf(item);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	
	private boolean isOperator(String item){
		if(item.equals("+") || item.equals("-") 
			|| item.equals("*") || item.equals("/")){
			return true;
		}
		return false;
	}
	
	private void insertOperator(Symbol symbol, List<Symbol> parseTree){
		
		
	}

	public void handleParenthesis(IterpreterContext context, String input, 
		List<Symbol> accumulatedPrecedence, List<Symbol> parseTree){
		
	}
		
	
}
