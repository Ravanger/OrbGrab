package org.br.game;

import java.util.List;

public class Vertex {

	double x, y, z;
	public final static int DIST = 3000;

	public Vertex(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vertex(Vertex p) {
		this.x = p.getX();
		this.y = p.getY();
		this.z = p.getZ();
	}

	public void move(double dx, double dy, double dz) {
		x += dx;
		y += dy;
		z += dz;
	}

	public void Scale(double sx, double sy, double sz) {
		x = x * sx;
		y = y * sy;
		z *= sz;
	}

	public double getLength() {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	public void TurnX(double a) {
		double y1 = this.y, z1 = this.z;
		this.y = y1 * Math.cos(a * Math.PI / 180) - z1 * Math.sin(a * Math.PI / 180);
		this.z = y1 * Math.sin(a * Math.PI / 180) + z1 * Math.cos(a * Math.PI / 180);
	}

	public void TurnY(double a) {
		double x1 = this.x, z1 = this.z;
		this.x = x1 * Math.cos(a * Math.PI / 180) - z1 * Math.sin(a * Math.PI / 180);
		this.z = x1 * Math.sin(a * Math.PI / 180) + z1 * Math.cos(a * Math.PI / 180);
	}

	public void TurnZ(double a) {
		double x1 = this.x, y1 = this.y;
		this.x = x1 * Math.cos(a * Math.PI / 180) - y1 * Math.sin(a * Math.PI / 180);
		this.y = x1 * Math.sin(a * Math.PI / 180) + y1 * Math.cos(a * Math.PI / 180);
	}

	public Point PerspectiveProjection() {
		Point p2d = new Point((this.x * DIST) / (this.z + DIST), (this.y * DIST) / (this.z + DIST));
		return p2d;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getZ() {
		return z;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public static double[] getMinMaxXYZ(List<Vertex> positions) {
		double maxX = 0;
		double maxY = 0;
		double minX = 0;
		double minY = 0;
		double maxZ = 0;
		double minZ = 0;
		for (Vertex p : positions) {
			if (p.getX() > maxX) {
				maxX = p.getX();
			}
			else {
				minX = p.getX();
			}
			if (p.getY() > maxY) {
				maxY = p.getY();
			}
			else {
				minY = p.getY();
			}
			if (p.getZ() > maxZ) {
				maxZ = p.getZ();
			}
			else {
				minZ = p.getZ();
			}
		}

		return new double[] { minX, minY, minZ, maxX, maxY, maxZ };
	}

	public String toString() {
		return new StringBuilder(getClass().getName()).append(" X:").append(getX()).append(" Y:").append(getY()).append(" Z:").append(getZ()).toString();
	}
}
