package org.br.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Paints Main Menu and its buttons
 * 
 * @author Lone Wolf
 */
public class MainMenu extends JPanel implements KeyListener {

	private Image bgimg = (new ImageIcon("img/title.png").getImage());

	public MainMenu() {
		super();
		// this.setLayout(null);
	}

	/** Paints the panel and it's components. **/
	public void paint(Graphics g) {
		g.drawImage(bgimg, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == 10 || arg0.getKeyCode() == 32) { // 10 = Enter, 32 = spacebar
			Game.game.setStartGame(true);
		}
		else if (arg0.getKeyCode() == 72) { // 72 = H
			// ADD HELP PANEL HERE
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
