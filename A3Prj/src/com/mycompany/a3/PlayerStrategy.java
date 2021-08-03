package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class PlayerStrategy implements IStrategy {
	private NonPlayerCyborg npc;
	private PlayerCyborg player;
	//private GameWorld gw;
	
	public PlayerStrategy(NonPlayerCyborg npc, PlayerCyborg player) {
		this.npc = npc;
		this.player = player;
		//this.gw = gw;
	}
	
	
	@Override
	public void apply() {
		// TODO Auto-generated method stub
		Point playerLoc = player.getLocation();
		Point npcLoc = npc.getLocation();
		double dX = playerLoc.getX() - npcLoc.getX();
		double dY = playerLoc.getX() - npcLoc.getX();
		double dTheta;
		
		dTheta = Math.toDegrees(MathUtil.atan(dX/dY));
		Math.abs(MathUtil.round(dTheta));
		
		if (dX > 0 && dY > 0) { // ++
			if (playerLoc.getY() < npcLoc.getY()) {
				npc.setHeading(90 + (int)dTheta);
			} else {
				npc.setHeading((int)dTheta);
			}
		} else { // --
			if (playerLoc.getY() < npcLoc.getY()) {
				npc.setHeading(180 + (int)dTheta);
			} else {
				npc.setHeading(270 + (int)dTheta);
			}
		} 
		
		/*
		if(dX > 0 && dY > 0) { 				// +x, +y 
			npc.setHeading((int)dTheta);
			System.out.println("\n\n\n\n\n\n\n\n"+ dTheta+"\n\n\n\n\n\n\n\n\n\nprinting: + +" +
			"player:" + playerLoc.getX() + "npc:" + npcLoc.getX() + "\n\n\n\n\n\n\n\n\n\n\n\n\n");
		} else if (dX > 0 && dY < 0) { 		// +x, -y
			//npc.setHeading(180 + (int) dTheta);
			npc.setHeading(0);
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n"+ dTheta+"\n\n\n\n\n\n\nprinting: + -" +
					"player:" + playerLoc.getX() + "npc:" + npcLoc.getX() + "\n\n\n\n\n\n\n\n\n\n\n\n\n");
		} else if (dX < 0 && dY > 0) { 		// -x, +y
			//npc.setHeading(360 - (int) dTheta);
			npc.setHeading(0);
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n"+dTheta+"\n\n\n\n\n\n\nprinting: - +" +
					"player:" + playerLoc.getX() + "npc:" + npcLoc.getX() + "\n\n\n\n\n\n\n\n\n\n\n\n\n");
		} else { 							// -x, -y
			//npc.setHeading(360 - (int)dTheta);
			npc.setHeading(0);
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n"+dTheta+"\n\n\n\n\n\n\nprinting: - -" +
					"player:" + playerLoc.getX() + "npc:" + npcLoc.getX() + "\n\n\n\n\n\n\n\n\n\n\n\n\n");
		} */
		/*
		int heading = (int) Math.toDegrees(MathUtil.atan2(dX, dY));
		heading = heading < 0 ? 360 + heading : heading;
		npc.setHeading(heading);*/
	}
	
	public String toString() {
		return "Player Strategy";
	}
}
