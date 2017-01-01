package com.brovenge.junkman.object.bad;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.brovenge.junkman.Game;
import com.brovenge.junkman.graphics.SpriteSheet;
import com.brovenge.junkman.object.GameObject;
import com.brovenge.junkman.object.Handler;
import com.brovenge.junkman.object.ID;

public class FoodScraps extends GameObject {

	private Handler handler;

	private BufferedImage foodScrapsSprite;

	public FoodScraps(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		move();

		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);

		foodScrapsSprite = ss.grabImage(5, 2, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public void update() {
		y += velY;

		if (y >= Game.height + 64) {
			handler.clearObjects();
		}
	}

	public void render(Graphics g) {
		g.drawImage(foodScrapsSprite, x, y, null);
	}

}
