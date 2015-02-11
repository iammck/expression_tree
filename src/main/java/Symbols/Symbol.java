import java.util.List;

public abstract class Symbol implements Interpretable, Evaluatable {
	public abstract ComponentNode build();
	public abstract int precedenceComparedTo(Symbol otherSymbol);

	public int precedenceComparedTo(Interpretable other){
		return precedenceComparedTo((Symbol) other);
	}
	
	public int precedenceComparedTo(Evaluatable other){
		return precedenceComparedTo((Symbol) other);
	}
}
