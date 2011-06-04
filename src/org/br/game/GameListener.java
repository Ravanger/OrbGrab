package org.br.game;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;

import org.br.game.sprites.CirclingBallGroup;

public class GameListener implements MouseInputListener, KeyListener, MouseWheelListener {

	private Picture pic;
	private Graphics g;
	private CirclingBallGroup player;
	int mx = 0, my = 0;

	public GameListener(Picture pic, CirclingBallGroup player) {
		this.pic = pic;
		this.player = player;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
			player.zoom(1.1, player.getCenter());
			pic.repaint();
		}
		else {
			player.zoom(0.9, player.getCenter());
			pic.repaint();
		}
	}

	public void mouseClicked(java.awt.event.MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			player.switchSpinning();
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
		player.turnZ(a, player.getCenterBall().getCenter());
	}

	private void turnXY(int a, int b) {
		// a is for x, b is for y.
		player.turnX(a, player.getCenterBall().getCenter());
		player.turnY(b, player.getCenterBall().getCenter());
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
