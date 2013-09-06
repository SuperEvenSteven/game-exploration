package com.widgets.big.game.demo;

import java.awt.Rectangle;

import com.widgets.big.game.demo.assets.AssetBackground;
import com.widgets.big.game.demo.assets.AssetPlayer;
import com.widgets.big.game.demo.assets.Assets;
import com.widgets.big.game.demo.assets.Assets.AssetType;
import com.widgets.big.game.engine.Sprite;
import com.widgets.big.game.framework.Background;
import com.widgets.big.game.utils.CollisionDetector;

public class Enemy {

	private int power, speedX, centerX, centerY;

	private final Rectangle enemyBoundary = new Rectangle(0, 0, 0, 0);

	public int health = 5;

	private int movementSpeed;
	private final Sprite sprite;

	private final Assets assets = Assets.instance();
	private final Player hero = ((AssetPlayer) assets.get(AssetType.HERO))
			.getPlayer();
	private final Background bg1 = ((AssetBackground) assets
			.get(AssetType.BACKGROUND1)).getBackground();

	public Enemy(int centerX, int centerY, Sprite sprite) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.sprite = sprite;
	}

	public void update(float deltaTimeElapsedMs) {

		centerX += Util.factorByElapsedTimeMs(speedX, deltaTimeElapsedMs);
		speedX = bg1.getSpeedX() * 5 + movementSpeed;
		enemyBoundary.setBounds(centerX - 25, centerY - 25, 50, 60);
		if (enemyBoundary.intersects(Player.perimeter)) {
			follow();
			if (CollisionDetector.hasCollided(enemyBoundary,
					hero.getBoundaries())) {

			}
		}

		sprite.getAnimation().update(50);
	}

	public void follow() {
		if (centerX < -95 || centerX > 810) {
			movementSpeed = 0;
		} else if (Math.abs(hero.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		} else {
			if (hero.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else
				movementSpeed = -1;
		}
	}

	public void die() {

	}

	public void attach() {

	}

	// Getters & Setters

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Rectangle getCollisionBoundary() {
		return enemyBoundary;
	}
}
