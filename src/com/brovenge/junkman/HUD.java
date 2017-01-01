package com.brovenge.junkman;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public int health = 100;
	private int greenValue = 255;

	private int score = 0;
	private int level = 10;
	private int trashCollected = 0;
	private int scoreKeep = 0;

	public void update() {
		health = Game.clamp(health, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);

		greenValue = health * 2;

		score++;

		scoreKeep++;

		if (scoreKeep >= 1000) {
			scoreKeep = 0;
			level += 1;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(15, 15, 200, 16);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, health * 2, 16);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 16);

		g.setColor(Color.white);
		g.drawString("Score: " + score, 15, 53);
		g.drawString("Level: " + level, 15, 68);
		g.drawString("Trash: " + getTrashCollected(), 15, 83);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setTrashCollected(int trashCollected) {
		this.trashCollected = trashCollected;
	}

	public int getTrashCollected() {
		return trashCollected;
	}

}
