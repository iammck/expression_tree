import java.util.List;
import java.util.ArrayList;

public class MacroCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		List<Command> commands = new ArrayList<Command>();
		commands.add(new FormatCommand(context, "infix"));
		commands.add(new ExpressionCommand(context, arg));
		commands.add(new EvaluateCommand(context, "infix"));
		commands.add(new QuitCommand(context, null));
		return new MacroCommand(context, commands, arg);
	}
}
