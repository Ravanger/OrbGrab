package Project;

public class Point {

	double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
