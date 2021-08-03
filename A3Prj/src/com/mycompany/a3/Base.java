package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Base extends Fixed{
	private int sequenceNumber;
	private static final int color = ColorUtil.rgb(220, 0, 0); // all base same color
	private static final int size = 120;
	private Vector<GameObject> collisionVec = new Vector<GameObject>();
	private boolean isSelected;

	public Base(Point location, int sequenceNumber) {
		super(size, location, color);
		// TODO Auto-generated constructor stub
		this.sequenceNumber = sequenceNumber;
		isSelected = false;
	}
	
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	public void setSequenceNumber(int mySeqNum) {
		sequenceNumber = mySeqNum;
	}
	
	@Override
	public void setColor(int color) {} // can't change color
	
	public String toString() {
		String base = "\nBase: ";
		String baseNum = "  sequence number = " + getSequenceNumber();
		return base + super.toString() + baseNum;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		Point v[] = new Point[3];
		int x = (int) pCmpRelPrnt.getX();
		int y = (int) pCmpRelPrnt.getY();
		int middleX = (int) this.getLocation().getX() + x;
		int middleY = (int) this.getLocation().getY() + y;
		
		v[0] = new Point(middleX, (middleY + (size / 2) ) ); // middle
		v[1] = new Point( (middleX - (size / 2) ), (middleY - (size / 2) ) ); // upper left
		v[2] = new Point( (middleX + (size / 2) ), (middleY - (size / 2) ) ); // upper right
		
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
		if (isSelected) {
			g.drawPolygon(pointX, pointY, 3);
		} else {
			g.fillPolygon(pointX, pointY, 3);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.getSequenceNumber(), middleX - 10, middleY - 35);
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (otherObject instanceof Cyborg) {
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
		}
		return result;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
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

}
