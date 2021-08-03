package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.plaf.Border;

public class GameCheckBox extends CheckBox {
	
	public GameCheckBox() {
		setStyle();
		setPadding();
	}
	
	public GameCheckBox(String name) {
		super(name);
		setStyle();
		setPadding();
	}
	
	// Stylize checkbox to match GameButton
	private void setStyle() {
		this.getAllStyles().setBgTransparency(255);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(40, 70, 180));
		this.getPressedStyle().setFgColor(ColorUtil.WHITE);
		this.getPressedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK)); // ColorUtil.rgb(40, 70, 180)
		
		this.getUnselectedStyle().setBgColor(ColorUtil.rgb(80, 130, 255));
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
	}
	
	private void setPadding() {
		this.getAllStyles().setPadding(LEFT, 3);
		this.getAllStyles().setPadding(RIGHT, 3);
		this.getAllStyles().setPadding(TOP, 3);
		this.getAllStyles().setPadding(BOTTOM, 3);
	}

}
