package tp.pr1;
//Clase que permite representar los resultados de la ejecución de un movimiento.
public class MoveResults {
	
	private boolean moved;
	private int score;
	private int maxPunt;
	
	public MoveResults(boolean movido,int score,int maxPunt) {
		this.moved = movido;
		this.score = score ;
		this.maxPunt = maxPunt;
	}
	
	
	public boolean isMoved() {
		return moved;
	}
	
	
	public int getScore() {
		return score;
	}
	
	
	public int getMaxPunt() {
		return maxPunt;
	}
	


}
