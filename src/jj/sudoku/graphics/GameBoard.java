package jj.sudoku.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import jj.sudoku.Cell;

public class GameBoard extends JPanel implements MouseListener, KeyListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -433695877996672967L;
	public static int OFFSET = 20;
	private int boardsize = 0;
	private int cellsize = 0;
	private Cell[][] grid;
	private Cell prevCell = null;

	public GameBoard(int width, int height) {

		this.boardsize = width - OFFSET * 2;

		this.cellsize = this.boardsize / 9;
		System.out.println("boardsize: " + this.boardsize);
		System.out.println("cellsize: " + this.cellsize);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(OFFSET, OFFSET, this.boardsize, this.boardsize);
		for (int i = 1; i * this.cellsize <= this.boardsize; i++) {
			g.drawLine(i * this.cellsize + OFFSET, OFFSET, i * this.cellsize + OFFSET, this.boardsize + OFFSET);
			g.drawLine(OFFSET, i * this.cellsize + OFFSET, this.boardsize + OFFSET, i * this.cellsize + OFFSET);
		}

		for (Cell[] xRow : this.grid) {
			for (Cell cell : xRow) {
				if (cell.isActive()) {
					g.setColor(Color.red);
					g.drawRect(cell.getX(), cell.getY(), cell.getCellSize(), cell.getCellSize());
					break;
				}
			}
		}
	}

	public int getCellsize() {
		return this.cellsize;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int xSec = -1;
		int ySec = -1;
		if (e.getX() - GameBoard.OFFSET >= 0) {
			xSec = (e.getX() - GameBoard.OFFSET) / this.cellsize;
		}
		if (e.getY() - GameBoard.OFFSET >= 0) {
			ySec = (e.getY() - GameBoard.OFFSET * 2) / this.cellsize;
		}
		if (xSec >= 0 && ySec >= 0) {
			System.out.println("xSec: " + xSec + "ySec: " + ySec);

			Cell cell = this.grid[xSec][ySec];
			cell.setActive(true);
			if (this.prevCell == null) {
				this.prevCell = cell;
			} else {
				this.prevCell.setActive(false);
				this.prevCell = cell;
			}
			repaint();
		}
		System.out.println("X: " + e.getX() + "Y: " + e.getY());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;

	}

}
