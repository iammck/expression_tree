import java.util.ArrayList;

public class InfixEvaluator extends Evaluator {

	protected ExpressionTree evaluate()throws InvalidInputException{
		ArrayList<Evaluatable> evaluatedList = new ArrayList<Evaluatable>();
		ArrayList<Evaluatable> pendingList = new ArrayList<Evaluatable>();

		// set the ExpressionTree traversal order
		expressionTree.setTraversalOrder(ExpressionTree.TraversalOrder.infix);
		
		for( ComponentNode node: expressionTree ){
			Evaluatable result = null;
			if ( node instanceof NumberLeafNode){
				result = new Number(node.getItem());
			} else if ( node instanceof 
				NegationCompositeUnaryNode){
				result = new Negation();
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
				} else if (node instanceof
					ExponentialCompositeBinaryNode){
					result = new Exponential();
				} else {
					throw new InvalidInputException(
						"Unable to evaluate " + node);
				}
			} else if ( node instanceof LeftParenthesisLeafNode
				|| node instanceof RightParenthesisLeafNode){
				result = new Parenthesis(node.getItem());
			} else {
				throw new InvalidInputException(
					"Unable to evaluate " + node);
			}
			if( result == null)
				throw new InvalidInputException(node + " has no evaluatable symbol.");
			result.addToEvaluator(evaluatedList, pendingList);
		}
		
		while (pendingList.size() > 0){
			Evaluatable lastEvaluatable;
			lastEvaluatable = pendingList.get(pendingList.size() - 1);
			if (lastEvaluatable.evaluate(evaluatedList)){
				pendingList.remove( lastEvaluatable );
			} else {
				throw new InvalidInputException(
					"Unable to evaluate " + lastEvaluatable);
			}	
		}
		if ( evaluatedList.size() == 1){
			ComponentNode root = ((Symbol)evaluatedList.get(0)).build();
			ExpressionTree.TraversalOrder order = 
						ExpressionTree.TraversalOrder.infix;
			return new ExpressionTree( order, root);	
		}
		throw new InvalidInputException("evaluated list did not turn out right.");
	}
}
