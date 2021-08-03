package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject{

	public Fixed(int size, Point location, int color) {
		super(size, location, color); // passes to parent class which is GameObject
		
	}
	
	@Override
	public void setLocation(Point myLocation) {
		//no change location allowed
	}

}
