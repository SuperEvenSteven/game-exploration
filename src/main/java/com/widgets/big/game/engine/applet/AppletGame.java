package com.widgets.big.game.engine.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;

import com.widgets.big.game.event.ScreenToDisplay;
import com.widgets.big.game.framework.Screen;
import com.widgets.big.game.framework.Util;
import com.widgets.big.game.utils.Utils;
import com.widgets.util.controller.ControllerListener;

public class AppletGame extends Applet {

	private static final long serialVersionUID = 2397885928260855130L;

	private Screen screen;

	float deltaTimeMs = 0;

	java.awt.Graphics second;

	float timeLastRunMs = System.nanoTime();

	private ComponentInput input;

	private Image image;

	private Runnable runnable;

	public AppletGame(Screen startScreen) {
		screen = startScreen;
	}

	@Override
	public void init() {

		System.out.println("game applet init method fired");

		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Alien Game");

		input = new ComponentInput(this);

		Util.controller().addListener(ScreenToDisplay.class,
				new ControllerListener<ScreenToDisplay>() {

					@Override
					public void event(ScreenToDisplay event) {
						AppletGame.this.setScreen(event.getScreen());
					}
				});

	}

	// Starts the thread that runs our main game loop
	@Override
	public void start() {
		System.out.println("game applet start method fired");
		runnable = new Thread(new Runnable() {
			@Override
			public void run() {
				AppletGame.this.run();
			}
		});
		Thread thread = new Thread(runnable);
		thread.start();
	}

	// Double buffering trick
	@Override
	public void update(java.awt.Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(java.awt.Graphics g) {
		screen.paint(g, this);
	}

	private void run() {
		System.out.println("starting loop");
		// update the game repeatedly
		while (true) {
			try {
				deltaTimeMs = Utils.calculateDeltaMs(timeLastRunMs);
				screen.update(deltaTimeMs, input.getKeyEvents());
				repaint();

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

}
