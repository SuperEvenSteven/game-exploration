package com.widgets.big.game.framework;

import java.awt.Component;

public interface Screen {

	void init();

	void update(float deltaTimeMs);

	void paint(java.awt.Graphics g, Component panel);

}
