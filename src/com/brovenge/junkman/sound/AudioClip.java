package com.brovenge.junkman.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioClip {
	private File file;
	private String path;

	public AudioClip(String path) {
		this.path = path;
		file = new File(path);

		if (!file.exists()) {
			System.out.println("ERROR >> Audio file " + path + " doesn't exist!");
		}
	}

	public AudioInputStream getAudioStream() {
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			return inputStream;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
