package org.br.game.sprites;

import java.util.List;

import org.br.game.GroupedSprite;

import Project.ASEFile;

/**
 * The concrete implementation of GroupedSprite with the group of 2 moving balls. One of the Ball is still, the second circles around it.
 * 
 * @author Boris
 */
public class CirclingBallGroup extends GroupedSprite {
	public CirclingBallGroup(List<Ball> sprites) {
		super(sprites);
	}

	// public String getName() {
	// return "CirclingBallGroup";
	// }

	@Override
	public void setClicked(boolean flag) {
		// nothing
	}

	@Override
	public boolean isClicked() {
		return false;
	}
}
