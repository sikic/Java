package Rules;

import java.util.Random;

import Game_2048.Board;
import Game_2048.Cell;
import Game_2048.Position;

public class Rules2048 implements GameRules {
	
	private static final int WIN = 2048;
	
	public void addNewCellAt(Board board, Position pos, Random rand) {
		
		int num = rand.nextInt(10);
		
		if (num < 8)
			num = 2;
		
		else
			num = 4;
		
		board.setCell(pos, num);
	}
	
	public int merge(Cell self, Cell other) {
		
		if (self.getValor() == other.getValor())
		{
			other.setValor(self.getValor() + other.getValor());
			self.setValor(0);
			
			return other.getValor();
		}
		else
			return 0;
		
	}
	
	public int getWinValue(Board board) {
		
		
		return board.maximoNum();
	}
	
	public boolean win(Board board) {
		
		if ( this.getWinValue(board)== WIN ) {
			return true;
		}
		else
			return false;
	}
	
}
