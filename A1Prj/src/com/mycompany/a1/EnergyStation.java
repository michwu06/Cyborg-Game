package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class EnergyStation extends Fixed {
	private int capacity;
	private static int color = ColorUtil.rgb(0, 220, 0); // all energy station same color

	public EnergyStation(int size, Point location) {
		super(size, location, color);
		// TODO Auto-generated constructor stub
		capacity = getSize();
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

}
