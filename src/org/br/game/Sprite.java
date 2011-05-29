package org.br.game;

import org.br.game.state.GameState;
 

public interface Sprite  {
 	
	void move(Direction dir);
	void still();
 	GameState getState();

	Position getPosition();
	void setPosition(Position pos);
	 
	
	void setClicked(boolean flag);
	boolean isClicked();
	
	String getName();
}
