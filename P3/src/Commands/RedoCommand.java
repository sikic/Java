package Commands;

import java.util.Scanner;

import Excepciones.Null_execute;
import Excepciones.Redo_Exception;
import Excepciones.Undo_Exception;
import Game_2048.Game;

public class RedoCommand extends Command{
private final static String  commandInfo = "redo";
private final static String helpInfo = "allows to run again a command previously made.";

	public RedoCommand() {
		super(commandInfo, helpInfo);
	}

	public boolean execute(Game game) throws Redo_Exception{
		try{
			game.redo();
			System.out.println("Redoing one move... ");
	}
	 catch(ArrayIndexOutOfBoundsException e) {
		throw new Redo_Exception("Error redo");
	 }
		return true;
	}

	public Command Parse(String[] commandWords, Scanner in) {

		if (commandWords[0].equalsIgnoreCase( commandInfo ))
			return new RedoCommand();
		else 
			return null;
	}

}
