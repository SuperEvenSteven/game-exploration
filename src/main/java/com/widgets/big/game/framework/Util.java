package com.widgets.big.game.framework;

import com.widgets.util.controller.Controller;
import com.widgets.util.controller.SynchronousController;

public class Util {

	private static Controller controller = new SynchronousController();

	public static Controller controller() {
		return controller;
	}
}
