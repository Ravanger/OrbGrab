package org.br.game;

import java.util.List;

/**
 * TODO use Point ?
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

	public Point getDistance(double x, double y) {
		return new Point(this.x + x, this.y + y);
	}

	public static double[] getMinMaxXY(List<Point> positions) {
		double maxX = 0;
		double maxY = 0;
		double minX = 0;
		double minY = 0;
		for (Point p : positions) {
			if (p.getX() > maxX) {
				maxX = p.getX();
			}
			if (p.getX() < minX) {
				minX = p.getX();
			}
			if (p.getY() > maxY) {
				maxY = p.getY();
			}
			if (p.getY() < minY) {
				minY = p.getY();
			}
		}

		return new double[] { minX, minY, maxX, maxY };
	}

	public void Move(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public void Scale(double sx, double sy) {
		x = x * sx;
		y = y * sy;
	}

	public void Turn(double a) {
		double x1 = this.x, y1 = this.y;
		this.x = x1 * Math.cos(a * Math.PI / 180) - y1 * Math.sin(a * Math.PI / 180);
		this.y = x1 * Math.sin(a * Math.PI / 180) + y1 * Math.cos(a * Math.PI / 180);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}