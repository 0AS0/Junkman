package com.brovenge.junkman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.brovenge.junkman.graphics.BufferedImageLoader;
import com.brovenge.junkman.menu.Menu;
import com.brovenge.junkman.menu.STATE;
import com.brovenge.junkman.object.Handler;
import com.brovenge.junkman.object.ID;
import com.brovenge.junkman.object.Player;
import com.brovenge.junkman.object.Spawner;
import com.brovenge.junkman.sound.AudioClip;
import com.brovenge.junkman.sound.AudioPlayer;
import com.brovenge.junkman.util.Timer;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -212310971761828317L;

	public static int width = 640;
	public static int height = width / 12 * 9;
	public static String title = "Junkman";

	private Thread thread;
	private boolean running = false;

	public static boolean paused = false;
	public static int difficulty = 0; // 0 = easy | 1 = normal | 2 = hard

	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	private Menu menu;
	private Timer timer = new Timer();
	public AudioClip music;

	public int time = timer.getTime();
	public int pastTime = time - 1;
	public int splashTime;

	public STATE gameState = STATE.Splash;

	public static BufferedImage spriteSheet;
	public static BufferedImage background;
	public static BufferedImage background_night;

	public Game() {
		new Window(width, height, title, this);

		BufferedImageLoader loader = new BufferedImageLoader();

		spriteSheet = loader.loadImage("/textures/spritesheet.png");
		background = loader.loadImage("/textures/background.png");
		background_night = loader.loadImage("/textures/background_night.png");

		music = new AudioClip("/sound/bg_music.wav");
		AudioPlayer.playSound(music);

		hud = new HUD();
		handler = new Handler();
		menu = new Menu(this, hud, handler);
		spawner = new Spawner(handler, this);
		this.addKeyListener(new KeyboardInput(handler, this));
		this.addMouseListener(menu);

		splashTime = menu.timer.getTime();

		handler.addObject(new Player(width / 2 - 32, 350, ID.Player, handler, hud));
	}

	private synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	private synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfUpdates = 60.0;
		double ns = 1000000000 / amountOfUpdates;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("UPS: " + updates + " FPS: " + frames);
				if (gameState == STATE.Splash) {
					splashTime--;
				} else if (gameState == STATE.Game) {
					time++;
					pastTime++;
					spawner.update();
				}
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void update() {
		if (gameState == STATE.Splash || gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Win || gameState == STATE.Help || gameState == STATE.Select) {
			menu.update();
		} else if (gameState == STATE.Game) {
			if (!paused) {
				handler.update();
				hud.update();

				if (hud.getLevel() >= 20) {
					gameState = STATE.Win;
				}

				if (hud.getHealth() <= 0) {
					gameState = STATE.GameOver;
				}
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(6, 141, 233));
		g.fillRect(0, 0, width, height);
		g.drawImage(background, 0, 0, null);

		if (gameState == STATE.Splash || gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Win || gameState == STATE.Help || gameState == STATE.Select) {
			menu.render(g);
		} else if (gameState == STATE.Game) {
			if (!paused) {
				handler.render(g);
				hud.render(g);
			}
		}

		Font arial = new Font("arial", 1, 50);
		Font arial2 = new Font("arial", 1, 25);
		Font arial3 = new Font("arial", 1, 20);
		g.setColor(Color.white);
		g.setFont(arial);

		if (paused) {
			g.drawString("Paused", 235, 100);

			g.setFont(arial2);
			g.drawString("Press 'ESC' to resume.", 190, 150);
			g.drawString("Press 'Q' to quit.", 230, 180);
			g.setFont(arial3);
			g.drawString("Copyright Brovenge", 437, 440);
		}

		g.dispose();
		bs.show();
	}

	public static int clamp(int val, int min, int max) {
		if (val >= max) {
			return val = max;
		} else if (val <= min) {
			return val = min;
		} else {
			return val;
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}
