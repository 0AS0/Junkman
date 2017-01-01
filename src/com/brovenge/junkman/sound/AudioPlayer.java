package com.brovenge.junkman.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	private static Clip clip;

	public static synchronized void playSound(AudioClip sfx) {
		Thread thread = new Thread() {
			public void run() {
				try {
					AudioInputStream stream = sfx.getAudioStream();

					clip = AudioSystem.getClip();
					clip.open(stream);
					clip.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}

}
