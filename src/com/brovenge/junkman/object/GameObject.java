package com.brovenge.junkman.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.brovenge.junkman.Game;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected ID id;
	protected int velX;
	protected int velY;

	private Random random = new Random();

	private int speed = random.nextInt(2);

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void move() {
		if (Game.difficulty == 0) {
			if (speed == 0) velY = 2;
			else velY = speed;
		} else if (Game.difficulty == 1) {
			speed = random.nextInt(4);
			if (speed == 0) velY = 4;
			else velY = speed;
		} else if (Game.difficulty == 2) {
			speed = random.nextInt(6);
			if (speed == 0) velY = 6;
			else velY = speed;
		}
	}

	public abstract void update();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getID() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

}
