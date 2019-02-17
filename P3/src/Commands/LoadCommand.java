package Commands;

import java.util.Scanner;

import Excepciones.Null_execute;
import Excepciones.Parse_null;
import Game_2048.Game;
import utils.MyStringUtils;

import java.io.*;

public class LoadCommand extends Command {
	
	private final static String  commandInfo = "Load < FileName >";
	private final static String helpInfo = "allows load a game from a file.";
	private String archivo;

	
	
	public LoadCommand(String archivo) {
		super(commandInfo, helpInfo);
		this.archivo = archivo;
	}

	
	public boolean execute(Game game) throws Null_execute {
		try {
			game.load(this.archivo);
			System.out.println("Game successfully loaded from file:2048,"+ game.version() + " version");
		} catch (Exception e) {
			
			throw new Null_execute("Error al cargar el archivo");
		}
		return true;
	}
	
	public Command Parse(String[] commandWords, Scanner in) throws Parse_null{
		if(commandWords[0].equalsIgnoreCase("load")) {
		if(commandWords[1] != null){
		try { 
			if(MyStringUtils.validFileName(commandWords[1]) == false)
				throw new Parse_null("El nombre del archivo no es valido.");
		}catch(Exception e) {
			throw new Parse_null("archivo no existe");
		}
			
		return new LoadCommand(commandWords[1]);
		}
		}
		return null;//mirar esto
	}
	
}
