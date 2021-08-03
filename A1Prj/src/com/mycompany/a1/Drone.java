package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class Drone extends Moveable{
	private static final int color = ColorUtil.rgb(0, 0, 0); 
	private Random rand = new Random();

	public Drone(int size, Point location, int speed, int heading) {
		super(size, location, color);
		// TODO Auto-generated constructor stub
		super.setSpeed(speed);
		super.setHeading(heading);
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub
		int random = -5 + rand.nextInt(11); // btwn -5 and 5
		
		float x = getLocation().getX();
		float y = getLocation().getY();
		
		if (random + getHeading() < 0) {
			super.setHeading(360 + (random + getHeading()));
		} else if (random + getHeading() > 359) {
			super.setHeading(random + getHeading() - 160);
		} else {
			super.setHeading(getHeading() + random);
		}
		double theta = Math.toRadians(90 - getHeading());
		float deltaX = (float) (Math.cos(theta)*getSpeed());
		float deltaY = (float) (Math.sin(theta)*getSpeed());	
		float newX = (float) (x + deltaX);
		float newY = (float) (y + deltaY);
		setLocation(new Point(newX, newY));
	}
	
	public void setHeading(int heading) {
		
	}
	
	public void setSpeed() {
		
	}
	
	public void setColor() {
		
	}
	
	public String toString() {
		String dr = "\nDrone: " + super.toString() + "  heading: " + getHeading() + "  speed: " + getSpeed();
		return dr;
	}

}
