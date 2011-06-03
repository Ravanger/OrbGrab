package org.br.game;

import java.awt.Graphics;

import org.br.game.state.GameState;

public interface Sprite {

	void still();

	void move(double x, double y, double z);

	// void Move(double x, double y, double z);
	//
	// void TurnX(double a, Vertex center);
	//
	// void TurnY(double a, Vertex center);
	//
	// void TurnZ(double a, Vertex center);
	//
	// void Zoom(double a, Vertex center);

	GameState getState();

	void setClicked(boolean flag);

	boolean isClicked();

	void paint(Graphics g);

	void setGraphics(Graphics g);

	Graphics getGraphics();

	// String getName();
}
