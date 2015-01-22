public class QuitCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new QuitCommand(context, arg);
	}
}
