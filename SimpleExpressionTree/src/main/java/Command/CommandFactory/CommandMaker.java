public interface CommandMaker {
	Command makeCommand(ExpressionTreeContext context, String arg)
					throws InvalidCommandException;
}
