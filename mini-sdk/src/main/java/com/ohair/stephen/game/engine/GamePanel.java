package com.ohair.stephen.game.engine;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.ohair.stephen.game.framework.Scene;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -8230885104132235017L;

	private final Game game;

	public GamePanel(Scene scene, Component keyListenerComponent, int w,
			int h) {
		super();
		setSize(w, h);

		game = new Game(this, keyListenerComponent, scene);
	}

	public void startGame() {
		game.init();
		game.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		game.update(g);
		game.paint(g);
	}

}
