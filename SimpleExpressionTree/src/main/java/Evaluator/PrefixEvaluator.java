import java.util.*;

public class PrefixEvaluator extends Evaluator {
	protected ExpressionTree evaluate()throws InvalidInputException{
		List<Evaluatable> evaluatedList;
		evaluatedList = new ArrayList<Evaluatable>();
		List<Evaluatable> pendingList;
		pendingList = new ArrayList<Evaluatable>();
		
		
		// set the ExpressionTree traversal order
		expressionTree.setTraversalOrder(ExpressionTree.TraversalOrder.prefix);

		
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
				throw new InvalidInputException(node 
						+ " has no evaluatable symbol.");
			pendingList.add(result);
		}
		Collections.reverse(pendingList);
		for(Evaluatable evaluatable: pendingList){
			evaluatable.evaluate(evaluatedList);
		}
		
		ComponentNode root = ((Symbol)evaluatedList.get(0)).build();
		ExpressionTree.TraversalOrder order = 
						ExpressionTree.TraversalOrder.infix;
		return new ExpressionTree( order, root);
	}
}