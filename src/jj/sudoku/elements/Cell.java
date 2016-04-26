package jj.sudoku.elements;

import java.awt.Color;
import java.awt.Graphics;

import jj.sudoku.constants.GameConstants;
import jj.sudoku.graphics.GraphicElement;

public class Cell implements GraphicElement {

	private int x, y, value;

	boolean active = false;
	private Color stdColor = Color.red;

	public Cell(int x, int y) {
		this.x = GameConstants.OFFSET + x * GameConstants.CELLSIZE;
		this.y = GameConstants.OFFSET + y * GameConstants.CELLSIZE;

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

	public void setActive(boolean b) {
		this.active = b;
	}

	public boolean isActive() {
		return this.active;
	}

	public int getXArrayPos() {
		return (this.x - GameConstants.OFFSET) / GameConstants.CELLSIZE;
	}

	public int getYArrayPos() {
		return (this.y - GameConstants.OFFSET) / GameConstants.CELLSIZE;
	}

	@Override
	public void drawMe(Graphics g, Color c) {
		g.setColor(c);
		g.drawRect(getX(), getY(), GameConstants.CELLSIZE, GameConstants.CELLSIZE);
		if (this.value != 0) {
			g.drawString(String.valueOf(this.value), this.getX() + GameConstants.CELLSIZE / 2,
					this.getY() + GameConstants.CELLSIZE / 2);
		}

	}

	@Override
	public void drawMe(Graphics g) {
		drawMe(g, this.stdColor);
	}

	public void printMe() {
		System.out.println("Horizontal Cell: " + (this.getX() - GameConstants.OFFSET) / GameConstants.CELLSIZE);
		System.out.println("Vertical Cell: " + (this.getY() - GameConstants.OFFSET) / GameConstants.CELLSIZE);

	}
}
