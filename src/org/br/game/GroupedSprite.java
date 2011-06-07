package org.br.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class which is a parent of all grouped sprites.
 * 
 * @author Boris
 */
public abstract class GroupedSprite extends SpriteBase {

	private List<Sprite> group = new ArrayList<Sprite>();

	public GroupedSprite(List<? extends Sprite> group) {
		this.group.addAll(group);
	}

	public List<Sprite> getGroup() {
		return group;
	}

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
