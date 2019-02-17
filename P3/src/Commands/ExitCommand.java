package Commands;




import Game_2048.Game;

public class ExitCommand extends NoParamsCommand {
	
	private final static String  commandInfo = "exit";
	private final static String helpInfo = "terminate the program.";
	


	public ExitCommand() {
		super(commandInfo, helpInfo);
		
	}

	public boolean execute(Game game) {
		System.out.println("!!!!!!!!!!!!GAAAAAAAAAAAAMEEEEEEEE OOOOOOOOOOVEEEEEEEER　　　　　　");
		game.setExit(true);
		return false;
	}
	
}
