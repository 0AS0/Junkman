package com.brovenge.junkman.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.brovenge.junkman.Game;
import com.brovenge.junkman.graphics.SpriteSheet;

public class HealthDrop extends GameObject {

	private Handler handler;
	private Random random = new Random();

	private BufferedImage healthDropSprite;

	public HealthDrop(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		if (Game.difficulty == 0) {
			velY = random.nextInt(2);
		} else if (Game.difficulty == 1) {
			velY = random.nextInt(4);
		} else if (Game.difficulty == 2) {
			velY = random.nextInt(6);
		}

		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);

		healthDropSprite = ss.grabImage(7, 2, 32, 32);
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
		g.drawImage(healthDropSprite, x, y, null);
	}

}
