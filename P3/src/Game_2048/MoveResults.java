package Game_2048;
//Clase que permite representar los resultados de la ejecución de un movimiento.
public class MoveResults {
	
	private boolean moved;
	private int score;
	
	public MoveResults(boolean movido,int score,int maxPunt) {
		this.moved = movido;
		this.score = score ;
	}
	
	
	public boolean isMoved() {
		return moved;
	}
	
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	

}
