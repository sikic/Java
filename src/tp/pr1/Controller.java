package tp.pr1;
//: Clase para controlar la ejecución del juego, preguntando al usuario qué quiere hacer y actualizando la partida de acuerdo a lo que éste indique.
import java.util.*;

public class Controller {

	Scanner in;
	private Game game;
	
	public Controller(int dimension,int baldIni,Random aleatorio) {
		this.in  = new Scanner(System.in);
		this.game = new Game(dimension,baldIni,aleatorio);
	}
	
	
	public void run() {
		String opcion;
		this.game.inicializa();
		MoveResults mov = this.game.BaldosasInicio();
		this.game.toString(mov);
		opcion = " ";
		
		while(!this.game.isFinished() && !opcion.equalsIgnoreCase("exit")) {
			
			System.out.print("Command > ");
			opcion = this.in.nextLine();
			
			if(opcion.equalsIgnoreCase("move")) {
				System.out.println("Move must be followed by a direction: up, down, left or right");
				
			}
			else if (opcion.equalsIgnoreCase("move up")) {
				
				this.game.move(Direction.UP);
			}
			else if (opcion.equalsIgnoreCase("move down")) {
				
				this.game.move(Direction.DOWN);
				
			}
			else if (opcion.equalsIgnoreCase("move rigth")) {
				
				this.game.move(Direction.RIGTH);
			}
			else if (opcion.equalsIgnoreCase("move left")) {
			
				this.game.move(Direction.LEFT);
			}
			else if (opcion.equalsIgnoreCase("reset")) {
				this.game.inicializa();
				this.game.BaldosasInicio();
				this.game.toString(mov);
			}
			
			else if (opcion.equalsIgnoreCase("help")) {
				this.game.help();
				
			}
			
			else if (opcion.equalsIgnoreCase("exit")) {
				System.out.println(MyStringUtils.centre("����������GAAAAAMEEE OOOOOOOVEEEEEEEERRRR!!!!!!!!!!!!!",80));
			}
			else 
				System.out.println("Unknown command\r\n");
			
		}
		
	}
	
}
