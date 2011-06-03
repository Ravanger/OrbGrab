package org.br.game;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.br.game.sprites.Ball;
import org.br.game.sprites.CirclingBallGroup;

public class Picture extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Picture() {
		super();
		this.setLocation(0, 85);
		this.setLayout(new FlowLayout());
		this.setSize(800, 600);
		this.setDoubleBuffered(true);
	}

	public void paintBall(Ball shape, Graphics g) {
		// g.clearRect(0, 0, 800, 600);
		ArrayList<Triangle> triangles = shape.perspectiveProjection();
		for (int i = 0; i < triangles.size(); i++) {
			triangles.get(i).FillTriangle(g);
		}
	}

	public void paintBallGroup(CirclingBallGroup player, Graphics g) {
		g.clearRect(0, 0, 800, 600);
		for (Sprite sprite : player.getGroup()) {
			paintBall((Ball) sprite, g);
		}
	}

	public void paintHelp(Graphics g) {
		String helpstring1 = "Click the left mouse button to change the spinning ball.";
		String helpstring2 = "Collect all the cubes in the minimum amount of clicks.";
		g.clearRect(0, 0, 800, 600);
		g.drawString(helpstring1, 100, 100);
		g.drawString(helpstring2, 100, 130);
	}
}
