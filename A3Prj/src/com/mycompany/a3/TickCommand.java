package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command {
	private GameWorld gw;

	public TickCommand(GameWorld gameWorld) {
		super("Tick");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//gw.tick();	// tick clock
	}
}
