package org.br.game.sprites;

import java.awt.Color;

import org.br.game.ASEParser;
import org.br.game.Game;
import org.br.game.Log;
import org.br.game.Sprite;
import org.br.game.StatefullSprite;
import org.br.game.Vertex;

public class Ball extends StatefullSprite {

	private long sleep = Game.getGame().getSleepTime();
	private Ball centerBall;

	private Thread movingThread;
	private boolean circling = false;

	private Vertex grpCenter = new Vertex(0, 0, 0);
	private CirclingBallGroup owner;

	public Ball(ASEParser filereader, Color color, String name) {

		super(filereader, color, name);
	}

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
		// Vertex center = getCenterBall().getCenter();
		if (centerBall != null) {
			if (isActive()) {
				centerBall.setActive(false);// Deactivates the center ball
				circleAround();
			}
			else {
				// Vertex center = getGroup().getCenter();
				// move(center.getX(), center.getY(), 0);
				stop();
			}
		}
		else {
		}
	}

	void circleAround() {
		movingThread = new Thread() {
			double radians = 0;

			public void run() {
				while (circling) {
					spin(radians);
					Sprite targetDetected = Game.getGame().getTargets().collisionDetected(Ball.this);
					if (targetDetected != null) {
						Game.getGame().hit(targetDetected);
					}
					try {
						Thread.sleep(sleep);// Sleeps for 0.1 seconds
						radians += 1;
					}
					catch (InterruptedException e) {
						Log.warn(getClass(), this + ": Thread failed");
					}
				}
			}
		};
		movingThread.start();
	}

	private void spin(double a) {
		double newX = getRadius() * Math.cos(Math.toDegrees(a));
		double newY = getRadius() * Math.sin(Math.toDegrees(a));
		move(newX, newY, 0);
	}

	public Vertex getGroupCenter() {
		return grpCenter;
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

	public void moveIn(double x, double y, double z) {
		super.move(x, y, z);
	}

}
