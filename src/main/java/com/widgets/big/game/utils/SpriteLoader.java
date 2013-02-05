package com.widgets.big.game.utils;

import java.awt.image.BufferedImage;

import com.widgets.big.game.framework.Animation;

public class SpriteLoader {

	public static void parseSprite(BufferedImage image, int frameSize,
			int numberOfTiles, int[] frameDurations, Animation animation) {
		// detect if image is a tileset
		int rows = 1;
		int cols = 1;
		if (image.getHeight(null) % frameSize == 0)
			rows = image.getHeight(null) / frameSize;
		else
			throw new RuntimeException(
					"sprite height not equally divisible by specified tile height");

		if (image.getWidth(null) % frameSize == 0)
			cols = image.getWidth(null) / frameSize;
		else
			throw new RuntimeException(
					"sprite width not equally divisible by specified tile height");

		int currentTile = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (animation.getFrameCount() < numberOfTiles) {
					System.out.println("size : x = " + frameSize * (j + 1)
							+ ", y = " + frameSize * (i + 1));
					animation.addFrame(image.getSubimage(frameSize * j,
							frameSize * i, frameSize - 1, frameSize - 1),
							frameDurations[currentTile]);
				}
				currentTile++;
			}
		}
	}

}
