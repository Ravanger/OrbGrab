package Project;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Picture extends JPanel {

	public Picture() {
		super();
		this.setLocation(0, 85);
		this.setLayout(new FlowLayout());
		this.setSize(800, 600);
		this.setDoubleBuffered(true);
	}

	public void paintShape(ASEFile shape, Graphics g) {
		g.clearRect(0, 0, 800, 600);
		ArrayList<Triangle> triangles = shape.PerspectiveProjection();
		for (int i = 0; i < triangles.size(); i++) {
			triangles.get(i).FillTriangle(g);
		}
	}

	public void paintHelp(Graphics g) {
		g.clearRect(0, 0, 800, 600);
		g.drawRect(15, 15, 770, 570);
	}
}
