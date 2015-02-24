public class ConsoleHelpEventHandler implements HelpEventHandler {
	public void handleEvent(String event, Object data){
		String msg = "format [infix | postfix | prefix]\n"
				+ "\tsets the expected expression format\n"
				+ "expression expr\n"
				+ "\tset the expression tree to expr\n"
				+ "print [infix | postfix | prefix\n"
				+ "\t| inorder | postorder | preorder]\n"
				+ "evaluate  [infix | postfix | prefix]\n"
				+ "\tevaluate and print the expression tree\n"
				+ "quit\n"
				+ "\texit\n"
				+ "reset\n"
				+ "\treset the expression tree\n"
				+ "help\n"
				+ "\tprint this message.\n";
		
		System.out.println(msg);
	}
	
	public void quit(){
		
	}
}
