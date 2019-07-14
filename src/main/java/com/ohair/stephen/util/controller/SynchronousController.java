package com.ohair.stephen.util.controller;

/**
 * Acts as an event bus. Notifies registered listeners of events synchronously.
 * 
 * @author Dave Moten
 * 
 */
public class SynchronousController implements Controller {

	public enum Singleton {

		INSTANCE;

		private final SynchronousController instance = new SynchronousController();

		public SynchronousController instance() {
			return instance;
		}

	}

	private final ControllerCore core = new ControllerCore();

	/**
	 * Notifies the controller that an event occurred. The controller will then
	 * notify registered listeners interested in that type of event.
	 * 
	 * @param event
	 */
	@Override
	public <T extends Event> void event(T event) {
		for (Class<? extends Event> cls : core.getClasses()) {
			if (cls.isInstance(event)) {
				notifyListeners(cls, event);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T extends Event> void notifyListeners(Class<? extends T> cls,
			T event) {
		for (ControllerListener listener : core.getListeners(cls)) {
			listener.event(event);
		}
	}

	@Override
	public <T extends Event> void addListener(Class<T> cls,
			ControllerListener<T> listener) {
		core.addListener(cls, listener);
	}

	@Override
	public <T extends Event> boolean removeListener(Class<T> cls,
			ControllerListener<T> listener) {
		return core.removeListener(cls, listener);
	}
}
