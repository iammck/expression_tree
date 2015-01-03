public abstract class Operator extends Symbol {	
	
	/*
	 * Adds this operator instance to the accumulated operators list just after
	 * attepting to interprete any preveous operaton of equal or less precedence.
	 *
	 */
	public void addToSymbols(List<Symbol> parsedSymbols, List<Symbol> accumSymbols){		
		// while there are accumulated opperators
		while ( accumOperators.size() > 0){
			// get last opperation using an index.
			int index = accumOperators.size() - 1;
			Symbol lastOp = accumOperators.get(index);
			// if this does not have less or equal 
			// precedence over last op, interprete.
			if (!hasLessOrEqualPrecedenceOver(lastOp)){
				if (lastOp.interpret(parsedSymbols)){
					// remove it from the accumulated operators.
					accumSymbols.remove(lastOp);
				
				} else { // else can not interprete. break for loop.
					break;
				}
			} else { // else this has the greater precedence. break for loop.
				break;
			}			
		}		
		// put this on accumOperators.
		accumOperators.add(this);
	}	
}
