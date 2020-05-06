package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4810618286807932601L; //ctrl + shift + O to import faster

	public Window (int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height)); //preferred size of window
		frame.setMaximumSize(new Dimension(width, height)); //max size of window
		frame.setMinimumSize(new Dimension(width, height)); //min size of window
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // puts the "x" button to work
		frame.setResizable(false); //can or not resize the window
		frame.setLocationRelativeTo(null); //default = opens on top left | null = opens on the middle
		frame.add(game); //add Game class
		frame.setVisible(true); //to see the frame
		game.start(); //run/star the run method
		}
}
