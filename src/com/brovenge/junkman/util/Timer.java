package com.brovenge.junkman.util;

public class Timer {

	private int seconds;
	private boolean up;

	public Timer() {
		seconds = 0;
		up = true;
	}

	public Timer(int seconds) {
		this.seconds = seconds;
		up = false;
	}

	public int getTime() {
		return seconds;
	}

	public void update() {
		if (up) {
			seconds++;
		} else {
			if (seconds <= 0) return;
			seconds--;
		}
	}

}
