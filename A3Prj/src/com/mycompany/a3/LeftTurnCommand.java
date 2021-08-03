package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftTurnCommand extends Command {
	private GameWorld gw;

	public LeftTurnCommand(GameWorld gameWorld) {
		super("Left Turn");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.steerLeft(15); // turn left
	}
}
