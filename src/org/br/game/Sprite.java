package org.br.game;

import java.awt.Graphics;

/**
 * Sprite interface. Contains all needed methods for sprites.
 * 
 * @author Lone Wolf
 */
public interface Sprite {

	void init();

	void move(double x, double y, double z);

	void turnX(double a, Vertex center);

	void turnY(double a, Vertex center);

	void turnZ(double a, Vertex center);

	void zoom(double a, Vertex center);

	Vertex getCenter();

	void setActive(boolean flag);

	boolean isActive();

	void paint(Graphics g);

	void setPicture(Picture picture);

	void repaintAll();
}
