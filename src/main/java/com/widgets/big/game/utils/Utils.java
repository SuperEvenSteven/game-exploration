package com.widgets.big.game.utils;

public class Utils {

	// TODO this does not look like it returns millis!!!
	public static float calculateDeltaMs(float lastTimeNs) {
		float deltaTime = (System.nanoTime() - lastTimeNs) / 1000000f;

		if (deltaTime > 3.15) {
			deltaTime = (float) 3.15;
		}
		return deltaTime;
	}

}
