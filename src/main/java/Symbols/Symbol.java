import java.util.List;

public abstract class Symbol implements Interpretable {
	public abstract ComponentNode build();
	public abstract int precedenceComparedToSymbol(Symbol otherSymbol);

	public int precedenceComparedTo(Interpretable other){
		return precedenceComparedToSymbol((Symbol) other);
	}

//	public abstract boolean evaluate(List<Symbol> evaluatedSymbols);
//	public abstract void addToEvaluateLists(List<Symbol> evaluatedSymbols, 
//								List<Symbol> pendingSymbols);	

}
