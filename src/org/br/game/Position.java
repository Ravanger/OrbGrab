package org.br.game;

import java.util.List;

/**
 * TODO use Point ?
 * 
 * @author Boris
 * 
 */
public class Position {

	private double x, y;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Position distanced(double x, double y) {
		return new Position(this.x + x, this.y + y);
	}

	public static double[] getMinMaxXY(List<Position> positions) {
		double maxX = 0;
		double maxY = 0;
		double minX = 0;
		double minY = 0;
		for (Position p : positions) {
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

		return new double[] { minX,minY,maxX, maxY };
	}

}
