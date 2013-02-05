package com.widgets.big.game.engine.applet;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.widgets.big.game.framework.Pool;
import com.widgets.big.game.framework.Input.KeyEvent;
import com.widgets.big.game.framework.Pool.PoolObjectFactory;

public class AppletKeyHandler implements KeyListener {

	boolean[] pressedKeys = new boolean[128];
	Pool<KeyEvent> keyEventPool;
	List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
	private final List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

	public AppletKeyHandler(AppletGame game) {
		PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>() {

			public KeyEvent createObject() {
				return new KeyEvent();
			}
		};
		keyEventPool = new Pool<KeyEvent>(factory, 100);
		game.addKeyListener(this);
	}

	public boolean isKeyPressed(int keyCode) {
		if (keyCode < 0 || keyCode > 127)
			return false;
		return pressedKeys[keyCode];
	}

	public List<KeyEvent> getKeyEvents() {
		synchronized (this) {
			int len = keyEvents.size();
			for (int i = 0; i < len; i++)
				keyEventPool.free(keyEvents.get(i));
			keyEvents.clear();
			keyEvents.addAll(keyEventsBuffer);
			keyEventsBuffer.clear();
			return keyEvents;
		}
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		synchronized (this) {
			KeyEvent keyEvent = keyEventPool.newObject();
			keyEvent.keyCode = e.getKeyCode();
			keyEvent.keyChar = e.getKeyChar();
			keyEvent.type = KeyEvent.KEY_DOWN;
			keyEventsBuffer.add(keyEvent);
		}
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
		synchronized (this) {
			KeyEvent keyEvent = keyEventPool.newObject();
			keyEvent.keyCode = e.getKeyCode();
			keyEvent.keyChar = e.getKeyChar();
			keyEvent.type = KeyEvent.KEY_UP;
			keyEventsBuffer.add(keyEvent);
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void keyTyped(java.awt.event.KeyEvent e) {

	}
}
