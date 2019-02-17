package Game_2048;
import java.util.*; // importa las API de java 

import Excepciones.Null_execute;
import Excepciones.Parse_null;
import Rules.GameRules;
import Rules.Rules2048;
import tp.pr1.GameState;
import tp.pr1.GameType;
import tp.pr1.arrayBoards;
//Clase que  representa el estado de una partida (tablero, dimensión del tablero, número de baldosas en la configuración inicial y un objeto de la clase java.util.Ramdom para gestionar el comportamiento aleatorio del juego).
public class Game {
	private int size;
	private Board board;
	private int initCells;
	private Random myRandom; 
	private MoveResults mov;
	private boolean exit;
	private arrayBoards undo;//undo
	private arrayBoards redo;
	private GameRules currentRules;
	private GameType type_game;


	public Game (int dimension,int baldIni,long seed,GameRules rules){
		
		this.board = new Board(dimension);
		this.size=dimension;
		this.initCells= baldIni;
		this.myRandom = new Random(seed);
		this.undo = new arrayBoards();
		this.redo = new arrayBoards();
		this.currentRules = new Rules2048();
		this.exit = false;
		this.type_game = GameType.ORIG;
		
	}
	
	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public Position ramdomCasillaInicial(){//Devuelve la posicion en la que se va a crear el numInicial.
		
	
	int fil = myRandom.nextInt(this.size);
	int col = myRandom.nextInt(this.size);
	
	return new Position(fil,col);
	
	}
	
	public int generarNum() {//Genera aleatoriamente el numero inicial y lo inserta si es posible.
		
		Position pos = new Position (0,0);		
		int num = myRandom.nextInt(10);
		pos = ramdomCasillaInicial();
		
		if (num < 8)
			num = 2;
		
		else
			num = 4;
		
		while(!this.board.isEmpty(pos)) {//Si pos contiene un valor, busca otra pos que no tenga valor.
			
			pos = ramdomCasillaInicial();
		}
		
		this.board.setCell(pos, num);
		return num;
	}
	
	public void BaldosasInicio() {
		int max= 0;
		int num;
		for (int i = 0; i < this.initCells; i++) {
			num =this.generarNum();
			if (num >max){
				max= num;
			}
			
		}
		this.mov = new MoveResults(false,0,max);
		
	}
	
	public String toStringGame() {//Devuelve un string con el tablero y la puntuacionMax y normal.
		String game= this.board.toString() +"Mejor valor: " + this.currentRules.getWinValue(board) + "			" + "Score: " + this.mov.getScore() + '\n' + '\n'; 
		return game;
	}
	
	public void move(Direction dir) {//Ejecuta el movimiento.
		
			this.mov = this.board.executeMove(dir,this.currentRules);
			this.currentRules.addNewCell(board, this.myRandom);
			this.toString();	
	}
	
	
	public boolean isFinished() {//Comprueba si el juego a finalizado.
		
		boolean end = false;
		
		 if (this.currentRules.win(board)) {
			 System.out.print("Enhorabuena ha superado el juego");
			 end = true;
		 }
		 else if (this.currentRules.lose(board)) {
			 System.out.print("GAMEE OVER");
			 end = true;
		 }
			 
		return end;
	}
	
	public String version() throws Null_execute {
		
		switch (this.type_game) {
		case ORIG:
			return "original";
		case FIB:
			return "fibbonacci";
		case INV:
			return "inverse";
		}
		throw new Null_execute();
	}
	public void reset() { //Hace un reset del tablero.
		this.board = new Board(this.board.getBoardSize()); 
		this.currentRules.initBoard(board, this.initCells, this.myRandom);
	}
	
	private GameState getState(){
		
		int x ;
		GameState aState = new GameState(this.mov.getScore(),this.board.getMaxPunt(),this.board.getBoardSize());
		for (int i = 0; i < this.board.getBoardSize(); i++){
			for (int j = 0; j < this.board.getBoardSize(); j++) {
				x = this.board.getState()[i][j];
				aState.getBoardState()[i][j] =x;
			}
			
		}
		return aState;
	}
	
	private void setState(GameState aState){
		this.board.setState(aState.getBoardState());//el error esta aqui ya que le entra null
		this.mov.setScore(aState.getScored()); // preguntar
					
	}
	
	public void cambiarTab(Board board) {
		this.board = new Board(board.getBoardSize());
		this.board.cambiarTablero(board);
	}

	
	public void guardaEstado(){
		this.undo.push(getState());
	}
	
	
	public void undo(){
		GameState x = undo.pop();
		redo.push(getState());//mete en el array
		setState(x);
		undo.setContador(undo.getContador() - 1);
	
		
		
	}
	
	public void redo(){
			setState(redo.pop());
			guardaEstado();
			redo.setContador(redo.getContador() - 1);
	}
	
	public void setCurrentRules(GameRules currentRules, int iniciales , int tam , Random seed) {
		this.currentRules = currentRules;
		Board x = this.currentRules.createBoard(tam);
		this.currentRules.initBoard(x,iniciales,seed);
		this.undo = new arrayBoards();
		this.redo = new arrayBoards();
		cambiarTab(x);
		
	}

	public void store(String archivo) throws Null_execute {
		board.store(archivo,this.initCells,this.currentRules.getWinValue(board),version());
		
	}
	
	public GameType load(String archivo) throws Null_execute{
		try{
		board.load(archivo);
		this.type_game= type_game.distincion(board.getType());
		this.currentRules =this.type_game.crearNormas(); 
		this.undo = new arrayBoards();
		this.redo = new arrayBoards();
		return type_game;
		} catch (Parse_null e) {
		throw new Null_execute();
		}
	}
}
