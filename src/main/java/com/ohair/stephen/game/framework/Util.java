package com.ohair.stephen.game.framework;

import com.ohair.stephen.util.controller.Controller;
import com.ohair.stephen.util.controller.SynchronousController;

public class Util {

	private static Controller controller = new SynchronousController();

	public static Controller controller() {
		return controller;
	}
}
