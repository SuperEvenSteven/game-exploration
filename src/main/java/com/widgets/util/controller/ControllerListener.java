package com.widgets.util.controller;

public interface ControllerListener<T extends Event> {
	void event(T event);
}
