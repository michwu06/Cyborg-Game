package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RightTurnCommand extends Command{
	private GameWorld gw;

	public RightTurnCommand(GameWorld gameWorld) {
		super("Right Turn");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.steerRight(15);// turn right
	}
}
