package jj.sudoku.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import jj.sudoku.constants.GameConstants;

public abstract class AbstractSectionElement implements SectionElement {

	private Cell[] sectionArray;

	public AbstractSectionElement() {
		this.sectionArray = new Cell[GameConstants.CELLS];
	}

	public AbstractSectionElement(Cell[] sectionArray) {
		this.sectionArray = sectionArray;
	}

	@Override
	public void drawMe(Graphics g, Color c) {
		g.setColor(c);
		for (Cell cell : this.sectionArray) {
			if (cell != null) {
				cell.drawMe(g, c);
			}
		}

	}

	public abstract Color getColor();

	@Override
	public void drawMe(Graphics g) {
		drawMe(g, getColor());

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

	@Override
	public void addPossibleNumber(int number) {
		for (Cell cell : this.sectionArray) {
			cell.addPossibleNumber(number);
		}

	}

	@Override
	public List<Integer> getTakenNumberForSection() {
		List<Integer> takenNumbers = new ArrayList<Integer>();
		for (Cell cell : this.sectionArray) {
			if (cell.getValue() > 0) {
				takenNumbers.add(cell.getValue());
			}
		}
		return takenNumbers;
	}

}
