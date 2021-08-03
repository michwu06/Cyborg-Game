package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public interface ISelectable {
	public boolean isSelected();
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	public void setSelected(boolean s);
	public void draw(Graphics g, Point pCmpRelPrnt);
}
