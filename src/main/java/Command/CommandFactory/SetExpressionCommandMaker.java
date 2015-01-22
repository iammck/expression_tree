public class SetExpressionCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new SetExpressionCommand(context, arg);
	}
}
