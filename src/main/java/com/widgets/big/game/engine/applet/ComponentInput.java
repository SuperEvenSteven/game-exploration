package com.widgets.big.game.engine.applet;

import java.awt.Component;
import java.util.List;

import com.widgets.big.game.framework.Input;

public class ComponentInput implements Input {
	ComponentKeyHandler keyHandler;

	public ComponentInput(Component game) {
		keyHandler = new ComponentKeyHandler(game);
	}

	@Override
	public boolean isKeyPressed(int keyCode) {
		return false;
	}

	@Override
	public boolean isTouchDown(int pointer) {
		return false;
	}

	@Override
	public int getTouchX(int pointer) {
		return 0;
	}

	@Override
	public int getTouchY(int pointer) {
		return 0;
	}

	@Override
	public float getAccelX() {
		return 0;
	}

	@Override
	public float getAccelY() {
		return 0;
	}

	@Override
	public float getAccelZ() {
		return 0;
	}

	@Override
	public List<KeyEvent> getKeyEvents() {
		return keyHandler.getKeyEvents();
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		return null;
	}

}
