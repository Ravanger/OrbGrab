package Project;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ASEFile extends JPanel {

	private Face[] faces;

	public Face[] getFaces() {
		return faces;
	}

	public Vertex[] getVertexes() {
		return vertexes;
	}

	private Vertex[] vertexes;
	private Color color;
	private ASEParser filereader;

	public ASEParser getFileReader() {
		return filereader;
	}

	public ASEFile(ASEParser filereader, Color color) {
		this.filereader = filereader;
		this.color = color;
		double[] vertexarr = filereader.readASEVertex();
		double[] facearr = filereader.readASEFace();
		faces = new Face[filereader.readFACENUM()];
		vertexes = new Vertex[filereader.readVERTEXNUM()];
		for (int i = 0, j = 0; i < vertexes.length; i++, j += 3) {
			vertexes[i] = new Vertex(vertexarr[j], vertexarr[j + 1], vertexarr[j + 2]); // vertexarr[j] is x, vertexarr[j+1] is y, vertexarr[j+2] is z
		}
		for (int i = 0, j = 0; i < faces.length; i++, j += 3)// makes all the triangles with the points that are in the p array
		{
			faces[i] = new Face(vertexes[(int) facearr[j]], vertexes[(int) facearr[j + 1]], vertexes[(int) facearr[j + 2]], color); // instructions are in ASEParse
		}
	}

	public ASEFile(ASEFile ASEfile) {
		faces = new Face[ASEfile.faces.length];
		for (int i = 0; i < ASEfile.faces.length; i++) {
			faces[i] = new Face(ASEfile.faces[i]);
		}
		color = ASEfile.color;
		filereader = new ASEParser(ASEfile.getFileReader().getFilePath());
	}

	public Color getColor() {
		return color;
	}

	public Vertex getCenter() {
		Vertex center = new Vertex(0, 0, 0);
		for (int i = 0; i < faces.length; i++) {
			center.Move(faces[i].getCenter().getX() / (faces.length), faces[i].getCenter().getY() / (faces.length), faces[i].getCenter().getZ() / (faces.length));
		}
		return center;
	}

	public void Move(double x, double y, double z) {
		for (int i = 0; i < faces.length; i++) {
			faces[i].Move(x, y, z);
		}
	}

	public void TurnX(double a, Vertex center) {
		this.Move(-center.getX(), -center.getY(), -center.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].TurnX(a);
		}
		this.Move(center.getX(), center.getY(), center.getZ());
	}

	public void TurnY(double a, Vertex p) {
		this.Move(-p.getX(), -p.getY(), -p.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].TurnY(a);
		}
		this.Move(p.getX(), p.getY(), p.getZ());
	}

	public void TurnZ(double a, Vertex p) {
		this.Move(-p.getX(), -p.getY(), -p.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].TurnZ(a);
		}
		this.Move(p.getX(), p.getY(), p.getZ());
	}

	public void Zoom(double a, Vertex center) {
		for (int i = 0; i < faces.length; i++) {
			faces[i].Zoom(a, center);
		}
	}

	public ArrayList PerspectiveProjection() {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (int i = 0; i < faces.length; i += 2) {
			double vertex;
			Color color;
			vertex = faces[i].HowSeen(i);
			color = faces[i].SetRGBColor(vertex);

			if (vertex > 0) {
				triangles.add(faces[i].PerspectiveProjection(color));
				triangles.add(faces[i + 1].PerspectiveProjection(color));
			}

		}
		return triangles;
	}

	public ArrayList PerspectiveProjectionTriangle() {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (int i = 0; i < faces.length; i++) {
			triangles.add(faces[i].PerspectiveProjection(faces[i].getC()));
		}
		return triangles;
	}
}
