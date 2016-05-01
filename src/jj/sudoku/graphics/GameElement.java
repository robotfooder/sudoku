package jj.sudoku.graphics;

import java.awt.Color;
import java.awt.Graphics;

public interface GameElement {

	public void drawMe(Graphics g, Color c);

	public void drawMe(Graphics g);

	public void removePossibleNumber(int number);

	public void addPossibleNumber(int number);

}
