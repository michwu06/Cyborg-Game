package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Base extends Fixed{
	private int sequenceNumber;
	private static final int color = ColorUtil.rgb(220, 0, 0); // all base same color
	private static final int size = 20;

	public Base(Point location, int sequenceNumber) {
		super(size, location, color);
		// TODO Auto-generated constructor stub
		this.sequenceNumber = sequenceNumber;
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

}
