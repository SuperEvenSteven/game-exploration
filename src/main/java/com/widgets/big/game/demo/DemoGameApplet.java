package com.widgets.big.game.demo;

import com.widgets.big.game.engine.GameApplet;

public class DemoGameApplet extends GameApplet {

	private static final long serialVersionUID = -5332047309746656438L;

	public DemoGameApplet() {
		super(new LoadingScreen(), "Alien game", 800, 480);
	}

}
