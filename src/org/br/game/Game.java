package org.br.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.br.game.sprites.Ball;
import org.br.game.sprites.CirclingBallGroup;

public class Game extends JFrame {
	private Picture pic;
	private JMenuBar menubar;
	private JMenu filemenu;
	private JMenuItem exit, help;
	private GameListener gamelistener;
	private CirclingBallGroup player;
	private boolean gameover = false;
	private Graphics g;
	private List<Ball> sprites = new ArrayList<Ball>();
	private String ballModel = "models/small_ball.ase";

	public Game(String st) {
		super(st);
		// pic = new Picture();
		// this.getContentPane().add(this, BorderLayout.CENTER);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// gamelistener = new GameListener(this, player);
		BuildMenu();
		this.setVisible(true);
		this.addMouseListener(gamelistener);
		this.addMouseWheelListener(gamelistener);
		this.addMouseMotionListener(gamelistener);
		g = this.getGraphics();
	}

	private void createBalls() {
		Ball ball1 = new Ball(new ASEParser(ballModel), Color.gray, "Ball 1");
		Ball ball2 = new Ball(new ASEParser(ballModel), Color.red, "Ball 2");
		ball1.Zoom(2, ball1.getCenter());
		ball2.Zoom(2, ball2.getCenter());
		ball1.setClicked(true);
		ball1.setCenterBall(ball2);// set Center ball as ball2
		sprites.add(ball1);
		sprites.add(ball2);
	}

	// public void initGame() {
	// Spheres spheres = new Spheres();
	// }

	public void startGame() {

	}

	public void BuildMenu() {
		menubar = new JMenuBar();
		filemenu = new JMenu("File");
		menubar.add(filemenu);
		// help = new JMenuItem("Help");
		// filemenu.add(help);
		// help.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// g = pic.getGraphics();
		// pic.paintHelp(g);
		// }
		// });
		exit = new JMenuItem("Exit");
		filemenu.add(exit);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}

	private void initPlayer() {
		createBalls();
		player = new CirclingBallGroup(sprites);
		player.setGraphics(g);
		player.getGroup().get(1).move(player.getGroupDistance(), player.getGroupDistance(), 0); // move ball 1
		player.move(100, 100, 0);
		player.still();
	}

	public static void main(String[] args) {
		Game game = new Game("OrbGrab");
		game.setJMenuBar(game.menubar);
		game.initPlayer();
	}
}
