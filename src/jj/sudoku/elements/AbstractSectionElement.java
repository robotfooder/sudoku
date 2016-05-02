package jj.sudoku.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Cell> findCellsWithUniquePossibleValue() {
		List<Cell> cells = new ArrayList<Cell>();
		Map<Integer, Integer> uniqueValues = new HashMap<Integer, Integer>();
		for (Cell cell : this.sectionArray) {
			List<Integer> possibleNumbers = cell.getPossibleNumbers();
			for (Integer possibleNumber : possibleNumbers) {
				Integer count = uniqueValues.get(possibleNumber);
				if (count == null) {
					count = 1;
				} else {
					count++;
				}
				uniqueValues.put(possibleNumber, count);
			}
		}
		for (Map.Entry<Integer, Integer> entry : uniqueValues.entrySet()) {
			Integer count = entry.getValue();
			if (count == 1) {
				Cell cell = getCellWithPossibleValue(entry.getKey());
				if (cell != null) {
					cells.add(cell);
					cell.setUniqueValueForSection(entry.getKey());
				}
			}
		}
		return cells;
	}

	private Cell getCellWithPossibleValue(Integer key) {
		for (Cell cell : this.sectionArray) {
			List<Integer> possibleNumbers = cell.getPossibleNumbers();
			for (Integer possibleNumber : possibleNumbers) {
				if (possibleNumber == key) {
					return cell;
				}
			}
		}
		return null;
	}

}
