package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	private Game game;
	private Point pointer;
	private boolean pressed;
	
	public MapView(GameWorld gw, Game game) {
		this.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));
		this.game = game;
		this.gw = gw;
		pointer = new Point(0, 0);
		pressed = false;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		// code here to call method in GameWorld (observable) output game objects info to console
		//gw = (GameWorld) observable; 
		gw.map();
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point origin = new Point(this.getX(), this.getY());
		IIterator iterator = gw.getCollection().getIterator();
		while (iterator.hasNext()) {
			IDrawable draw = (IDrawable) iterator.getNext();
			draw.draw(g, origin);
		}
	}
	
	public Point getPointer() {
		return pointer;
	}
	
	public void setPressed(boolean p) {
		pressed = p;
	}
	
	public boolean getPressed() {
		return pressed;
	}
	
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pCmpRelPrnt = new Point(getX(), getY());
		Point pPtrRelPrnt = new Point(x, y);
		
		//save location if user pressed
		if (pressed) {
			pointer = new Point(x- getX(), y - getY());
			gw.moveSelected(pointer);
			pressed = false;
		}
		
		IIterator iterator = gw.getCollection().getIterator();
		if (game.isPaused()) {
			while(iterator.hasNext()) {
				GameObject obj = iterator.getNext();
				if (obj instanceof ISelectable) {
					ISelectable select = (ISelectable) obj;
					if (select.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						select.setSelected(true);
					} else {
						select.setSelected(false);
					}
				}
			}
		} // end if 
		repaint();
	}
}
