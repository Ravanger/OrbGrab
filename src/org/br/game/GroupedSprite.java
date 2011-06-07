package org.br.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.br.game.state.GameState;

public abstract class GroupedSprite extends StatefullSprite {

	private List<Sprite> group = new ArrayList<Sprite>();

	public GroupedSprite(List<? extends Sprite> group) {
		this.group.addAll(group);
	}

	public List<Sprite> getGroup() {
		return group;
	}

	// protected Vertex getGroupCenter() {
	//
	// List<Point> all = new ArrayList<Point>();
	// for (Sprite groupMember : group) {
	// all.add(groupMember.getPosition());
	// }
	// double[] minMax = Point.getMinMaxXY(all);
	// double maxX = minMax[2];
	// double maxY = minMax[3];
	//
	// double minX = minMax[0];
	// double minY = minMax[1];
	//
	// Point groupCenter = new Point((maxX - minX) / 2, (maxY - minY) / 2);
	//
	// return groupCenter;
	// }

	@Override
	public void paint(Graphics g) {
		for (Sprite groupMember : group) {
			groupMember.paint(g);
		}
	}

	public void init() {
		for (Sprite groupMember : group) {
			groupMember.init();
		}
		setState(GameState.STILL);
	}

	public void move(Direction dir) {
		// for (Sprite groupMember : group) {
		// // groupMember.move(dir);
		// }
		setState(GameState.MOVING);
	}

	protected int getRadius() {
		return Game.getGame().getRadius();
	}

	public void setPicture(Picture picture) {
		for (Sprite groupMember : group) {
			groupMember.setPicture(picture);
		}
	}
}
