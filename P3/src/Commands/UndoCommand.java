package Commands;

import java.util.Scanner;

import Excepciones.Null_execute;
import Excepciones.Undo_Exception;
import Game_2048.Game;

public class UndoCommand extends Command{
	
	private final static String  commandInfo = "undo";
	private final static String helpInfo = "restore the game to the state it had before the last movement.";

	public UndoCommand() {
		super(commandInfo, helpInfo);
	}


	public boolean execute(Game game) throws Undo_Exception {
		try {
			game.undo();
			System.out.println("Undoing one move... ");
		}
		catch(ArrayIndexOutOfBoundsException e){
			
			throw new Undo_Exception("error undo");
		}
		return true;	
	}

	
	public Command Parse(String[] commandWords, Scanner in) {
		
		if (commandWords[0].equalsIgnoreCase( commandInfo ))
			return new UndoCommand();
		else 
			return null;
	}

	public static void main(String[] args) {
		
	}
}
