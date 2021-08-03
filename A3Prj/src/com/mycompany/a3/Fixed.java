package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject implements ISelectable {

	public Fixed(int size, Point location, int color) {
		super(size, location, color); // passes to parent class which is GameObject
		super.setLocation(location);
		
	}
	
	@Override
	public void setLocation(Point myLocation) {
		//no change location allowed
		super.setLocation(myLocation);
	}

}
