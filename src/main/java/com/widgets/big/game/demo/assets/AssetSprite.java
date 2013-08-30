package com.widgets.big.game.demo.assets;

import com.widgets.big.game.engine.ui.Sprite;

public class AssetSprite implements Asset {

	private final Sprite sprite;

	public AssetSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

}
