package org.br.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Paints Main Menu and adds listeners to it.
 * 
 * @author Boris
 */
public class MainMenu extends JPanel implements KeyListener {

	private Image bgimg = (new ImageIcon("img/title.png").getImage());

	public MainMenu() {
		setFocusable(true);
		addKeyListener(this);
	}

	public void paint(Graphics g) {
		g.drawImage(bgimg, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == 10 || arg0.getKeyCode() == 32) { // 10 = Enter, 32 = space bar
			Game.GAME.setStartGame(true);
			Game.GAME.startGame();
		}
		else if (arg0.getKeyCode() == 72) { // 72 = H
			JOptionPane.showMessageDialog(this, "OrbGrab is a simple game. You control a group of two balls. One of the balls spins around the other one. \nWhen you press the left mouse button, the balls switch roles and the second ball starts spinning around the first. \nThe object of the game is to move the group and collect all of the cubes to raise your score. \nBut stay away from the edges of the screen! If you hit the edge you get bounced back and lose points!");
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
