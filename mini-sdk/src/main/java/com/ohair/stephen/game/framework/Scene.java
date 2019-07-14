package com.ohair.stephen.game.framework;

import java.awt.Component;
import java.util.List;

import com.ohair.stephen.game.framework.Input.KeyEvent;

public interface Scene {

	void update(float deltaTimeMs, List<KeyEvent> keyEvents);

	void paint(java.awt.Graphics g, Component panel);

}
