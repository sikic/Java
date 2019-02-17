package Commands;


import java.util.Scanner;

import Excepciones.Parse_null;
import Game_2048.Direction;
import Game_2048.Game;

public class MoveCommand extends Command{
	private final static String  commandInfo = "move <direction>";
	private final static String helpInfo = "execute a move in one of the directions : up ,down ,left ,rigth.";
 	private Direction direccion;
 	
	public MoveCommand( Direction dir){
		
		super(commandInfo, helpInfo);
		direccion = dir;
	
	}

	

	public Command Parse(String[] commandWords, Scanner in) throws Parse_null{
		
		if(commandWords[0].equalsIgnoreCase("move")) {
			if(commandWords[1] != null) {
				switch (commandWords[1].toLowerCase()) {
				
				case "up":
					direccion = Direction.UP;
					break;
					
				case "down":
					direccion = Direction.DOWN;
					break;
					
				case "rigth":
					direccion = Direction.RIGTH;
					break;
					
				case "left":
					direccion = Direction.LEFT;
					break;
					
				default: throw new Parse_null("Unknown direction, the available directions are : UP,DOWN, RIGTH,LEFT."); /*System.out.println("Unknown direction, the available directions are : UP,DOWN, RIGTH,LEFT.");*/
					
				}
			
				return new MoveCommand(direccion);
			}
			else
			
			throw new Parse_null("error , missing direction...");
		}
		else 
			return null;
	}
		
		
	public boolean execute(Game game) {
		
		game.guardaEstado();// mete estado en la posicion correspondiente
		game.move(direccion);
		return true;

	}



}
