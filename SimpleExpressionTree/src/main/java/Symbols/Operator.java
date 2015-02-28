import java.util.List;

public abstract class Operator extends Symbol {		
	protected abstract Number operate(Number firstParam, Number secondParam);
}
