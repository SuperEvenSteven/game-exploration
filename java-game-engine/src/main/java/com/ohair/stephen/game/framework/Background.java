package com.ohair.stephen.game.framework;

public class Background {

	private int bgX, bgY, speedX;

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public Background(int bgX, int bgY) {
		super();
		this.bgX = bgX;
		this.bgY = bgY;
		this.speedX = 0;
	}

	public void update() {
		bgX += speedX;

		// TODO - Hardcoded background size, fix!
		if (bgX <= -2160) {
			bgX += 4320;
		}
	}

}
