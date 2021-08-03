package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerCyborg extends Cyborg {
	private static PlayerCyborg player;
	private Vector<GameObject> collisionVec = new Vector<GameObject>();
	
	private PlayerCyborg(Point location) {
		super(location);
		// TODO Auto-generated constructor stub
		super.setSpeed(30);
	}
	
	// only 1 player cyborg can be made
	public static PlayerCyborg getPlayer() {
		if (player == null) {
			player = new PlayerCyborg(new Point(100, 100));
		}
		return player;
	}
	
	public String toString() {
		String name = "\nPlayer Cyborg: ";
		return name + super.toString();
	}
	
	public void clear() {
		super.setLocation(new Point(100, 100));
		super.setBase(1);
		super.setDamage(0);
		super.setEnergyLvl(3000);
		super.setHeading(0);
		super.setSpeed(30);
		super.setMaxSpeed(300);
		super.setSteeringDirection(0);
		super.setColor(ColorUtil.rgb(0, 0, 200));
		collisionVec.clear();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		int playerX = (int) this.getLocation().getX();
		int playerY = (int) this.getLocation().getY();
		int newX = (x + playerX) - (this.getSize() / 2);
		int newY = (y + playerY) - (this.getSize() / 2);
		
		g.setColor(this.getColor());
		g.fillRect(newX, newY, this.getSize(), this.getSize());
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		boolean result = false;
		float x = this.getLocation().getX() + (this.getSize() / 2);
		float y = this.getLocation().getY() + (this.getSize() / 2);
		float otherX = otherObject.getLocation().getX() + (otherObject.getSize() / 2);
		float otherY = otherObject.getLocation().getY() + (otherObject.getSize() / 2);
		
		// distance center
		float diffX = otherX - x;
		float diffY = otherY - y;
		float distance = diffX*diffX + diffY*diffY;
		
		float r = this.getSize() / 2;
		float otherR = otherObject.getSize() / 2;
		float sumR = r*r + 2*r*otherR +otherR*otherR;
		if (collisionVec.contains(otherObject)) {
			if (distance > sumR) {
				result = false;
				collisionVec.remove(otherObject);
			}
		} else {
			if (distance <= sumR) {
				result = true;
				collisionVec.add(otherObject);
				System.out.println("collided with " + otherObject.toString());
			}
		}
		return result;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		if (otherObject instanceof Drone) {
			System.out.println("Collide with Drone ");
			this.addDamageLevel(); // add damage
			this.dimColor();
			this.setSpeed(-5); // reduce speed
			this.setSpeed(0); // enforce max limit
			GameWorld.getCyborgSound().play();
			if (this.getDamage() >= 10) {
				GameWorld.getDeadSound().play();
			}
		} else if (otherObject instanceof EnergyStation) {
			EnergyStation es = (EnergyStation) otherObject;
			int num = es.getCapacity();
			this.addEnergyLevel(num);
			if (es.getCapacity() > 0) {
				GameWorld.getEnergySound().play();
			}
		} else if (otherObject instanceof Base) {
			int num = ((Base) otherObject).getSequenceNumber();
			if (this.getBase() + 1 == num) {
				this.updateBase();
				GameWorld.getBaseSound().play();
			}
		} else if (otherObject instanceof NonPlayerCyborg) {
			System.out.println("Collide with NPC");
			this.addDamageLevel(2);
			this.dimColor();
			this.setSpeed(0);
			GameWorld.getCyborgSound().play();
			if (this.getDamage() >= 10) {
				GameWorld.getDeadSound().play();
			}
		}
	}
}
