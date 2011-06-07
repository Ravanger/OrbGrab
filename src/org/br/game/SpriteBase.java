package org.br.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Base abstract class for all sprites.
 * 
 * @author Boris
 */
public abstract class SpriteBase implements Sprite {

	private Picture picture;
	private Face[] faces;
	private ASEParser filereader;
	private Vertex[] vertexes;
	private Color color;
	private String name;
	private boolean active = false;

	public SpriteBase() {
	}

	public SpriteBase(ASEParser filereader, Color color, String name) {
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
	}

	/**
	 * Calls the move() method for all faces in a sprite.
	 */
	public void move(double x, double y, double z) {
		for (int i = 0; i < faces.length; i++) {
			faces[i].move(x, y, z);
			repaintAll();
		}
	}

	/**
	 * Calls the turnX() method for all faces in a sprite.
	 */
	public void turnX(double a, Vertex center) {
		this.move(-center.getX(), -center.getY(), -center.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].turnX(a);
		}
		this.move(center.getX(), center.getY(), center.getZ());
	}

	/**
	 * Calls the turnY() method for all faces in a sprite.
	 */
	public void turnY(double a, Vertex p) {
		this.move(-p.getX(), -p.getY(), -p.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].turnY(a);
		}
		this.move(p.getX(), p.getY(), p.getZ());
	}

	/**
	 * Calls the turnZ() method for all faces in a sprite.
	 */
	public void turnZ(double a, Vertex p) {
		this.move(-p.getX(), -p.getY(), -p.getZ());
		for (int i = 0; i < faces.length; i++) {
			faces[i].turnZ(a);
		}
		this.move(p.getX(), p.getY(), p.getZ());
	}

	/**
	 * Calls the zoom() method for all faces in a sprite.
	 */
	public void zoom(double a, Vertex center) {
		for (int i = 0; i < faces.length; i++) {
			faces[i].zoom(a, center);
		}
	}

	/**
	 * For each face, checks if it should be seen using the howSeen() method, gives it shading using the setRGBColor() method and turns the faces into triangles using perspectiveProjection().
	 * 
	 * @return
	 */
	public ArrayList<Triangle> perspectiveProjection() {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (int i = 0; i < faces.length; i++) {
			double vertex = faces[i].howSeen();
			if (vertex > 0) {
				Color color = faces[i].setRGBColor(vertex);
				triangles.add(faces[i].perspectiveProjection(color));
			}
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
			triangles.get(i).paint(g);
		}
	}

	public void repaintAll() {
		getPicture().repaint();
	}

	/**
	 * Returns the center vertex of the sprite.
	 */
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
