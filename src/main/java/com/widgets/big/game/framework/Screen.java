package com.widgets.big.game.framework;

import com.widgets.big.game.engine.applet.AppletGame;

public abstract class Screen {

	protected final Game game;

	public Screen(Game game) {
		this.game = game;
	}

	public void init() {
	}

	public void update(float deltaTimeMs) {
	}

	public void paint(java.awt.Graphics g, AppletGame game) {

	}

}
