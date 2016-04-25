package jj.sudoku.game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import jj.sudoku.constants.GameConstants.Direction;
import jj.sudoku.constants.GameConstants.ElementType;
import jj.sudoku.elements.Cell;
import jj.sudoku.elements.Grid;
import jj.sudoku.elements.Row;
import jj.sudoku.graphics.GraphicElement;

public class GamePlay {

	Map<ElementType, GraphicElement> elementsMap = new LinkedHashMap<ElementType, GraphicElement>();

	private Cell prevCell = null;

	public GamePlay() {

		this.elementsMap.put(ElementType.GRID, new Grid());
		this.elementsMap.put(ElementType.VERTICAL_ROW, new Row());
		this.elementsMap.put(ElementType.HORIZONTAL_ROW, new Row());
	}

	public void tick(MouseEvent e) {

		System.out.println("X: " + e.getX() + "Y: " + e.getY());
		Grid grid = (Grid) this.elementsMap.get(ElementType.GRID);
		Cell cell = grid.getCell(e.getX(), e.getY());
		if (cell != null) {
			cell.printMe();
			cell.setActive(true);
			this.elementsMap.put(ElementType.ACTIVE_CELL, cell);
			calculateAffectedCells(cell);
			if (this.prevCell == null) {
				this.prevCell = cell;
			} else {
				this.prevCell.setActive(false);
				this.prevCell = cell;
			}

		}

	}

	private void calculateAffectedCells(Cell cell) {
		Grid grid = (Grid) this.elementsMap.get(ElementType.GRID);
		Cell[] rowArray = grid.getRow(cell, Direction.HORIZONATAL);
		Row horRow = (Row) this.elementsMap.get(ElementType.HORIZONTAL_ROW);
		Row vertRow = (Row) this.elementsMap.get(ElementType.VERTICAL_ROW);
		horRow.setRowArray(rowArray);
		rowArray = grid.getRow(cell, Direction.VERTICAL);
		vertRow.setRowArray(rowArray);

	}

	public void drawElements(Graphics g) {
		for (Map.Entry<ElementType, GraphicElement> entry : this.elementsMap.entrySet()) {
			GraphicElement element = entry.getValue();
			element.drawMe(g);
		}

	}

}
