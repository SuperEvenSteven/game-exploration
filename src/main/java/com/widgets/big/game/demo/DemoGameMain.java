package com.widgets.big.game.demo;

import com.widgets.big.game.engine.GameFrame;

public class DemoGameMain {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameFrame frame = new GameFrame(new LoadingScreen(),
						"Alien Game", 800, 480);
				frame.setVisible(true);
				frame.startGame();
			}
		});
	}
}
