public class Parenthesis extends Symbol{		
	/*
	 * will add itself as top accum symbol if (. Otherwise, iterates through
	 * accumSymbols calling interpret() then removing from accumOperators.
	 */	 
	public void addToSymbols(List<Symbol> parsedSymbols, List<Symbol> accumSymbols){		
		// if symbol is (, put into accumulated operators
		if (getSymbol().equals("(")){
			accumSymbols.add(this);
			return;
		}
		// while there are accumulated operators and the top is not (
		Symbol topSymbol = accumSymbols.get(accumSymbols.size() - 1);
		while ( !topSymbol.getSymbol.equals("(") ){
			// if able to interprete interprete.
			if (topSymbol.interpret(parsedSymbols)){
				// remove it from the accumulated operators.
				accumOperators.remove(topOperator);
			} else { // jump out of while loop.
				break;
			}
			topSymbol = accumSymbols.get(accumSymbols.size() - 1);
		}
		// if the top operator is a (, then remove it.
		if ( topOperator.getSymbol.equals("(")){
			accumOperators.remove(topOperator);
		}
	}
	
	
	
	public boolean interpret(List<Symbol> parseArray){
		return false;
	}
	
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		if (otherSymbol instanceof Parenthesis)
			return true;
		return false;
	}
}
