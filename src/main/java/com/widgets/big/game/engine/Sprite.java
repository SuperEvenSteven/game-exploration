package com.widgets.big.game.engine;

import java.awt.image.BufferedImage;

import com.widgets.big.game.framework.Animation;
import com.widgets.big.game.utils.SpriteLoader;

/**
 * A 2-dimensional image that is to be integrated into a larger image i.e. a
 * Background. Each image is expected to be squared i.e. 48x48
 * 
 * @author Stephen O'Hair
 * 
 */
public class Sprite {

	private Animation animation;

	/**
	 * Constructor.
	 * 
	 * @param image
	 * @param frameHeight
	 * @param size
	 */
	public Sprite(BufferedImage image, int frameHeight, int numberOfTiles,
			int frameDurations[]) {

		animation = new Animation();

		SpriteLoader.parseSprite(image, frameHeight, numberOfTiles,
				frameDurations, animation);

	}

	/**
	 * Updates the GameObject relative to the elapsed time in millis.
	 * 
	 * @param deltaTimeElapsedMs
	 */
	void update(float deltaTimeElapsedMs) {
		// update animation
	}

	/**
	 * Returns the {@link Sprite} {@link Animation}
	 * 
	 * @return
	 */
	public Animation getAnimation() {
		return animation;
	}
}
