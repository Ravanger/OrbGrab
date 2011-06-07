package org.br.game;

/**
 * Same as Point class, but has 3 coordinates: X, Y, and Z.
 * 
 * @author Boris
 */
public class Vertex {

	double x, y, z;
	public final static int DIST = 3000; // Used for perspective projection.
	private final int dif = 30; // The max difference used in the collision detection.

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

	/**
	 * Moves all the coordinates by the given values.
	 * 
	 * @param dx
	 * @param dy
	 * @param dz
	 */
	public void move(double dx, double dy, double dz) {
		x += dx;
		y += dy;
		z += dz;
	}

	/**
	 * Scales all the coordinates by the given values.
	 * 
	 * @param sx
	 * @param sy
	 * @param sz
	 */
	public void scale(double sx, double sy, double sz) {
		x *= sx;
		y *= sy;
		z *= sz;
	}

	/**
	 * Gets the length of the vertex.
	 * 
	 * @return
	 */
	public double getLength() {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	/**
	 * Turns the vertex around the X axis.
	 * 
	 * @param a
	 */
	public void turnX(double a) {
		double y1 = y, z1 = z;
		y = y1 * Math.cos(a * Math.PI / 180) - z1 * Math.sin(a * Math.PI / 180);
		z = y1 * Math.sin(a * Math.PI / 180) + z1 * Math.cos(a * Math.PI / 180);
	}

	/**
	 * Turns the vertex around the Y axis.
	 * 
	 * @param a
	 */
	public void turnY(double a) {
		double x1 = x, z1 = z;
		x = x1 * Math.cos(a * Math.PI / 180) - z1 * Math.sin(a * Math.PI / 180);
		z = x1 * Math.sin(a * Math.PI / 180) + z1 * Math.cos(a * Math.PI / 180);
	}

	/**
	 * Turns the vertex around the Z axis.
	 * 
	 * @param a
	 */
	public void turnZ(double a) {
		double x1 = x, y1 = y;
		x = x1 * Math.cos(a * Math.PI / 180) - y1 * Math.sin(a * Math.PI / 180);
		y = x1 * Math.sin(a * Math.PI / 180) + y1 * Math.cos(a * Math.PI / 180);
	}

	/**
	 * Turns a 3D vertex into a 2D point.
	 * 
	 * @return
	 */
	public Point perspectiveProjection() {
		Point p2d = new Point((getX() * DIST) / (getZ() + DIST), (getY() * DIST) / (getZ() + DIST));
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

	/**
	 * Used for collision detection. Checks if the vertex is equal to another vertex (centers of sprites) plus or minus the dif amount.
	 */
	@Override
	public boolean equals(Object other) {
		boolean eq = false;
		if (other instanceof Vertex) {
			Vertex otherVertex = (Vertex) other;
			if (Math.abs(otherVertex.getX() - this.getX()) <= dif)
				if (Math.abs(otherVertex.getY() - this.getY()) <= dif)
					eq = true;
		}
		return eq;
	}

	public String toString() {
		return new StringBuilder(getClass().getName()).append(" X:").append(getX()).append(" Y:").append(getY()).append(" Z:").append(getZ()).toString();
	}
}
