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
	
	public MemoryMatchModel() {

	}
	
	public void reset(String difficulty, String matchCount) {
		f_tiles.clear();
		
		int tileCount = Integer.parseInt(difficulty.substring(0, 1));
		tileCount *= tileCount;
		
		int matches = Integer.parseInt(matchCount);
		
		ImageIcon defaultImage = new ImageIcon("default.png");
		
		File imageDirectory = new File("." + File.separator + "images");
		File[] files = imageDirectory.listFiles();
		List<File> images = new LinkedList<File>(Arrays.asList(files));
		
		for(int i = 0; i < tileCount; i += matches) {
			File file = images.remove(0);
			ImageIcon image = new ImageIcon(file.getPath());
			
			for(int j = 0; j < matches; ++j) {
				JToggleButton tile = new JToggleButton(defaultImage);
				tile.setSelectedIcon(image);
				f_tiles.add(tile);
			}
		}
		
		Collections.shuffle(images);
	}
	
	public JToggleButton[] getTiles() {
		return f_tiles.toArray(new JToggleButton[f_tiles.size()]);
	}
}
