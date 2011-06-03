package org.br.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.br.game.sprites.Ball;
import org.br.game.sprites.CirclingBallGroup;

public class Game extends JFrame {
	private static Picture pic;
	private static JMenuBar menubar;
	private static JMenu filemenu;
	private static JMenuItem exit, help;
	private static GameListener gamelistener;
	private static CirclingBallGroup player;
	private boolean gameover = false;
	private static Graphics g;
	private static List<Ball> sprites;
	private static String ballModel = "models/small_ball.ase";

	private static void createBalls() {
		sprites.add(new Ball(new ASEParser(ballModel), Color.gray));
		sprites.add(new Ball(new ASEParser(ballModel), Color.red));
	}

	public Game(String st) {
		super(st);
		pic = new Picture();
		this.getContentPane().add(pic, BorderLayout.CENTER);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gamelistener = new GameListener(pic, player);
		BuildMenu();
		this.setVisible(true);
		this.addMouseListener(gamelistener);
		this.addMouseWheelListener(gamelistener);
		this.addMouseMotionListener(gamelistener);
	}

	// public void initGame() {
	// Spheres spheres = new Spheres();
	// }

	public void startGame() {

	}

	public static void BuildMenu() {
		menubar = new JMenuBar();
		filemenu = new JMenu("File");
		menubar.add(filemenu);
		help = new JMenuItem("Help");
		filemenu.add(help);
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				g = pic.getGraphics();
				pic.paintHelp(g);
			}
		});
		exit = new JMenuItem("Exit");
		filemenu.add(exit);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}

	public static void main(String[] args) {
		createBalls();
		player = new CirclingBallGroup(sprites);
		Game game = new Game("OrbGrab");
		game.setJMenuBar(menubar);
	}
}
