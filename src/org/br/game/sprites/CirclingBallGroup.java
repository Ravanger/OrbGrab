package org.br.game.sprites;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.br.game.GroupedSprite;
import org.br.game.Sprite;
import org.br.game.Vertex;

import Project.ASEFile;

/**
 * The concrete implementation of GroupedSprite with the group of 2 moving balls. One of the Ball is still, the second circles around it.
 * 
 * @author Boris
 */
public class CirclingBallGroup extends GroupedSprite {

	public CirclingBallGroup(List<Ball> sprites) {
		super(sprites);
	}

	// public String getName() {
	// return "CirclingBallGroup";
	// }

	@Override
	public void setClicked(boolean flag) {
		// nothing
	}

	@Override
	public boolean isClicked() {
		return false;
	}

	public Vertex getCenter() {
		Ball ball1 = (Ball) getGroup().get(0);
		Ball ball2 = (Ball) getGroup().get(1);
		Vertex center1 = ball1.getCenter();
		Vertex center2 = ball2.getCenter();
		List<Vertex> positions = new ArrayList<Vertex>(2);
		positions.add(center1);
		positions.add(center2);
		double[] groupCenter = Vertex.getMinMaxXYZ(positions);
		double centerZ = groupCenter[5] - groupCenter[2];
		double centerX = groupCenter[3] - groupCenter[0];
		double centerY = groupCenter[4] - groupCenter[1];
		Vertex center = new Vertex(centerX, centerY, centerZ);
		return center;
	}

	@Override
	public void move(double x, double y, double z) {
		for (Sprite sprite : getGroup()) {
			sprite.move(x, y, z);
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGraphics(Graphics g) {
		for(Sprite sprite : getGroup()){
			sprite.setGraphics(g);
		}
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}
}
