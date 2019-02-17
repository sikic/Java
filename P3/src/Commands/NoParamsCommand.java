package Commands;

import java.util.Scanner;

import Game_2048.Game;

public abstract class  NoParamsCommand extends Command{

	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		
	}
	
	
	public  Command Parse(String [] commandWords, Scanner in){
		
		if(commandWords[0].equalsIgnoreCase(commandName))
			return this;
		else 
			return null;

	}		
	public  abstract boolean execute(Game game);
		// esta clase no se implementa , se hace en cada subclase
	
}
