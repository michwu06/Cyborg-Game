package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideNPCCommand extends Command{
	private GameWorld gw;

	public CollideNPCCommand(GameWorld gameWorld) {
		super("Collide with NPC");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.cyborgColli();	// collision with NPC
	}
}
