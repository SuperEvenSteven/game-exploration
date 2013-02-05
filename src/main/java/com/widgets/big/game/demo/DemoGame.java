package com.widgets.big.game.demo;

import com.widgets.big.game.engine.applet.AppletGame;
import com.widgets.big.game.framework.Screen;

public class DemoGame extends AppletGame {

	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}

}
