package org.br.game;

import java.util.ArrayList;
import java.util.List;

import org.br.game.state.GameState;

public abstract class GroupedSprite extends StatefullSprite {

	private List<Sprite> group = new ArrayList<Sprite>();

	// a default distance between group members
	public static final int RADIUS = 100;

	private static int dist = RADIUS;

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

	public void still() {
		for (Sprite groupMember : group) {
			groupMember.still();
		}
		setState(GameState.STILL);
	}

	public void move(Direction dir) {
		// for (Sprite groupMember : group) {
		// // groupMember.move(dir);
		// }
		setState(GameState.MOVING);
	}

	/**
	 * Get the distance (radius) between group members
	 * 
	 * @return
	 */
	public static int getGroupDistance() {
		return dist;
	}

	/**
	 * Set a distance (radius) between group members
	 * 
	 * @return
	 */
	protected void setGroupDistance(int distance) {
		this.dist = distance;
	}

}
