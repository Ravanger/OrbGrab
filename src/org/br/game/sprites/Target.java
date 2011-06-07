package org.br.game.sprites;

import java.awt.Color;

import org.br.game.ASEParser;
import org.br.game.Game;
import org.br.game.SpriteBase;
import org.br.game.Vertex;

/**
 * The target class.
 * 
 * @author Lone Wolf
 */
public class Target extends SpriteBase {

	public Target(ASEParser filereader, Color color, String name) {
		super(filereader, color, name);
	}

	/**
	 * Basic thread to animate rotation of the cube.
	 */
	public void rotate() {
		Thread thread = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(100L);
						turnX(5, getCenter());
						turnY(5, getCenter());
						turnZ(5, getCenter());
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

	/**
	 * Checks if the cube went out of bounds of the screen and if it did, move it back.
	 */
	public void move(double x, double y, double z) {
		Vertex center = getCenter();
		if (center.getX() + x < 0) {
			x = -x;
		}
		if (center.getX() + x > Game.getGame().getWidth()) {
			x = -x;
		}
		if (center.getY() + y < 0) {
			y = -y;
		}
		if (center.getY() + y > Game.getGame().getHeight()) {
			y = -y;
		}
		super.move(x, y, z);
	}
}
