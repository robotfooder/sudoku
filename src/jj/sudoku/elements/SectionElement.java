package jj.sudoku.elements;

import java.util.List;

import jj.sudoku.graphics.GameElement;

public interface SectionElement extends GameElement {

	public List<Integer> getTakenNumberForSection();

}
