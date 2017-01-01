package com.brovenge.junkman.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.brovenge.junkman.Game;
import com.brovenge.junkman.HUD;
import com.brovenge.junkman.graphics.BufferedImageLoader;
import com.brovenge.junkman.object.Handler;
import com.brovenge.junkman.util.Timer;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	public Timer timer;

	public static BufferedImage splash;
	BufferedImageLoader loader = new BufferedImageLoader();

	public Menu(Game game, HUD hud, Handler handler) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
		this.timer = new Timer(3);

		splash = loader.loadImage("/textures/splash.png");
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.Menu) {
			if (mouseOver(mx, my, 230, 160, 180, 54)) {
				game.gameState = STATE.Select;
				return;
			}

			if (mouseOver(mx, my, 230, 240, 180, 54)) {
				game.gameState = STATE.Help;
			}

			if (mouseOver(mx, my, 230, 320, 180, 54)) {
				System.exit(1);
			}
		}

		if (game.gameState == STATE.Select) {
			if (mouseOver(mx, my, 230, 160, 180, 54)) {
				game.gameState = STATE.Game;
				Game.difficulty = 0;
				handler.clearAllObjects();
				hud.setHealth(100);
				hud.setScore(0);
				hud.setLevel(1);
				hud.setTrashCollected(0);
				return;
			}

			if (mouseOver(mx, my, 230, 240, 180, 54)) {
				game.gameState = STATE.Game;
				Game.difficulty = 1;
				handler.clearAllObjects();
				hud.setHealth(100);
				hud.setScore(0);
				hud.setLevel(1);
				hud.setTrashCollected(0);
				return;
			}

			if (mouseOver(mx, my, 230, 320, 180, 54)) {
				game.gameState = STATE.Game;
				Game.difficulty = 2;
				handler.clearAllObjects();
				hud.setHealth(100);
				hud.setScore(0);
				hud.setLevel(1);
				hud.setTrashCollected(0);
				return;
			}
		}

		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 230, 320, 180, 54)) {
				game.gameState = STATE.Menu;
			}
		}

		if (game.gameState == STATE.GameOver) {
			if (mouseOver(mx, my, 230, 320, 180, 54)) {
				game.gameState = STATE.Menu;
			}
		}

		if (game.gameState == STATE.Win) {
			if (mouseOver(mx, my, 230, 320, 180, 54)) {
				game.gameState = STATE.Menu;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}

	public void update() {
		if (game.gameState == STATE.Splash) {
			if (game.splashTime <= 0) {
				game.gameState = STATE.Menu;
			}
		}
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Splash) {
			g.drawImage(splash, 0, -20, null);
		} else if (game.gameState == STATE.Menu) {
			Font arial = new Font("arial", 1, 50);
			Font arial2 = new Font("arial", 1, 30);
			Font arial3 = new Font("arial", 1, 20);

			g.setFont(arial);
			g.setColor(Color.white);
			g.drawString("Junkman", 210, 100);

			g.setColor(Color.white);
			g.setFont(arial2);
			g.drawRect(230, 160, 180, 54);
			g.drawString("Play", 290, 196);

			g.drawRect(230, 240, 180, 54);
			g.drawString("Help", 290, 277);

			g.drawRect(230, 320, 180, 54);
			g.drawString("Quit", 290, 358);

			g.setFont(arial3);
			g.drawString("Copyright Brovenge", 437, 440);
		} else if (game.gameState == STATE.Select) {
			Font arial = new Font("arial", 1, 50);
			Font arial2 = new Font("arial", 1, 30);
			Font arial3 = new Font("arial", 1, 20);

			g.setFont(arial);
			g.setColor(Color.white);
			g.drawString("Difficulty", 210, 100);

			g.setFont(arial2);
			g.drawRect(230, 160, 180, 54);
			g.drawString("Easy", 285, 196);

			g.drawRect(230, 240, 180, 54);
			g.drawString("Normal", 270, 278);

			g.drawRect(230, 320, 180, 54);
			g.drawString("Hard", 288, 358);

			g.setFont(arial3);
			g.drawString("Copyright Brovenge", 437, 440);
		} else if (game.gameState == STATE.Help) {
			Font arial = new Font("arial", 1, 50);
			Font arial2 = new Font("arial", 1, 30);
			Font arial3 = new Font("arial", 4, 20);
			Font arial4 = new Font("arial", 1, 20);

			g.setFont(arial);
			g.setColor(Color.white);
			g.drawString("Help", 265, 100);

			g.setFont(arial3);
			g.drawString("Welcome to Junkman! It's my first game so go easy on me!", 58, 140);
			g.drawString("The city has a lot of trash. They want you to clean the trash.", 56, 160);
			g.drawString("Some trash is good. But some is bad. So don't pick up the bad.", 45, 180);
			g.drawString("There are 20 levels. Each level contains more trash.", 100, 200);
			g.drawString("Once you complete 20 levels, you win.", 160, 220);
			g.drawString("But if you pick up too much bad trash, you will die.", 100, 240);

			g.setFont(arial4);
			g.drawString("Music by edtijo", 250, 275);
			g.drawString("Bug reports please contact brovenge on twitter @brovenge", 35, 300);

			g.setFont(arial2);
			g.drawRect(230, 320, 180, 54);
			g.drawString("Back", 285, 358);

			g.setFont(arial4);
			g.drawString("Copyright Brovenge", 437, 440);
		} else if (game.gameState == STATE.GameOver) {
			Font arial = new Font("arial", 1, 50);
			Font arial2 = new Font("arial", 1, 30);
			Font arial3 = new Font("arial", 1, 20);

			g.setFont(arial);
			g.setColor(Color.white);
			g.drawString("Game Over", 190, 100);

			g.setFont(arial3);
			g.drawString("You lost with a score of: " + hud.getScore(), 185, 200);
			g.drawString("Tweet your score to @Its0AS0", 176, 225);

			g.setFont(arial2);
			g.drawRect(230, 320, 180, 54);
			g.drawString("Try Again", 252, 358);

			g.setFont(arial3);
			g.drawString("Copyright Brovenge", 437, 440);
		} else if (game.gameState == STATE.Win) {
			Font arial = new Font("arial", 1, 50);
			Font arial2 = new Font("arial", 1, 30);
			Font arial3 = new Font("arial", 1, 20);

			g.setFont(arial);
			g.setColor(Color.white);
			g.drawString("You Win!", 205, 100);

			g.setFont(arial3);
			g.drawString("You lost with a score of: " + hud.getScore(), 185, 200);
			g.drawString("You collected " + hud.getTrashCollected() + " pieces of trash.", 170, 225);
			g.drawString("Tweet your score to @Its0AS0", 176, 250);

			g.setFont(arial2);
			g.drawRect(230, 320, 180, 54);
			g.drawString("Try Again", 252, 358);

			g.setFont(arial3);
			g.drawString("Copyright Brovenge", 437, 440);
		}
	}
}
