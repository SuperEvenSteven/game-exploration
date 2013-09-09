package com.widgets.util.controller;

public interface Controller {
	<T extends Event> void event(T event);

	<T extends Event> void addListener(Class<T> cls,
			ControllerListener<T> listener);

	<T extends Event> boolean removeListener(Class<T> cls,
			ControllerListener<T> listener);

}
