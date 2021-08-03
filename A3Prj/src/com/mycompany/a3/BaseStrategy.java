package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class BaseStrategy implements IStrategy {
	
	private NonPlayerCyborg npc;
	private GameWorld gw;
		
	public BaseStrategy(NonPlayerCyborg npc, GameWorld gw) {
		this.npc = npc;
		this.gw = gw;
	}

	@Override
	public void apply() {
		// Go to next base
		npc.setHeading(0);
		Point cyborgLoc = npc.getLocation();
		Point baseLoc = gw.getNextBaseLocation(npc); // next base loc
		double dX = baseLoc.getX() - cyborgLoc.getX();
		double dY = baseLoc.getY() - cyborgLoc.getY();
		
		int heading = (int) Math.toDegrees(MathUtil.atan2(dX, dY));
		heading = heading < 0 ? 360 + heading : heading;
		npc.setHeading(heading);
	}
	
	public String toString() {
		return "Base Strategy";
	}
}
