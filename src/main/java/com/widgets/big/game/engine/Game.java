package com.widgets.big.game.engine;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.TimeUnit;

import com.widgets.big.game.event.ScreenToDisplay;
import com.widgets.big.game.framework.Screen;
import com.widgets.big.game.framework.Util;
import com.widgets.util.controller.ControllerListener;

public class Game {

	private static final int FRAMES_PER_SECOND = 60;

	public static long REFRESH_INTERVAL_MS = TimeUnit.SECONDS.toMillis(1)
			/ FRAMES_PER_SECOND;

	private Screen screen;
	private Image image;
	private final Component component;
	private final ComponentInput input;

	long timeLastRunMs = System.currentTimeMillis();

	private final boolean useWaitNotify;

	public Game(Component component, Component keyListeningComponent,
			Screen screen, boolean useWaitNotify) {
		this.component = component;
		this.screen = screen;
		this.useWaitNotify = useWaitNotify;
		input = new ComponentInput(keyListeningComponent);
		Util.controller().addListener(ScreenToDisplay.class,
				new ControllerListener<ScreenToDisplay>() {

					@Override
					public void event(ScreenToDisplay event) {
						Game.this.setScreen(event.getScreen());
					}
				});
	}

	public Game(Component component, Component keyListeningComponent,
			Screen screen) {
		this(component, keyListeningComponent, screen, true);
	}

	private void runGameLoop() {
		System.out.println("starting loop");

		// update the game repeatedly
		while (true) {
			long durationMs = redraw();
			try {
				Thread.sleep(Math.max(0, REFRESH_INTERVAL_MS - durationMs));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private final Object redrawLock = new Object();

	private long redraw() {

		long t = System.currentTimeMillis();
		long deltaTimeMs = t - timeLastRunMs;
		screen.update(deltaTimeMs, input.getKeyEvents());
		timeLastRunMs = t;

		// asynchronously signals the paint to happen in the swing thread
		component.repaint();

		// use a lock here that is only released once the paintComponent
		// has happened so that component.repaint() calls don't queue up that
		// are delayed and we get jerky drawing

		waitForPaint();

		return System.currentTimeMillis() - t;
	}

	private void waitForPaint() {
		if (useWaitNotify)
			try {
				synchronized (redrawLock) {
					redrawLock.wait();
				}
			} catch (InterruptedException e) {
			}
	}

	private void clearRedrawLock() {
		synchronized (redrawLock) {
			redrawLock.notify();
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
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				runGameLoop();
			}
		});
		thread.start();
	}

	public void update(java.awt.Graphics g) {
		clearRedrawLock();
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
