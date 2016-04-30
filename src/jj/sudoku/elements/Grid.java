package jj.sudoku.elements;

import java.awt.Color;
import java.awt.Graphics;

import jj.sudoku.constants.GameConstants;
import jj.sudoku.constants.GameConstants.Direction;
import jj.sudoku.graphics.GameElement;

public class Grid implements GameElement {

	Cell[][] gridArray = new Cell[GameConstants.CELLS][GameConstants.CELLS];
	private int gridSize;
	private Color stdColor = Color.black;

	public Grid() {
		for (int x = 0; x < this.gridArray.length; x++) {
			for (int y = 0; y < this.gridArray.length; y++) {
				this.gridArray[x][y] = new Cell(x, y);
			}
		}
		this.gridSize = GameConstants.CELLS * GameConstants.CELLSIZE;
	}

	@Override
	public void drawMe(Graphics g, Color c) {
		g.setColor(c);
		g.drawRect(GameConstants.OFFSET, GameConstants.OFFSET, this.gridSize, this.gridSize);
		for (int i = 1; i * GameConstants.CELLSIZE <= this.gridSize; i++) {
			g.drawLine(i * GameConstants.CELLSIZE + GameConstants.OFFSET, GameConstants.OFFSET,
					i * GameConstants.CELLSIZE + GameConstants.OFFSET, this.gridSize + GameConstants.OFFSET);
			g.drawLine(GameConstants.OFFSET, i * GameConstants.CELLSIZE + GameConstants.OFFSET,
					this.gridSize + GameConstants.OFFSET, i * GameConstants.CELLSIZE + GameConstants.OFFSET);
		}

	}

	@Override
	public void drawMe(Graphics g) {
		this.drawMe(g, this.stdColor);

	}

	public Cell getCellByCoordinates(int x, int y) {
		int xSec = -1;
		int ySec = -1;
		if (x - GameConstants.OFFSET >= 0) {
			xSec = (x - GameConstants.OFFSET) / GameConstants.CELLSIZE;
		}
		if (y - GameConstants.OFFSET >= 0) {
			ySec = (y - GameConstants.OFFSET * 2) / GameConstants.CELLSIZE;
		}
		if (xSec >= 0 && ySec >= 0) {
			return this.gridArray[xSec][ySec];

		}

		return null;
	}

	public Cell getCell(int x, int y) {
		return this.gridArray[x][y];
	}

	public Cell[] getRow(Cell cell, Direction dir) {
		Cell[] row = new Cell[GameConstants.CELLS];

		for (int n = 0; n < row.length; n++) {
			if (dir.equals(Direction.HORIZONATAL)) {
				row[n] = this.gridArray[n][cell.getYArrayPos()];
			} else {
				row[n] = this.gridArray[cell.getXArrayPos()][n];
			}

		}
		return row;
	}

	@Override
	public void removePossibleNumber(int Number) {
		// TODO Auto-generated method stub

	}

}
