package Game_2048;
//: Clase para controlar la ejecución del juego, preguntando al usuario qué quiere hacer y actualizando la partida de acuerdo a lo que éste indique.
import java.util.*;

import Commands.Command;
import Commands.CommandParser;
import Excepciones.Null_execute;
import Excepciones.Parse_null;
import Excepciones.Redo_Exception;
import Excepciones.Undo_Exception;
import Rules.GameRules;

public class Controller {

	private Scanner in;
	private Game game;
	
	public Controller(int dimension,int baldIni,long seed,GameRules rules) {
		this.in = new Scanner(System.in);
		this.game = new Game(dimension,baldIni,seed,rules);
	}
	
	
	
	public void run() throws Undo_Exception, Redo_Exception {
		String opcion;
		boolean imprime;
		Command aux;
		String [] commandWords = new String[2]; 
		game.BaldosasInicio();
		System.out.println(game.toStringGame());
		opcion = " ";

		while(!this.game.isFinished()  && !game.isExit()) {

			System.out.print("Command > ");
			opcion = in.nextLine();
			commandWords[0] = opcion.split("\\s+")[0];
			
			if (opcion.split("\\s+").length == 2)
				commandWords[1] = opcion.split("\\s+")[1];
			else
				commandWords[1] = null;
			
			try {
				aux = CommandParser.parseCommand(commandWords , in);
				imprime = aux.execute(game);
				if(imprime == true)
					System.out.println(game.toStringGame());
			}
			catch(Parse_null e){
				System.out.println(e.getMessage());
					
			}
			
			catch(Null_execute b){
				
				System.out.println(b.getMessage());

			}
			catch(Undo_Exception b){
				
				System.out.println(b.getMessage());

			}
			catch(Redo_Exception b){
				
				System.out.println(b.getMessage());
			
		}
		
	}
	}
	}


	