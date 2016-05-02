package jj.sudoku.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import jj.sudoku.constants.GameConstants;
import jj.sudoku.constants.GameConstants.Direction;
import jj.sudoku.graphics.GameElement;

public class Grid implements GameElement {

	Cell[][] gridArray = new Cell[GameConstants.CELLS][GameConstants.CELLS];
	private int gridSize;
	private Color stdColor = Color.black;
	Section[] sections;
	Row[] verticalRows, horisontalRows;
	private List<AbstractSectionElement[]> gridSections = new ArrayList<AbstractSectionElement[]>();

	public Grid() {
		for (int x = 0; x < this.gridArray.length; x++) {
			for (int y = 0; y < this.gridArray.length; y++) {
				this.gridArray[x][y] = new Cell(x, y);
			}
		}
		this.gridSize = GameConstants.CELLS * GameConstants.CELLSIZE;
		loadSections();
	}

	private void loadSections() {

		this.sections = getSections();
		this.verticalRows = getRows(Direction.VERTICAL);
		this.horisontalRows = getRows(Direction.HORIZONATAL);
		this.gridSections.add(this.sections);
		this.gridSections.add(this.verticalRows);
		this.gridSections.add(this.horisontalRows);

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

	private Cell[] getCellsInRow(Cell cell, Direction direction) {
		Cell[] row = new Cell[GameConstants.CELLS];

		for (int n = 0; n < row.length; n++) {
			if (direction.equals(Direction.HORIZONATAL)) {
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

	@Override
	public void addPossibleNumber(int number) {
		// TODO Auto-generated method stub

	}

	public Row[] getRows(Direction direction) {
		Row[] rows = new Row[GameConstants.CELLS];
		int x = 0;
		int y = 0;

		for (int n = 0; n < GameConstants.CELLS; n++) {
			if (direction.equals(Direction.HORIZONATAL)) {
				y = n;
			} else {
				x = n;
			}
			Cell startCell = getCell(x, y);
			Row row = new Row(getCellsInRow(startCell, direction));
			rows[n] = row;
		}

		return rows;
	}

	public Section[] getSections() {
		Section[] sections = new Section[GameConstants.CELLS];
		int row = 0;
		int col = 0;
		for (int n = 0; n < GameConstants.CELLS; n++) {
			col = (n / 3) * 3;
			row = (n % 3) * 3;
			Cell[] sectionGrid = new Cell[GameConstants.CELLS];
			int count = 0;
			for (int x = col; x < col + 3; x++) {
				for (int y = row; y < row + 3; y++) {
					sectionGrid[count] = getCell(x, y);
					count++;
				}
			}
			sections[n] = new Section(sectionGrid);
		}

		return sections;

	}

	public Row getRow(Cell cell, Direction direction) {
		Row row;
		if (direction.equals(Direction.HORIZONATAL)) {
			row = this.horisontalRows[cell.getYArrayPos()];
		} else {
			row = this.verticalRows[cell.getXArrayPos()];
		}
		return row;
	}

	public Section getSection(Cell cell) {
		int rowSection = cell.getYArrayPos() / 3;
		int colSection = cell.getXArrayPos() / 3;
		int arrayPos = rowSection * 1 + 3 * colSection;
		return this.sections[arrayPos];
	}

	public List<Cell> findUniqueCellsInSections() {
		List<Cell> uniqueCellsInSections = new ArrayList<Cell>();
		// loop through the sections
		for (AbstractSectionElement[] sections : this.gridSections) {
			for (AbstractSectionElement section : sections) {
				List<Cell> uniqueCellsInSection = section.findCellsWithUniquePossibleValue();
				for (Cell cell : uniqueCellsInSection) {
					if (!uniqueCellsInSections.contains(cell)) {
						uniqueCellsInSections.add(cell);
					}
				}
			}
		}
		return uniqueCellsInSections;
	}

}
