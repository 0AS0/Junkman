package com.brovenge.junkman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.brovenge.junkman.menu.STATE;
import com.brovenge.junkman.object.GameObject;
import com.brovenge.junkman.object.Handler;
import com.brovenge.junkman.object.ID;

public class KeyboardInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[2];

	private Game game;

	public KeyboardInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;

		for (int i = 0; i < keyDown.length; i++) {
			keyDown[i] = false;
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				// Keyboard events for player

				// LEFT Movement Control
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-5);
					keyDown[0] = true;
				}

				// RIGHT Movement Control
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(5);
					keyDown[1] = true;
				}
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			if (game.gameState == STATE.Game) {
				if (Game.paused) {
					Game.paused = false;
				} else {
					Game.paused = true;
				}
			}
		}

		if (key == KeyEvent.VK_Q) System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[0] = false;
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[1] = false;

				if (!keyDown[0] && !keyDown[1]) tempObject.setVelX(0);
			}
		}
	}

}
