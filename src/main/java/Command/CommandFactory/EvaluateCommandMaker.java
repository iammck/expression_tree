public class EvaluateCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new EvaluateCommand(context, arg);
	}
}
