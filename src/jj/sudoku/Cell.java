package jj.sudoku;

import java.awt.Graphics;

import jj.sudoku.graphics.GameBoard;

public class Cell {

	private int x, y, value;
	private int cellSize;
	boolean active = false;

	public Cell(int x, int y, int cellSize) {
		this.x = GameBoard.OFFSET + x * cellSize;
		this.y = GameBoard.OFFSET + y * cellSize;
		this.cellSize = cellSize;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getCellSize() {
		return this.cellSize;
	}

	public void setActive(boolean b) {
		this.active = b;
	}

	public boolean isActive() {
		return this.active;
	}

	public int getXArrayPos() {
		return (this.x - GameBoard.OFFSET) / this.cellSize;
	}

	public int getYArrayPos() {
		return (this.y - GameBoard.OFFSET) / this.cellSize;
	}

	public void draw(Graphics g) {
		g.drawRect(getX(), getY(), this.cellSize, this.cellSize);

	}
}
