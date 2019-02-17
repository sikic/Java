package Commands;

import java.util.Scanner;

import Excepciones.Null_execute;
import Excepciones.Parse_null;
import Excepciones.Redo_Exception;
import Excepciones.Undo_Exception;
import Game_2048.Game;

public class hola extends Command {
	private final static String  commandInfo = "hola";
	private final static String helpInfo = "hola que tal.";
	
	public hola() {
		super(commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) throws Null_execute, Undo_Exception, Redo_Exception {
		System.out.println("bien echo");
		return false;
	}

	@Override
	public Command Parse(String[] commandWords, Scanner in) throws Parse_null {
		
		if(commandWords[0].equalsIgnoreCase(commandInfo)) {
			return new hola();
		}
		return null;
	}

}
