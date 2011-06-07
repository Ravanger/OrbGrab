package org.br.game;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

/**
 * Listener for the game class. Contains mouse and key actions for controlling the game.
 * 
 * @author Boris
 */
public class GameListener implements MouseInputListener, KeyListener {

	private Game game;
	private Picture pic;

	public GameListener(Game game, Picture pic) {
		this.game = game;
		this.pic = pic;
	}

	/**
	 * On mouse click, calls the switchSpinning() function which switches the active ball.
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			game.getPlayer().switchSpinning();
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 72) { // 72 = H
			JOptionPane.showMessageDialog(pic, "OrbGrab is a simple game. You control a group of two balls. One of the balls spins around the other one. \nWhen you press the left mouse button, the balls switch roles and the second ball starts spinning around the first. \nThe object of the game is to move the group and collect all of the cubes to raise your score. \nBut stay away from the edges of the screen! If you hit the edge you get bounced back and lose points!");
		}
	}
}