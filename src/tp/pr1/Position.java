package tp.pr1;

//Clase que permite representar posiciones del tablero.

public class Position {
	private int fila;
	private int columna;
	
	public Position(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	public int getFila() {
		return fila;
	}
	
	public void setFila(int fila) {
		this.fila = fila;
	}
	
	public int getColumna() {
		return columna;
	}
	
	public void setColumna(int columna) {
		this.columna = columna;
	}
	//No utilizada.
	public Position neighbourd(Direction dir, int size){
		Position vecino = new Position(0,0);
		
		if(dir == Direction.UP && this.fila-- >= 0){
			vecino.fila = this.fila--;
			vecino.columna= this.columna;
		}
		else if(dir == Direction.DOWN && this.fila++ < size){
			vecino.fila = this.fila++;
			vecino.columna= this.columna;
			}
		else if(dir == Direction.RIGTH && this.columna++ < size){
			vecino.fila = this.fila;
			vecino.columna = this.columna++;
			}
		else if(dir == Direction.LEFT && this.columna-- >= 0){
			vecino.fila = this.fila;
			vecino.columna= this.columna--;
			}	
		return vecino;
	}
	


}
