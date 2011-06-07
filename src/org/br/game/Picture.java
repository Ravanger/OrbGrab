package org.br.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This class handles all of the painting.
 * 
 * @author Boris
 */
public class Picture extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Sprite> listOfSprites = new ArrayList<Sprite>();
	private Sprite centerBall, spinningBall;
	private Image bgimg = (new ImageIcon("img/bg.png").getImage());

	public Picture() {
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
		Vertex one = centerBall.getCenter();
		Vertex two = spinningBall.getCenter();
		g.clearRect(0, 0, 800, 600);
		g.drawImage(bgimg, 0, 0, getWidth(), getHeight(), this);
		g.drawLine((int) one.getX(), (int) one.getY(), (int) two.getX(), (int) two.getY());
		g.drawString("Score: " + Game.getGame().getScore(), Game.getGame().getWidth() - 100, 20);
		for (Sprite sprite : getListOfSprites()) {
			sprite.paint(g);
		}
	}

	public Sprite getCenterBall() {
		return centerBall;
	}

	public void setCenterBall(Sprite centerBall) {
		this.centerBall = centerBall;
	}

	public Sprite getSpinningBall() {
		return spinningBall;
	}

	public void setSpinningBall(Sprite spinningBall) {
		this.spinningBall = spinningBall;
	}

}
