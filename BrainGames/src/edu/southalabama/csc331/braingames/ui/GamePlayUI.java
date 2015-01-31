package edu.southalabama.csc331.braingames.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GamePlayUI extends JPanel {
	
	private BrainGamesUI f_brainGamesUI;
	
	public GamePlayUI(BrainGamesUI brainGamesUI) {
		f_brainGamesUI = brainGamesUI;
		
		setLayout(new BorderLayout());
		
		JTabbedPane tpGames = new JTabbedPane();
		tpGames.addTab("Memory Match", new MemoryMatchUI());
		tpGames.addTab("Concentration", new ConcentrationUI());
		
		add(tpGames, BorderLayout.CENTER);
	}
	
}
