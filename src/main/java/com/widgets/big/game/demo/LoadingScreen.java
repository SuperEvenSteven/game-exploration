package com.widgets.big.game.demo;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.widgets.big.game.demo.assets.AssetEnemies;
import com.widgets.big.game.demo.assets.AssetImage;
import com.widgets.big.game.demo.assets.AssetSprite;
import com.widgets.big.game.demo.assets.Assets;
import com.widgets.big.game.demo.assets.Assets.AssetType;
import com.widgets.big.game.engine.applet.Sprite;
import com.widgets.big.game.event.NewScreen;
import com.widgets.big.game.framework.Input.KeyEvent;
import com.widgets.big.game.framework.Screen;
import com.widgets.big.game.framework.Util;

public class LoadingScreen implements Screen {

	@Override
	public void update(float deltaTimeMs, List<KeyEvent> keyEvents) {
		System.out.println("loaded resources");
		// Setup hero images
		BufferedImage characterStanding = getImage("sprites/standing.png");
		BufferedImage characterWalkingLeft = getImage("sprites/walking_left.png");
		BufferedImage characterWalkingRight = getImage("sprites/walking_right.png");
		BufferedImage characterDucking = getImage("sprites/ducking.png");
		BufferedImage characterJumpingRight = getImage("sprites/jumping_right.png");
		BufferedImage characterJumpingLeft = getImage("sprites/jumping_left.png");

		Assets assets = Assets.instance();

		// Animation Sprites
		int[] standingDuration = { 500 };
		AssetSprite standingSprite = new AssetSprite(new Sprite(
				characterStanding, 40, 1, standingDuration));
		int[] walkingDuration = { 150, 150, 150 };
		AssetSprite heroWalkingLeft = new AssetSprite(new Sprite(
				characterWalkingLeft, 40, 3, walkingDuration));
		AssetSprite heroWalkingRight = new AssetSprite(new Sprite(
				characterWalkingRight, 40, 3, walkingDuration));
		int[] jumpingDuration = { 500 };
		AssetSprite heroJumpingLeft = new AssetSprite(new Sprite(
				characterJumpingLeft, 40, 1, jumpingDuration));
		AssetSprite heroJumpingRight = new AssetSprite(new Sprite(
				characterJumpingRight, 40, 1, jumpingDuration));
		int[] duckingDuration = { 500 };
		AssetSprite heroDucking = new AssetSprite(new Sprite(characterDucking,
				40, 1, duckingDuration));

		assets.put(AssetType.PLAYER_STANDING, standingSprite);
		assets.put(AssetType.PLAYER_WALK_LEFT, heroWalkingLeft);
		assets.put(AssetType.PLAYER_WALK_RIGHT, heroWalkingRight);
		assets.put(AssetType.PLAYER_JUMP_LEFT, heroJumpingLeft);
		assets.put(AssetType.PLAYER_JUMP_RIGHT, heroJumpingRight);
		assets.put(AssetType.PLAYER_DUCK, heroDucking);

		BufferedImage heliboy = getImage("sprites/heliboy.png");
		int[] enemyFrameDuration = { 50, 50, 50, 50, 50 };
		AssetSprite enemy1 = new AssetSprite(new Sprite(heliboy, 96, 5,
				enemyFrameDuration));
		assets.put(AssetType.ENEMY, enemy1);

		// Load background iamge to repeat
		BufferedImage background = getImage("backgrounds/background.png");
		assets.put(AssetType.BACKGROUND, new AssetImage(background));

		// Load tiles for tilemap
		BufferedImage tileDirt = getImage("tiles/tiledirt.png");
		BufferedImage tilegrassTop = getImage("tiles/tilegrasstop.png");
		BufferedImage tilegrassBot = getImage("tiles/tilegrassbot.png");
		BufferedImage tilegrassLeft = getImage("tiles/tilegrassleft.png");
		BufferedImage tilegrassRight = getImage("tiles/tilegrassright.png");

		assets.put(AssetType.TILE_DIRT, new AssetImage(tileDirt));
		assets.put(AssetType.TILE_GRASS_TOP, new AssetImage(tilegrassTop));
		assets.put(AssetType.TILE_GRASS_BOT, new AssetImage(tilegrassBot));
		assets.put(AssetType.TILE_GRASS_LEFT, new AssetImage(tilegrassLeft));
		assets.put(AssetType.TILE_GRASS_RIGHT, new AssetImage(tilegrassRight));

		assets.put(AssetType.ENEMIES, new AssetEnemies(new ArrayList<Enemy>()));

		System.out.println("resources loaded");

		Util.controller().event(new NewScreen(new MenuScreen()));
	}

	private BufferedImage getImage(String path) {
		URL url = LoadingScreen.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
			return img;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void paint(Graphics g, Component game) {
	}
}
