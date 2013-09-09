package com.widgets.big.game.demo;

import com.widgets.big.game.engine.Game;

public class Util {

	public static int factorByElapsedTimeMs(int value, float elapsedTimeMs) {
		return Math.round(value * elapsedTimeMs / Game.REFRESH_INTERVAL_MS);
	}
}
