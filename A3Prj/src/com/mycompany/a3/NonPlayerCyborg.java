package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class NonPlayerCyborg extends Cyborg {
	private IStrategy curStrategy;
	private Vector<GameObject> collisionVec = new Vector<GameObject>();
	
	public NonPlayerCyborg(Point location) {
		super(location);
		super.setDamage(-1000);
		super.setEnergyLvl(10000);
		super.setSpeed(50);
		
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		String name = "\nNPC: ";
		String strategy = "\nStrategy: ";
		return name + super.toString() + strategy + curStrategy;
	}
	
	public void setStrategy(IStrategy curStrategy) {
		// TODO Auto-generated method stub
		this.curStrategy = curStrategy;
	}

	public void invokeStrategy() {
		// TODO Auto-generated method stub
		curStrategy.apply();
	}
	
	public IStrategy getCurStrategy() {
		return curStrategy;
	}
	
	public boolean isBaseStrategy() {
		return (curStrategy instanceof BaseStrategy); // Test if this works
	}
	
	public void switchStrategy(GameWorld gw) {
		if(isBaseStrategy()) {
			setStrategy(new PlayerStrategy(this, PlayerCyborg.getPlayer()));
		} else {
			setStrategy(new BaseStrategy(this, gw));
		}
		//super.updateBase();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		int npcX = (int) this.getLocation().getX();
		int npcY = (int) this.getLocation().getY();
		int newX = (x + npcX) - (this.getSize() / 2);
		int newY = (y + npcY) - (this.getSize() / 2);
		
		g.setColor(this.getColor());
		g.drawRect(newX, newY, this.getSize(), this.getSize());
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
			this.addDamageLevel(); // add damage
			this.dimColor();
			GameWorld.getCyborgSound().play();
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
		} else if (otherObject instanceof PlayerCyborg) {
			this.addDamageLevel(2);
			this.dimColor();
		} else if (otherObject instanceof NonPlayerCyborg) {
			//System.out.println("Collide with NPC");
			this.addDamageLevel(2);
			this.dimColor();
			GameWorld.getCyborgSound().play();
		}
	}
	
	
}
