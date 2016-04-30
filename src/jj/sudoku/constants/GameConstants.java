package jj.sudoku.constants;

import java.awt.Font;

public class GameConstants {

	public static final int CELLSIZE = 55;
	public static final int OFFSET = 20;
	public static final int CELLS = 9;
	private static int fontSize = (int) (GameConstants.CELLSIZE / 1.2D);
	public static Font font = new Font(Font.DIALOG, Font.PLAIN, fontSize);

	public enum Direction {
		HORIZONATAL, VERTICAL;
	}

	public enum ElementType {
		GRID, VERTICAL_ROW, HORIZONTAL_ROW, ACTIVE_CELL, ACTIVE_SECTION;
	}

}
