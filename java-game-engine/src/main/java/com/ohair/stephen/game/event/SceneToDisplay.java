package com.ohair.stephen.game.event;

import com.ohair.stephen.game.framework.Scene;
import com.ohair.stephen.util.controller.Event;

public class SceneToDisplay implements Event {

	private final Scene scene;

	public SceneToDisplay(Scene scene) {
		this.scene = scene;
	}

	public Scene getScene() {
		return scene;
	}

}
