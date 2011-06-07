package org.br.game.sprites;

import java.awt.Color;

import org.br.game.ASEParser;
import org.br.game.Game;
import org.br.game.Log;
import org.br.game.StatefullSprite;
import org.br.game.Vertex;

public class Target extends StatefullSprite {

	public Target(ASEParser filereader, Color color, String name) {
		super(filereader, color, name);
	}

	public void rotate() {
		Thread thread = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(100L);
						turnX(5, getCenter());
						turnY(5, getCenter());
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

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
