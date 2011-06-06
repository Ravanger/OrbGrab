package org.br.game;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;

public class GameListener implements MouseInputListener, KeyListener, MouseWheelListener {

	private Picture pic;
	private Game game;
	int mx = 0, my = 0;

	public GameListener(Picture pic, Game game) {
		this.pic = pic;
		this.game = game;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
			game.getPlayer().zoom(1.1, game.getPlayer().getCenter());
			pic.repaint();
		}
		else {
			game.getPlayer().zoom(0.9, game.getPlayer().getCenter());
			pic.repaint();
		}
	}

	public void mouseClicked(java.awt.event.MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			game.getPlayer().switchSpinning();
			pic.repaint();
		}
	}

	public void mouseReleased(java.awt.event.MouseEvent e) {
	}

	public void mouseEntered(java.awt.event.MouseEvent e) {
	}

	public void mouseExited(java.awt.event.MouseEvent e) {
	}

	public void mouseDragged(java.awt.event.MouseEvent e) {
		int x = e.getX(), y = e.getY();
		if (e.getModifiers() == InputEvent.BUTTON2_MASK) {// Middle mouse button
			turnXY(x - mx, y - my);
			pic.repaint();
		}
		else if (e.getModifiers() == InputEvent.BUTTON3_MASK) {// Right mouse button
			turnZ(x - mx);
			pic.repaint();
		}
		mx = x;
		my = y;
	}

	private void turnZ(int a) {
		game.getPlayer().turnZ(a, game.getPlayer().getCenterBall().getCenter());
	}

	private void turnXY(int a, int b) {
		// a is for x, b is for y.
		game.getPlayer().turnX(a, game.getPlayer().getCenterBall().getCenter());
		game.getPlayer().turnY(b, game.getPlayer().getCenterBall().getCenter());
	}

	public void mouseMoved(java.awt.event.MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void mousePressed(java.awt.event.MouseEvent e) {
		this.mx = e.getX();
		this.my = e.getY();
		e.consume();
	}
}
