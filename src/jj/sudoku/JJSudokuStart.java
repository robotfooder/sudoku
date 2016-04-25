package jj.sudoku;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jj.sudoku.graphics.GameBoard;

public class JJSudokuStart {

	public static int SCREEN_WIDTH = 535;
	public static int SCREEN_HEIGHT = 600;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Cell[][] grid = new Cell[9][9];
				int cellsize = (SCREEN_WIDTH - GameBoard.OFFSET * 2) / 9;

				GameBoard board = loadScreen();

				initGame(cellsize, grid);
				board.setGrid(grid);

			}

			private void initGame(int cellSize, Cell[][] grid) {

				for (int x = 0; x < grid.length; x++) {
					for (int y = 0; y < grid.length; y++) {
						grid[x][y] = new Cell(x, y, cellSize);
					}
				}

			}

			private GameBoard loadScreen() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				GameBoard board = new GameBoard(SCREEN_WIDTH, SCREEN_HEIGHT);
				JFrame window = new JFrame();
				window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
				window.setResizable(false);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.add(board);
				window.setVisible(true);
				window.addMouseListener(board);
				window.addKeyListener(board);
				return board;

			}
		});

	}

}
