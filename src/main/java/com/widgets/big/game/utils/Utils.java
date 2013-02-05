package com.widgets.big.game.utils;

public class Utils {

	public static float calculateDeltaMs(float lastTimeMs) {
		float deltaTime = (System.nanoTime() - lastTimeMs) / 10000000.000f;

		if (deltaTime > 3.15) {
			deltaTime = (float) 3.15;
		}
		return deltaTime;
	}

}
