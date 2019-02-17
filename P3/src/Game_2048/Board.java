package Game_2048;

import Rules.GameRules;
import utils.MyStringUtils;
import java.io.*;

import Excepciones.Null_execute;

//Clase que almacena el estado de un tablero y proporciona métodos necesarios para la gestión de dicho estado.
public class Board {
	
	private Cell [ ][ ] board;
	private int boardSize;
	private int score;
	private int maxPunt;
	private int numCell;
	private boolean moved;
	private static final int FIN = 2048;
	private String type;
	
	public Board(int size){
		this.boardSize = size;// crea el tama�o.
		this.board = new Cell [size][size]; // inicializa el array a tamaño o size.
		for (int i =0; i < this.boardSize;i++) { //Inicializa el tablero a 0.
			for(int j= 0; j < this.boardSize;j++) {
				this.board[i][j] = new Cell(0);
			}
		}
	}
	
	
	
	public int getBoardSize() {
		return boardSize;
	}



	public void setCell(Position pos,int value){//Introduce un valor en una posicion del tablero.
		this.board[pos.getFila()][pos.getColumna()].setValor(value);
	}
	
	private Cell[] cogerArray(Direction dir,int n){//Coje una columna o fila dependiendo de la dir.
		Cell [] arrayAux = new Cell [this.boardSize];
		if(dir == Direction.UP) {
			int j = this.boardSize -1;
			for (int i = 0;i < this.boardSize;i++) {
				arrayAux[i]=this.board[j][n];
				j--;
			}
		}
		else if(dir == Direction.DOWN) {
			int j = 0;
			for (int i = 0;i < this.boardSize;i++) {
				arrayAux[i]=this.board[j][n];
				j++;
			}
		}	
		else if(dir == Direction.RIGTH) {
			int j = 0;
			for (int i = 0;i < this.boardSize;i++) {
				arrayAux[i]=this.board[n][j];
				j++;
			}
		}
		else if(dir == Direction.LEFT) {
			int j = this.boardSize -1 ;
			for (int i = 0;i < this.boardSize;i++) {
				arrayAux[i]=this.board[n][j];
				j--;
			}
		}	
		return arrayAux;
		}
	
	private Cell[] desplazar(Cell [] arrayAux) {//Desplaza el array a la derecha.
		
		for(int i = this.boardSize-1;i >=0;i--)
		{
			if(arrayAux[i].getValor()== 0)
			{
				for (int j = i-1;j >=0;j--) {
					if(arrayAux[j].getValor()!= 0 ) {
						arrayAux[i].setValor(arrayAux[j].getValor());
						arrayAux[j].setValor(0);
						j= -1;
					}
				}
			}
		}
		
		return arrayAux;
	}
	
	private Cell[] fusionar(Cell [] arrayAux,GameRules rules) { //Fusiona el array a la derecha y guarda el socre y maxPunt.
		for(int i = this.boardSize - 1; i> 0 ;i--) {
			this.score+= arrayAux[i-1].doMerge(arrayAux[i],rules);
			if( arrayAux[i].getValor()> this.maxPunt) {/**********/
				this.maxPunt=arrayAux[i].getValor();
			}
		}
		
		return arrayAux;
	}
	
	private void devolverArray(Cell[] arrayAux,int n,Direction dir) {//Devuelve el array ya colocado y fusionado.
		int j=0;
		if ( dir == Direction.UP) {
			for(int i = this.boardSize-1;i >=0;i-- ) {
				this.board[j][n]=arrayAux[i];
				j++;
			}
		}
		else if ( dir == Direction.DOWN) {
			for(int i = 0;i <=this.boardSize-1;i++ ) {
				this.board[j][n]=arrayAux[i];
				j++;
			}
		}
		else if ( dir == Direction.RIGTH) {
			for(int i = 0;i <= this.boardSize-1;i++ ) {
				this.board[n][j]=arrayAux[i];
				j++;
			}
		}
		else if ( dir == Direction.LEFT) {
			for(int i = this.boardSize-1;i>=0;i-- ) {
				this.board[n][j]=arrayAux[i];
				j++;
			}
		}
	}
	
	
	
