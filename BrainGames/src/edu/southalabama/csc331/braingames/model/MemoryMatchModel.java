package edu.southalabama.csc331.braingames.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class MemoryMatchModel {
	
	private ArrayList<JToggleButton> f_tiles = new ArrayList<JToggleButton>();
	private int f_gridSize = 0;
	
	public MemoryMatchModel() {

	}
	
	public void newGame(String gridSize, String difficulty) {
		f_tiles.clear();
		
		f_gridSize = Integer.parseInt(gridSize.split("x")[0]);
		int tileCount = f_gridSize * f_gridSize;
		
		int matchCount = 0;
		switch(difficulty) {
			case "Easy":
				matchCount = 2;
				break;
			case "Normal":
				matchCount = 3;
				break;
			case "Hard":
				matchCount = 4;
				break;
			default: break;
		};
		
		ImageIcon defaultImage = new ImageIcon("default.png");
		
		File imageDirectory = new File("." + File.separator + "images");
		File[] files = imageDirectory.listFiles();
		List<File> images = new LinkedList<File>(Arrays.asList(files));
		Collections.shuffle(images);
		
		for(int i = 0; i < tileCount / matchCount; ++i) {
			File file = images.remove(0);
			ImageIcon image = new ImageIcon(file.getPath());
			
			for(int j = 0; j < matchCount; ++j) {
				JToggleButton tile = new JToggleButton(defaultImage);
				tile.setSelectedIcon(image);
				f_tiles.add(tile);
			}
		}
		
		if(tileCount % matchCount != 0) {
			JToggleButton tile = new JToggleButton(defaultImage);
			ImageIcon image = new ImageIcon("joker.png");
			tile.setSelectedIcon(image);
			f_tiles.add(tile);
		}
		
		Collections.shuffle(f_tiles);
	}
	
	public JToggleButton[] getTiles() {
		return f_tiles.toArray(new JToggleButton[f_tiles.size()]);
	}
	
	public int getGridSize() {
		return f_gridSize;
	}
}
