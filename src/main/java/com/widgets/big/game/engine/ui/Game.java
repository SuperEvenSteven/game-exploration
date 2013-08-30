package com.widgets.big.game.engine.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import com.widgets.big.game.event.ScreenToDisplay;
import com.widgets.big.game.framework.Screen;
import com.widgets.big.game.framework.Util;
import com.widgets.big.game.utils.Utils;
import com.widgets.util.controller.ControllerListener;

public class Game {

	private Screen screen;
	private Image image;
	private final Component component;
	private final ComponentInput input;

	public Game(Component component, Screen screen) {
		this.component = component;
		this.screen = screen;
		input = new ComponentInput(component);
		Util.controller().addListener(ScreenToDisplay.class,
				new ControllerListener<ScreenToDisplay>() {

					@Override
					public void event(ScreenToDisplay event) {
						Game.this.setScreen(event.getScreen());
					}
				});
	}

	public void run() {
		System.out.println("starting loop");

		float timeLastRunMs = System.nanoTime();

		// update the game repeatedly
		while (true) {
			try {
				float deltaTimeMs = Utils.calculateDeltaMs(timeLastRunMs);
				screen.update(deltaTimeMs, input.getKeyEvents());
				component.repaint();

				// at full speed this will run at 60fps by sleeping for 17ms
				// every frame
				Thread.sleep(17);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				System.out.println(e);
			}
			timeLastRunMs = System.nanoTime();
		}
	}

	private void setScreen(Screen screen) {
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");

		// this.screen.pause();
		// this.screen.dispose();
		// screen.resume();
		screen.update(0, input.getKeyEvents());
		this.screen = screen;
	}

	public void init() {
		image = component.createImage(component.getWidth(),
				component.getHeight());
	}

	public void start() {
		System.out.println("game applet start method fired");
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Game.this.run();
			}
		});
		thread.start();
	}

	public void update(java.awt.Graphics g) {
		// Buffered drawing
		Graphics graphics = image.getGraphics();
		graphics.setColor(component.getBackground());
		graphics.fillRect(0, 0, component.getWidth(), component.getHeight());
		graphics.setColor(component.getForeground());
		paint(graphics);

		g.drawImage(image, 0, 0, component);
	}

	public void paint(java.awt.Graphics g) {
		screen.paint(g, component);
	}
}
