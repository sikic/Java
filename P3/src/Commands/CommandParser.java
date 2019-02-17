package Commands;

import java.util.Scanner;

import Excepciones.Parse_null;
public class CommandParser {
	
	//array de tipo comandos
	private static Command[] availableCommands = { new HelpCommand() , new ResetCommand() ,new ExitCommand() ,new UndoCommand(),new RedoCommand(),new MoveCommand(null),new PlayCommand(null,0,0,0)
			,new LoadCommand(""),new SaveCommand(""), new hola()};
	
	
	public static Command parseCommand(String [] commandWord ,Scanner in) throws Parse_null{
		

			Command x = availableCommands[0].Parse(commandWord, in);
			
				for (int i = 0; i < availableCommands.length; i++) {
					x = availableCommands[i].Parse(commandWord, in);
					
					 if (x != null){
						return x;
					 }
				}
				
				throw new Parse_null("Error el comando introducido no es valido");
	}
	
	public static String commandHelp() {
		
		String aux = "";
		System.out.println("The available commands are: ");
		for (int i = 0; i < availableCommands.length; i++) {
			aux += availableCommands[i].helpText() + '\n';
		}
		return aux;
	}
	

}
