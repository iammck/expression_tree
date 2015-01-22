public class PrintCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new PrintCommand(context, arg);
	}
}
