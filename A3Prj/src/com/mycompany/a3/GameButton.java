package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class GameButton extends Button {
	
	public GameButton() {
		setButtonStyle();
		setButtonPadding();
	}
	
	public GameButton(String buttonName) {
		super(buttonName);
		setButtonStyle();
		setButtonPadding();
	}
	
	// Style buttons between pressed and non pressed buttons
	private void setButtonStyle() {
		this.getAllStyles().setBgTransparency(255);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(40, 70, 180));
		this.getPressedStyle().setFgColor(ColorUtil.WHITE);
		this.getPressedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK)); // ColorUtil.rgb(40, 70, 180)
		
		this.getUnselectedStyle().setBgColor(ColorUtil.rgb(80, 130, 255));
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
	}
	
	// Set padding on buttons to prevent squishing
	private void setButtonPadding() {
		this.getAllStyles().setPadding(LEFT, 3);
		this.getAllStyles().setPadding(RIGHT, 3);
		this.getAllStyles().setPadding(TOP, 3);
		this.getAllStyles().setPadding(BOTTOM, 3);
	}

	public void setButtonDefault() {
		this.getAllStyles().setBgTransparency(255);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(50, 80, 190));
		this.getPressedStyle().setFgColor(ColorUtil.WHITE);
		this.getPressedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		this.getUnselectedStyle().setBgColor(ColorUtil.rgb(70, 120, 255));
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
	}
	
	public void setDark() {
		this.getAllStyles().setBgTransparency(255);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(70, 120, 255));
		this.getPressedStyle().setFgColor(ColorUtil.WHITE);
		this.getPressedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK)); 
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
	}
	
	public void setDisabled() {
		this.getDisabledStyle().setBgTransparency(255);
		this.getDisabledStyle().setBgColor(ColorUtil.rgb(255, 0, 0));
		this.getDisabledStyle().setFgColor(ColorUtil.WHITE);
		this.getDisabledStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
	}
}
