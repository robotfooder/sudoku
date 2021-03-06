package jj.sudoku.graphics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import jj.sudoku.game.GamePlay;

public class GameBoard extends JPanel implements MouseListener, KeyListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -433695877996672967L;

	private GamePlay game;

	public GameBoard(GamePlay game) {
		this.game = game;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.game.drawElements(g);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.game.tick(e);
		repaint();

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
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_BACK_SPACE) {
			this.game.tick(0);
			repaint();
		} else {
			char key = e.getKeyChar();

			int keyValue = isNumeric(String.valueOf(key));
			if (keyValue != -1) {
				System.out.println(String.valueOf(key));
				this.game.tick(keyValue);
				repaint();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public int isNumeric(String key) {
		int n = -1;
		try {
			n = Integer.parseInt(key);
			if (n < 1 || n > 9) {
				System.out.println("Invalid number");
				return n;
			}
		} catch (NumberFormatException nFE) {
			System.out.println("Not an Integer");
			return n;
		}
		return n;
	}

}
