package com.widgets.big.game.demo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.widgets.big.game.engine.applet.AppletGame;
import com.widgets.big.game.engine.applet.Sprite;
import com.widgets.big.game.framework.Game;
import com.widgets.big.game.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTimeMs) {
		System.out.println("loaded resources");
		// Setup hero images and load them into Sprites
		Assets.characterStanding = getImage("sprites/standing.png");
		Assets.characterWalkingLeft = getImage("sprites/walking_left.png");
		Assets.characterWalkingRight = getImage("sprites/walking_right.png");
		Assets.characterDucking = getImage("sprites/ducking.png");
		Assets.characterJumpingRight = getImage("sprites/jumping_right.png");
		Assets.characterJumpingLeft = getImage("sprites/jumping_left.png");

		// Animation Sprites
		int[] standingDuration = { 500 };
		Assets.heroStanding = new Sprite(Assets.characterStanding, 40, 1,
				standingDuration);
		int[] walkingDuration = { 150, 150, 150 };
		Assets.heroWalkingLeft = new Sprite(Assets.characterWalkingLeft, 40, 3,
				walkingDuration);
		Assets.heroWalkingRight = new Sprite(Assets.characterWalkingRight, 40,
				3, walkingDuration);
		int[] jumpingDuration = { 500 };
		Assets.heroJumpingLeft = new Sprite(Assets.characterJumpingLeft, 40, 1,
				jumpingDuration);
		Assets.heroJumpingRight = new Sprite(Assets.characterJumpingRight, 40,
				1, jumpingDuration);
		int[] duckingDuration = { 500 };
		Assets.heroDucking = new Sprite(Assets.characterDucking, 40, 1,
				duckingDuration);

		Assets.heliboy = getImage("sprites/heliboy.png");
		int[] enemyFrameDuration = { 50, 50, 50, 50, 50 };
		Assets.enemy = new Sprite(Assets.heliboy, 96, 5, enemyFrameDuration);

		// Load backgrounds
		Assets.background = getImage("backgrounds/background.png");

		// Load tiles for tilemap
		Assets.tileDirt = getImage("tiles/tiledirt.png");
		Assets.tilegrassTop = getImage("tiles/tilegrasstop.png");
		Assets.tilegrassBot = getImage("tiles/tilegrassbot.png");
		Assets.tilegrassLeft = getImage("tiles/tilegrassleft.png");
		Assets.tilegrassRight = getImage("tiles/tilegrassright.png");
		System.out.println("resources loaded");

		game.setScreen(new MenuScreen(game));
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
	public void init() {
		super.init();
	}

	@Override
	public void paint(Graphics g, AppletGame game) {
		super.paint(g, game);
	}
}
