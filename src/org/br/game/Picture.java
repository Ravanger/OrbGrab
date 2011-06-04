package org.br.game;

//import java.awt.*;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.br.game.sprites.Ball;

public class Picture extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Sprite> listOfSprites = new ArrayList<Sprite>();

	public Picture() {
		super();
		this.setLocation(0, 85);
		this.setLayout(new FlowLayout());
		this.setSize(800, 600);
		this.setDoubleBuffered(true);
	}

	public List<Sprite> getListOfSprites() {
		return listOfSprites;
	}

	public void setListOfSprites(List<Sprite> listOfSprites) {
		this.listOfSprites = listOfSprites;
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, 800, 600);
		for (Sprite sprite : getListOfSprites()) {
			sprite.paint(g);
		}
	}
}
