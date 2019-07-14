package com.ohair.stephen.game.demo.assets;

import java.awt.image.BufferedImage;

public class AssetImage implements Asset {

	private final BufferedImage image;

	public AssetImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}
}
