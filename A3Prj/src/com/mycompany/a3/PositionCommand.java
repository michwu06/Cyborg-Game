package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command {
	private MapView map;
	
	public PositionCommand(MapView mv) {
		super("Position");
		// TODO Auto-generated constructor stub
		map = mv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		map.setPressed(true);
	}
}
