package org.br.game;

import java.awt.*;

/**
 * The Face (3D triangle) class. Has X, Y, and Z coordinates.
 * 
 * @author Boris
 */
public class Face {

	private Vertex p1, p2, p3;
	private Color color;

	public Face(Vertex p1, Vertex p2, Vertex p3, Color color) {
		this.p1 = new Vertex(p1);
		this.p2 = new Vertex(p2);
		this.p3 = new Vertex(p3);
		this.color = color;
	}

	public Vertex getP1() {
		return new Vertex(p1);
	}

	public Vertex getP2() {
		return new Vertex(p2);
	}

	public Vertex getP3() {
		return new Vertex(p3);
	}

	/**
	 * Moves the face by the given coordinates
	 * 
	 * @param dx
	 * @param dy
	 * @param dz
	 */
	public void move(double dx, double dy, double dz) {
		p1.move(dx, dy, dz);
		p2.move(dx, dy, dz);
		p3.move(dx, dy, dz);
	}

	/**
	 * Turns the face around the X axis
	 * 
	 * @param a
	 */
	public void turnX(double a) {
		p1.turnX(a);
		p2.turnX(a);
		p3.turnX(a);
	}

	/**
	 * Turns the face around the Y axis
	 * 
	 * @param a
	 */
	public void turnY(double a) {
		p1.turnY(a);
		p2.turnY(a);
		p3.turnY(a);
	}

	/**
	 * Turns the face around the Z axis
	 * 
	 * @param a
	 */
	public void turnZ(double a) {
		p1.turnZ(a);
		p2.turnZ(a);
		p3.turnZ(a);
	}

	/**
	 * Scales the face up or down depending on the given values.
	 * 
	 * @param sx
	 * @param sy
	 * @param sz
	 */
	public void scale(double sx, double sy, double sz) {
		p1.scale(sx, sy, sz);
		p2.scale(sx, sy, sz);
		p3.scale(sx, sy, sz);
	}

	/**
	 * Moves the face to 0, 0, 0, scales it by the given coordinate, and then moves it back to its previous position.
	 * 
	 * @param k
	 * @param center
	 */
	public void zoom(double k, Vertex center) {
		move(-(center.getX()), -(center.getY()), -(center.getZ()));
		scale(k, k, k);
		move((center.getX()), (center.getY()), (center.getZ()));
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Changes the face color depending on the view angle given.
	 * 
	 * @param viewAngle
	 * @return
	 */
	public Color setRGBColor(double viewAngle) {
		viewAngle = Math.abs(viewAngle);
		int red, green, blue;
		red = color.getRed();
		green = color.getGreen();
		blue = color.getBlue();
		return new Color((int) (red * (0.35 + 0.65 * viewAngle)), (int) (green * (viewAngle * 0.35 + 0.65)), (int) (blue * (viewAngle * 0.65 + 0.35)));
	}

	/**
	 * Returns the center vertex of the face.
	 * 
	 * @return
	 */
	public Vertex getCenter() {
		return (new Vertex((p1.getX() + p2.getX() + p3.getX()) / 3, (p1.getY() + p2.getY() + p3.getY()) / 3, (p1.getZ() + p2.getZ() + p3.getZ()) / 3));
	}

	/**
	 * Calculates the cosine between the view angle and the normal of the face. If the result is positive then we can see it, if negative, then we can't see it and there's no need to draw it.
	 * 
	 * @return
	 */
	public double howSeen() {
		Vertex normal, middle;
		Vertex p1 = getP1();
		Vertex p2 = getP2();
		Vertex p3 = getP3();
		double tmp1, tmp2, tmp3;
		middle = getCenter();
		p1.move(-p2.getX(), -p2.getY(), -p2.getZ());
		p3.move(-p2.getX(), -p2.getY(), -p2.getZ());

		tmp1 = p1.getY() * p3.getZ() - p1.getZ() * p3.getY();
		tmp2 = p1.getZ() * p3.getX() - p1.getX() * p3.getZ();
		tmp3 = p1.getX() * p3.getY() - p1.getY() * p3.getX();
		normal = new Vertex(tmp1, tmp2, tmp3);

		middle.move(0, 0, Vertex.DIST);
		tmp1 = 1 / normal.getLength();
		tmp2 = 1 / middle.getLength();
		normal.scale(tmp1, tmp1, tmp1);
		middle.scale(tmp2, tmp2, tmp2);
		tmp3 = normal.getX() * middle.getX() + normal.getY() * middle.getY() + normal.getZ() * middle.getZ();
		return tmp3;
	}

	/**
	 * Calls perspectiveProjection() for each vertex.
	 * 
	 * @param color
	 * @return
	 */
	public Triangle perspectiveProjection(Color color) {
		return new Triangle(getP1().perspectiveProjection(), getP2().perspectiveProjection(), getP3().perspectiveProjection(), color);
	}
}
