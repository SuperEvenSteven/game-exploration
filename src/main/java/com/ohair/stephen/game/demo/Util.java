package com.ohair.stephen.game.demo;

import com.ohair.stephen.game.engine.Game;

public class Util {

	public static int factorByElapsedTimeMs(int value, float elapsedTimeMs) {
		return Math.round(value * elapsedTimeMs / Game.REFRESH_INTERVAL_MS);
	}
}
