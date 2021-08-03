package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class StrategyCommand extends Command{
	private GameWorld gw;

	public StrategyCommand(GameWorld gameWorld) {
		super("Change Strategies");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.changeStrategy();	// switch strategies
	}
}
