import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

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
	private Image imageBuffer;

	public void start(Component component) {
		this.component = component;
		imageBuffer = component.createImage(component.getWidth(),
				component.getHeight());
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

		// draw the model state to a buffered image which when ready will get
		// painted by component.paint().
		drawModelToImageBuffer();

		// asynchronously signals the paint to happen in the awt event
		// dispatcher thread
		component.repaint();

		// use a lock here that is only released once the paintComponent
		// has happened so that we know exactly when the paint was completed and
		// thus know how long to pause till the next redraw.
		waitForPaint();

		// return time taken to do redraw in ms
		return System.currentTimeMillis() - t;
	}

	private void updateModel() {
		// do stuff here to the model based on time
	}

	private void drawModelToImageBuffer() {
		drawModel(imageBuffer.getGraphics());
	}

	private int x = 50;
	private int y = 50;

	private void drawModel(Graphics g) {
		g.setColor(component.getBackground());
		g.fillRect(0, 0, component.getWidth(), component.getHeight());
		g.setColor(component.getForeground());
		g.drawString("Hi", x++, y++);
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
		// paint the buffered image to the graphics
		g.drawImage(imageBuffer, 0, 0, component);

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
