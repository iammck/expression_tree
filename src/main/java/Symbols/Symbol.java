import java.util.List;

public abstract class Symbol implements Interpretable, Evaluatable {
	public abstract ComponentNode build();
	public abstract int precedenceComparedToSymbol(Symbol otherSymbol);

	public int precedenceComparedTo(Interpretable other){
		return precedenceComparedToSymbol((Symbol) other);
	}
	
	public int precedenceComparedTo(Evaluatable other){
		return precedenceComparedToSymbol((Symbol) other);
	}
}
