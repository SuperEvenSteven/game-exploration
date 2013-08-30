package com.widgets.big.game.demo;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.widgets.big.game.demo.assets.AssetBackground;
import com.widgets.big.game.demo.assets.AssetSprite;
import com.widgets.big.game.demo.assets.Assets;
import com.widgets.big.game.demo.assets.Assets.AssetType;
import com.widgets.big.game.engine.Sprite;
import com.widgets.big.game.framework.Background;

public class Player {

	public enum ActionState {
		STANDING, DUCKING, WALKING_LEFT, WALKING_RIGHT, JUMPING_LEFT, JUMPING_RIGHT
	}

	public static final int ENTIRE_PLAYER = 0;
	public static final int LEFT_SIDE_PLAYER = 1;
	public static final int RIGHT_SIDE_PLAYER = 2;

	private ActionState action = ActionState.STANDING;

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;

	private int centerX;
	private int centerY;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;

	private int speedX = 0;
	private int speedY = 0;

	public static Rectangle perimeter = new Rectangle(0, 0, 0, 0);

	private static List<Rectangle> boundaries = new ArrayList<Rectangle>();

	// public static Rectangle footLeft = new Rectangle(0, 0, 0, 0);
	// public static Rectangle footRight = new Rectangle(0, 0, 0, 0);

	private final ArrayList<Bullet> projectiles = new ArrayList<Bullet>();

	private final Assets assets = Assets.instance();

	private final Sprite standingSprite = ((AssetSprite) assets
			.get(AssetType.PLAYER_STANDING)).getSprite();
	private final Sprite walkingLeftSprite = ((AssetSprite) assets
			.get(AssetType.PLAYER_WALK_LEFT)).getSprite();
	private final Sprite walkingRightSprite = ((AssetSprite) assets
			.get(AssetType.PLAYER_WALK_RIGHT)).getSprite();
	private final Sprite jumpingLeftSprite = ((AssetSprite) assets
			.get(AssetType.PLAYER_JUMP_LEFT)).getSprite();
	private final Sprite jumpingRightSprite = ((AssetSprite) assets
			.get(AssetType.PLAYER_JUMP_RIGHT)).getSprite();
	private final Sprite duckingSprite = ((AssetSprite) assets
			.get(AssetType.PLAYER_DUCK)).getSprite();
	private Sprite currentSprite = standingSprite;

	private final Background bg1 = ((AssetBackground) assets
			.get(AssetType.BACKGROUND1)).getBackground();
	private final Background bg2 = ((AssetBackground) assets
			.get(AssetType.BACKGROUND2)).getBackground();

	public Player(int centerX, int centerY) {

		boundaries.add(new Rectangle(0, 0, 0, 0));
		boundaries.add(new Rectangle(0, 0, 0, 0));
		boundaries.add(new Rectangle(0, 0, 0, 0));

		setCenterX(centerX);
		setCenterY(centerY);
	}

	public void update(float deltaTimeElapsedMs) {
		// Moves character or scrolls background accordingly

		if (speedX < 0) {
			centerX += speedX;
		}
		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);

		}
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED / 5);
			bg2.setSpeedX(-MOVESPEED / 5);
		}

		// Updates Y Position
		centerY += speedY;

		// Handles Jumping
		speedY += 1;

		if (speedY > 3) {
			jumped = true;
		}

		// prevents going beyond coordinate of 0
		if (centerX + speedX <= 20) {
			centerX = 21;
		}

		Rectangle entirePlayerRect = boundaries.get(0);
		entirePlayerRect.setRect(centerX - 20, centerY - 20, 40, 40);
		Rectangle leftSidePlayerRect = boundaries.get(1);
		leftSidePlayerRect.setRect(centerX - 20, centerY - 20, 20, 40);
		Rectangle rightSidePlayerRect = boundaries.get(2);
		rightSidePlayerRect.setRect(centerX, centerY - 20, 20, 40);

		perimeter.setRect(centerX - 110, centerY - 110, 180, 180);

		// footLeft.setRect(centerX - 50, centerY + 20, 50, 15);
		// footRight.setRect(centerX, centerY + 20, 50, 15);

		currentSprite.getAnimation().update(50);
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	public void shoot() {
		if (readyToFire) {
			Bullet p = new Bullet(centerX + 20, centerY - 5);
			projectiles.add(p);
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public ArrayList<Bullet> getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public List<Rectangle> getBoundaries() {

		return boundaries;
	}

	public Sprite getSprite() {
		return currentSprite;
	}

	public void setSpriteAction(ActionState action) {
		this.action = action;
		if (ActionState.STANDING == action) {
			currentSprite = standingSprite;
		} else if (ActionState.DUCKING == action) {
			currentSprite = duckingSprite;
		} else if (ActionState.JUMPING_LEFT == action) {
			currentSprite = jumpingLeftSprite;
		} else if (ActionState.JUMPING_RIGHT == action) {
			currentSprite = jumpingRightSprite;
		} else if (ActionState.WALKING_LEFT == action) {
			currentSprite = walkingLeftSprite;
		} else if (ActionState.WALKING_RIGHT == action) {
			currentSprite = walkingRightSprite;
		} else
			throw new RuntimeException("player's action state unknown");
	}
}
