package Commands;


import java.util.Scanner;

import Excepciones.Parse_null;
import Excepciones.Redo_Exception;
import Excepciones.Undo_Exception;
import Excepciones.GameOverException;
import Excepciones.Null_execute;
import Game_2048.Game;

public abstract class Command { 

	private String helpText;// aqui va la descripcion de cada comando
	private String commandText;// basicamente aqui va el input de controller
	protected final String commandName;
	
	public Command(String commandInfo, String helpInfo){
	
		commandText = commandInfo;
		helpText = helpInfo; 
		String [] commandInfoWords = commandText.split("\\s+");/* el split lo que hace es separar en dos palabras desde el 
		simbolo que le metas entre parentesis y hay caracteres especiales , en este caso ese s+ se refiere al espacio entre palabras*/
		commandName = commandInfoWords[0];
	}
	

	public abstract boolean execute (Game game) throws Null_execute, Undo_Exception,Redo_Exception;
	public abstract Command Parse(String [] commandWords,Scanner in) throws Parse_null;
	public String helpText() { return "	"+ commandText +": " + helpText;}
	
}
