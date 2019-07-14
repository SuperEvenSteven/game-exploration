package com.ohair.stephen.game.demo.assets;

import com.ohair.stephen.game.demo.Player;

public class AssetPlayer implements Asset {
	private final Player player;

	public AssetPlayer(Player p) {
		player = p;
	}

	public Player getPlayer() {
		return player;
	}

}
