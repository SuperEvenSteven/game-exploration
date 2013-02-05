package com.widgets.big.game.demo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.widgets.big.game.engine.applet.Sprite;
import com.widgets.big.game.framework.Background;

public class Assets {

	// public enum Tile {
	// DIRT, GRASS_BOTTOM, GRASS_LEFT, GRASS_RIGHT, GRASS_TOP
	// }

	// Images
	public static Image image, menu, splash, background;

	public static BufferedImage characterStanding, characterWalkingLeft,
			characterWalkingRight, characterJumpingLeft, characterJumpingRight,
			characterDucking, heliboy;

	public static Image tileDirt, tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight;
	public static Sprite heroStanding, heroWalkingLeft, heroWalkingRight,
			heroJumpingLeft, heroJumpingRight, heroDucking, enemy;

	// Define all game objects
	public static Player hero;

	public static List<Enemy> enemies = new ArrayList<Enemy>();

	public static Background bg1;
	public static Background bg2;

}
