package com.widgets.big.game.demo.assets;

import java.util.ArrayList;
import java.util.List;

import com.widgets.big.game.demo.Enemy;

public class AssetEnemies implements Asset {

	private List<Enemy> enemies = new ArrayList<Enemy>();

	public AssetEnemies(List<Enemy> e) {
		enemies = e;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

}