	public MoveResults executeMove(Direction dir,GameRules rules) {//Ejecuta la accion de despalazar hacia una direccion.
		Cell[]arrayAux= new Cell[this.boardSize];
		int punt = this.score;
		int n= 0;
		while(n < this.boardSize) {
			arrayAux= this.cogerArray(dir, n);
			arrayAux= this.desplazar(arrayAux);
			arrayAux= this.fusionar(arrayAux,rules);
			arrayAux= this.desplazar(arrayAux);
			this.devolverArray( arrayAux, n, dir);
			n++;
		}
		if(punt < this.score) {
			this.moved= true;
		}
		else {
			this.moved =false;
		}
		return new MoveResults(this.moved,this.score,this.maxPunt);
	}

	public String toString() {//Crea un string del estado del tablero.
		int cellSize = 7;
		String space = " " ;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String cadena = "";
		String aux ="",aux1="";
		String s = "";
		for (int j = 0; j < getBoardSize(); j++) {
			for(int i = 0; i < getBoardSize();i++){
				
				cadena = String.valueOf(this.board[j][i].getValor());
				
				if(this.board[j][i].getValor() == 0)
				{
					cadena = space;
				}
				
				aux = MyStringUtils.repeat(vDelimiter + MyStringUtils.centre(cadena, cellSize), 1);
				s+= aux;
				 
			}
			
			aux1= vDelimiter + '\n' + MyStringUtils.repeat(hDelimiter, 8 * getBoardSize()) + '\n';
			 s+= aux1;
		}
		
		return s ;
		
	}

	
	public boolean freeCells(){ //Comprueba si hay celdas  iguales a 0.
		
		boolean lleno = true;
			for(int i = 0;i <this.boardSize; i++ ){
				for(int j = 0;j < this.boardSize;j++){
				if(this.board[i][j].getValor()== 0)
				{	lleno = false;
				}
			}	
		}
	return lleno;
	}
	
	public boolean fin2048 () {//Comprueba si se ha llegado a 2048.

		return this.maxPunt == FIN;
	}
	
	
	public boolean masMovi() {// Comprueba si es posible hacer un movimiento con el estado del tablero actual.
		boolean otroMovimiento= false;
		for(int i = 0; i < this.boardSize;i++) {
			for(int j= 0 ;j < this.boardSize;j++) {
				
				if( 0 <= (i-1) && this.board[i][j].getValor() == this.board[i-1][j].getValor()) {
					otroMovimiento = true;
				}
				else if( this.boardSize > (i+1) && this.board[i][j].getValor() == this.board[i+1][j].getValor()) {
					otroMovimiento = true;
				}
				else if( this.boardSize > (j+1) && this.board[i][j].getValor() == this.board[i][j+1].getValor()) {
					otroMovimiento = true;
				}
				else if( 0 <= (j-1) && this.board[i][j].getValor() == this.board[i][j-1].getValor()) {
					otroMovimiento = true;
				}
			}
		}
		return otroMovimiento;
	}
	
	public boolean isEmpty(Position pos) {//Comprueba si una posicion del tablero esta vacia.
		
		return this.board[pos.getFila()][pos.getColumna()].isEmpty();
	}
	
	
	public int [][] getState(){//Guarda en un array el estado del tablero.
		
		int [][] aState = new int[boardSize][boardSize];
		for (int i = 0; i < this.boardSize; i++){
			for (int j = 0; j < this.boardSize; j++) {
					aState[i][j] = this.board[i][j].getValor();
								
			}
			
		}
		return aState;
	}
	
