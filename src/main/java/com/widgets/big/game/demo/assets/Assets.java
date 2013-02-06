package com.widgets.big.game.demo.assets;

import java.util.HashMap;
import java.util.Map;

public class Assets {

	private static Assets instance;

	public static synchronized Assets instance() {
		if (instance == null)
			instance = new Assets();
		return instance;
	}

	public enum AssetType {
		MENU, SPLASH, BACKGROUND, BACKGROUND1, BACKGROUND2, HERO, ENEMIES, PLAYER_STANDING, PLAYER_WALK_LEFT, PLAYER_WALK_RIGHT, PLAYER_JUMP_LEFT, PLAYER_JUMP_RIGHT, PLAYER_DUCK, ENEMY, TILE_DIRT, TILE_GRASS, TILE_GRASS_TOP, TILE_GRASS_BOT, TILE_GRASS_LEFT, TILE_GRASS_RIGHT
	}

	private final Map<AssetType, Asset> assets = new HashMap<AssetType, Asset>();

	public Asset get(AssetType type) {
		return assets.get(type);
	}

	public void put(AssetType type, Asset asset) {
		assets.put(type, asset);
	}

	// // Images
	// public static Image image, menu, splash, background;
	//
	// public static BufferedImage characterStanding, characterWalkingLeft,
	// characterWalkingRight, characterJumpingLeft, characterJumpingRight,
	// characterDucking, heliboy;
	//
	// public static Image tileDirt, tilegrassTop, tilegrassBot, tilegrassLeft,
	// tilegrassRight;
	// public static Sprite heroStanding, heroWalkingLeft, heroWalkingRight,
	// heroJumpingLeft, heroJumpingRight, heroDucking, enemy;

	// // Define all game objects
	// public static Player hero;
	//
	// public static List<Enemy> enemies = new ArrayList<Enemy>();
	//
	// public static Background bg1;
	// public static Background bg2;

}
