package org.br.game.sprites;

import java.awt.Color;

import org.br.game.ASEParser;
import org.br.game.Game;
import org.br.game.Sprite;
import org.br.game.SpriteBase;
import org.br.game.Vertex;

/**
 * The Ball sprite.
 * 
 * @author Boris
 */
public class Ball extends SpriteBase {

	private long sleep = Game.getGame().getSleepTime();
	private Ball centerBall;
	private Thread movingThread;
	private boolean circling = false;
	private CirclingBallGroup owner;

	public Ball(ASEParser filereader, Color color, String name) {
		super(filereader, color, name);
	}

	/**
	 * Sets the group for each ball so it's aware of the other ball.
	 * 
	 * @param owner
	 */
	public void setGroup(CirclingBallGroup owner) {
		this.owner = owner;
	}

	private CirclingBallGroup getGroup() {
		return owner;
	}

	/**
	 * Checks if ball has a center ball, then checks if it's active (spinning) and calls the circleAround() function, if not, stops the thread, and if there's no center ball, do nothing.
	 */
	@Override
	public void init() {
		super.init();
		if (centerBall != null) {
			if (isActive()) {
				centerBall.setActive(false);// Deactivates the center ball
				circleAround();
			}
			else {
				stop();
			}
		}
		else {
		}
	}

	/**
	 * A thread that's used for the active ball. Inside the loop it keeps calling the spin() method which updates its coordinates.
	 */
	void circleAround() {
		movingThread = new Thread() {
			int inc = 0;

			public void run() {
				while (circling) {
					spin(inc);
					Sprite targetDetected = Game.getGame().getTargets().collisionDetected(Ball.this);
					if (targetDetected != null) {
						Game.getGame().hit(targetDetected);
					}
					try {
						Thread.sleep(sleep);// Sleeps for 0.1 seconds
						inc += 1;
					}
					catch (InterruptedException e) {
					}
				}
			}
		};
		movingThread.start();
	}

	/**
	 * Updates the spinning ball's coordinates
	 * 
	 * @param inc
	 */
	private void spin(int inc) {
		double newX = getRadius() * Math.cos(Math.toDegrees(inc));
		double newY = getRadius() * Math.sin(Math.toDegrees(inc));
		move(newX, newY, 0);
	}

	/**
	 * Stop moving
	 */
	void stop() {
		circling = false;
		setActive(false);
		if (movingThread != null) {
			movingThread.interrupt();
		}
	}

	/**
	 * Returns the center (non-spinning) ball.
	 * 
	 * @return
	 */
	public Ball getCenterBall() {
		return centerBall;
	}

	public void setCenterBall(Ball centerBall) {
		this.centerBall = centerBall;
	}

	public void setActive(boolean active) {
		super.setActive(active);
		circling = active;
	}

	public String toString() {
		return "CirclingBall: " + getName() + " circling:" + isActive();
	}

	private int getRadius() {
		return Game.getGame().getRadius();
	}

	/**
	 * If the ball hits the edge of the screen, bounce it back 200 pixels.
	 */
	public void move(double x, double y, double z) {
		Vertex center = getCenter();
		if (center.getX() + x < 0) {
			getGroup().move(200, 0, 0);
			getGroup().getCirclingBall().move(-getRadius() * Math.cos(45), -getRadius() * Math.sin(45), 0);
			Game.getGame().outOfBorder();
		}
		if (center.getX() + x > Game.getGame().getWidth()) {
			getGroup().move(-200, 0, 0);
			getGroup().getCirclingBall().move(-getRadius() * Math.cos(45), -getRadius() * Math.sin(45), 0);
			Game.getGame().outOfBorder();
		}
		if (center.getY() + y < 0) {
			getGroup().move(0, 200, 0);
			getGroup().getCirclingBall().move(-getRadius() * Math.cos(45), -getRadius() * Math.sin(45), 0);
			Game.getGame().outOfBorder();
		}
		if (center.getY() + y > Game.getGame().getHeight()) {
			getGroup().move(0, -200, 0);
			getGroup().getCirclingBall().move(-getRadius() * Math.cos(45), -getRadius() * Math.sin(45), 0);
			Game.getGame().outOfBorder();
		}
		super.move(x, y, z);
	}
}