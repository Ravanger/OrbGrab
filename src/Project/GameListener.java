package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;

public class GameListener implements ActionListener, MouseInputListener, KeyListener, MouseWheelListener {

	private Picture pic;
	private Graphics g;
	private Player player;
	int mx = 0, my = 0;

	public GameListener(Picture pic, Player player) {
		this.pic = pic;
		this.player = player;
	}

	public void actionPerformed(ActionEvent e) {
		g = this.pic.getGraphics();
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
		}

		else {
		}
	}

	public void mouseClicked(java.awt.event.MouseEvent e) {
	}

	public void mouseReleased(java.awt.event.MouseEvent e) {
	}

	public void mouseEntered(java.awt.event.MouseEvent e) {
	}

	public void mouseExited(java.awt.event.MouseEvent e) {
	}

	public void mouseDragged(java.awt.event.MouseEvent e) {
		int x = e.getX(), y = e.getY();
		if (e.getModifiers() == InputEvent.BUTTON2_MASK) {
			TurnXY(x - mx, y - my);
		}
		else if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			TurnZ(x - mx);
		}
		mx = x;
		my = y;
	}

	private void TurnZ(int a) {
	}

	private void TurnXY(int a, int b) {
	}

	public void mouseMoved(java.awt.event.MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void mousePressed(java.awt.event.MouseEvent e) {
		this.mx = e.getX();
		this.my = e.getY();
		e.consume();
		if (e.getModifiers() == InputEvent.BUTTON1_MASK)
			player.changeSpinning();
	}
}
