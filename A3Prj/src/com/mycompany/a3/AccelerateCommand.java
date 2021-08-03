package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command{
	private GameWorld gw;

	public AccelerateCommand(GameWorld gameWorld) {
		super("Accelerate");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.accelerate();		// accelerate player
	}

}
