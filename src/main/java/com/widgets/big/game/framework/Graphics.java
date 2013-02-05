package com.widgets.big.game.framework;

import java.awt.Image;

public interface Graphics {
	public static enum ImageFormat {
		ARGB8888, ARGB4444, RGB565
	}

	public Image newImage(String filename, ImageFormat format);

	public void clear(int color);

	public void drawPixel(int x, int y, int color);

	public void drawLine(int x, int y, int x2, int y2, int color);

	public void drawRect(int x, int y, int width, int height, int color);

	public int getWidth();

	public int getHeight();
}
