package jj.sudoku.game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import jj.sudoku.constants.GameConstants.Direction;
import jj.sudoku.elements.Cell;
import jj.sudoku.elements.Grid;
import jj.sudoku.elements.Row;
import jj.sudoku.graphics.GraphicElement;

public class GamePlay {

	List<GraphicElement> elements = new ArrayList<GraphicElement>();
	private Grid grid;
	private Row vertRow;
	private Row horRow;
	private Cell prevCell = null;

	public GamePlay() {
		this.grid = new Grid();
		this.vertRow = new Row();
		this.horRow = new Row();
		this.addElement(this.grid);
		this.addElement(this.vertRow);
		this.addElement(this.horRow);
	}

	public void tick(MouseEvent e) {

		System.out.println("X: " + e.getX() + "Y: " + e.getY());

		Cell cell = this.grid.getCell(e.getX(), e.getY());
		if (cell != null) {
			cell.printMe();
			cell.setActive(true);
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
		Cell[] rowArray = this.grid.getRow(cell, Direction.HORIZONATAL);
		this.horRow.setRowArray(rowArray);
		rowArray = this.grid.getRow(cell, Direction.VERTICAL);
		this.vertRow.setRowArray(rowArray);

	}

	public void addElement(GraphicElement element) {
		this.elements.add(element);

	}

	public void drawElements(Graphics g) {
		for (GraphicElement element : this.elements) {
			element.drawMe(g);

		}

	}

}
