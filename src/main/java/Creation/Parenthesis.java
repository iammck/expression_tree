import java.util.List;

public class Parenthesis extends Symbol{		
	
	public ComponentNode build(){
		return null;
	}
	
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
		while (topSymbol.interpret(parsedSymbols)){
			// remove it from the accumulated operators and get next.
			accumSymbols.remove(topSymbol);
			topSymbol = accumSymbols.get(accumSymbols.size() - 1);
		}
		// if the last top operator is a (, then remove it.
		if ( topSymbol.getSymbol().equals("(")){
			accumSymbols.remove(topSymbol);
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
