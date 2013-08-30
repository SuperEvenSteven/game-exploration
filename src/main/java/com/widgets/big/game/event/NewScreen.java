package com.widgets.big.game.event;

import com.widgets.big.game.framework.Screen;
import com.widgets.util.controller.Event;

public class NewScreen implements Event {

	private final Screen screen;

	public NewScreen(Screen screen) {
		this.screen = screen;
	}

	public Screen getScreen() {
		return screen;
	}

}
