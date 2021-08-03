package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements IDrawable, ICollider {
	private int size;
	private Point location;
	private int color;
	
	//constructor
	public GameObject(int size, Point location, int color) {
		this.size = size;
		this.location = location;
		this.color = color;
	}
	
	public int getSize() {
		return size;
	} //no change in size allowed
	
//	public void setSize(int mySize) {
//		size = mySize;
//	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point myLocation) {
		location = myLocation;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int myColor) {
		color = myColor;
	}
	
	public String toString() {
		String size = "\nsize: " + getSize() + "  location: " + Math.round(getLocation().getX()*10.0) / 10.0 + ", " +
				(Math.round(getLocation().getY()*10.0) / 10.0);

		String col = "  color: " + ColorUtil.red(getColor()) + ", " + ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor());
		return size + col;
	}
	
	private boolean bounds (Point loc) {
		float x = loc.getX();
		float y = loc.getY();
		return ((x < 0 || x > 1024) || (y < 0 || y > 768));
	}
}
