package org.br.game;

import java.awt.*;

/**
 * Triangle class made up of 3 Points.
 * 
 * @author Boris
 */
public class Triangle {

	Point p1, p2, p3;
	Color color;

	public Triangle(Point p1, Point p2, Point p3, Color color) {
		this.p1 = new Point(p1);
		this.p2 = new Point(p2);
		this.p3 = new Point(p3);
		this.color = color;
	}

	/**
	 * For each point in the triangle, calls the move() method and moves all the points.
	 * 
	 * @param dx
	 * @param dy
	 */
	public void move(double dx, double dy) {
		p1.move(dx, dy);
		p2.move(dx, dy);
		p3.move(dx, dy);
	}

	/**
	 * Rotates all the points in the triangle.
	 * 
	 * @param a
	 */
	public void rotate(double a) {
		p1.turn(a);
		p2.turn(a);
		p3.turn(a);
	}

	/**
	 * Returns the center point of the triangle.
	 * 
	 * @return
	 */
	public Point getCenter() {
		Point p = new Point((p1.getX() + p2.getX() + p3.getX()) / 3, (p1.getY() + p2.getY() + p3.getY()));
		return p;
	}

	/**
	 * Scales each point in the triangle.
	 * 
	 * @param sx
	 * @param sy
	 */
	public void scale(double sx, double sy) {
		p1.scale(sx, sy);
		p2.scale(sx, sy);
		p3.scale(sx, sy);
	}

	/**
	 * Zooms each point in the triangle.
	 * 
	 * @param k
	 */
	public void zoom(double k) {
		double xc, yc;
		xc = (p1.getX() + p2.getX() + p3.getX()) / 3;
		yc = (p1.getY() + p2.getY() + p3.getY()) / 3;
		this.move(-xc, -yc);
		this.scale(k, k);
		this.move(xc, yc);
	}

	/**
	 * Turns each point in the triangle.
	 * 
	 * @param a
	 */
	public void turn(double a) {
		double xc, yc;
		xc = (p1.getX() + p2.getX() + p3.getX()) / 3;
		yc = (p1.getY() + p2.getY() + p3.getY()) / 3;
		this.move(-xc, -yc);
		this.rotate(a);
		this.move(xc, yc);
	}

	/**
	 * Turns each point in the triangle.
	 * 
	 * @param a
	 * @param center
	 */
	public void turn(double a, Point center) {
		move(-center.getX(), -center.getY());
		rotate(a);
		move(center.getX(), center.getY());
	}

	public void paint(Graphics g) {
		g.setColor(color);
		int[] x = new int[3];
		int[] y = new int[3];
		x[0] = (int) p1.getX();
		x[1] = (int) p2.getX();
		x[2] = (int) p3.getX();
		y[0] = (int) p1.getY();
		y[1] = (int) p2.getY();
		y[2] = (int) p3.getY();
		g.fillPolygon(x, y, 3);
	}
}
