package edu.southalabama.csc331.braingames.ui;

import java.applet.Applet;
import java.awt.CardLayout;

public class BrainGamesUI extends Applet {
	//YAY
	private CardLayout f_cardLayout = new CardLayout();
	private int f_userId = -1;
	
	private final String LOGIN_PANEL = "LOGIN_PANEL";
	private final String REGISTER_PANEL = "REGISTER_PANEL";
	private final String GAME_PLAY_PANEL = "GAME_PLAY_PANEL";
	
	public BrainGamesUI() {
		setLayout(f_cardLayout);
		
		add(new LoginUI(this), LOGIN_PANEL);
		add(new RegisterUI(this), REGISTER_PANEL);
		add(new GamePlayUI(this), GAME_PLAY_PANEL);
	}
	
	public void showLoginPanel() {
		f_cardLayout.show(this, LOGIN_PANEL);
	}
	
	public void showRegisterPanel() {
		f_cardLayout.show(this, REGISTER_PANEL);
	}
	
	public void showGamePlayPanel(int userId) {
		f_userId = userId;
		f_cardLayout.show(this, GAME_PLAY_PANEL);
	}
}
