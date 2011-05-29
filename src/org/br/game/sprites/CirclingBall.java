package org.br.game.sprites;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.br.game.Direction;
import org.br.game.Log;
import org.br.game.Position;
import org.br.game.StatefullSprite;
import org.br.game.state.GameState;

public class CirclingBall extends StatefullSprite {

	private String name;
	// the CirclingBall this one will be circling around
	private CirclingBall ballCirclingAround;
	// ///////////////////////////////////////////////

	private boolean clicked = false;
	private Thread movingThread;
	private AtomicBoolean circling = new AtomicBoolean(false);
	private AtomicReference<Position> position = new AtomicReference<Position>(
			new Position(100, 100));

	public CirclingBall(CirclingBall ballCirclingAround, String name) {
		this.ballCirclingAround = ballCirclingAround;
		setName(name);
	}

	@Override
	public void move(Direction dir) {

	}

	/**
	 * This Ball is moving around the second Ball in its "still" state
	 * 
	 */
	@Override
	public void still() {
		setState(GameState.STILL);
		if (ballCirclingAround != null) {
			if (isClicked()) {
				ballCirclingAround.setClicked(false);
				ballCirclingAround.still();
				circleAround(ballCirclingAround);

			} else {
				stop();
			}
		} else {
			Log.warn(getClass(), "No CirclingBall found to circle around!");
		}

	}

	void circleAround(final CirclingBall other) {
		movingThread = new Thread() {
			public void run() {
				while (circling.get()) {
					Position pos = calculateCirclePositionAround( other.getPosition());
					setPosition(pos);
				}
			}
		};
		movingThread.start();
	}

	
	private Position calculateCirclePositionAround(Position other) {
		 Calculation of an ellipse ?
	}
	
	
	/**
	 * stop moving
	 */
	void stop() {
		circling.set(false);
		if (movingThread != null) {
			movingThread.interrupt();
			Log.info(getClass(), "stopped.");
		}
	}

	@Override
	public Position getPosition() {

		return position.get();
	}

	@Override
	public void setPosition(Position pos) {
		position.set(pos);

	}

	public CirclingBall getBallCirclingAround() {
		return ballCirclingAround;
	}

	public void setBallCirclingAround(CirclingBall ballCirclingAround) {
		this.ballCirclingAround = ballCirclingAround;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public String toString() {
		return "CirclingBall:" + getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
