package Project;

import java.awt.*;

public class Triangle {

	Point p1, p2, p3;
	Color c;

	public Triangle(double x1, double y1, double x2, double y2, double x3, double y3, Color c) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
		this.p3 = new Point(x3, y3);
		this.c = c;
	}

	public Point[] getPoints() {
		Point[] arr = new Point[3];
		arr[0] = new Point(p1);
		arr[1] = new Point(p2);
		arr[2] = new Point(p3);
		return arr;
	}

	public Triangle() {
		this.p1 = new Point(400, 250);
		this.p2 = new Point(400, 600);
		this.p3 = new Point(600, 600);
		c = Color.RED;
	}

	public Triangle(Point p1, Point p2, Point p3, Color c) {
		this.p1 = new Point(p1);
		this.p2 = new Point(p2);
		this.p3 = new Point(p3);
		this.c = c;
	}

	public void Move(double dx, double dy) {
		p1.Move(dx, dy);
		p2.Move(dx, dy);
		p3.Move(dx, dy);
	}

	public void rotate(double a) {
		p1.Turn(a);
		p2.Turn(a);
		p3.Turn(a);
	}

	public Point getCenter() {
		Point p = new Point((p1.getX() + p2.getX() + p3.getX()) / 3, (p1.getY() + p2.getY() + p3.getY()));
		return p;
	}

	public void Scale(double sx, double sy) {
		p1.Scale(sx, sy);
		p2.Scale(sx, sy);
		p3.Scale(sx, sy);
	}

	public void Zoom(double k) {
		double xc, yc;
		xc = (p1.getX() + p2.getX() + p3.getX()) / 3;
		yc = (p1.getY() + p2.getY() + p3.getY()) / 3;
		this.Move(-xc, -yc);
		this.Scale(k, k);
		this.Move(xc, yc);
	}

	public void Turn(double a) {
		double xc, yc;
		xc = (p1.getX() + p2.getX() + p3.getX()) / 3;
		yc = (p1.getY() + p2.getY() + p3.getY()) / 3;
		this.Move(-xc, -yc);
		this.rotate(a);
		this.Move(xc, yc);
	}

	public void Turn(double a, Point c) {
		this.Move(-c.getX(), -c.getY());
		this.rotate(a);
		this.Move(c.getX(), c.getY());
	}

	public void DrawTriangle(Graphics g) {
		g.setColor(c);
		g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
		g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p3.getX(), (int) p3.getY());
		g.drawLine((int) p3.getX(), (int) p3.getY(), (int) p2.getX(), (int) p2.getY());

	}

	public void FillTriangle(Graphics g) {
		g.setColor(c);
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

	public void paint(Graphics g) {
		FillTriangle(g);
	}

	public void paint1(Graphics g) {
		DrawTriangle(g);
	}

	protected boolean isInsideTriangle(Point[] pointss, Point[] vertices, Point p) {
		double x = p.getX(), y = p.getY();
		int w = 0;
		for (Point po : vertices) {
			if ((pointss[(int) po.getX()].getY() - y) * (pointss[(int) po.getY()].getY() - y) < 0) {
				double r = pointss[(int) po.getX()].getX() - x + (pointss[(int) po.getX()].getY() - y) * (pointss[(int) po.getY()].getX() - pointss[(int) po.getX()].getX()) / (pointss[(int) po.getX()].getY() - pointss[(int) po.getY()].getY());
				// if(r<0&&r>-0.5)
				// System.out.println(r);

				if (r > -0.0001) {
					if (pointss[(int) po.getX()].getY() < y) {
						w++;
					}
					else {
						w--;
					}
				}
			}
			else if (pointss[(int) po.getX()].getY() == y && pointss[(int) po.getX()].getX() > x) {
				if (pointss[(int) po.getY()].getY() > y) {
					w += 0.5;
				}
				else {
					w -= 0.5;
				}
			}
			else if (pointss[(int) po.getY()].getY() == y && pointss[(int) po.getY()].getX() > x) {
				if (pointss[(int) po.getX()].getY() < y) {
					w += 0.5;
				}
				else {
					w -= 0.5;
				}
			}
		}
		return w != 0;
	}
}
