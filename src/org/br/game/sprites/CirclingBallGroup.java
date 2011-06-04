package org.br.game.sprites;

import java.util.ArrayList;
import java.util.List;

import org.br.game.Game;
import org.br.game.GroupedSprite;
import org.br.game.Sprite;
import org.br.game.Vertex;

/**
 * The concrete implementation of GroupedSprite with the group of 2 moving balls. One of the Ball is still, the second circles around it.
 * 
 * @author Boris
 */
public class CirclingBallGroup extends GroupedSprite {

	public CirclingBallGroup(List<Sprite> sprites) {
		super(sprites);
	}

	@Override
	public void setClicked(boolean flag) {
		// nothing
	}

	public void switchSpinning() {
		Ball ball1 = (Ball) getGroup().get(0);
		Ball ball2 = (Ball) getGroup().get(1);
		if (ball1.isClicked()) {
			ball1.setClicked(false);
			ball1.setCenterBall(null);// Removes center ball from ball1
			ball2.setClicked(true);
			ball2.setCenterBall(ball1);// set Center ball as ball1
			Game.getGame().getPlayer().still();
		}
		else {
			ball1.setClicked(true);
			ball2.setCenterBall(null);// Removes center ball from ball2
			ball2.setClicked(false);
			ball1.setCenterBall(ball2);// set Center ball as ball2
			Game.getGame().getPlayer().still();
		}
	}

	@Override
	public boolean isClicked() {
		return false;
	}

	/**
	 * Returns center vertex of the group, else returns null.
	 * 
	 * @return
	 */
	public Vertex getCenter() {
		Vertex result = null;
		for (Sprite sprite : getGroup()) {
			Ball ball = (Ball) sprite;
			if (ball.getCenterBall() != null) {
				result = ball.getGroupCenter();
				break;
			}
		}
		return result;
	}

	/*
	 * public Vertex getCenter() { Ball ball1 = (Ball) getGroup().get(0); Ball ball2 = (Ball) getGroup().get(1); Vertex center1 = ball1.getCenter(); Vertex center2 = ball2.getCenter(); List<Vertex> positions = new ArrayList<Vertex>(2); positions.add(center1); positions.add(center2); double[] groupCenter = Vertex.getMinMaxXYZ(positions); double centerZ = groupCenter[5] - groupCenter[2]; double centerX = groupCenter[3] - groupCenter[0]; double centerY = groupCenter[4] - groupCenter[1]; Vertex center = new Vertex(centerX, centerY, centerZ); return center; }
	 */

	/**
	 * Returns the stationary ball.
	 * 
	 * @return
	 */
	public Ball getCenterBall() {
		Ball center = null;
		for (Sprite sprite : getGroup()) {
			if (((Ball) sprite).getCenterBall() == null) {
				center = (Ball) sprite;
				break;
			}
		}
		return center;
	}

	@Override
	public void move(double x, double y, double z) {
		for (Sprite sprite : getGroup()) {
			sprite.move(x, y, z);
		}
	}

	@Override
	public void zoom(double a, Vertex center) {
		for (Sprite sprite : getGroup()) {
			sprite.zoom(a, center);
		}
	}

	public void repaintAll() {
	}

	@Override
	public void turnX(double a, Vertex center) {
		for (Sprite sprite : getGroup()) {
			sprite.turnX(a, center);
		}
	}

	@Override
	public void turnY(double a, Vertex center) {
		for (Sprite sprite : getGroup()) {
			sprite.turnY(a, center);
		}
	}

	@Override
	public void turnZ(double a, Vertex center) {
		for (Sprite sprite : getGroup()) {
			sprite.turnZ(a, center);
		}
	}
}