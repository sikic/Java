package tp.pr1;

//Clase que almacena el estado de un tablero y proporciona métodos necesarios para la gestión de dicho estado.
public class Board {
	
	private Cell [ ][ ] board;
	private int boardSize;
	private int score;
	private int maxPunt;
	private boolean moved;
	
	public Board(int size){
		this.boardSize = size;// crea el tama�o 
		this.board = new Cell [size][size]; // inicializa el array a tama�o size
	}
	
	
	
	public void setCell(Position pos,int value){
		this.board[pos.getFila()][pos.getColumna()].setValor(value);
	}
	public Cell[] cogerArray(Direction dir,int n){
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
	
	public Cell[] desplazar(Cell [] arrayAux) {
		
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
	public Cell[] fusionar(Cell [] arrayAux) {
		for(int i = this.boardSize - 1; i> 0 ;i--) {
			this.score+= arrayAux[i-1].doMerge(arrayAux[i]);
			if( arrayAux[i].getValor()> this.maxPunt) {
				this.maxPunt=arrayAux[i].getValor();
			}
		}
		
		return arrayAux;
	}
	
	public void devolverArray(Cell[] arrayAux,int n,Direction dir) {
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
	
	
	
	public MoveResults executeMove(Direction dir) {//Score es la puntuacion total, y el maxPunt es el numero mas alto que hay en el tablero.
		Cell[]arrayAux= new Cell[this.boardSize];
		int punt = this.score;
		int n= 0;
		while(n < this.boardSize) {
			arrayAux= this.cogerArray(dir, n);
			arrayAux= this.desplazar(arrayAux);
			arrayAux= this.fusionar(arrayAux);
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

	public String toString() {
		int cellSize = 7;
		String space = " " ;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String cadena = "";
		String aux ="",aux1="";
		String s = "";
		for (int j = 0; j < board.length; j++) {
			for(int i = 0; i < board.length;i++){
				
				cadena = String.valueOf(this.board[j][i].getValor());
				
				if(this.board[j][i].getValor() == 0)
				{
					cadena = space;
				}
				
				aux = MyStringUtils.repeat(vDelimiter + MyStringUtils.centre(cadena, cellSize), 1);
				s+= aux;
				 
			}
			
			aux1= vDelimiter + '\n' + MyStringUtils.repeat(hDelimiter, 8 * this.boardSize) + '\n';
			 s+= aux1;
		}
		
		return s ;
		
	}

	
	public void inicializaArray()
	{
		
		for (int i =0; i < this.boardSize;i++) {
			for(int j= 0; j < this.boardSize;j++) {
				this.board[i][j] = new Cell(0);
			}
		}
		
	}
	
	
	public boolean freeCells(){ 
		
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
	
	public boolean fin2048 () {

		return this.maxPunt == 2048;
	}
	
	
	public boolean masMovi() {
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
	
	public boolean isEmpty(Position pos) {
		
		return this.board[pos.getFila()][pos.getColumna()].isEmpty();
	}
	public  static void main (String args[]){
		MoveResults mov = new MoveResults(false,0,0);
		Cell tam = new Cell(4);
		Cell tam1 = new Cell (2);
		Cell tam2 = new Cell (2);
		Cell tam3 = new Cell (2);
		Cell tam4 = new Cell (3);
		Cell tam5 = new Cell (1);
		Cell tam6 = new Cell (3);
		Cell tam7 = new Cell (1);
		Cell tam8 = new Cell (2);
		Board tablero = new Board(3);
		Direction dire=  Direction.LEFT;
		tablero.board[0][0]= tam;
		tablero.board[0][1]= tam1;
		tablero.board[0][2]= tam8;
		tablero.board[1][1]= tam2;
		tablero.board[1][0]= tam3;
		tablero.board[1][2]= tam4;
		tablero.board[2][0]= tam5;
		tablero.board[2][1]= tam6;
		tablero.board[2][2]= tam7;
		System.out.println(tablero.toString());
		mov =tablero.executeMove(dire);
		System.out.println(tablero.toString());
		//tablero.freeCells();
		System.out.println(tablero.toString());
		
		
	}


	
}

