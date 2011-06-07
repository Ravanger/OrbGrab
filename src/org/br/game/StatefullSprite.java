package org.br.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.br.game.state.GameState;

public abstract class StatefullSprite implements Sprite {

	private Picture picture;

	private Face[] faces;
	private ASEParser filereader;
	private Vertex[] vertexes;
	private Color color;
	private String name;
	private boolean active = false;

	private GameState state = GameState.UNKNOWN;

	public StatefullSprite() {

	}

	public StatefullSprite(ASEParser filereader, Color color, String name) {
		this.filereader = filereader;

		this.color = color;

		double[] vertexarr = filereader.readASEVertex();
		double[] facearr = filereader.readASEFace();
		faces = new Face[filereader.readFACENUM()];
		vertexes = new Vertex[filereader.readVERTEXNUM()];
		for (int i = 0, j = 0; i < vertexes.length; i++, j += 3) {
			vertexes[i] = new Vertex(vertexarr[j], vertexarr[j + 1], vertexarr[j + 2]); // vertexarr[j] is x, vertexarr[j+1] is y, vertexarr[j+2] is z
		}
		for (int i = 0, j = 0; i < faces.length; i++, j += 3)// makes all the triangles with the points that are in the facearr array
		{
			faces[i] = new Face(vertexes[(int) facearr[j]], vertexes[(int) facearr[j + 1]], vertexes[(int) facearr[j + 2]], color); // instructions are in ASEParser
		}
		this.name = name;
	}

	@Override
	public void init() {
		setState(GameState.STILL);
	}

	public GameState getState() {
		return state;
	}

	protected void setState(GameState state) {
		this.state = state;
	}

	public void move(double x, double y, double z) {
		for (int i = 0; i < faces.length; i++) {
			faces[i].move(x, y, z);
			repaintAll();
		}
	}

	public void turnX(double a, Vertex center) {
		this.move(-center.getX(), -center.getY(), -center.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].turnX(a);
		}
		this.move(center.getX(), center.getY(), center.getZ());
	}

	public void turnY(double a, Vertex p) {
		this.move(-p.getX(), -p.getY(), -p.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].turnY(a);
		}
		this.move(p.getX(), p.getY(), p.getZ());
	}

	public void turnZ(double a, Vertex p) {
		this.move(-p.getX(), -p.getY(), -p.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].turnZ(a);
		}
		this.move(p.getX(), p.getY(), p.getZ());
	}

	public void zoom(double a, Vertex center) {
		for (int i = 0; i < faces.length; i++) {
			faces[i].zoom(a, center);
		}
	}

	public ArrayList<Triangle> perspectiveProjection() {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (int i = 0; i < faces.length; i += 2) {
			double vertex;
			Color color;
			vertex = faces[i].howSeen(i);
			color = faces[i].setRGBColor(vertex);

			if (vertex > 0) {
				triangles.add(faces[i].perspectiveProjection(color));
				triangles.add(faces[i + 1].perspectiveProjection(color));
			}

		}
		return triangles;
	}

	public ArrayList<Triangle> perspectiveProjectionTriangle() {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (int i = 0; i < faces.length; i++) {
			triangles.add(faces[i].perspectiveProjection(faces[i].getC()));
		}
		return triangles;
	}

	public ASEParser getFileReader() {
		return filereader;
	}

	public Face[] getFaces() {
		return faces;
	}

	public Vertex[] getVertexes() {
		return vertexes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public void paint(Graphics g) {
		ArrayList<Triangle> triangles = perspectiveProjection();
		for (int i = 0; i < triangles.size(); i++) {
			triangles.get(i).FillTriangle(g);
		}
	}

	public void repaintAll() {
		getPicture().repaint();
	}

	public Vertex getCenter() {
		Vertex vertex = new Vertex(0, 0, 0);
		for (int i = 0; i < getFaces().length; i++) {
			vertex.move(getFaces()[i].getCenter().getX() / (getFaces().length), getFaces()[i].getCenter().getY() / (getFaces().length), getFaces()[i].getCenter().getZ() / (getFaces().length));
		}
		return vertex;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;

	}

}
