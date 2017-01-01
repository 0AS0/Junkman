package com.brovenge.junkman;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	private static final long serialVersionUID = 6102036840425049842L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		Dimension dimension = new Dimension(width, height);

		frame.setPreferredSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setMinimumSize(dimension);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.add(game);
		frame.setVisible(true);
	}

}
