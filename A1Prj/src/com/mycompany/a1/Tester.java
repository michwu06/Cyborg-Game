package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Tester {
	public static void main(String[] args) {
		//Point p1 = new Point();
		//GameObject g1 = new GameObject(21, new Point(350, 63), ColorUtil.BLUE);
		//System.out.println(g1);
		//Base g2 = new Base(new Point(350, 63), 4);
		//System.out.println(g2);
		//EnergyStation g3 = new EnergyStation(40, new Point(350, 63));
		//System.out.println(g3);
		//System.out.println(calcMaxSpeed(100, 10));
		
		// Test heading
//		Cyborg g4 = new Cyborg(10, new Point(350, 63));
//		System.out.println(g4);
//		g4.setSteeringDirection(20);
//		g4.setHeading(340);
//		g4.steerRight();
//		//g4.changeHeading(-5);
//		System.out.println(g4);
		
		// Test max speed
//		Cyborg c1 = new Cyborg(10, new Point(250, 500));
//		System.out.println(c1);
		
//		Drone d1 = new Drone (10, new Point(350, 63), 20, 21);
//		System.out.println(d1);
		
		GameWorld w1 = new GameWorld();
		w1.init();

//		c1.setSpeed(48);
//		c1.setDamage(0);
//		c1.setSpeed(0);
//		System.out.println("Initial state: Speed = " + c1.getSpeed() + " MaxSpeed = " + c1.getMaxSpeed());
//		for(int i = 0; i < 10; i++) {
//			c1.addDamageLevel();
//			c1.setSpeed(0);
//			System.out.println("State " + (i + 1) + ": Speed = " + c1.getSpeed() + " MaxSpeed = " + c1.getMaxSpeed());
//
//		}
	}
	
	
	/*public static int calcMaxSpeed(int maxSpeed, int damageLevel) {
		return maxSpeed - (int) (damageLevel * 0.1 * maxSpeed);
	}*/
}
