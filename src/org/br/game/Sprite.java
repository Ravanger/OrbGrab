package org.br.game;

import org.br.game.state.GameState;

public interface Sprite {

	void still();

	// void move(Direction dir);

	GameState getState();

	Point getPosition();

	void setPosition(Point pos);

	void setClicked(boolean flag);

	boolean isClicked();

	// String getName();
}
