package jj.sudoku.constants;

public class GameConstants {

	public static final int CELLSIZE = 55;
	public static final int OFFSET = 20;
	public static final int CELLS = 9;

	public enum Direction {
		HORIZONATAL, VERTICAL;
	}

	public enum ElementType {
		GRID, VERTICAL_ROW, HORIZONTAL_ROW, ACTIVE_CELL;
	}

}
