package com.widgets.big.game.demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Self contained demo swing game for stackoverflow frame rate question.
 * 
 * @author dave
 * 
 */
public class MyGame {

	private static final long REFRESH_INTERVAL_MS = 17;
	private final Object redrawLock = new Object();
	private Component component;
	private boolean keepGoing = true;

	public void start(Component component) {
		this.component = component;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				runGameLoop();
			}
		});
		thread.start();
	}

	public void stop() {
		keepGoing = false;
	}

	/**
	 * Self contained demo swing game for stackoverflow frame rate question.
	 * 
	 * @author dave
	 * 
	 */
	private void runGameLoop() {
		// update the game repeatedly
		while (keepGoing) {
			long durationMs = redraw();
			try {
				Thread.sleep(Math.max(0, REFRESH_INTERVAL_MS - durationMs));
			} catch (InterruptedException e) {
			}
		}
	}

	private long redraw() {

		long t = System.currentTimeMillis();

		// At this point perform changes to the model that the component will
		// redraw

		updateModel();

		drawModelToLocalImage();

		// asynchronously signals the paint to happen in the swing thread
		component.repaint();

		// use a lock here that is only released once the paintComponent
		// has happened so that component.repaint() calls don't queue up that
		// are delayed and we get jerky drawing

		waitForPaint();

		// return time taken to do redraw in ms
		return System.currentTimeMillis() - t;
	}

	private void drawModelToLocalImage() {

	}

	private void updateModel() {
		// do stuff here to the model
	}

	private void waitForPaint() {
		try {
			synchronized (redrawLock) {
				redrawLock.wait();
			}
		} catch (InterruptedException e) {
		}
	}

	private void resume() {
		synchronized (redrawLock) {
			redrawLock.notify();
		}
	}

	public void paint(Graphics g) {
		// paint the model to the graphics

		// resume the game loop
		resume();
	}

	public static class MyComponent extends JPanel {

		private final MyGame game;

		public MyComponent(MyGame game) {
			this.game = game;
		}

		@Override
		protected void paintComponent(Graphics g) {
			game.paint(g);
		}
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MyGame game = new MyGame();
				MyComponent component = new MyComponent(game);

				JFrame frame = new JFrame();
				// put the component in a frame

				frame.setTitle("Demo");
				frame.setSize(800, 600);

				frame.setLayout(new BorderLayout());
				frame.getContentPane().add(component, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

				game.start(component);
			}
		});
	}

}
