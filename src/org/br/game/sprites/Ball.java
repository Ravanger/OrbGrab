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

public class Ball extends StatefullSprite {

	private Picture picture;
	private String name;
	private Ball centerBall;
	private boolean clicked = false;
	private Thread movingThread;
	private Boolean circling = false;
	private Face[] faces;
	private Vertex[] vertexes;
	private Color color;
	private ASEParser filereader;
	private Vertex center = new Vertex(0, 0, 0);
	private CirclingBallGroup owner;

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

	public void setGroup(CirclingBallGroup owner) {
		this.owner = owner;
	}

	private CirclingBallGroup getGroup() {
		return owner;
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

	/**
	 * Checks if ball has a center ball, then checks if it's active (spinning) and calls the circleAround() function, if not, stops the thread, and if there's no center ball, log a warning.
	 */
	@Override
	public void init() {
		setState(GameState.STILL);
		Vertex center = getCenterBall().getCenter();
		if (centerBall != null) {
			if (isActive()) {
				centerBall.setActive(false);// Deactivates the center ball
				circleAround();
			}
			else {
				// Vertex center = getGroup().getCenter();
				// move(center.getX(), center.getY(), 0);
				stop();
			}
		}
		else {
			Log.warn(getClass(), this + ": No center ball found to circle around!");
		}
	}

	void circleAround() {
		movingThread = new Thread() {
			double radians = 0;

			public void run() {
				while (circling) {
					spin(radians);
					try {
						Thread.sleep(100L);// Sleeps for 0.1 seconds
						radians += 1;
					}
					catch (InterruptedException e) {
						Log.warn(getClass(), this + ": Thread failed");
					}
				}
			}
		};
		movingThread.start();
	}

	private void spin(double a) {
		double newX = CirclingBallGroup.getRadius() * Math.cos(Math.toDegrees(a));
		double newY = CirclingBallGroup.getRadius() * Math.sin(Math.toDegrees(a));
		move(newX, newY, 0);
		Log.info(getClass(), this + " " + newX + "; " + newY + " Degree: " + Math.toDegrees(a));
	}

	public Face[] getFaces() {
		return faces;
	}

	public Vertex[] getVertexes() {
		return vertexes;
	}

	public Vertex getGroupCenter() {
		return center;
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

	public boolean isActive() {
		return clicked;
	}

	public void setActive(boolean clicked) {
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
