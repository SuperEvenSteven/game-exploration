package com.widgets.big.game.engine.applet;

import java.util.List;

import com.widgets.big.game.framework.Input;

public class AppletInput implements Input {
	AppletKeyHandler keyHandler;

	public AppletInput(AppletGame game) {
		keyHandler = new AppletKeyHandler(game);
	}

	public boolean isKeyPressed(int keyCode) {
		return false;
	}

	public boolean isTouchDown(int pointer) {
		return false;
	}

	public int getTouchX(int pointer) {
		return 0;
	}

	public int getTouchY(int pointer) {
		return 0;
	}

	public float getAccelX() {
		return 0;
	}

	public float getAccelY() {
		return 0;
	}

	public float getAccelZ() {
		return 0;
	}

	public List<KeyEvent> getKeyEvents() {
		return keyHandler.getKeyEvents();
	}

	public List<TouchEvent> getTouchEvents() {
		return null;
	}

}
