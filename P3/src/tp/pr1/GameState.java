package tp.pr1;

public class GameState {
	
	private int score;
	private int highest;
	private int [][] boardState;
	
	public GameState(int score, int highest,int boardSize){
		
		this.score = score;
		this.highest = highest;
		this.boardState = new int [boardSize][boardSize];
		for (int i =0; i < boardSize;i++) {
			for(int j= 0; j < boardSize;j++) {
				this.boardState[i][j] = 0;
			}
		}
		
	}
	
	public int[][] getBoardState() {
		return boardState;
	}

	public int getScored() {
		return this.score;
	}

	public int getMaxPunt() {
		return this.highest;
	}

	public int valueBoardState(int fila,int col){
		return this.boardState[fila][col];
	}
	

}
