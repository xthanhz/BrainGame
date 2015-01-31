package edu.southalabama.csc331.braingames.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterUI extends JPanel implements ActionListener {

	private BrainGamesUI f_brainGamesUI;
	
	public RegisterUI(BrainGamesUI brainGamesUI) {
		f_brainGamesUI = brainGamesUI;
		
		setLayout(new GridBagLayout());
		
		JPanel pnlControls = new JPanel();
		pnlControls.setLayout(new BoxLayout(pnlControls, BoxLayout.Y_AXIS));
		
		JLabel lblTitle = new JLabel("Register");
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextField tbUsername = new JTextField();
		tbUsername.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextField tbPassword = new JTextField();
		tbPassword.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextField tbEmail = new JTextField();
		tbEmail.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		
		JPanel pnlButtons = new JPanel();
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		
		pnlButtons.add(btnSubmit);
		pnlButtons.add(btnCancel);
		
		pnlControls.add(lblTitle);
		pnlControls.add(Box.createVerticalStrut(16));
		pnlControls.add(lblUsername);
		pnlControls.add(tbUsername);
		pnlControls.add(lblPassword);
		pnlControls.add(tbPassword);
		pnlControls.add(lblEmail);
		pnlControls.add(tbEmail);
		pnlControls.add(Box.createVerticalStrut(16));
		pnlControls.add(pnlButtons);
		
		add(pnlControls);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Submit":
			f_brainGamesUI.showLoginPanel();
			break;
		case "Cancel":
			f_brainGamesUI.showLoginPanel();
			break;
		default: break;
	}
	}
}
