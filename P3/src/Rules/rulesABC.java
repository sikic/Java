package Rules;

import java.util.Random;

import Game_2048.Board;
import Game_2048.Cell;
import Game_2048.Position;

public class rulesABC implements GameRules {

	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		// TODO Auto-generated method stub
		char letra = rand.;
	}

	@Override
	public int merge(Cell self, Cell other) {
		return 0;
	}

	@Override
	public int getWinValue(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean win(Board board) {
		// TODO Auto-generated method stub
		return false;
	}

}
