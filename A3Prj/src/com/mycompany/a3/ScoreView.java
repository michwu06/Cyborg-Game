package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	private GameWorld gw;

	private Label timeLabel;
	private Label timeNum;
	private Label lifeLabel;
	private Label lifeNum;
	private Label energyLvl;
	private Label energyNum;
	private Label damLvl;
	private Label damNum;
	private Label soundLabel;
	private Label soundNum;
	private Label baseLabel;
	private Label baseNum;
	
	public ScoreView() {
		// create center top labels
		this.setLayout(new FlowLayout(Component.CENTER));
		timeLabel = new Label("Time:");
		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(timeLabel);
		
		timeNum = new Label();
		timeNum.getAllStyles().setPadding(Component.RIGHT, 5);
		timeNum.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(timeNum);
		
		lifeLabel = new Label("Lives Left:");
		lifeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(lifeLabel);
		
		lifeNum = new Label();
		lifeNum.getAllStyles().setPadding(Component.RIGHT, 5);
		lifeNum.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(lifeNum);
		
		baseLabel = new Label("Last Base Reached:");
		baseLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(baseLabel);
		
		baseNum = new Label();
		baseNum.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(baseNum);
		
		energyLvl = new Label("Energy Level:");
		energyLvl.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(energyLvl);
		
		energyNum = new Label();
		energyNum.getAllStyles().setPadding(Component.RIGHT, 5);
		energyNum.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(energyNum);
		
		damLvl = new Label("Damage Level:");
		damLvl.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(damLvl);
		
		damNum = new Label();
		damNum.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(damNum);
		
		soundLabel = new Label("Sound:");
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(soundLabel);
		
		soundNum = new Label();
		soundNum.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(soundNum);
	}
	
	// checking sounds on or off
	private String sound(boolean sound) {
		String mySound = "OFF";
		if (sound) {
			mySound = "ON";
		}
		return mySound;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		// code here to update labels from the game/player-cyborg state data
		gw = (GameWorld) observable;
		int lives = gw.getLives();
		int time = gw.getClock();
		//System.out.println("lives: "+ lives + "\ntime: " + time);
		int damageLvl = PlayerCyborg.getPlayer().getDamage();
		int enerLvl = PlayerCyborg.getPlayer().getEnergyLevel();
		int baseNumber = PlayerCyborg.getPlayer().getBase();
		boolean sound = gw.getUserSound();
		String strSound = sound(sound);
		
		timeNum.setText(time + "       ");
		lifeNum.setText(lives + "  ");
		energyNum.setText(enerLvl + "      ");
		damNum.setText(damageLvl + "  ");
		baseNum.setText(baseNumber + "  ");
		soundNum.setText(strSound + "  ");
	}

}
