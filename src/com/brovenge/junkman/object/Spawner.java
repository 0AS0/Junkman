package com.brovenge.junkman.object;

import java.util.Random;

import com.brovenge.junkman.Game;
import com.brovenge.junkman.object.bad.BrokenGlass;
import com.brovenge.junkman.object.bad.CigaretteButts;
import com.brovenge.junkman.object.bad.FoodScraps;
import com.brovenge.junkman.object.bad.YardTrimmings;
import com.brovenge.junkman.object.good.BrokenElectronics;
import com.brovenge.junkman.object.good.CandyWrapper;
import com.brovenge.junkman.object.good.Cardboard;
import com.brovenge.junkman.object.good.Paper;
import com.brovenge.junkman.object.good.ScrapMetal;
import com.brovenge.junkman.object.good.ScrapPlastic;
import com.brovenge.junkman.object.good.SodaCan;
import com.brovenge.junkman.object.good.WaterBottle;
import com.brovenge.junkman.object.good.Wood;

public class Spawner {

	private Handler handler;
	private Game game;
	private Random random = new Random();

	private int item = random.nextInt(13);

	public Spawner(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	public void spawn() {
		if (item == 0) {
			handler.addObject(new HealthDrop(random.nextInt(Game.width), -50, ID.HealthDrop, handler));
			item = random.nextInt(13);
		} else if (item == 1) {
			handler.addObject(new Paper(random.nextInt(Game.width), -50, ID.Paper, handler));
			item = random.nextInt(13);
		} else if (item == 2) {
			handler.addObject(new Cardboard(random.nextInt(Game.width), -50, ID.Cardboard, handler));
			item = random.nextInt(13);
		} else if (item == 3) {
			handler.addObject(new Wood(random.nextInt(Game.width), -50, ID.Wood, handler));
			item = random.nextInt(13);
		} else if (item == 4) {
			handler.addObject(new WaterBottle(random.nextInt(Game.width), -50, ID.WaterBottle, handler));
			item = random.nextInt(13);
		} else if (item == 5) {
			handler.addObject(new SodaCan(random.nextInt(Game.width), -50, ID.SodaCan, handler));
			item = random.nextInt(13);
		} else if (item == 6) {
			handler.addObject(new CandyWrapper(random.nextInt(Game.width), -50, ID.CandyWrapper, handler));
			item = random.nextInt(13);
		} else if (item == 7) {
			handler.addObject(new ScrapPlastic(random.nextInt(Game.width), -50, ID.ScrapPlastic, handler));
			item = random.nextInt(13);
		} else if (item == 8) {
			handler.addObject(new ScrapMetal(random.nextInt(Game.width), -50, ID.ScrapMetal, handler));
			item = random.nextInt(13);
		} else if (item == 9) {
			handler.addObject(new BrokenElectronics(random.nextInt(Game.width), -50, ID.BrokenElectronics, handler));
			item = random.nextInt(13);
		} else if (item == 10) {
			handler.addObject(new YardTrimmings(random.nextInt(Game.width), -50, ID.YardTrimmings, handler));
			item = random.nextInt(13);
		} else if (item == 11) {
			handler.addObject(new CigaretteButts(random.nextInt(Game.width), -50, ID.CigaretteButts, handler));
			item = random.nextInt(13);
		} else if (item == 12) {
			handler.addObject(new FoodScraps(random.nextInt(Game.width), -50, ID.FoodScraps, handler));
			item = random.nextInt(13);
		} else if (item == 13) {
			handler.addObject(new BrokenGlass(random.nextInt(Game.width), -50, ID.BrokenGlass, handler));
			item = random.nextInt(13);
		}
	}

	public void update() {
		if (game.time - game.pastTime == 1) {
			for (int i = 0; i < 2; i++) {
				spawn();
			}
		}
	}

}
