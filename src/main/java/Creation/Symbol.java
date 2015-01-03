public abstract class Symbol {
	private String symbol;
	
	public abstract boolean interpret(List<Symbol> parseArray);
	public abstract boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol);
	public abstract void addToSymbols(List<Symbol> parsedSymbols, List<Symbol> accumSymbols);
	
	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
	
	public string getSymbolStrung(){
		return symbol;
	}
}
