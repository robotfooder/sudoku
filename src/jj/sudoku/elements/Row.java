package jj.sudoku.elements;

import java.awt.Color;

public class Row extends AbstractSectionElement {

	private Color stdColor = Color.blue;

	public Row(Cell[] cells) {
		super(cells);
	}

	@Override
	public Color getColor() {
		return this.stdColor;
	}

}
