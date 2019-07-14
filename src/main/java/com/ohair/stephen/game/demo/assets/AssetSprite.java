package com.ohair.stephen.game.demo.assets;

import com.ohair.stephen.game.engine.Sprite;

public class AssetSprite implements Asset {

	private final Sprite sprite;

	public AssetSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

}
