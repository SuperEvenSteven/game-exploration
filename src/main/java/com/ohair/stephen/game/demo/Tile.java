package com.ohair.stephen.game.demo;

import java.awt.Image;
import java.awt.Rectangle;

import com.ohair.stephen.game.demo.Player.ActionState;
import com.ohair.stephen.game.demo.assets.AssetBackground;
import com.ohair.stephen.game.demo.assets.AssetImage;
import com.ohair.stephen.game.demo.assets.AssetPlayer;
import com.ohair.stephen.game.demo.assets.Assets;
import com.ohair.stephen.game.demo.assets.Assets.AssetType;
import com.ohair.stephen.game.framework.Background;

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
	/**
	 * TODO What units for speedX? e.g. pixels per second?
	 */
	private int tileX, tileY, speedX, type;
	private Image tileImage;

	private final Assets assets = Assets.instance();
	private final Player hero = ((AssetPlayer) assets.get(AssetType.HERO))
			.getPlayer();
	private final Background bg1 = ((AssetBackground) assets
			.get(AssetType.BACKGROUND1)).getBackground();

	private final Rectangle tileBoundary;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		tileBoundary = new Rectangle(0, 0, 0, 0);

		if (type == DIRT) {
			tileImage = ((AssetImage) Assets.instance()
					.get(AssetType.TILE_DIRT)).getImage();
		} else if (type == GROUND) {
			tileImage = ((AssetImage) Assets.instance().get(
					AssetType.TILE_GRASS_TOP)).getImage();
		} else if (type == LEFT_GROUND) {
			tileImage = ((AssetImage) Assets.instance().get(
					AssetType.TILE_GRASS_LEFT)).getImage();

		} else if (type == RIGHT_GROUND) {
			tileImage = ((AssetImage) Assets.instance().get(
					AssetType.TILE_GRASS_RIGHT)).getImage();

		} else if (type == CEILING) {
			tileImage = ((AssetImage) Assets.instance().get(
					AssetType.TILE_GRASS_BOT)).getImage();
		} else {
			type = 0;
		}

	}

	public void update(float deltaTimeElapsedMs) {

		speedX = bg1.getSpeedX() * 5;

		tileX += Util.factorByElapsedTimeMs(speedX, deltaTimeElapsedMs);
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
				hero.setJumped(false);
				if (hero.getSpeedX() == 0 && !hero.isDucked()) {
					hero.setSpriteAction(ActionState.STANDING);
				} else if (hero.getSpeedX() > 0)
					hero.setSpriteAction(ActionState.WALKING_RIGHT);
				else if (hero.getSpeedX() < 0)
					hero.setSpriteAction(ActionState.WALKING_LEFT);
				hero.setSpeedY(0);
				hero.setCenterY(tileY - 19);
				// System.out.println("player collided with ground or dir");
				break;
			case CEILING:
				hero.setCenterY(tileY + 63);
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
				hero.setSpeedX(0);
				hero.setCenterX(tileX + 19);
				break;
			case LEFT_GROUND:
				hero.setSpeedX(0);
				hero.setCenterX(tileX + 19);
				break;
			// System.out.println("player collided on left side");
			}
		}
		if (tileBoundary.intersects(hero.getBoundaries().get(
				Player.RIGHT_SIDE_PLAYER))) {
			switch (type) {
			case DIRT:
				hero.setSpeedX(0);
				hero.setCenterX(tileX - 19);
			case RIGHT_GROUND:
				hero.setSpeedX(0);
				hero.setCenterX(tileX - 19);
				break;
			}
			// System.out.println("player collided on right side");
		}
	}

}
