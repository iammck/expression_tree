public abstract class Symbol {
	protected InterpreterContext context;
	
	public abstract boolean interpret(List<Symbol> parseArray);
	private abstract boolean hasPrecedenceOver(Symbol otherOperator);
	
	public Symbol(InterpreterContext context){
		this.context = context;
	}
}
