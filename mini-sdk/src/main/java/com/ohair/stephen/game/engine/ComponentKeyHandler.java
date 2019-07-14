package com.ohair.stephen.game.engine;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.ohair.stephen.game.framework.Pool;
import com.ohair.stephen.game.framework.Input.KeyEvent;
import com.ohair.stephen.game.framework.Pool.PoolObjectFactory;

public class ComponentKeyHandler implements KeyListener {

	boolean[] pressedKeys = new boolean[128];
	Pool<KeyEvent> keyEventPool;
	List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
	private final List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

	public ComponentKeyHandler(Component component) {
		PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>() {

			@Override
			public KeyEvent createObject() {
				return new KeyEvent();
			}
		};
		keyEventPool = new Pool<KeyEvent>(factory, 100);
		component.addKeyListener(this);
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

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		synchronized (this) {
			KeyEvent keyEvent = keyEventPool.newObject();
			keyEvent.keyCode = e.getKeyCode();
			keyEvent.keyChar = e.getKeyChar();
			keyEvent.type = KeyEvent.KEY_DOWN;
			keyEventsBuffer.add(keyEvent);
		}
	}

	@Override
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

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {

	}
}
