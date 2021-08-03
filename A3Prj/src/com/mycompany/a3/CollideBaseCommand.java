package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideBaseCommand extends Command{
	private GameWorld gw;
	
	public CollideBaseCommand(GameWorld gameWorld) {
		super("Collide with Base");
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.showBaseMenu();	// Open a menu for the user to input a base num to collide with
	}
}
