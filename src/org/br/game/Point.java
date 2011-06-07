package org.br.game;

/**
 * Basic point class. Has x and y coordinates.
 * 
 * @author Boris
 */
public class Point {

	private double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	/**
	 * Moves the point by the given coordinates.
	 * 
	 * @param dx
	 * @param dy
	 */
	public void move(double dx, double dy) {
		x += dx;
		y += dy;
	}

	/**
	 * Scales the point up or down depending on the given values.
	 * 
	 * @param sx
	 * @param sy
	 */
	public void scale(double sx, double sy) {
		x = x * sx;
		y = y * sy;
	}

	/**
	 * Turns the point in a circle based on the given angle.
	 * 
	 * @param a
	 */
	public void turn(double a) {
		double x1 = x, y1 = y;
		setX(x1 * Math.cos(a * Math.PI / 180) - y1 * Math.sin(a * Math.PI / 180));
		setY(x1 * Math.sin(a * Math.PI / 180) + y1 * Math.cos(a * Math.PI / 180));
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}