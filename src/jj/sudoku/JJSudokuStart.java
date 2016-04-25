package jj.sudoku;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jj.sudoku.constants.GameConstants;
import jj.sudoku.game.GamePlay;
import jj.sudoku.graphics.GameBoard;

public class JJSudokuStart {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GamePlay game = initElements();
				loadScreen(game);

			}

			private GamePlay initElements() {
				GamePlay game = new GamePlay();
				return game;

			}

			private void loadScreen(GamePlay game) {
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
				int screenHeight = GameConstants.CELLS * GameConstants.CELLSIZE + GameConstants.OFFSET * 3;
				int screenWIdth = GameConstants.CELLS * GameConstants.CELLSIZE + GameConstants.OFFSET * 3;
				GameBoard board = new GameBoard(game);
				JFrame window = new JFrame();
				window.setSize(screenWIdth, screenHeight);
				window.setResizable(false);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.add(board);
				window.setVisible(true);
				window.addMouseListener(board);
				window.addKeyListener(board);

			}
		});

	}

}
