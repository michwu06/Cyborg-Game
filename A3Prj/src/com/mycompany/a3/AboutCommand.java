package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	
	private GameWorld gw;
	
	public AboutCommand(GameWorld gameWorld) {
		super("About");
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.showAboutMenu();	// Display an About information menu to the user
	}

}