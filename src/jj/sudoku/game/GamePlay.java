package jj.sudoku.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jj.sudoku.constants.GameConstants;
import jj.sudoku.constants.GameConstants.Direction;
import jj.sudoku.constants.GameConstants.ElementType;
import jj.sudoku.elements.Cell;
import jj.sudoku.elements.Grid;
import jj.sudoku.elements.Row;
import jj.sudoku.elements.Section;
import jj.sudoku.elements.SectionElement;
import jj.sudoku.graphics.GameElement;

public class GamePlay {

	Map<ElementType, GameElement> activeElementsMap = new LinkedHashMap<ElementType, GameElement>();

	private Cell prevCell = null;
	private List<Cell> numberedCells = new ArrayList<Cell>();
	private List<Cell> onlyOnePossibleValueCells = new ArrayList<Cell>();
	private List<Cell> uniquePossibleValueInSectionCells = new ArrayList<Cell>();
	private Grid grid;

	public GamePlay() {

		// load the grid and all cells
		this.activeElementsMap.put(ElementType.GRID, new Grid());
		this.grid = getGrid();

	}

	private Grid getGrid() {
		return (Grid) this.activeElementsMap.get(ElementType.GRID);
	}

	public void tick(MouseEvent e) {

		System.out.println("X: " + e.getX() + "Y: " + e.getY());

		Cell cell = this.grid.getCellByCoordinates(e.getX(), e.getY());
		if (cell != null) {
			cell.printMe();
			cell.setActive(true);
			setActiveSections(cell);
			this.activeElementsMap.put(ElementType.ACTIVE_CELL, cell);
			if (this.prevCell == null) {
				this.prevCell = cell;
			} else {
				this.prevCell.setActive(false);
				this.prevCell = cell;
			}
		}
	}

	private void setActiveSections(Cell cell) {

		Row verticalRow = this.grid.getRow(cell, Direction.VERTICAL);
		Row horisontalRow = this.grid.getRow(cell, Direction.HORIZONATAL);
		Section section = this.grid.getSection(cell);

		this.activeElementsMap.put(ElementType.HORIZONTAL_ROW, horisontalRow);
		this.activeElementsMap.put(ElementType.VERTICAL_ROW, verticalRow);
		this.activeElementsMap.put(ElementType.ACTIVE_SECTION, section);
	}

	public void drawElements(Graphics g) {
		for (Cell cell : this.numberedCells) {
			cell.drawMe(g, Color.black);
		}
		for (Map.Entry<ElementType, GameElement> entry : this.activeElementsMap.entrySet()) {
			GameElement element = entry.getValue();
			element.drawMe(g);
		}

		for (Cell cell : this.onlyOnePossibleValueCells) {
			cell.drawMe(g, Color.MAGENTA);
		}

		for (Cell cell : this.uniquePossibleValueInSectionCells) {
			cell.drawMe(g, Color.ORANGE);
		}

	}

	public void tick(int keyValue) {
		Cell activeCell = (Cell) this.activeElementsMap.get(ElementType.ACTIVE_CELL);

		if (keyValue > 0) {
			if (activeCell.setValue(keyValue)) {
				this.numberedCells.add(activeCell);
				updateAffectedCellsAdd(keyValue);
			}
		} else {
			// remove number from cell
			updateAffectedCellsRemove(activeCell.getValue());
			activeCell.clearValue();
			setPossibleNumbersForCell(activeCell);
			Iterator<Cell> iter = this.numberedCells.iterator();
			while (iter.hasNext()) {
				if (iter.next().equals(activeCell)) {
					iter.remove();
					break;
				}
			}
		}
		findInterestingCells();

	}

	private void findInterestingCells() {
		findCellsWithOnePossibleValue();
		findCellsWithUniquePossibleValueInSection();

	}

	private void findCellsWithUniquePossibleValueInSection() {
		this.uniquePossibleValueInSectionCells.clear();
		List<Cell> cells = this.grid.findUniqueCellsInSections();
		this.uniquePossibleValueInSectionCells.addAll(cells);

	}

	private void findCellsWithOnePossibleValue() {
		this.onlyOnePossibleValueCells.clear();
		for (int x = 0; x < GameConstants.CELLS; x++) {
			for (int y = 0; y < GameConstants.CELLS; y++) {
				Cell cell = this.grid.getCell(x, y);
				if (cell.onlyOnePossible()) {
					this.onlyOnePossibleValueCells.add(cell);
				}
			}
		}
	}

	private void setPossibleNumbersForCell(Cell activeCell) {
		activeCell.makeAllNumbersPossible();
		for (Map.Entry<ElementType, GameElement> entry : this.activeElementsMap.entrySet()) {
			GameElement element = entry.getValue();
			if (element instanceof SectionElement) {
				SectionElement section = (SectionElement) element;
				activeCell.removePossibleNumbers(section.getTakenNumberForSection());
			}
		}

	}

	private void updateAffectedCellsRemove(int number) {
		for (Map.Entry<ElementType, GameElement> entry : this.activeElementsMap.entrySet()) {
			GameElement element = entry.getValue();
			element.addPossibleNumber(number);
		}

	}

	private void updateAffectedCellsAdd(int number) {
		for (Map.Entry<ElementType, GameElement> entry : this.activeElementsMap.entrySet()) {
			GameElement element = entry.getValue();
			element.removePossibleNumber(number);
		}

	}

}
