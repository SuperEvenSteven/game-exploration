package com.widgets.big.game.demo.assets;

import java.awt.Image;

public class AssetTile implements Asset {

	private final Image tile;

	public AssetTile(Image image) {
		tile = image;
	}

	public Image getTile() {
		return tile;
	}

}
