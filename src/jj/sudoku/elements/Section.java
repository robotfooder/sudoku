package jj.sudoku.elements;

import java.awt.Color;

public class Section extends AbstractSectionElement {

	private Color stdColor = Color.green;

	public Section(Cell[] sectionGrid) {
		super(sectionGrid);
	}

	@Override
	public Color getColor() {
		return this.stdColor;
	}

}
