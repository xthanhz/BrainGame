package edu.southalabama.csc331.braingames.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import edu.southalabama.csc331.braingames.model.MemoryMatchModel;

public class MemoryMatchUI extends JPanel implements ActionListener {
	
	private final String[] GRID_SIZE = { "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10" };
	private final String[] DIFFICULTY = { "Easy", "Normal", "Hard" };
	
	private MemoryMatchModel f_memoryMatchModel = new MemoryMatchModel();
	private JComboBox<String> f_cbGridSize;
	private JComboBox<String> f_cbDifficulty;
	private JPanel f_pnlTiles;

	public MemoryMatchUI() {
		setLayout(new BorderLayout());

		JPanel pnlHeader = new JPanel();

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(this);

		JLabel lblGridSize = new JLabel("Grid Size: ");

		f_cbGridSize = new JComboBox<String>(GRID_SIZE);

		JLabel lblDifficulty = new JLabel("Difficulty: ");

		f_cbDifficulty = new JComboBox<String>(DIFFICULTY);

		JLabel lblTimer = new JLabel("Time 00:00");

		pnlHeader.add(btnNewGame);
		pnlHeader.add(lblGridSize);
		pnlHeader.add(f_cbGridSize);
		pnlHeader.add(lblDifficulty);
		pnlHeader.add(f_cbDifficulty);
		pnlHeader.add(lblTimer);

		add(pnlHeader, BorderLayout.NORTH);

		f_pnlTiles = new JPanel(new GridLayout());
		
		add(f_pnlTiles, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "New Game":
			f_memoryMatchModel.newGame((String) f_cbGridSize.getSelectedItem(),
					(String) f_cbDifficulty.getSelectedItem());
			
			f_pnlTiles.removeAll();

			f_pnlTiles.setLayout(new GridLayout(f_memoryMatchModel.getGridSize(), 
					f_memoryMatchModel.getGridSize()));
			
			JToggleButton[] tiles = f_memoryMatchModel.getTiles();
			for(JToggleButton tile : tiles)
				f_pnlTiles.add(tile);
			
			f_pnlTiles.revalidate();
			f_pnlTiles.repaint();
			
			break;
		default:
			break;
		}
	}
}
