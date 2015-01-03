public class Number extends Symbol {
	public boolean interpret(List<Symbol> parsedList){
		return true;
	}	
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		return true;
	}
	
	public void addToSymbols(List<Symbol> parsedSymbols, List<Symbol> accumSymbols){
		parsedSymbols.add(this);
	}
	
}
