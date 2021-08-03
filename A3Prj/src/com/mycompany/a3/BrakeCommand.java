package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command{
	private GameWorld gw;

	public BrakeCommand(GameWorld gameWorld) {
		super("Brake");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.brake();			// brake
	}
}
