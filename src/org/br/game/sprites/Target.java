package org.br.game.sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.br.game.ASEParser;
import org.br.game.Face;
import org.br.game.Picture;
import org.br.game.StatefullSprite;
import org.br.game.Triangle;
import org.br.game.Vertex;
import org.br.game.state.GameState;

public class Target extends StatefullSprite {

	private ASEParser filereader;
	private Color color;
	private Face[] faces;
	private Vertex[] vertexes;
	private String name;
	private Picture picture;

	public Target(ASEParser filereader, Color color, String name) {
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
	public void still() {
		setState(GameState.STILL);
	}

	@Override
	public void move(double x, double y, double z) {
		for (int i = 0; i < faces.length; i++) {
			faces[i].move(x, y, z);
			repaintAll();
		}
	}

	@Override
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

	@Override
	public Vertex getCenter() {
		Vertex vertex = new Vertex(0, 0, 0);
		for (int i = 0; i < faces.length; i++) {
			vertex.move(faces[i].getCenter().getX() / (faces.length), faces[i].getCenter().getY() / (faces.length), faces[i].getCenter().getZ() / (faces.length));
		}
		return vertex;
	}

	@Override
	public void setClicked(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isClicked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void paint(Graphics g) {
		ArrayList<Triangle> triangles = perspectiveProjection();
		for (int i = 0; i < triangles.size(); i++) {
			triangles.get(i).FillTriangle(g);
		}
	}

	@Override
	public void repaintAll() {
		getPicture().repaint();
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}
}
