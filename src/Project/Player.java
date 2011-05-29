package Project;

public class Player {

	private ASEFile ball1;
	private ASEFile ball2;
	private int spinning;
	private int VELOCITY = 10, RADIUS = 20;

	public Player(ASEFile ball1, ASEFile ball2) {
		this.ball1 = ball1;
		this.ball2 = ball2;
		spinning = 1;
	}

	public ASEFile getBall1() {
		return ball1;
	}

	public ASEFile getBall2() {
		return ball2;
	}

	public void Collide(ASEFile shape) {

	}

	/**
	 * Switches from one spinning ball to the other
	 * 
	 * @param spinning
	 */
	public void changeSpinning() {
		if (spinning == 1)
			spinning = 2;
		else
			spinning = 1;
	}

	public int getRadius() {
		return RADIUS;
	}

	/**
	 * Gets ball1, ball2 and current angle and makes one ball spin around the other
	 * 
	 * @param ball1
	 * @param ball2
	 * @param angle
	 */
	public void Spin(ASEFile ball1, ASEFile ball2, double angle) {
		double Px;
		double Py;
		if (spinning == 1) {
			Px = ball1.getCenter().getX();
			Py = ball1.getCenter().getY();
			ball2.getCenter().setX(Px + RADIUS * Math.cos(angle));
			ball2.getCenter().setY(Py + RADIUS * Math.sin(angle));
		}
		if (spinning == 2) {
			Px = ball2.getCenter().getX();
			Py = ball2.getCenter().getY();
			ball1.getCenter().setX(Px + RADIUS * Math.cos(angle));
			ball1.getCenter().setY(Py + RADIUS * Math.sin(angle));
		}
	}
}
