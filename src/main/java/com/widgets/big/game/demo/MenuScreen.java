package com.widgets.big.game.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.widgets.big.game.engine.applet.AppletGame;
import com.widgets.big.game.framework.Game;
import com.widgets.big.game.framework.Input;
import com.widgets.big.game.framework.Input.KeyEvent;
import com.widgets.big.game.framework.Screen;

public class MenuScreen extends Screen {

	private static final int KEY_ENTER = 10;
	private List<KeyEvent> keyEvents;

	public MenuScreen(Game game) {
		super(game);
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void update(float deltaTimeMs) {
		keyEvents = game.getInput().getKeyEvents();

		// keyEvents are being modified outside of the current thread
		for (int i = 0; i < keyEvents.size(); i++) {
			KeyEvent keyEvent = keyEvents.get(i);
			if (keyEvent.type == Input.KeyEvent.KEY_DOWN) {
				System.out.println(keyEvent.keyChar + " character pressed");
				System.out.println(keyEvent.keyCode + " code pressed");
				if (keyEvent.keyCode == KEY_ENTER) {
					game.setScreen(new FirstLevelScreen(game));
				}
			}
		}

	}

	@Override
	public void paint(Graphics g, AppletGame game) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 480);
		g.setColor(Color.WHITE);
		g.drawString("Demo Game", 320, 240);
		g.setColor(Color.BLUE);
		g.drawString("Hit ENTER to try again", 270, 290);
	}

}
