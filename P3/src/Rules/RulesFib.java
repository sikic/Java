package Rules;

import java.util.Random;

import Game_2048.Board;
import Game_2048.Cell;
import Game_2048.Position;
import utils.MyMathsUtil;

public class RulesFib implements GameRules{ 
	
	private static final int WIN = 144;
	
	public void addNewCellAt(Board board, Position pos, Random rand) {
		
		int num = rand.nextInt(10);
		
		if (num < 8)
			num = 1;
		
		else
			num = 2;
		
		board.setCell(pos, num);
	}

	public int merge(Cell self, Cell other) {
		
		if(self.getValor() == 1 && other.getValor() == 1) {
			
			other.setValor(self.getValor() + other.getValor());
			self.setValor(0);
			
			return other.getValor();
		}
		
		else if ( MyMathsUtil.nextFib(self.getValor()) == other.getValor())
		{
			other.setValor(MyMathsUtil.nextFib(other.getValor()));
			self.setValor(0);
			
			return other.getValor();
		}
		else if ((MyMathsUtil.nextFib(self.getValor()) - self.getValor()) == other.getValor()) {
			self.setValor(MyMathsUtil.nextFib(self.getValor()));
			other.setValor(0);
			return  self.getValor();
			
		}
		else
			return 0;
		
	}
	
	public int getWinValue(Board board) {
		
		return board.maximoNum();
	}
	
	public boolean win(Board board) {
		
	if ( this.getWinValue(board)== WIN )
		return true;
	
	else
		return false;
	}
}

