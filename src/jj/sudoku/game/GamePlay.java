package jj.sudoku.game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import jj.sudoku.constants.GameConstants;
import jj.sudoku.constants.GameConstants.Direction;
import jj.sudoku.constants.GameConstants.ElementType;
import jj.sudoku.elements.Cell;
import jj.sudoku.elements.Grid;
import jj.sudoku.elements.Row;
import jj.sudoku.elements.Section;
import jj.sudoku.graphics.GraphicElement;

public class GamePlay {

	Map<ElementType, GraphicElement> elementsMap = new LinkedHashMap<ElementType, GraphicElement>();
	Section[] sectionArray = new Section[GameConstants.CELLS];

	private Cell prevCell = null;

	public GamePlay() {

		this.elementsMap.put(ElementType.GRID, new Grid());
		this.elementsMap.put(ElementType.VERTICAL_ROW, new Row());
		this.elementsMap.put(ElementType.HORIZONTAL_ROW, new Row());
		loadSections();
	}

	private void loadSections() {
		Grid grid = this.getGrid();
		int row = 0;
		int col = 0;
		for (int n = 0; n < GameConstants.CELLS; n++) {
			col = (n / 3) * 3;
			row = (n % 3) * 3;
			Cell[] sectionGrid = new Cell[GameConstants.CELLS];
			int count = 0;
			for (int x = col; x < col + 3; x++) {
				for (int y = row; y < row + 3; y++) {
					sectionGrid[count] = grid.getCell(x, y);
					count++;
				}
			}
			this.sectionArray[n] = new Section(sectionGrid);
		}

	}

	private Grid getGrid() {
		return (Grid) this.elementsMap.get(ElementType.GRID);
	}

	public void tick(MouseEvent e) {

		System.out.println("X: " + e.getX() + "Y: " + e.getY());
		Grid grid = getGrid();
		Cell cell = grid.getCellByCoordinates(e.getX(), e.getY());
		if (cell != null) {
			cell.printMe();
			cell.setActive(true);
			calculateAffectedCells(cell);
			this.elementsMap.put(ElementType.ACTIVE_CELL, cell);
			if (this.prevCell == null) {
				this.prevCell = cell;
			} else {
				this.prevCell.setActive(false);
				this.prevCell = cell;
			}

		}

	}

	private void calculateAffectedCells(Cell cell) {
		Grid grid = getGrid();
		Cell[] rowArray = grid.getRow(cell, Direction.HORIZONATAL);
		Row horRow = (Row) this.elementsMap.get(ElementType.HORIZONTAL_ROW);
		Row vertRow = (Row) this.elementsMap.get(ElementType.VERTICAL_ROW);
		horRow.setRowArray(rowArray);
		rowArray = grid.getRow(cell, Direction.VERTICAL);
		vertRow.setRowArray(rowArray);
		this.elementsMap.put(ElementType.ACTIVE_SECTION, this.getActiveSection(cell));

	}

	private Section getActiveSection(Cell cell) {
		int rowSection = cell.getYArrayPos() / 3;
		int colSection = cell.getXArrayPos() / 3;
		int arrayPos = rowSection * 1 + 3 * colSection;
		return this.sectionArray[arrayPos];
	}

	public void drawElements(Graphics g) {
		for (Map.Entry<ElementType, GraphicElement> entry : this.elementsMap.entrySet()) {
			GraphicElement element = entry.getValue();
			element.drawMe(g);
		}

	}

	public void tick(int keyValue) {
		Cell activeCell = (Cell) this.elementsMap.get(ElementType.ACTIVE_CELL);
		activeCell.setValue(keyValue);

	}

}
