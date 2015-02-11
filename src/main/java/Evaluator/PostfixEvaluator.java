import java.util.*;
public class PostfixEvaluator extends Evaluator {
	protected Double evaluate(){
		List<Evaluatable> evaluatedList;
		evaluatedList = new ArrayList<Evaluatable>();
		
		for( ComponentNode node: expressionTree ){
			Evaluatable result = null;
			if ( node instanceof NumberLeafNode){
				result = new Number(node.getItem());
			} else if ( node instanceof
				CompositeBinaryNode) {
				if (node instanceof
					AdditionCompositeBinaryNode){
					result = new Addition();
				} else if ( node instanceof
					SubtractionCompositeBinaryNode){
					result = new Subtraction();
				} else if (node instanceof
					MultiplicationCompositeBinaryNode){
					result = new Multiplication();
				} else if (node instanceof
					DivisionCompositeBinaryNode){
					result = new Division();
				}
			}
			if( result == null)
				throw new InvalidInputException(node + " has no evaluatable symbol.");
			result.evaluate(evaluatedList);
		}

		return ((Number)evaluatedList.get(0)).toDouble();
	}
}
