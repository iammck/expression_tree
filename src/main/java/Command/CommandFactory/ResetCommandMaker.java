public class ResetCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new ResetCommand(context, arg);
	}
}
