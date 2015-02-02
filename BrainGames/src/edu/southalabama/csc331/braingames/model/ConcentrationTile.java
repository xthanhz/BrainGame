package edu.southalabama.csc331.braingames.model;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ConcentrationTile extends JButton {
	
	private int f_value = 0;
	private int f_gridX = 0;
	private int f_gridY = 0;
	
	public ConcentrationTile(int gridX, int gridY) {
		setText(String.valueOf(f_value));
		f_gridX = gridX;
		f_gridY = gridY;
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setPreferredSize(new Dimension(30, 25));
	}
	
	public int getValue() {
		return f_value;
	}
	
	public int getGridX() {
		return f_gridX;
	}
	
	public int getGridY() {
		return f_gridY;
	}
	
	public void setValue(int value) {
		f_value = value;
		
		setText(String.valueOf(f_value));
	}
	
	public void increment() {
		++f_value;
		
		if(f_value > 3)
			f_value = 0;
		
		setText(String.valueOf(f_value));
	}
	
	public void decrement() {
		--f_value;
		
		if(f_value < 0)
			f_value = 3;
		
		setText(String.valueOf(f_value));
	}
}
