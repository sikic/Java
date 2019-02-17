package Rules;

import java.util.Random;

import Game_2048.Board;
import Game_2048.Cell;
import Game_2048.Position;

public interface GameRules {

	void addNewCellAt(Board board, Position pos, Random rand);
	int merge(Cell self, Cell other);
	int getWinValue(Board board);
	boolean win(Board board);
	default boolean lose(Board board) {
		
		boolean perdido= false;
		if (!board.masMovi() && !board.freeCells()){
			perdido = true;
		}
		return perdido;
	}
	
	default Board createBoard(int size) {	//Cuya implementacion por defecto crea y devuelve un tabler size × size

		return new Board(size);
		
	}

	default void addNewCell(Board board, Random rand) {// cuya implementacion por defecto elige una posición libre de board e invoca el método addNewCellAt() para añadir una célula en	esa posición,
	
		int x = rand.nextInt(board.getBoardSize());
		int y = rand.nextInt(board.getBoardSize());
		Position pos = new Position(x,y);
		
		while(!board.isEmpty(pos)) {
			x = rand.nextInt(board.getBoardSize());
			y = rand.nextInt(board.getBoardSize());
			pos = new Position(x,y);
		}
		
		addNewCellAt(board,pos,rand);
			
	}
	
	default void initBoard(Board board, int numCells, Random rand) {//cuya implementación por defecto inicializa board eligiendo numCells	posiciones libres, e invoca el método addNewCellAt() para añadir nuevas células en esas posiciones.
		
		for (int i = 0; i < numCells ; i++) {
			 addNewCell(board, rand);
		}
	}
	
}
