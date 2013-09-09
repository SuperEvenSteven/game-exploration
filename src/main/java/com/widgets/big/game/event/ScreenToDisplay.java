package com.widgets.big.game.event;

import com.widgets.big.game.framework.Screen;
import com.widgets.util.controller.Event;

public class ScreenToDisplay implements Event {

	private final Screen screen;

	public ScreenToDisplay(Screen screen) {
		this.screen = screen;
	}

	public Screen getScreen() {
		return screen;
	}

}
