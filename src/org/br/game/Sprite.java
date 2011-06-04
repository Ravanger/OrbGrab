package org.br.game;

import java.awt.Graphics;

import org.br.game.state.GameState;

public interface Sprite {

	void still();

	void move(double x, double y, double z);

	void turnX(double a, Vertex center);

	void turnY(double a, Vertex center);

	void turnZ(double a, Vertex center);

	void zoom(double a, Vertex center);

	GameState getState();

	void setClicked(boolean flag);

	boolean isClicked();

	void paint(Graphics g);

	void setPicture(Picture picture);

	void repaintAll();
}
