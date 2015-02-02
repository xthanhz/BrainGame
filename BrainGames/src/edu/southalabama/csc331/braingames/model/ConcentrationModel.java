package edu.southalabama.csc331.braingames.model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class ConcentrationModel implements ActionListener {
	
	private final String[] f_difficulty = { "Huh, What?", "Dumb", "Real easy", "Easy", "Normal", "Hard", "Real hard", "Master", "Impossible" };
	private final Integer[] f_passes = { 3, 6, 9, 12, 15, 18, 21, 24, 27 };
	private final int f_gridWidth = 6;
	private final int f_gridHeight = 6;
	
	private ArrayList<ClickCountListener> f_clickCountListeners = new ArrayList<ClickCountListener>();
	private ArrayList<ScoreChangedListener> f_scoreChangesListeners = new ArrayList<ScoreChangedListener>();
	private HashMap<String, Integer[]> f_scoresByDifficulty = new HashMap<String, Integer[]>();
	private ArrayList<Point> f_resetList = new ArrayList<Point>();
	private Stack<Point> f_undoList = new Stack<Point>();
	private ConcentrationTile[][] f_tiles = null;
	private Timer f_scoreTimer = new Timer();
	private int f_clickCount = 0;
	private int f_currentScore = 0;
	private boolean f_playing = false;
	
	public ConcentrationModel() {
		
		for(String difficulty : f_difficulty) {
			// TODO: Query player scores from database
			f_scoresByDifficulty.put(difficulty, new Integer[10]);
		}
		
		f_tiles = new ConcentrationTile[f_gridWidth][f_gridHeight];
		for(int x = 0; x < f_gridWidth; ++x) {
			for(int y = 0; y < f_gridHeight; ++y) {
				f_tiles[x][y] = new ConcentrationTile(x, y);
				f_tiles[x][y].addActionListener(this);
			}
		}
	}
	
	public ConcentrationTile[][] getTiles() {
		return f_tiles;
	}
	
	public int getGridWidth() {
		return f_gridWidth;
	}
	
	public int getGridHeight() {
		return f_gridHeight;
	}
	
	public void addClickCountListener(ClickCountListener clickCountListener) {
		f_clickCountListeners.add(clickCountListener);
	}
	
	public void addScoreChangedListener(ScoreChangedListener scoreChangedListener) {
		f_scoreChangesListeners.add(scoreChangedListener);
	}
	
	public void newGame(String difficulty) {
		
		f_resetList.clear();
		f_undoList.clear();
		
		for(int x = 0; x < f_gridWidth; ++x) {
			for(int y = 0; y < f_gridHeight; ++y) {
				f_tiles[x][y].setValue(0);
			}
		}
		
		int index = Arrays.asList(f_difficulty).indexOf(difficulty);
		
		int passes = 0;
		if(index >= 0 && index < f_passes.length)
			passes = f_passes[index];
		
		Random rnd = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < passes; ++i) {
			int x = rnd.nextInt(f_gridWidth);
			int y = rnd.nextInt(f_gridHeight);
			
			f_resetList.add(new Point(x, y));
			
			f_tiles[x][y].increment();
			
			if(x > 0)
				f_tiles[x - 1][y].increment();
			if(x < f_gridWidth - 1)
				f_tiles[x + 1][y].increment();
			if(y > 0)
				f_tiles[x][y - 1].increment();
			if(y < f_gridHeight - 1)
				f_tiles[x][y + 1].increment();
		}
		
		resetScore();
		resetClickCount();
		f_playing = true;
	}
	
	public void restart() {
		
		f_undoList.clear();
		
		for(int x = 0; x < f_gridWidth; ++x) {
			for(int y = 0; y < f_gridHeight; ++y) {
				f_tiles[x][y].setValue(0);
			}
		}
		
		for(Point pt : f_resetList) {
			f_tiles[pt.x][pt.y].increment();
			
			if(pt.x > 0)
				f_tiles[pt.x - 1][pt.y].increment();
			if(pt.x < f_gridWidth - 1)
				f_tiles[pt.x + 1][pt.y].increment();
			if(pt.y > 0)
				f_tiles[pt.x][pt.y - 1].increment();
			if(pt.y < f_gridHeight - 1)
				f_tiles[pt.x][pt.y + 1].increment();
		}
		
		resetScore();
		resetClickCount();
		f_playing = true;
	}
	
	public void undo() {
		if(!f_undoList.isEmpty()) {
			Point pt = f_undoList.pop();
			
			ConcentrationTile tile = f_tiles[pt.x][pt.y];
			
			tile.increment();
			
			if(tile.getGridX() > 0)
				f_tiles[tile.getGridX() - 1][tile.getGridY()].increment();
			if(tile.getGridX() < f_gridWidth - 1)
				f_tiles[tile.getGridX() + 1][tile.getGridY()].increment();
			if(tile.getGridY() > 0)
				f_tiles[tile.getGridX()][tile.getGridY() - 1].increment();
			if(tile.getGridY() < f_gridHeight - 1)
				f_tiles[tile.getGridX()][tile.getGridY() + 1].increment();
		}
	}
	
	private void resetScore() {
		f_currentScore = 1000;
		
		f_scoreTimer.cancel();
		f_scoreTimer = new Timer();
		
		f_scoreTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				--f_currentScore;
				
				for(ScoreChangedListener scoreChangeListener : f_scoreChangesListeners)
					scoreChangeListener.scoreChanged(f_currentScore);
				
				if(f_currentScore == 0)
					f_scoreTimer.cancel();
			}
		}, 0, 100);
	}
	
	private void resetClickCount() {
		f_clickCount = 0;
		
		for(ClickCountListener clickCountListener : f_clickCountListeners)
			clickCountListener.clickCount(f_clickCount);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!f_playing)
			return;
		
		ConcentrationTile tile = (ConcentrationTile)e.getSource();
		
		tile.decrement();
		
		if(tile.getGridX() > 0)
			f_tiles[tile.getGridX() - 1][tile.getGridY()].decrement();
		if(tile.getGridX() < f_gridWidth - 1)
			f_tiles[tile.getGridX() + 1][tile.getGridY()].decrement();
		if(tile.getGridY() > 0)
			f_tiles[tile.getGridX()][tile.getGridY() - 1].decrement();
		if(tile.getGridY() < f_gridHeight - 1)
			f_tiles[tile.getGridX()][tile.getGridY() + 1].decrement();
		
		f_undoList.push(new Point(tile.getGridX(), tile.getGridY()));
			
		++f_clickCount;
		
		for(ClickCountListener clickCountListener : f_clickCountListeners)
			clickCountListener.clickCount(f_clickCount);
		
		boolean won = false;
		for(int x = 0; x < f_gridWidth; ++x) {
			for(int y = 0; y < f_gridHeight; ++y) {
				won |= f_tiles[x][y].getValue() == 0;
			}
		}
		
		if(won) {
			// TODO: Inform the player that he/she has won.
			// TODO: Save score to database
		}
	}
}
