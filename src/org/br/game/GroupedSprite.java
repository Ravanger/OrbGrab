package org.br.game;

import java.util.ArrayList;
import java.util.List;

import org.br.game.state.GameState;

public abstract class GroupedSprite extends StatefullSprite {

	private List<Sprite> group = new ArrayList<Sprite>();

	// a default distance between group members
	public static final int DEFAULT_DISTANCE = 100;

	private int distance = DEFAULT_DISTANCE;

	public GroupedSprite(List<? extends Sprite> group) {
		this.group.addAll(group);

	}

	@Override
	public Position getPosition() {

		return calculateGroupCenter();
	}

	@Override
	public void setPosition(Position pos) {
		Position center = calculateGroupCenter();
		for (Sprite groupMember : group) {
			groupMember.setPosition(center.distanced(getGroupDistance(),
					getGroupDistance()));
		}

	}

	protected Position calculateGroupCenter() {

		List<Position> all = new ArrayList<Position>();
		for (Sprite groupMember : group) {
			all.add(groupMember.getPosition());
		}
		double[] minMax = Position.getMinMaxXY(all);
		double maxX = minMax[2];
		double maxY = minMax[3];

		double minX = minMax[0];
		double minY = minMax[1];

		Position groupCenter = new Position((maxX - minX) / 2,
				(maxY - minY) / 2);

		return groupCenter;
	}

	public void still() {
		for (Sprite groupMember : group) {
			groupMember.still();
		}
		setState(GameState.STILL);
	}

	public void move(Direction dir) {
		for (Sprite groupMember : group) {
			groupMember.move(dir);
		}
		setState(GameState.MOVING);
	}

	/**
	 * Get a distance between group members
	 * 
	 * @return
	 */
	public int getGroupDistance() {
		return distance;
	}

	/**
	 * Set a distance between group members
	 * 
	 * @return
	 */
	protected void setGroupDistance(int distance) {
		this.distance = distance;
	}

}
