package com.brovenge.junkman.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.brovenge.junkman.Game;
import com.brovenge.junkman.HUD;
import com.brovenge.junkman.graphics.SpriteSheet;
import com.brovenge.junkman.sound.AudioClip;
import com.brovenge.junkman.sound.AudioPlayer;

public class Player extends GameObject {

	private Handler handler;
	private HUD hud;
	private AudioClip goodPickup;
	private AudioClip badPickup;

	private BufferedImage playerSprite;

	public Player(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;

		goodPickup = new AudioClip("/sound/good_pickup.wav");
		badPickup = new AudioClip("/sound/bad_pickup.wav");

		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);

		playerSprite = ss.grabImage(1, 1, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			// GOOD TRASH COLLISION
			if (tempObject.getID() == ID.Paper) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.Cardboard) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.Wood) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.WaterBottle) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.SodaCan) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.CandyWrapper) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.ScrapPlastic) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.ScrapMetal) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.BrokenElectronics) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setTrashCollected(hud.getTrashCollected() + 1);
					play(1);
				}
			}

			if (tempObject.getID() == ID.HealthDrop) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setHealth(hud.getHealth() + 1);
					play(1);
				}
			}

			// BAD TRASH COLLISION
			if (tempObject.getID() == ID.YardTrimmings) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setHealth(hud.getHealth() - 10);
					play(2);
				}
			}

			if (tempObject.getID() == ID.CigaretteButts) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setHealth(hud.getHealth() - 10);
					play(2);
				}
			}

			if (tempObject.getID() == ID.FoodScraps) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setHealth(hud.getHealth() - 10);
					play(2);
				}
			}

			if (tempObject.getID() == ID.BrokenGlass) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					hud.setHealth(hud.getHealth() - 10);
					play(2);
				}
			}
		}
	}

	private void play(int type) {
		if (type == 1) {
			AudioPlayer.playSound(goodPickup);
		} else if (type == 2) {
			AudioPlayer.playSound(badPickup);
		}
	}

	public void update() {
		x += velX;
		x += velY;

		x = Game.clamp(x, 0, Game.width - 38);

		collision();
	}

	public void render(Graphics g) {
		g.drawImage(playerSprite, x, y, null);
	}

}
