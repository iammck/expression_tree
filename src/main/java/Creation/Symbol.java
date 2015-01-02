public abstract class Symbol {
	protected InterpreterContext context;
	
	public abstract boolean interpret(List<Symbol> parseArray);
	private abstract boolean hasPrecedenceOver(Symbol otherOperator);
	
	public Symbol(InterpreterContext context){
		this.context = context;
	}
	
	private String symbol;
	
	public void setSymbolString(String symbol){
		this.symbol = symbol;
	}
	
	public string getSymbolStrung(){
		return symbol;
	}
}
