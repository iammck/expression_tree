public class PrintVisitor implements ComponentNodeVisitor{
	public void visit(ComponentNode responder){
		System.out.print(responder.getItem());
	}
}
