package com.widgets.big.game.framework;

import java.awt.Component;
import java.util.List;

import com.widgets.big.game.framework.Input.KeyEvent;

public interface Screen {

	void update(float deltaTimeMs, List<KeyEvent> keyEvents);

	void paint(java.awt.Graphics g, Component panel);

}
