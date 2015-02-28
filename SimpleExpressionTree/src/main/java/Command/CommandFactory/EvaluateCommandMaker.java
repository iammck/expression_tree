public class EvaluateCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
					throws InvalidCommandException {
		return new EvaluateCommand(context, arg);
	}
}
