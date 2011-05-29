package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMain extends JFrame {

	private static Picture pic;
	private static ASEFile asefile, ball, cube;
	private static JMenuBar menubar;
	private static JMenu filemenu;
	private static JMenuItem open, ballmenuitem, cubemenuitem, playermenuitem, exit, help;
	private static GameListener mylistener;
	private static Player player;
	private Graphics g;

	public MyMain(String st) {
		super(st);
		pic = new Picture();
		this.getContentPane().add(pic, BorderLayout.CENTER);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mylistener = new GameListener(pic, player);
		BuildMenu();
		this.setVisible(true);
		this.addMouseListener(mylistener);
		this.addMouseWheelListener(mylistener);
		this.addMouseMotionListener(mylistener);
		// g = pic.getGraphics();
		// pic.paintShape(player.getBall1(), g);
		// pic.paintShape(player.getBall2(), g);
	}

	public static void BuildMenu() {
		menubar = new JMenuBar();
		filemenu = new JMenu("File");
		menubar.add(filemenu);
		open = new JMenuItem("Open");
		filemenu.add(open);
		filemenu.addSeparator();
		ballmenuitem = new JMenuItem("Ball");
		cubemenuitem = new JMenuItem("Cube");
		playermenuitem = new JMenuItem("Player");
		filemenu.add(ballmenuitem);
		filemenu.add(cubemenuitem);
		filemenu.add(playermenuitem);
		filemenu.addSeparator();
		help = new JMenuItem("Help");
		filemenu.add(help);
		exit = new JMenuItem("Exit");
		filemenu.add(exit);
		ballmenuitem.addActionListener(mylistener);
		cubemenuitem.addActionListener(mylistener);
		playermenuitem.addActionListener(mylistener);
		help.addActionListener(mylistener);
		exit.addActionListener(mylistener);
		open.addActionListener(mylistener);
	}

	public static JMenuItem getOpen() {
		return open;
	}

	public static void main(String[] args) {
		player = new Player(new ASEFile(new ASEParser("models/small_ball.ase"), Color.gray), new ASEFile(new ASEParser("models/small_ball.ase"), Color.gray));
		ball = new ASEFile(new ASEParser("models/small_ball.ase"), Color.yellow);
		cube = new ASEFile(new ASEParser("models/cubik1.ase"), Color.BLUE);
		ball.Move(400, 400, 0);
		ball.Zoom(50, ball.getCenter());
		player.getBall1().Zoom(5, player.getBall1().getCenter());
		player.getBall2().Zoom(5, player.getBall2().getCenter());
		player.getBall2().Move(player.getRadius(), player.getRadius(), 0);
		cube.Move(200, 200, 0);
		MyMain my = new MyMain("Game");
		my.setJMenuBar(menubar);
	}

	public static ASEFile createBall() {
		ball = new ASEFile(new ASEParser("models/small_ball.ase"), Color.yellow);
		return ball;
	}

	public static ASEFile createCube() {
		cube = new ASEFile(new ASEParser("models/cubik1.ase"), Color.BLUE);
		return cube;
	}

	public static Player createPlayer() {
		player = new Player(new ASEFile(new ASEParser("models/small_ball.ase"), Color.gray), new ASEFile(new ASEParser("models/small_ball.ase"), Color.gray));
		return player;
	}
}
