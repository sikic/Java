package Commands;

import Game_2048.Game;

public class HelpCommand extends NoParamsCommand {
	
	private final static String  commandInfo = "help";
	private final static String helpInfo = "print this help message.";
	
	public HelpCommand() {
		super(commandInfo, helpInfo);
	}

	public boolean execute(Game game) {
		
		System.out.println(CommandParser.commandHelp());
		return false;
	}
	
}
