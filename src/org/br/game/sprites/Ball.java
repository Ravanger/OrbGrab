package org.br.game.sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.br.game.ASEParser;
import org.br.game.Face;
import org.br.game.Log;
import org.br.game.Picture;
import org.br.game.StatefullSprite;
import org.br.game.Triangle;
import org.br.game.Vertex;
import org.br.game.state.GameState;

import sun.awt.RepaintArea;

public class Ball extends StatefullSprite {

	private Picture picture;
	private String name;
	private Ball centerBall;
	private boolean clicked = false;
	private Thread movingThread;
	private Boolean circling = false;
	// private Point point = new Point(100, 100);
	private Face[] faces;
	private Vertex[] vertexes;
	private Color color;
	private ASEParser filereader;

	// private Graphics g;

	public Ball(ASEParser filereader, Color color, String name) {
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

	// public Ball(Ball centerBall) {
	// this.centerBall = centerBall;
	// faces = new Face[centerBall.faces.length];
	// for (int i = 0; i < centerBall.faces.length; i++) {
	// faces[i] = new Face(centerBall.faces[i]);
	// }
	// color = centerBall.color;
	// filereader = new ASEParser(centerBall.getFileReader().getFilePath());
	// }

	public Color getColor() {
		return color;
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
			vertex = faces[i].HowSeen(i);
			color = faces[i].SetRGBColor(vertex);

			if (vertex > 0) {
				triangles.add(faces[i].PerspectiveProjection(color));
				triangles.add(faces[i + 1].PerspectiveProjection(color));
			}

		}
		return triangles;
	}

	public ArrayList<Triangle> perspectiveProjectionTriangle() {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (int i = 0; i < faces.length; i++) {
			triangles.add(faces[i].PerspectiveProjection(faces[i].getC()));
		}
		return triangles;
	}

	public ASEParser getFileReader() {
		return filereader;
	}

	/**
	 * This ball is moving around the second Ball which is in the "still" state
	 */
	@Override
	public void still() {
		setState(GameState.STILL);
		if (centerBall != null) {
			if (isClicked()) {
				centerBall.setClicked(false);
				centerBall.still();
				circleAround(centerBall);
			}
			else {
				stop();
			}
		}
		else {
			Log.warn(getClass(), this + ": No CirclingBall found to circle around!");
		}
	}

	void circleAround(final Ball centerBall) {
		movingThread = new Thread() {
			double angle = 0;

			public void run() {
				while (circling) {
					spin(angle);
					try {
						Thread.sleep(100L);// Sleeps for 0.1 seconds
						angle += 1.5;
					}
					catch (InterruptedException e) {
						Log.warn(getClass(), this + ": Thread failed");
					}
				}
			}
		};
		movingThread.start();
	}

	private void spin(double angle) {
		double newX = 2 * CirclingBallGroup.getGroupDistance() * Math.cos(angle);
		double newY = 2 * CirclingBallGroup.getGroupDistance() * Math.sin(angle);
		move(newX, newY, 0);

		Log.info(getClass(), this + " " + newX + "; " + newY + " Degree: " + angle);
	}

	public Face[] getFaces() {
		return faces;
	}

	public Vertex[] getVertexes() {
		return vertexes;
	}

	public Vertex getCenter() {
		Vertex vertex = new Vertex(0, 0, 0);
		for (int i = 0; i < faces.length; i++) {
			vertex.move(faces[i].getCenter().getX() / (faces.length), faces[i].getCenter().getY() / (faces.length), faces[i].getCenter().getZ() / (faces.length));
		}
		return vertex;
	}

	/**
	 * Stop moving
	 */
	void stop() {
		circling = false;
		if (movingThread != null) {
			movingThread.interrupt();
			Log.info(getClass(), "Stopped thread");
		}
	}

	public void paint(Graphics g) {
		ArrayList<Triangle> triangles = perspectiveProjection();
		for (int i = 0; i < triangles.size(); i++) {
			triangles.get(i).FillTriangle(g);
		}
	}

	public Ball getCenterBall() {
		return centerBall;
	}

	public void setCenterBall(Ball centerBall) {
		this.centerBall = centerBall;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
		circling = clicked;
	}

	public String toString() {
		return "CirclingBall: " + getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
