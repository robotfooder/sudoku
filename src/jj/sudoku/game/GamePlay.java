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

	Map<ElementType, GameElement> elementsMap = new LinkedHashMap<ElementType, GameElement>();
	Section[] sectionArray = new Section[GameConstants.CELLS];

	private Cell prevCell = null;
	private List<Cell> numberedCells = new ArrayList<Cell>();
	private List<Cell> onlyOnePossibleCells = new ArrayList<Cell>();

	public GamePlay() {

		this.elementsMap.put(ElementType.GRID, new Grid());
		this.elementsMap.put(ElementType.VERTICAL_ROW, new Row());
		this.elementsMap.put(ElementType.HORIZONTAL_ROW, new Row());
		loadSections();
	}

	private void loadSections() {
		Grid grid = this.getGrid();
		int row = 0;
		int col = 0;
		for (int n = 0; n < GameConstants.CELLS; n++) {
			col = (n / 3) * 3;
			row = (n % 3) * 3;
			Cell[] sectionGrid = new Cell[GameConstants.CELLS];
			int count = 0;
			for (int x = col; x < col + 3; x++) {
				for (int y = row; y < row + 3; y++) {
					sectionGrid[count] = grid.getCell(x, y);
					count++;
				}
			}
			this.sectionArray[n] = new Section(sectionGrid);
		}

	}

	private Grid getGrid() {
		return (Grid) this.elementsMap.get(ElementType.GRID);
	}

	public void tick(MouseEvent e) {

		System.out.println("X: " + e.getX() + "Y: " + e.getY());
		Grid grid = getGrid();
		Cell cell = grid.getCellByCoordinates(e.getX(), e.getY());
		if (cell != null) {
			cell.printMe();
			cell.setActive(true);
			calculateAffectedCells(cell);
			this.elementsMap.put(ElementType.ACTIVE_CELL, cell);
			if (this.prevCell == null) {
				this.prevCell = cell;
			} else {
				this.prevCell.setActive(false);
				this.prevCell = cell;
			}
		}
	}

	private void calculateAffectedCells(Cell cell) {
		Grid grid = getGrid();
		Cell[] rowArray = grid.getRow(cell, Direction.HORIZONATAL);
		Row horRow = (Row) this.elementsMap.get(ElementType.HORIZONTAL_ROW);
		Row vertRow = (Row) this.elementsMap.get(ElementType.VERTICAL_ROW);
		horRow.setSectionArray(rowArray);
		rowArray = grid.getRow(cell, Direction.VERTICAL);
		vertRow.setSectionArray(rowArray);
		this.elementsMap.put(ElementType.ACTIVE_SECTION, this.getActiveSection(cell));
	}

	private Section getActiveSection(Cell cell) {
		int rowSection = cell.getYArrayPos() / 3;
		int colSection = cell.getXArrayPos() / 3;
		int arrayPos = rowSection * 1 + 3 * colSection;
		return this.sectionArray[arrayPos];
	}

	public void drawElements(Graphics g) {
		for (Cell cell : this.numberedCells) {
			cell.drawMe(g, Color.black);
		}
		for (Map.Entry<ElementType, GameElement> entry : this.elementsMap.entrySet()) {
			GameElement element = entry.getValue();
			element.drawMe(g);
		}

		for (Cell cell : this.onlyOnePossibleCells) {
			cell.drawMe(g, Color.MAGENTA);
		}

	}

	public void tick(int keyValue) {
		Cell activeCell = (Cell) this.elementsMap.get(ElementType.ACTIVE_CELL);

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
		this.onlyOnePossibleCells.clear();
		Grid grid = this.getGrid();
		for (int x = 0; x < GameConstants.CELLS; x++) {
			for (int y = 0; y < GameConstants.CELLS; y++) {
				Cell cell = grid.getCell(x, y);
				if (cell.onlyOnePossible()) {
					this.onlyOnePossibleCells.add(cell);
				}

			}
		}
	}

	private void setPossibleNumbersForCell(Cell activeCell) {
		activeCell.makeAllNumbersPossible();
		for (Map.Entry<ElementType, GameElement> entry : this.elementsMap.entrySet()) {
			GameElement element = entry.getValue();
			if (element instanceof SectionElement) {
				SectionElement section = (SectionElement) element;
				activeCell.removePossibleNumbers(section.getTakenNumberForSection());
			}
		}

	}

	private void updateAffectedCellsRemove(int number) {
		for (Map.Entry<ElementType, GameElement> entry : this.elementsMap.entrySet()) {
			GameElement element = entry.getValue();
			element.addPossibleNumber(number);
		}

	}

	private void updateAffectedCellsAdd(int number) {
		for (Map.Entry<ElementType, GameElement> entry : this.elementsMap.entrySet()) {
			GameElement element = entry.getValue();
			element.removePossibleNumber(number);
		}

	}

}
