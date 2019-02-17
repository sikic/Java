package Commands;

import Game_2048.Game;

public class ResetCommand extends NoParamsCommand{
	private  static String  commandInfo = "reset";
	private final static String helpInfo = "start a new game.";
	
	public ResetCommand() {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	
	public boolean execute(Game game) { 
		game.reset();
		return true;
	}

}
