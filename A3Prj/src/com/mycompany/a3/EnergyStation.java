package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class EnergyStation extends Fixed {
	private int capacity;
	private static int color = ColorUtil.rgb(0, 220, 0); // all energy station same color
	private Vector<GameObject> collisionVec = new Vector<GameObject>();
	private boolean isSelected;
	//private GameWorld gw;

	public EnergyStation(int size, Point location) {
		super((size+70), location, color);
		// TODO Auto-generated constructor stub
		capacity = size;
		isSelected = false;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int myCapacity) {
		capacity = myCapacity;
	}
	
//	@Override
//	public void setColor(int color) {} // can't change color
	
	public String toString() {
		String es = "\nenergy station: ";
		String esCap = "  capacity = " + getCapacity();
		return es + super.toString() + esCap;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return isSelected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		float pX = pPtrRelPrnt.getX();
		float pY = pPtrRelPrnt.getY();
		float locX = pCmpRelPrnt.getX() + this.getLocation().getX();
		float locY = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		if ( (locX - (this.getSize() / 2) <= pX) && (pX <= locX + (this.getSize() / 2) ) ) {
			if ( (locY - (this.getSize() / 2) <= pY) && (pY<= locY + (this.getSize() / 2) ) ) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setSelected(boolean s) {
		// TODO Auto-generated method stub
		isSelected = s;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		int esX = (int) this.getLocation().getX();
		int esY = (int) this.getLocation().getY();
		int newX = (x + esX) - (this.getSize() / 2);
		int newY = (y + esY) - (this.getSize() / 2);
		
		g.setColor(this.getColor());
		
		if(isSelected()) {
			g.drawArc(newX, newY, this.getSize(), this.getSize(), 0, 360);
		} else {
			g.fillArc(newX, newY, this.getSize(), this.getSize(), 0, 360);
		}
		
		g.setColor(ColorUtil.BLACK);
		//g.drawString("" + this.getCapacity(), newX + this.getSize() / 4, newY + this.getSize() / 4);
		g.drawString("" + this.getCapacity(), newX + this.getSize() / 4, newY + this.getSize() / 3);
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
			}
		}
		return result;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		if (otherObject instanceof Cyborg) { //PlayerCyborg || otherObject instanceof NonPlayerCyborg) {
			//this.setCapacity(0);
			//this.setColor(ColorUtil.rgb(0, 100, 0));
			// create new energy station
			if (this.getCapacity() != 0) {
				GameWorld.energyColli(this);
			}
		}
	}

}
