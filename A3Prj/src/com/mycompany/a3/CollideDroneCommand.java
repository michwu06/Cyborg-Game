package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideDroneCommand extends Command{
	private GameWorld gw;

	public CollideDroneCommand(GameWorld gameWorld) {
		super("Collide with Drone");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.droneColli();	// collision with Drone
	}
}
