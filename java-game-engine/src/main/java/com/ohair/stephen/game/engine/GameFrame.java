package com.ohair.stephen.game.engine;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.ohair.stephen.game.framework.Scene;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 4168852368206508729L;
	private final GamePanel gamePanel;

	public GameFrame(Scene scene, String title, int w, int h) {
		setTitle(title);
		setSize(w, h);
		gamePanel = new GamePanel(scene, this, w, h);

		setLayout(new BorderLayout());
		getContentPane().add(gamePanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void startGame() {
		gamePanel.startGame();
	}

}
