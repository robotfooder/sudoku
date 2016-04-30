package jj.sudoku.elements;

import java.awt.Color;
import java.awt.Graphics;

import jj.sudoku.graphics.GameElement;

public class Section implements GameElement {

	private Cell[] sectionArray;
	private Color stdColor = Color.green;

	public Section(Cell[] sectionGrid) {
		this.sectionArray = sectionGrid;
	}

	@Override
	public void drawMe(Graphics g, Color c) {
		g.setColor(c);
		for (Cell cell : this.sectionArray) {
			if (cell != null) {
				cell.drawMe(g, this.stdColor);
			}
		}

	}

	@Override
	public void drawMe(Graphics g) {
		drawMe(g, this.stdColor);

	}

	public void setSectionArray(Cell[] sectionArray) {
		this.sectionArray = sectionArray;
	}

	@Override
	public void removePossibleNumber(int number) {
		for (Cell cell : this.sectionArray) {
			cell.removePossibleNumber(number);
		}

	}

}
