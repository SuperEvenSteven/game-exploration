package com.widgets.big.game.engine.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.widgets.big.game.framework.Screen;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 4168852368206508729L;
	private final GamePanel gamePanel;

	public GameFrame(Screen startScreen, String title) {
		setTitle(title);
		setSize(800, 480);
		gamePanel = new GamePanel(startScreen, this);

		setLayout(new BorderLayout());
		getContentPane().add(gamePanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void startGame() {
		gamePanel.startGame();
	}

}
