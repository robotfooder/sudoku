package jj.sudoku.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jj.sudoku.constants.GameConstants;
import jj.sudoku.graphics.GameElement;

public class Cell implements GameElement {

	private int x, y;
	private int value = 0;
	boolean active = false;
	private Color stdColor = Color.red;
	private List<Integer> possibleNumbers = new ArrayList<Integer>();

	public Cell(int x, int y) {
		this.x = GameConstants.OFFSET + x * GameConstants.CELLSIZE;
		this.y = GameConstants.OFFSET + y * GameConstants.CELLSIZE;
		for (int n = 1; n <= GameConstants.CELLS; n++) {
			this.possibleNumbers.add(n);
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
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
		if (this.value > 0) {
			g.setFont(GameConstants.font);
			g.drawString(String.valueOf(this.value), this.getX() + (GameConstants.font.getSize() / 3),
					this.getY() + GameConstants.font.getSize());
		}
	}

	@Override
	public void drawMe(Graphics g) {
		drawMe(g, this.stdColor);
	}

	public void printMe() {
		System.out.println("Horizontal Cell: " + (this.getX() - GameConstants.OFFSET) / GameConstants.CELLSIZE);
		System.out.println("Vertical Cell: " + (this.getY() - GameConstants.OFFSET) / GameConstants.CELLSIZE);
		System.out.println("Valid numbers: " + this.possibleNumbers.toString());

	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
		if (value > 0) {
			this.possibleNumbers.clear();
		}
	}

	@Override
	public void removePossibleNumber(int number) {
		if (!this.possibleNumbers.isEmpty()) {
			Iterator<Integer> iter = this.possibleNumbers.iterator();
			while (iter.hasNext()) {
				Integer possibleNumber = iter.next();
				if (possibleNumber == number) {
					iter.remove();
					break;
				}
			}
		}

	}
}
