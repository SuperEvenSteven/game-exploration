package com.widgets.big.game.framework;

public interface Game {
	public Input getInput();

	public void setScreen(Screen screen);

	public Screen getCurrentScreen();

	public Screen getStartScreen();

}
