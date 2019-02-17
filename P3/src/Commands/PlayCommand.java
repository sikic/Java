package Commands;

import java.util.Random;
import java.util.Scanner;

import Excepciones.Parse_null;
import Game_2048.Game;
import Rules.GameRules;
import tp.pr1.GameType;
import utils.MyStringUtils;

public class PlayCommand extends Command {
	
	private final static String  commandInfo = "play <game>";
	private final static String helpInfo = "Start a new game of one of the game types: original,fin,inverse.";
	private int tamTab;
	private int numCelIni;
	private int seed;
	private GameType gameType;
	
	public PlayCommand(GameType type,int tam,int inic ,int seed )
	{
		super(commandInfo, helpInfo);
		this.gameType = type;
		this.tamTab = tam;
		this.numCelIni = inic;
		this.seed = seed;
		
	
	}
	
	public boolean execute(Game game){
		
		Random rand = new Random(seed);
		GameRules rules = this.gameType.crearNormas();
		game.setCurrentRules(rules,this.numCelIni,this.tamTab,rand);
		return true;		
	}

	public Command Parse(String[] commandWords ,Scanner in) throws Parse_null {
		
		if(commandWords[0].equalsIgnoreCase("play")){
			
		GameType tipo = GameType.ORIG; // da igual con que se empieze luego va a ser cambiado
		
		try {
			
			tipo.distincion(commandWords[1]);
			String aux[]; 
			System.out.println("Please enter the size of the board: ");
			aux = in.nextLine().trim().split("//s");//borra los espacios si los hay y coje solo la palabra o el vacio si nos e escribe nada
			
			if(aux[0].hashCode() == 0){	// sabemos que ha elegido el valor por defecto de el tama�o
				this.tamTab = 4;
				System.out.println("Using the default size of board: 4");
			}
			else {// ha introducido unos datos
				while(!MyStringUtils.isNumeric(aux[0])) {
					System.out.println("Error...Please introduce a number.");
					aux = in.nextLine().trim().split("//s");
				}
				while(Integer.valueOf(aux[0]) < 2){
					System.out.println("The minimun size of the board must be two... try again.");
					 aux = in.nextLine().trim().split("//s");
				}
				
				this.tamTab = Integer.valueOf((aux[0]));
			}
				
				
			aux[0] = null;
			System.out.println("Please enter the number of initial cells: ");
			aux = in.nextLine().trim().split("//s");//borra los espacios si los hay y coje solo la palabra o el vacio si nos e escribe nada
			
			if(aux[0].hashCode() == 0) {
				this.numCelIni = 2;
				System.out.println("Using the default number of initial cells: 2");
			}
			else  {
				while(!MyStringUtils.isNumeric(aux[0])) {
					
					System.out.println("Error...Please introduce a number.");
					aux = in.nextLine().trim().split("//s");
					
				}
				
				if(Integer.valueOf(aux[0]) > this.tamTab*this.tamTab) {
					throw new Parse_null("Sorry, but the number of initital cells must be smaller or equal than the size of the board." );
				}
				this.numCelIni = Integer.valueOf((aux[0]));
			}
			aux[0] = null;
	
			System.out.println("Please enter the seed for the pseudo-random number generator: ");
			aux = in.nextLine().trim().split("//s");//borra los espacios si los hay y coje solo la palabra o el vacio si nos e escribe nada
			
			if(aux[0].hashCode() == 0) {
				
				this.seed = (int)(Math.random()* 1000);
				System.out.println("Using the default seed for the pseudo-random number generator:" + this.seed);
				
			}
			else  {
				
				while(!MyStringUtils.isNumeric(aux[0])) {
					
					System.out.println("Error...Please introduce a number.");
					aux = in.nextLine().trim().split("//s");
					
				}
				this.seed = Integer.valueOf((aux[0]));
	
			}
			aux[0] = null;
			
			return new PlayCommand(tipo.distincion(commandWords[1]),this.tamTab,this.numCelIni,this.seed);
		   }
		catch(NullPointerException e){
		
			throw new Parse_null("falta introducir el tipo de juego");
			}
		}
		
	return null;
	
  }	
}

