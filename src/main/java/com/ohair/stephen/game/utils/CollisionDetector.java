package com.ohair.stephen.game.utils;

import java.awt.Rectangle;
import java.util.List;

public class CollisionDetector {

	public static boolean hasCollided(Rectangle r1, List<Rectangle> r2) {
		for (Rectangle rect : r2) {
			if (r1.intersects(rect))
				return true;
		}
		return false;
	}

	public static boolean hasCollided(Rectangle r1, Rectangle... r2) {
		for (Rectangle rect : r2) {
			if (r1.intersects(rect))
				return true;
		}
		return false;
	}
}
