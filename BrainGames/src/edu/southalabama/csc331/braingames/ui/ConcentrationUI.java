package edu.southalabama.csc331.braingames.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.southalabama.csc331.braingames.model.ClickCountListener;
import edu.southalabama.csc331.braingames.model.ConcentrationModel;
import edu.southalabama.csc331.braingames.model.ConcentrationTile;
import edu.southalabama.csc331.braingames.model.ScoreChangedListener;

public class ConcentrationUI extends JPanel implements ActionListener, ClickCountListener, ScoreChangedListener {
	
	private static final String[] DIFFICULTY = { "Huh, What?", "Dumb", "Real easy", "Easy", "Normal", "Hard", "Real hard", "Master", "Impossible" };
	private static final String[] SOLVE_SPEED = { "Fast", "Normal", "Slow" };
	
	ConcentrationModel f_concentrationModel = new ConcentrationModel();
	JComboBox<String> f_cbDifficulty;
	JComboBox<String> f_cbSolveSpeed;
	JPanel f_pnlTiles;
	JPanel f_pnlHint;
	JTextArea f_taScores;
	JTextField f_tfClicksUsed;
	JTextField f_tfCurrentScore;
	
	public ConcentrationUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		f_concentrationModel.addClickCountListener(this);
		f_concentrationModel.addScoreChangedListener(this);
		
		JPanel pnlHeader = new JPanel();
		
		f_cbDifficulty = new JComboBox<String>(DIFFICULTY);
		JButton btnNewGame = new JButton("New");
		btnNewGame.addActionListener(this);
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(this);
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(this);
		
		pnlHeader.add(new JLabel("Difficulty setting:"));
		pnlHeader.add(f_cbDifficulty);
		pnlHeader.add(new JLabel("-"));
		pnlHeader.add(btnNewGame);
		pnlHeader.add(new JLabel("-"));
		pnlHeader.add(btnRestart);
		pnlHeader.add(new JLabel("-"));
		pnlHeader.add(btnUndo);
		
		add(pnlHeader);
		
		JPanel pnlCenter = new JPanel();
		
		f_pnlTiles = new JPanel(new GridLayout(f_concentrationModel.getGridWidth(), 
				f_concentrationModel.getGridWidth(), 1, 1));
		
		ConcentrationTile[][] tiles = f_concentrationModel.getTiles();
		for(int x = 0; x < f_concentrationModel.getGridWidth(); ++x) {
			for(int y = 0; y < f_concentrationModel.getGridHeight(); ++y) {
				f_pnlTiles.add(tiles[x][y]);
			}
		}
		
		f_taScores = new JTextArea(10, 10);
		f_taScores.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		pnlCenter.add(f_pnlTiles);
		pnlCenter.add(f_taScores);
		
		add(pnlCenter);
		
		JPanel pnlStats = new JPanel();
		
		JPanel pnlClicks = new JPanel();
		pnlClicks.add(new JLabel("Clicks used:"));
		f_tfClicksUsed = new JTextField(4);
		f_tfClicksUsed.setText("0");
		pnlClicks.add(f_tfClicksUsed);
		
		JPanel pnlScore = new JPanel();
		pnlScore.add(new JLabel("Current score:"));
		f_tfCurrentScore = new JTextField(4);
		f_tfCurrentScore.setText("0");
		pnlScore.add(f_tfCurrentScore);
		
		pnlStats.add(pnlClicks);
		pnlStats.add(pnlScore);
		
		add(pnlStats);
		
		JPanel pnlFooter = new JPanel();
		
		JPanel pnlHint = new JPanel();
		pnlHint.setLayout(new BoxLayout(pnlHint, BoxLayout.Y_AXIS));
		
		f_pnlHint = new JPanel(new GridLayout(3, 3, 1, 1));
		for(int i = 0; i < 9; ++i) {
			JButton tile = new JButton();
			tile.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			tile.setPreferredSize(new Dimension(30, 25));
			f_pnlHint.add(tile);
		}
		
		JButton btnHint = new JButton("Hint");
		btnHint.addActionListener(this);
		btnHint.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pnlHint.add(f_pnlHint);
		pnlHint.add(btnHint);

		pnlFooter.add(pnlHint);
		
		JPanel pnlSolve = new JPanel();
		pnlSolve.setLayout(new BoxLayout(pnlSolve, BoxLayout.Y_AXIS));
	
		JPanel pnlSolveSpeed = new JPanel();
		pnlSolveSpeed.add(new JLabel("Solve speed:"));
		f_cbSolveSpeed = new JComboBox<String>(SOLVE_SPEED);
		pnlSolveSpeed.add(f_cbSolveSpeed);
		
		JButton btnSolve = new JButton("Solve");
		btnSolve.addActionListener(this);
		btnSolve.setAlignmentX(Component.RIGHT_ALIGNMENT);

		pnlSolve.add(pnlSolveSpeed);
		pnlSolve.add(btnSolve);
		
		pnlFooter.add(pnlSolve);
		
		add(pnlFooter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "New":
				f_concentrationModel.newGame((String)f_cbDifficulty.getSelectedItem());
				break;
			case "Restart":
				f_concentrationModel.restart();
				break;
			case "Undo":
				f_concentrationModel.undo();
				break;
			case "Hint":
				break;
			case "Solve":
				break;
			default: break;
		}
	}

	@Override
	public void scoreChanged(int score) {
		f_tfCurrentScore.setText(String.valueOf(score));
	}

	@Override
	public void clickCount(int count) {
		f_tfClicksUsed.setText(String.valueOf(count));
	}
}
