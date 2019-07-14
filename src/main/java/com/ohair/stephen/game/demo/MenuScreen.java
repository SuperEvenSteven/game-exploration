package com.ohair.stephen.game.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;

import com.ohair.stephen.game.event.SceneToDisplay;
import com.ohair.stephen.game.framework.Input;
import com.ohair.stephen.game.framework.Scene;
import com.ohair.stephen.game.framework.Util;
import com.ohair.stephen.game.framework.Input.KeyEvent;

public class MenuScreen implements Scene {

	private static final int KEY_ENTER = 10;

	@Override
	public void update(float deltaTimeMs, List<KeyEvent> keyEvents) {

		// keyEvents are being modified outside of the current thread
		for (int i = 0; i < keyEvents.size(); i++) {
			KeyEvent keyEvent = keyEvents.get(i);
			if (keyEvent.type == Input.KeyEvent.KEY_DOWN) {
				System.out.println(keyEvent.keyChar + " character pressed");
				System.out.println(keyEvent.keyCode + " code pressed");
				if (keyEvent.keyCode == KEY_ENTER) {
					Util.controller().event(
							new SceneToDisplay(new FirstLevelScreen()));
				}
			}
		}

	}

	@Override
	public void paint(Graphics g, Component game) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 480);
		g.setColor(Color.WHITE);
		g.drawString("Demo Game", 320, 240);
		g.setColor(Color.BLUE);
		g.drawString("Hit ENTER to try again", 270, 290);
	}

}
