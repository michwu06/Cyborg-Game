package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {
	private GameWorld gw;

	public HelpCommand(GameWorld gameWorld) {
		super("Help");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.showHelpMenu(); // show key commands
	}
}
