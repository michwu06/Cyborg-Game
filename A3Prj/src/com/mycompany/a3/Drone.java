package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.util.Random;
import java.util.Vector;

public class Drone extends Moveable{
	private static final int color = ColorUtil.rgb(0, 0, 0); 
	private Random rand = new Random();
	private Vector<GameObject> collisionVec = new Vector<GameObject>();

	public Drone(int size, Point location, int speed, int heading) {
		super(size, location, color);
		// TODO Auto-generated constructor stub
		super.setSpeed(speed);
		super.setHeading(heading);
	}


	@Override
	public void move(float time) {
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
		float distance = getSpeed() * (time/1000);
		float deltaX = (float) (Math.cos(theta)* distance);
		float deltaY = (float) (Math.sin(theta)* distance);	
		float newX = (float) (x + deltaX);
		float newY = (float) (y + deltaY);
		
		if (newX < 0) {
			super.setHeading(getHeading() - 180);
		} else if (newY > GameWorld.getSpaceW()) {
			super.setHeading(getHeading() + 180);
		}
		
		if (newY < 0) {
			super.setHeading(getHeading() + 180);
		} else if (newY > GameWorld.getSpaceH()) {
			super.setHeading(getHeading() - 180);
		}
		
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


	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		int middleX = (int) this.getLocation().getX();
		int middleY = (int) this.getLocation().getY();
		Point v[] = new Point[3];
		
		v[0] = new Point(x + middleX, y + (middleY + (this.getSize() / 2) ) ); // middle
		// upper left
		v[1] = new Point(x + (middleX - (this.getSize() / 2) ), y + (middleY - (this.getSize() / 2) ) );
		// upper right
		v[2] = new Point(x + (middleX + (this.getSize() / 2) ), y + (middleY - (this.getSize() / 2) ) );
		
		// x points
		int pointX[] = {
				(int) v[0].getX(),
				(int) v[1].getX(),
				(int) v[2].getX()
		};
		
		// y points
		int pointY[] = {
				(int) v[0].getY(),
				(int) v[1].getY(),
				(int) v[2].getY()
		};
		
		g.setColor(this.getColor());
		g.drawPolygon(pointX, pointY, 3);
	}


	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		boolean result = false;
		float x = this.getLocation().getX() + (this.getSize() / 2);
		float y = this.getLocation().getY() + (this.getSize() / 2);
		float otherX = otherObject.getLocation().getX() + (otherObject.getSize() / 2);
		float otherY = otherObject.getLocation().getY() + (otherObject.getSize() / 2);
		
		//distance from center
		float diffX = otherX - x;
		float diffY = otherY - y;
		float diffCenter = diffX*diffX + diffY*diffY;
		float r = this.getSize() / 2;
		float otherR = otherObject.getSize() / 2;
		float sumR = r*r + 2*r*otherR + otherR*otherR;
		
		if (collisionVec.contains(otherObject)) {
			if (sumR < diffCenter) {
				result = false;
				collisionVec.remove(otherObject);
			}
		} else {
			if (sumR >= diffCenter ) {
				result = true;
				collisionVec.add(otherObject);
			}
		}
		return result;
	}


	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}

}
