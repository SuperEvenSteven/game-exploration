package com.widgets.big.game.demo;

import java.awt.Image;
import java.awt.Rectangle;

import com.widgets.big.game.demo.Player.ActionState;

/**
 * @author Stephen O'Hair
 * 
 */
public class Tile {

	private static final int CEILING = 2;
	private static final int LEFT_GROUND = 4;
	private static final int RIGHT_GROUND = 6;
	private static final int GROUND = 8;
	private static final int DIRT = 5;
	private int tileX, tileY, speedX, type;
	private Image tileImage;

	private final Player hero = Assets.hero;

	private final Rectangle tileBoundary;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		tileBoundary = new Rectangle(0, 0, 0, 0);

		if (type == DIRT) {
			tileImage = Assets.tileDirt;
		} else if (type == GROUND) {
			tileImage = Assets.tilegrassTop;
		} else if (type == LEFT_GROUND) {
			tileImage = Assets.tilegrassLeft;

		} else if (type == RIGHT_GROUND) {
			tileImage = Assets.tilegrassRight;

		} else if (type == CEILING) {
			tileImage = Assets.tilegrassBot;
		} else {
			type = 0;
		}

	}

	public void update(float deltaTimeElapsedMs) {
		speedX = Assets.bg1.getSpeedX() * 5;
		tileX += speedX;
		// move the tile boundary in sync with the tile itself
		tileBoundary.setBounds(tileX, tileY, 40, 40);

		// if any object colides with the 25 squares surrounding the player then
		// check if it has collided with the player otherwise ignore it
		if (tileBoundary.intersects(Player.perimeter) && type != 0) {
			checkHorizontalCollision();
			checkVerticalCollision();
		}

	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	/**
	 * Checks if the player has collided vertically i.e. tile like a platform or
	 * ground. Sets the player above the tile in a standing state.
	 */
	public void checkVerticalCollision() {
		if (tileBoundary.intersects(hero.getBoundaries().get(
				Player.ENTIRE_PLAYER))) {
			switch (type) {
			case GROUND:
				Assets.hero.setJumped(false);
				if (Assets.hero.getSpeedX() == 0 && !Assets.hero.isDucked()) {
					Assets.hero.setSpriteAction(ActionState.STANDING);
				} else if (Assets.hero.getSpeedX() > 0)
					Assets.hero.setSpriteAction(ActionState.WALKING_RIGHT);
				else if (Assets.hero.getSpeedX() < 0)
					Assets.hero.setSpriteAction(ActionState.WALKING_LEFT);
				Assets.hero.setSpeedY(0);
				Assets.hero.setCenterY(tileY - 19);
				// System.out.println("player collided with ground or dir");
				break;
			case CEILING:
				Assets.hero.setCenterY(tileY + 63);
				// System.out.println("player collided with ceiling");
				break;
			}
		}
	}

	public void checkHorizontalCollision() {
		if (tileBoundary.intersects(hero.getBoundaries().get(
				Player.LEFT_SIDE_PLAYER))) {
			switch (type) {
			case DIRT:
				Assets.hero.setSpeedX(0);
				Assets.hero.setCenterX(tileX + 19);
				break;
			case LEFT_GROUND:
				Assets.hero.setSpeedX(0);
				Assets.hero.setCenterX(tileX + 19);
				break;
			// System.out.println("player collided on left side");
			}
		}
		if (tileBoundary.intersects(hero.getBoundaries().get(
				Player.RIGHT_SIDE_PLAYER))) {
			switch (type) {
			case DIRT:
				Assets.hero.setSpeedX(0);
				Assets.hero.setCenterX(tileX - 19);
			case RIGHT_GROUND:
				Assets.hero.setSpeedX(0);
				Assets.hero.setCenterX(tileX - 19);
				break;
			}
			// System.out.println("player collided on right side");
		}
	}

}
