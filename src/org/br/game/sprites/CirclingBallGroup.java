package org.br.game.sprites;

import java.util.List;

import org.br.game.GroupedSprite;
import org.br.game.Sprite;
import org.br.game.Vertex;

/**
 * The concrete implementation of GroupedSprite with the group of 2 moving ball sprites. One of the sprites is still, the second circles around it.
 * 
 * @author Boris
 */
public class CirclingBallGroup extends GroupedSprite {

	public CirclingBallGroup(List<Sprite> sprites) {
		super(sprites);
	}

	@Override
	public void setActive(boolean flag) {
		// nothing
	}

	/**
	 * Switches between the active sprites.
	 */
	public void switchSpinning() {
		Ball centerBall = getCenterBall();
		Ball circlingBall = getCirclingBall();

		circlingBall.stop();
		centerBall.setActive(true);
		circlingBall.setCenterBall(null);
		centerBall.setCenterBall(circlingBall);// set Center ball as ball1

		double[] delta = getDeltaPos(circlingBall, centerBall);
		centerBall.move(delta[0], delta[1], delta[2]); // Moves the (previously) center ball on top of the (previously) circling ball.
		centerBall.move(-getRadius() * Math.cos(45), -getRadius() * Math.sin(45), 0);// Moves the (previously) center ball to the starting point on the orbit.
		init();
	}

	/**
	 * We need the delta position of the sprites in order to move the sprites correctly.
	 * 
	 * @param ball1
	 * @param ball2
	 * @return
	 */
	private double[] getDeltaPos(Ball ball1, Ball ball2) {
		Vertex ball1Pos = ball1.getCenter();
		Vertex ball2Pos = ball2.getCenter();
		double dX = ball1Pos.getX() - ball2Pos.getX();
		double dY = ball1Pos.getY() - ball2Pos.getY();
		double dZ = ball1Pos.getZ() - ball2Pos.getZ();
		return new double[] { dX, dY, dZ };
	}

	@Override
	public boolean isActive() {
		return false;
	}

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

	/**
	 * Returns the circling ball.
	 * 
	 * @return
	 */
	public Ball getCirclingBall() {
		Ball circling = null;
		for (Sprite sprite : getGroup()) {
			if (((Ball) sprite).getCenterBall() != null) {
				circling = (Ball) sprite;
				break;
			}
		}
		return circling;
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