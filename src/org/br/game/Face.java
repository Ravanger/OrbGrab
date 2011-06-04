package org.br.game;

import java.awt.*;

public class Face {

	Vertex p1, p2, p3;
	Color c;

	public Face(Vertex p1, Vertex p2, Vertex p3, Color c) {
		this.p1 = new Vertex(p1);
		this.p2 = new Vertex(p2);
		this.p3 = new Vertex(p3);
		this.c = c;
	}

	public Face(Vertex p1, Vertex p2, Vertex p3) {
		this.p1 = new Vertex(p1);
		this.p2 = new Vertex(p2);
		this.p3 = new Vertex(p3);
	}

	public Face(Face tr) {
		p1 = new Vertex(tr.p1);
		p2 = new Vertex(tr.p2);
		p3 = new Vertex(tr.p3);
		c = tr.getC();
	}

	public Color getC() {
		return c;
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

	// public Triangle3D()
	// {
	// this.p1=new Point3D(1,0,0);
	// this.p2= new Point3D(0,-1,0);
	// this.p3=new Point3D(600 ,600);
	// c= Color.RED;
	// }

	public void move(double dx, double dy, double dz) {
		p1.move(dx, dy, dz);
		p2.move(dx, dy, dz);
		p3.move(dx, dy, dz);
	}

	public void turnX(double a) {
		p1.TurnX(a);
		p2.TurnX(a);
		p3.TurnX(a);
	}

	public void turnY(double a) {
		p1.TurnY(a);
		p2.TurnY(a);
		p3.TurnY(a);
	}

	public void turnZ(double a) {
		p1.TurnZ(a);
		p2.TurnZ(a);
		p3.TurnZ(a);
	}

	public void Scale(double sx, double sy, double sz) {
		p1.Scale(sx, sy, sz);
		p2.Scale(sx, sy, sz);
		p3.Scale(sx, sy, sz);
	}

	public void zoom(double k, Vertex center) {
		this.move(-(center.getX()), -(center.getY()), -(center.getZ()));
		this.Scale(k, k, k);
		this.move((center.getX()), (center.getY()), (center.getZ()));
	}

	public void TurnX(double a, Vertex center) {
		this.move(-(center.getX()), -(center.getY()), -(center.getZ()));
		this.turnX(a);
		this.move((center.getX()), (center.getY()), (center.getZ()));
	}

	public void TurnY(double a, Vertex center) {
		this.move(-(center.getX()), -(center.getY()), -(center.getZ()));
		this.turnY(a);
		this.move((center.getX()), (center.getY()), (center.getZ()));
	}

	public void TurnZ(double a, Vertex center) {
		this.move(-(center.getX()), -(center.getY()), -(center.getZ()));
		this.turnZ(a);
		this.move((center.getX()), (center.getY()), (center.getZ()));
	}

	public void DrawTriangle(Graphics g) {
		g.setColor(c);
		Point p4, p5, p6;
		p4 = this.p1.PerspectiveProjection();
		p5 = this.p2.PerspectiveProjection();
		p6 = this.p3.PerspectiveProjection();
		new Triangle(p4.getX(), p4.getY(), p5.getX(), p5.getY(), p6.getX(), p6.getY(), c).paint1(g);

	}

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public Color SetRGBColor(double a) {
		a = Math.abs(a);
		int red, green, blue;
		red = c.getRed();
		green = c.getGreen();
		blue = c.getBlue();
		return new Color((int) (red * (0.35 + 0.65 * a)), (int) (green * (a * 0.35 + 0.65)), (int) (blue * (a * 0.65 + 0.35)));
	}

	public void FillTriangle(Graphics g, Color c) {
		g.setColor(c);
		Point p4, p5, p6;
		p4 = this.p1.PerspectiveProjection();
		p5 = this.p2.PerspectiveProjection();
		p6 = this.p3.PerspectiveProjection();
		new Triangle(p4.getX(), p4.getY(), p5.getX(), p5.getY(), p6.getX(), p6.getY(), c).paint(g);

	}

	public void paint(Graphics g, Color c) {
		FillTriangle(g, c);
	}

	public void paint(Graphics g) {
		DrawTriangle(g);
	}

	public Vertex getCenter() {
		return (new Vertex((p1.getX() + p2.getX() + p3.getX()) / 3, (p1.getY() + p2.getY() + p3.getY()) / 3, (p1.getZ() + p2.getZ() + p3.getZ()) / 3));
	}

	public Vertex[] getPoints() {
		Vertex[] arr = new Vertex[3];
		arr[0] = new Vertex(p1);
		arr[1] = new Vertex(p2);
		arr[2] = new Vertex(p3);
		return arr;
	}

	public double HowSeen(int i) {
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
		normal.Scale(tmp1, tmp1, tmp1);
		middle.Scale(tmp2, tmp2, tmp2);
		tmp3 = normal.getX() * middle.getX() + normal.getY() * middle.getY() + normal.getZ() * middle.getZ();
		return tmp3;
	}

	public Triangle PerspectiveProjection(Color c) {
		return new Triangle(p1.PerspectiveProjection(), p2.PerspectiveProjection(), p3.PerspectiveProjection(), c);
	}
}
