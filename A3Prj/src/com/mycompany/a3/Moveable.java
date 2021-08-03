package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Moveable extends GameObject {
	private int heading;
	private int speed;

	//Constructor
	public Moveable(int size, Point location, int color) {
		super(size, location, color);
		
	}
	
	
	// Assume that cyborgs and drones will implement different move algorithms.
	public abstract void move(float time); // 0 north, 90 east, 180 south, 270 west.
	
	public int getHeading() { 
		return heading;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setHeading(int myHeading) {
		heading = myHeading;
	}
	
	public void setSpeed(int mySpeed) {
		speed = mySpeed;
	}
}
