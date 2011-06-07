package org.br.game.sprites;

import java.util.List;

import org.br.game.GroupedSprite;
import org.br.game.Log;
import org.br.game.Picture;
import org.br.game.Sprite;
import org.br.game.Vertex;

public class TargetGroup extends GroupedSprite {

	public TargetGroup(List<Sprite> sprites) {
		super(sprites);
	}

	@Override
	public void move(double x, double y, double z) {
		for (Sprite sprite : getGroup()) {
			sprite.move(x, y, z);
		}
	}

	@Override
	public void turnX(double a, Vertex center) {
		for (Sprite sprite : getGroup()) {
			sprite.turnX(a, center);
		}
	}

	@Override
	public void turnY(double a, Vertex center) {
		for (Sprite sprite : getGroup()) {
			sprite.turnY(a, center);
		}
	}

	@Override
	public void turnZ(double a, Vertex center) {
		for (Sprite sprite : getGroup()) {
			sprite.turnZ(a, center);
		}
	}

	public boolean isCollisionDetected(Sprite other) {
		boolean detected = false;
		for (Sprite sprite : getGroup()) {
			if (sprite.getCenter().equals(other.getCenter())) {
				detected = true;
				break;
			}
		}
		return detected;
	}

	@Override
	public void zoom(double a, Vertex center) {
		for (Sprite sprite : getGroup()) {
			sprite.zoom(a, center);
		}
	}

	@Override
	public Vertex getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActive(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void repaintAll() {
		// TODO Auto-generated method stub

	}

	public void setPicture(Picture picture) {
		for (Sprite groupMember : getGroup()) {
			groupMember.setPicture(picture);
		}
	}

}
