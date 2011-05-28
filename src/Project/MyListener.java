package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.event.MouseInputListener;

public class MyListener implements ActionListener, MouseInputListener, KeyListener, MouseWheelListener {

	private Picture pic;
	private Graphics g;
	private ASEFile asefile, cube, ball;
	private static JFileChooser filechooser;
	private static ASEParser aseparser;
	private static String FilePath;
	private boolean clicked = false;
	private Player player;

	private enum ListOfShapes {

		Ball, Cube, Open, Help, Exit, Player
	};

	ListOfShapes listofshapes;
	int mx = 0, my = 0;

	public MyListener(Picture pic, ASEFile cube, ASEFile ball, ASEFile asefile, Player player) {
		this.pic = pic;
		this.cube = cube;
		this.ball = ball;
		this.asefile = asefile;
		this.player = player;
	}

	public void actionPerformed(ActionEvent e) {
		listofshapes = ListOfShapes.valueOf(e.getActionCommand());
		g = this.pic.getGraphics();
		switch (listofshapes) {
			case Ball:
				pic.paintShape(ball, g);
				break;
			case Cube:
				pic.paintShape(cube, g);
				break;
			case Open:
				filechooser = new JFileChooser();
				int returnVal = filechooser.showOpenDialog(MyMain.getOpen());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					setFilePath(file.toString());
					aseparser = new ASEParser(getFilePath());
					asefile = new ASEFile(aseparser, Color.GREEN);
				}
				pic.paintShape(asefile, g);
				break;
			case Player:
				pic.paintPlayer(player, g);
				break;
			case Help:
				pic.paintHelp(g);
				break;
			case Exit:
				System.exit(0);
				break;
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			switch (listofshapes) {
				case Ball:
					ball.Zoom(0.9, ball.getCenter());
					pic.paintShape(ball, g);
					break;
				case Cube:
					cube.Zoom(0.9, cube.getCenter());
					pic.paintShape(cube, g);
					break;
				case Open:
					asefile.Zoom(0.9, asefile.getCenter());
					pic.paintShape(asefile, g);
					break;
			}
		}
		else {
			switch (listofshapes) {
				case Ball:
					ball.Zoom(1.1, ball.getCenter());
					pic.paintShape(ball, g);
					break;
				case Cube:
					cube.Zoom(1.1, cube.getCenter());
					pic.paintShape(cube, g);
					break;
				case Open:
					asefile.Zoom(1.1, asefile.getCenter());
					pic.paintShape(asefile, g);
					break;
			}
		}
	}

	public static void setFilePath(String FilePath) {
		MyListener.FilePath = FilePath;
	}

	public static String getFilePath() {
		return FilePath;
	}

	public void mouseClicked(java.awt.event.MouseEvent e) {
	}

	public void mouseReleased(java.awt.event.MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void mouseEntered(java.awt.event.MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void mouseExited(java.awt.event.MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void mouseDragged(java.awt.event.MouseEvent e) {
		int x = e.getX(), y = e.getY();
		if (e.getModifiers() == InputEvent.BUTTON2_MASK) {
			TurnXY(x - mx, y - my);
		}
		else if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			TurnZ(x - mx);
		}
		else {
			Move(e);
		}
		mx = x;
		my = y;
	}

	private void TurnZ(int a) {
		switch (listofshapes) {
			case Ball:
				ball.TurnZ(a, ball.getCenter());
				pic.paintShape(ball, g);
				break;
			case Cube:
				cube.TurnZ(a, cube.getCenter());
				pic.paintShape(cube, g);
				break;
			case Open:
				asefile.TurnZ(a, asefile.getCenter());
				pic.paintShape(asefile, g);
				break;
		}
	}

	private void TurnXY(int a, int b) {
		switch (listofshapes) {
			case Ball:
				ball.TurnX(b, ball.getCenter());
				ball.TurnY(a, ball.getCenter());
				pic.paintShape(ball, g);
				break;
			case Cube:
				cube.TurnX(a, cube.getCenter());
				cube.TurnY(a, cube.getCenter());
				pic.paintShape(cube, g);
				break;
			case Open:
				asefile.TurnX(a, asefile.getCenter());
				asefile.TurnY(a, asefile.getCenter());
				pic.paintShape(asefile, g);
				break;
		}
	}

	private void Move(java.awt.event.MouseEvent e) {
		int x = e.getX(), y = e.getY();
		switch (listofshapes) {
			case Ball:
				ball.Move(x - mx, y - my, 0);
				pic.paintShape(ball, g);
				break;
			case Cube:
				cube.Move(x - mx, y - my, 0);
				pic.paintShape(cube, g);
				break;
			case Open:
				asefile.Move(x - mx, y - my, 0);
				pic.paintShape(asefile, g);
				break;
		}

	}

	public void mouseMoved(java.awt.event.MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void keyTyped(KeyEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void keyPressed(KeyEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void keyReleased(KeyEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void mousePressed(java.awt.event.MouseEvent e) {
		this.mx = e.getX();
		this.my = e.getY();
		e.consume();
	}
}
