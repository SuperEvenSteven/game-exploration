package com.ohair.stephen.game.event;

import com.ohair.stephen.game.framework.Scene;
import com.ohair.stephen.util.controller.Event;

public class SceneToDisplay implements Event {

	private final Scene screen;

	public SceneToDisplay(Scene screen) {
		this.screen = screen;
	}

	public Scene getScene() {
		return screen;
	}

}
