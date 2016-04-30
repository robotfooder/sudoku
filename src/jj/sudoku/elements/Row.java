package jj.sudoku.elements;

import java.awt.Color;
import java.awt.Graphics;

import jj.sudoku.constants.GameConstants;
import jj.sudoku.graphics.GameElement;

public class Row implements GameElement {
	private Cell[] rowArray;
	private Color stdColor = Color.blue;

	public Row() {
		this.rowArray = new Cell[GameConstants.CELLS];
	}

	@Override
	public void drawMe(Graphics g, Color c) {
		g.setColor(c);
		for (Cell cell : this.rowArray) {
			if (cell != null) {
				cell.drawMe(g, this.stdColor);
			}
		}

	}

	@Override
	public void drawMe(Graphics g) {
		drawMe(g, this.stdColor);

	}

	public void setRowArray(Cell[] rowArray) {
		this.rowArray = rowArray;

	}

	@Override
	public void removePossibleNumber(int number) {
		for (Cell cell : this.rowArray) {
			cell.removePossibleNumber(number);
		}

	}

}
