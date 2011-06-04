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
	// private JMenuBar menubar;
	private JMenu filemenu;
	private JMenuItem exit, help;
	private GameListener gamelistener;
	private CirclingBallGroup player;
	private boolean gameover = false;
	private Graphics g;
	private List<Sprite> sprites = new ArrayList<Sprite>();
	private String ballModel = "models/small_ball.ase";

	public Game(String st) {
		super(st);

		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(buildMenu());
		g = getGraphics();
		// setContentPane(getGameContent());
		this.getContentPane().add(getGameContent(), BorderLayout.CENTER);
	}

	private void addPicListener() {
		gamelistener = new GameListener(pic, getPlayer());
		pic.addMouseListener(gamelistener);
		pic.addMouseWheelListener(gamelistener);
		pic.addMouseMotionListener(gamelistener);
	}

	private Picture getGameContent() {

		pic = new Picture();
		Ball ball1 = new Ball(new ASEParser(ballModel), Color.gray, "Ball 1");
		Ball ball2 = new Ball(new ASEParser(ballModel), Color.red, "Ball 2");
		List<Sprite> playerSprites = new ArrayList<Sprite>(2);
		playerSprites.add(ball1);
		playerSprites.add(ball2);
		ball1.zoom(2, ball1.getCenter());
		ball2.zoom(2, ball2.getCenter());
		ball1.setClicked(true);
		ball1.setCenterBall(ball2);// set Center ball as ball2

		setPlayer(new CirclingBallGroup(playerSprites));
		getPlayer().setPicture(pic);
		sprites.add(getPlayer());

		pic.setListOfSprites(sprites);
		addPicListener();
		return pic;
	}

	public void startGame() {
		setVisible(true);
		getPlayer().getGroup().get(1).move(player.getGroupDistance(), player.getGroupDistance(), 0); // move ball 1 to center of group (radius)
		getPlayer().move(100, 100, 0);
		getPlayer().still();
	}

	private JMenuBar buildMenu() {
		JMenuBar menubar = new JMenuBar();
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
		return menubar;
	}

	public static void main(String[] args) {
		Game game = new Game("OrbGrab");
		game.startGame();
	}

	private CirclingBallGroup getPlayer() {
		return player;
	}

	private void setPlayer(CirclingBallGroup player) {
		this.player = player;
	}
}
