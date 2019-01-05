package tp.pr1;
import java.util.*; // importa las API de java 
//Clase que  representa el estado de una partida (tablero, dimensión del tablero, número de baldosas en la configuración inicial y un objeto de la clase java.util.Ramdom para gestionar el comportamiento aleatorio del juego).
public class Game {
	private int size;
	private Board board;
	private int initCells;
	private Random myRandom; 
	
	
	
	public Game (int dimension,int baldIni,Random aleatorio){
		this.board = new Board(dimension);
		this.size=dimension;
		this.initCells= baldIni;
		this.myRandom = aleatorio;
		
	}
	
	public Position ramdomCasillaInicial(){
		
	Random aleatorio = new Random();
	int fil = aleatorio.nextInt(this.size);
	int col = aleatorio.nextInt(this.size);
	
	return new Position(fil,col);
	
	}
	
	public int generarNum() {
		
		Random aleatorio = new Random();
		Position pos = new Position (0,0);		
		int num = aleatorio.nextInt(10);
		pos = ramdomCasillaInicial();
		
		if (num < 8)
			num = 2;
		
		else
			num = 4;
		
		while(!this.board.isEmpty(pos)) {
			
			pos = ramdomCasillaInicial();
		}
		
		this.board.setCell(pos, num);
		return num;
	}
	
	public MoveResults BaldosasInicio() {
		int max= 0;
		int num;
		for (int i = 0; i < this.initCells; i++) {
			num =this.generarNum();
			if (num >max){
				max= num;
			}
			
		}
		return new MoveResults(false,0,max);
		
	}
	
	public void toString(MoveResults mov) {
		// este to string se metera en move , donde se llamara a execute move que es lo que le entra por parametro
		System.out.println(this.board.toString());
		System.out.print("Highest: " + mov.getMaxPunt() + "			" + "Score: " + mov.getScore() + '\n' + '\n');
	}
	
	public void move(Direction dir) {

			
			MoveResults move = new MoveResults (false,0,0);
			move =this.board.executeMove(dir);
			this.generarNum();
			this.toString(move);	
		
	}
	
	
	public boolean isFinished() {
		
		boolean end = false;
		boolean numeroMax = false;
		boolean lleno= false;
		boolean noMasMov = false;
		lleno =this.board.freeCells();
		numeroMax= this.board.fin2048();
		
		 if (numeroMax) {
			 System.out.print("Enhorabuena ha superado el juego");
			 end = true;
		 }
		 else if (lleno) {
			 noMasMov = this.board.masMovi();
			 if (!noMasMov) {
			 System.out.print("GAMEE OVER");
			 }
			 end = true;
		 }
			 
		return end;
	}
	
	public void inicializa() {
		this.board.inicializaArray();
	}
	
	public void help() {
		
		System.out.println(MyStringUtils.centre("--------USER MANUAL---------", 80) + '\n');
		System.out.println("-Move <direction>: execute a move in one of the four directions, up, down, left, right.");
		System.out.println("-Reset: start a new game.");
		System.out.println("-Help: print this help message.");
		System.out.println("-Exit: terminate the program." + '\n');


	}
	
}
