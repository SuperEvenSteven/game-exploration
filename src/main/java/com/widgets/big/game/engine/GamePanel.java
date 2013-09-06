package com.widgets.big.game.engine;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.widgets.big.game.framework.Screen;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -8230885104132235017L;

	private final Game game;

	public GamePanel(Screen startScreen, Component keyListenerComponent, int w,
			int h) {
		setSize(w, h);

		game = new Game(this, keyListenerComponent, startScreen);
	}

	public void startGame() {
		game.init();
		game.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		game.update(g);
		game.paint(g);
		game.finishedPaint();
	}

}
