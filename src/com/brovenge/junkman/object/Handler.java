package com.brovenge.junkman.object;

import java.awt.Graphics;
import java.util.LinkedList;

import com.brovenge.junkman.Game;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearObjects() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.y >= Game.height - 64) {
				if (tempObject.getID() != ID.Player) {
					removeObject(tempObject);
					i--;
				}
			}
		}
	}

	public void clearAllObjects() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.getID() != ID.Player) {
				removeObject(tempObject);
				i--;
			}
		}
	}

	public void update() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.update();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

}
