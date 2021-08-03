package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideEnergyStationCommand extends Command{
	private GameWorld gw;

	public CollideEnergyStationCommand(GameWorld gameWorld) {
		super("Collide with Energy Station");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//gw.energyColli();			// collision with energy station
	}
}
