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

public class LoginUI extends JPanel implements ActionListener {
	
	private BrainGamesUI f_brainGamesUI;
	
	public LoginUI(BrainGamesUI brainGamesUI) {
		f_brainGamesUI = brainGamesUI;
		
		setLayout(new GridBagLayout());
		
		JPanel pnlControls = new JPanel();
		pnlControls.setLayout(new BoxLayout(pnlControls, BoxLayout.Y_AXIS));
		
		JLabel lblTitle = new JLabel("Brain Games");
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
		
		JPasswordField tbPassword = new JPasswordField();
		tbPassword.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		
		JPanel pnlButtons = new JPanel();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		
		JButton btnGuest = new JButton("Guest");
		btnGuest.addActionListener(this);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(this);
		
		pnlButtons.add(btnLogin);
		pnlButtons.add(btnGuest);
		pnlButtons.add(btnRegister);
		
		pnlControls.add(lblTitle);
		pnlControls.add(Box.createVerticalStrut(16));
		pnlControls.add(lblUsername);
		pnlControls.add(tbUsername);
		pnlControls.add(lblPassword);
		pnlControls.add(tbPassword);
		pnlControls.add(Box.createVerticalStrut(16));
		pnlControls.add(pnlButtons);
		
		add(pnlControls);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Login":
				f_brainGamesUI.showGamePlayPanel(0);
				break;
			case "Guest":
				f_brainGamesUI.showGamePlayPanel(-1);
				break;
			case "Register":
				f_brainGamesUI.showRegisterPanel();
				break;
			default: break;
		}
	}
}
