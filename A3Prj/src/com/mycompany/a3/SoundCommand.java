package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
	private GameWorld gw;
	private Game game;

	public SoundCommand(Game game, GameWorld gameWorld) {
		super("Enable Sound");
		// TODO Auto-generated constructor stub
		gw = gameWorld;
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//gw.sound();	// change sound mode
		if (!game.isPaused()) {
			gw.sound();
		}
		gw.setUserSound();
	}
}
