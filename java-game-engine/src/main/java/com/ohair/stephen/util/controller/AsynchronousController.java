package com.ohair.stephen.util.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousController implements Controller {

	public enum Singleton {

		INSTANCE;

		private final AsynchronousController instance = new AsynchronousController();

		public AsynchronousController instance() {
			return instance;
		}

	}

	private final ExecutorService executorService;

	private final ControllerCore core = new ControllerCore();

	public AsynchronousController(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public AsynchronousController() {
		this(Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors()));
	}

	/**
	 * Notifies the controller that an event occurred. The controller will then
	 * notify registered listeners interested in that type of event.
	 * 
	 * @param event
	 */
	@Override
	public <T extends Event> void event(final T event) {
		for (Class<? extends Event> cls : core.getClasses()) {
			if (cls.isInstance(event)) {
				for (@SuppressWarnings("rawtypes")
				final ControllerListener listener : core.getListeners(cls)) {
					Runnable r = new Runnable() {
						@SuppressWarnings("unchecked")
						@Override
						public void run() {
							try {
								listener.event(event);
							} catch (RuntimeException e) {
								e.printStackTrace();
							}
						}
					};
					executorService.execute(r);
				}
			}
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