	public void setState(int [][] aState){//Cambia el estado del tablero con los valores del parametro.
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++) {
				this.board[i][j].setValor(aState[i][j]);

			}
		}
		 
	}
	public int minimoNum(){// Calcula el numero minimo del tablero(sin coger el 0)
		int minimo=2048;
		for (int i = 0; i < this.boardSize;i++) {
			for (int j = 0; j < this.boardSize;j++)
				if (minimo > this.board[i][j].getValor() &&this.board[i][j].getValor() != 0) {
					minimo= this.board[i][j].getValor();
				}
		}
		return minimo;
	}
	
	public int maximoNum(){// Calcula el numero maximo del tablero
		int maximo = 0;
		for (int i = 0; i < this.boardSize;i++) {
			for (int j = 0; j < this.boardSize;j++)
				if (maximo < this.board[i][j].getValor() &&this.board[i][j].getValor() != 0) {
					maximo = this.board[i][j].getValor();
				}
		}
		return maximo;
	}

	public int getMaxPunt() {
		return maxPunt;
	}
	
	public void cambiarTablero(Board board) {
		for (int i = 0; i < board.getBoardSize(); i++) {
			for (int j = 0; j < board.getBoardSize() ; j++) {
				this.board[i][j].setValor(board.board[i][j].getValor());
			}
		}
	}
	
	public void store(String archivo,int iniciales, int maximo ,String version) throws Null_execute{
			try {
				FileWriter escribir = new FileWriter(archivo);
				BufferedWriter escritura = new BufferedWriter(escribir);
				escritura.write(MyStringUtils.inicio);
				// espacio en blanco
				escritura.newLine();
				escritura.newLine();
				for (int i = 0; i < this.boardSize; i++) {
					for (int j = 0; j < this.boardSize; j++) {
						escritura.write(String.valueOf(this.board[i][j].getValor()) + '\t');
					}
					// cambio de linea
					escritura.newLine();
				}
				// espacio en blanco
				escritura.newLine();
				escritura.write(String.valueOf(iniciales)+ '\t' + String.valueOf(maximo) + '\t' + version);
				System.out.println("Game successfully saved to file; use load command to reload.");
				escritura.close();
			} catch (IOException e) {
				throw new Null_execute("error en el store");
			}
			
		
			
		
	}
	
	public void load(String archivo) throws Null_execute{
		
		try {
			String[] auxiliar;
			FileReader leer = new FileReader(archivo);
			BufferedReader lectura = new BufferedReader(leer);
			
			if(lectura.readLine().equalsIgnoreCase(MyStringUtils.inicio)){
				if(lectura.readLine().equals("")){
					auxiliar = lectura.readLine().split("\\t");
					int nuevotab[][] = new int[auxiliar.length][auxiliar.length];
					
					while(!auxiliar[0].equals("")) {
					for (int i = 0; i < nuevotab.length; i++) {
						for (int j = 0; j < nuevotab.length; j++) {
							nuevotab[i][j] = Integer.parseInt(auxiliar[j]);
						}
						auxiliar = lectura.readLine().split("\\t");
					 }
					}
					auxiliar = lectura.readLine().split("\\t");
					Board carga = new Board(nuevotab.length);
					for (int i = 0; i < nuevotab.length; i++) {
						for (int j = 0; j < nuevotab.length; j++) {
							Position pos = new Position(i,j);
							carga.setCell(pos, nuevotab[i][j]);
						}
					}
					this.numCell = Integer.parseInt(auxiliar[0]);
					this.boardSize = nuevotab.length;
					this.maxPunt= Integer.parseInt(auxiliar[1]);//rompe encapsulacion??
					type = auxiliar[2];
					cambiarTablero(carga);
					lectura.close();
				}
				else
					lectura.close();		
			}
			else {
				    lectura.close();
					throw new Null_execute("el archivo del que se pretende cargar no esta en condiciones.");
			}
			
			
		} catch (IOException e){
			throw new Null_execute("Error de Load.");
		}
		
	}



	public String getType() {
		return type;
	}
}
