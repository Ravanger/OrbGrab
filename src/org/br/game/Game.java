package org.br.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.br.game.sprites.Ball;
import org.br.game.sprites.CirclingBallGroup;
import org.br.game.sprites.Target;
import org.br.game.sprites.TargetGroup;

/**
 * The main class of OrgGrab, starts the game itself.
 * 
 * @author Lone Wolf
 */
public class Game extends JFrame {
	private Picture pic;
	static Game GAME;
	private MainMenu mainMenu = new MainMenu();
	private GameListener gamelistener;
	private CirclingBallGroup player;
	private TargetGroup targets;
	private boolean startGame = false;
	private Graphics g;
	private List<Sprite> sprites = new ArrayList<Sprite>();
	private String ballModel = "models/small_ball.ase";
	private String cubeModel = "models/cubik1.ase";
	private int score = 0;

	public Game(String st) {
		super(st);
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		g = getGraphics();
	}

	/**
	 * Adds GameListener (MouseListener) to pic (JPanel)
	 */
	private void addPicListener() {
		gamelistener = new GameListener(pic, this);
		pic.addMouseListener(gamelistener);
		pic.addMouseWheelListener(gamelistener);
		pic.addMouseMotionListener(gamelistener);
	}

	/**
	 * Creates the Ball sprites and sets the starting center ball (Ball 2).
	 * 
	 * @return
	 */
	private Picture getGameContent() {
		pic = new Picture();
		Ball ball1 = new Ball(new ASEParser(ballModel), Color.gray, "Ball 1");
		Ball ball2 = new Ball(new ASEParser(ballModel), Color.red, "Ball 2");
		List<Sprite> targetSprites = new ArrayList<Sprite>(5);
		for (int i = 0; i < 2; i++) {
			Target target = new Target(new ASEParser(cubeModel), Color.green, "Target " + i);
			targetSprites.add(target);
		}
		List<Sprite> playerSprites = new ArrayList<Sprite>(2);
		playerSprites.add(ball1);
		playerSprites.add(ball2);
		for (Sprite sprite : playerSprites) {
			sprite.zoom(2, sprite.getCenter());
		}
		for (Sprite sprite : targetSprites) {
			sprite.zoom(0.2, sprite.getCenter());
		}
		ball1.setCenterBall(ball2);// set Center ball as ball2
		ball1.setActive(true);// Activate ball 1 (make it spin)
		setPlayer(new CirclingBallGroup(playerSprites));
		setTargets(new TargetGroup(targetSprites));
		getPlayer().setPicture(pic);
		getTargets().setPicture(pic);
		ball1.setGroup(getPlayer());
		ball2.setGroup(getPlayer());
		sprites.add(getPlayer()); // adds player (CirclingBallGroup) to sprites (List of Sprites).
		sprites.add(getTargets());// Adds target to list of sprites
		pic.setListOfSprites(sprites);
		addPicListener();
		return pic;
	}

	/**
	 * Moves one ball to the center of the group using the group's radius
	 */
	public void startGame() {
		setVisible(true);
		if (getStartGame()) {
			getContentPane().remove(mainMenu);
			getContentPane().add(getGameContent());
			setJMenuBar(buildMenu());
			pack();
			pic.grabFocus();
			repaint();
			getPlayer().getCenterBall().move(getPlayer().getRadius() * Math.cos(45), getPlayer().getRadius() * Math.sin(45), 0);// Moves the center ball
			getPlayer().move(150, 150, 0);// Moves the player group
			getPlayer().init();
			pic.setCenterBall(getPlayer().getCenterBall());
			pic.setSpinningBall(getPlayer().getCirclingBall());
			for (int i = 0; i < getTargets().getGroup().size(); i++) {
				Random rand = new Random();
				Target target = (Target) getTargets().getGroup().get(i);
				target.move(rand.nextDouble() * 100 * i, rand.nextDouble() * 100 * i, 0);
				target.rotate();
			}
		}
		else {
			setJMenuBar(null);
			if (pic != null) {
				getContentPane().remove(pic);
			}
			getContentPane().add(mainMenu, BorderLayout.CENTER);
			pack();
			mainMenu.grabFocus();
		}

	}

	/**
	 * Builds the JMenu that's used in the game and returns it
	 * 
	 * @return
	 */
	private JMenuBar buildMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu filemenu;
		JMenuItem exit, help;
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

	public CirclingBallGroup getPlayer() {
		return player;
	}

	private void setPlayer(CirclingBallGroup player) {
		this.player = player;
	}

	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}

	public boolean getStartGame() {
		return startGame;
	}

	public static Game getGame() {
		return GAME;
	}

	public static void main(String[] args) {
		GAME = new Game("OrbGrab");
		GAME.startGame();
	}

	public TargetGroup getTargets() {
		return targets;
	}

	private void setTargets(TargetGroup targets) {
		this.targets = targets;
	}

	public int getScore() {
		return score;
	}

	public void incrementScore() {
		score++;
	}
}
