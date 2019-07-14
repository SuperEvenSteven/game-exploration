package com.ohair.stephen.game.demo;

import java.awt.Rectangle;
import java.util.List;

import com.ohair.stephen.game.demo.assets.AssetEnemies;
import com.ohair.stephen.game.demo.assets.Assets;
import com.ohair.stephen.game.demo.assets.Assets.AssetType;

public class Bullet {

	private int x, y, speedX;
	private boolean visible;

	private Rectangle collisionBoundary;

	private final Assets assets = Assets.instance();
	private final List<Enemy> enemies = ((AssetEnemies) assets
			.get(AssetType.ENEMIES)).getEnemies();

	public Bullet(int startX, int startY) {
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;

		collisionBoundary = new Rectangle(0, 0, 0, 0);
	}

	public void update(float deltaElapsedTimeMs) {
		x += speedX;
		collisionBoundary.setBounds(x, y, 10, 5);
		if (x > 800) {
			visible = false;
			collisionBoundary = null;
		}
		if (x < 800) {
			checkCollision();
		}
	}

	private void checkCollision() {

		for (Enemy enemy : enemies) {

			if (collisionBoundary.intersects(enemy.getCollisionBoundary())) {
				visible = false;

				if (enemy.health > 0) {
					enemy.health -= 1;
				}
				if (enemy.health == 0) {
					enemy.setCenterX(-100);
					FirstLevelScreen.score += 5;
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
