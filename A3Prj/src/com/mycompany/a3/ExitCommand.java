package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
	private GameWorld gw;

	public ExitCommand(GameWorld gameWorld) {
		super("Exit");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.exit(); // exit
	}
}
