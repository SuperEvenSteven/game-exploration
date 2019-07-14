package com.ohair.stephen.util.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ControllerCore {
	private final Map<Class<? extends Event>, List<ControllerListener<? extends Event>>> map = new HashMap<Class<? extends Event>, List<ControllerListener<? extends Event>>>();

	/**
	 * Adds a listener for events of type cls. Also will be notified of any
	 * events that are subclasses of cls.
	 * 
	 * @param <T>
	 * @param cls
	 * @param listener
	 */
	public synchronized <T extends Event> void addListener(Class<T> cls,
			ControllerListener<T> listener) {
		if (map.get(cls) == null)
			map.put(cls, new ArrayList<ControllerListener<? extends Event>>());
		map.get(cls).add(listener);
	}

	/**
	 * Returns true if and only if the listener was registered with the
	 * controlller and was successfully removed.
	 * 
	 * @param <T>
	 * @param cls
	 * @param listener
	 * @return
	 */
	public synchronized <T extends Event> boolean removeListener(Class<T> cls,
			ControllerListener<T> listener) {
		if (map.get(cls) == null)
			return false;
		else
			return map.get(cls).remove(listener);
	}

	@SuppressWarnings("unchecked")
	public synchronized <T extends Event> List<ControllerListener<T>> getListeners(
			Class<T> cls) {
		if (map.containsKey(cls)) {
			List<ControllerListener<T>> list = new ArrayList<ControllerListener<T>>();
			for (ControllerListener<? extends Event> listener : map.get(cls)) {
				list.add((ControllerListener<T>) listener);
			}
			return list;
		} else
			return new ArrayList<ControllerListener<T>>();
	}

	public synchronized Set<Class<? extends Event>> getClasses() {
		return new HashSet<Class<? extends Event>>(map.keySet());
	}

}
