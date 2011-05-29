package org.br.game;

import org.br.game.state.GameState;

public abstract class StatefullSprite implements Sprite {

	private GameState state = GameState.UNKNOWN;

	public GameState getState() {
		return state;
	}

	protected void setState(GameState state) {
		this.state = state;
	}

}
