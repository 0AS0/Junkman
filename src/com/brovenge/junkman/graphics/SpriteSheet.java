package com.brovenge.junkman.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sprite;

	public SpriteSheet(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage image = sprite.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return image;
	}

}
