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

	private MemoryMatchModel f_memoryMatchModel = new MemoryMatchModel();
	private final String[] DIFFICULTIES = { "3x3", "4x4", "5x5", "6x6", "7x7",
			"8x8", "9x9", "10x10" };
	private final String[] MATCH_COUNT = { "2", "3", "4" };
	private JComboBox<String> f_cbDifficulty;
	private JComboBox<String> f_cbMatchCount;
	private JPanel f_pnlTiles;

	public MemoryMatchUI() {
		setLayout(new BorderLayout());

		JPanel pnlHeader = new JPanel();

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(this);

		JLabel lblDifficulty = new JLabel("Difficulty: ");

		f_cbDifficulty = new JComboBox<String>(DIFFICULTIES);

		JLabel lblMatchCount = new JLabel("Match Count: ");

		f_cbMatchCount = new JComboBox<String>(MATCH_COUNT);

		JLabel lblTimer = new JLabel("Time 00:00");

		pnlHeader.add(btnReset);
		pnlHeader.add(lblDifficulty);
		pnlHeader.add(f_cbDifficulty);
		pnlHeader.add(lblMatchCount);
		pnlHeader.add(f_cbMatchCount);
		pnlHeader.add(lblTimer);

		add(pnlHeader, BorderLayout.NORTH);

		f_pnlTiles = new JPanel(new GridLayout());
		add(f_pnlTiles, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Reset":
			f_memoryMatchModel.reset((String) f_cbDifficulty.getSelectedItem(),
					(String) f_cbMatchCount.getSelectedItem());
			
			f_pnlTiles.removeAll();
			
			JToggleButton[] tiles = f_memoryMatchModel.getTiles();
			int size = (int)Math.sqrt((double)tiles.length);
			f_pnlTiles.setLayout(new GridLayout(size, size));
			
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
