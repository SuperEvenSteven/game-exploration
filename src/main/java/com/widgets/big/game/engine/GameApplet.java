package com.widgets.big.game.engine;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;

import com.widgets.big.game.framework.Screen;

public class GameApplet extends Applet {

	private static final long serialVersionUID = 2397885928260855130L;

	private final Game game;

	private final int requestedWidth;

	private final int requestedHeight;

	private final String title;

	/**
	 * Constructor.
	 * 
	 * @param startScreen
	 */
	public GameApplet(Screen startScreen, String title, int requestedWidth,
			int requestedHeight) {
		this.title = title;
		this.requestedWidth = requestedWidth;
		this.requestedHeight = requestedHeight;
		game = new Game(this, this, startScreen);
	}

	@Override
	public void init() {
		System.out.println("initializing");

		configureApplet();

		game.init();

	}

	private void configureApplet() {
		System.out.println("game applet init method fired");
		setSize(requestedWidth, requestedHeight);
		setBackground(Color.BLACK);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle(title);
	}

	@Override
	public void start() {
		// Starts the thread that runs our main game loop
		game.start();
	}

	@Override
	public void update(java.awt.Graphics g) {
		game.update(g);
	}

	@Override
	public void paint(java.awt.Graphics g) {
		game.paint(g);
	}

}
