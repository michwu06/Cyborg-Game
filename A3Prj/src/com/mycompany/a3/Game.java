package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private ScoreView sv;
	private MapView mv;
	private UITimer timer;
	private boolean pause = false;
	
	// GameButton
	private GameButton paused;
	private GameButton accelerate;
	private GameButton left;
	private GameButton brake;
	private GameButton right;
	private GameButton position;
	private GameButton sideMenuExit;
	private GameCheckBox soundOption;
	private GameButton sideMenuAccelerate;
	private GameButton sideMenuAbout;
	private GameButton strategy;
	
	// Commands
	private AboutCommand aboutCommand;
	private AccelerateCommand accelerateCommand;
	private BrakeCommand brakeCommand;
	private ExitCommand exitCommand;
	private HelpCommand helpCommand;
	private LeftTurnCommand leftTurnCommand;
	private RightTurnCommand rightTurnCommand;
	private StrategyCommand strategyCommand;
	private SoundCommand sound;
	private PauseCommand pauseCommand;
	private PositionCommand positionCommand;
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw, this);
		sv = new ScoreView();
		
		// add observers
		gw.addObserver(mv);
		gw.addObserver(sv);

		// set borderLayout style
		this.setLayout(new BorderLayout());
		
		// Create left right and bottom containers
		Container leftContainer = new Container();
		leftContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.GRAY));
		Container rightContainer = new Container();
		rightContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.GRAY));
		Container bottomContainer = new Container();
		bottomContainer.setLayout(new FlowLayout(Component.CENTER));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.GRAY));
		
		// Place views in containers
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.WEST, leftContainer);
		this.add(BorderLayout.EAST, rightContainer);
		this.add(BorderLayout.SOUTH, bottomContainer);
		this.add(BorderLayout.CENTER, mv);
		
		// Create game buttons
		accelerate = new GameButton("Accelerate");
		left = new GameButton("Left");
		brake = new GameButton("Brake");
		right = new GameButton("Right");
//		GameButton colliBase = new GameButton("Collide with Base");
//		GameButton colliDrone = new GameButton("Collide with Drone");
//		GameButton colliNPC = new GameButton("Collide with NPC");
//		GameButton colliEnergyStation = new GameButton("Collide with Energy Station");
//		GameButton tick = new GameButton("Tick");
		sideMenuAccelerate = new GameButton("Accelerate");
		sideMenuAbout = new GameButton("About Info");
		sideMenuExit = new GameButton("Exit");
		soundOption = new GameCheckBox("Enable Sound");
		soundOption.getAllStyles().setAlignment(CENTER);
		strategy = new GameButton("Change Strategies");
		//GameButton sideMenuHelp = new GameButton("Help Info");
		position = new GameButton("Position");
		paused = new GameButton("Pause");
		
		// Add game buttons to containers
		leftContainer.add(accelerate);
		leftContainer.add(left);
		leftContainer.add(strategy);
		rightContainer.add(brake);
		rightContainer.add(right);
//		bottomContainer.add(colliNPC);
//		bottomContainer.add(colliBase);
//		bottomContainer.add(colliEnergyStation);
//		bottomContainer.add(colliDrone);
//		bottomContainer.add(tick);
		bottomContainer.add(position);
		bottomContainer.add(paused);
		
		// Create command for game action
		aboutCommand = new AboutCommand(gw);
		accelerateCommand = new AccelerateCommand(gw);
		brakeCommand = new BrakeCommand(gw);
//		CollideBaseCommand collideBaseCommand = new CollideBaseCommand(gw);
//		CollideEnergyStationCommand collideEnergyStationCommand = new CollideEnergyStationCommand(gw);
//		CollideNPCCommand collideNPCCommand = new CollideNPCCommand(gw);
//		CollideDroneCommand collideDroneCommand = new CollideDroneCommand(gw);
		strategyCommand = new StrategyCommand(gw);
		exitCommand = new ExitCommand(gw);
		helpCommand = new HelpCommand(gw);
		leftTurnCommand = new LeftTurnCommand(gw);
		rightTurnCommand = new RightTurnCommand(gw);
//		TickCommand tickCommand = new TickCommand(gw);
		sound = new SoundCommand(this, gw);
		pauseCommand = new PauseCommand(this);
		positionCommand = new PositionCommand(mv);
		
		// Create tool bar to the Form
		Toolbar myToolbar = new Toolbar();
		this.setToolbar(myToolbar);
		Toolbar.setOnTopSideMenu(true);
		
		// Add functions to tool bar
		myToolbar.addComponentToLeftSideMenu(soundOption);
		myToolbar.addComponentToLeftSideMenu(sideMenuAccelerate, accelerateCommand);
		myToolbar.addComponentToLeftSideMenu(sideMenuAbout, aboutCommand);
		myToolbar.addComponentToLeftSideMenu(sideMenuExit, exitCommand);
		myToolbar.addCommandToRightBar(helpCommand);
		
		// Add key listeners for commands
		this.addKeyListener('a', accelerateCommand);
		this.addKeyListener('b', brakeCommand);
		this.addKeyListener('l', leftTurnCommand);
		this.addKeyListener('r', rightTurnCommand);
//		this.addKeyListener('e', collideEnergyStationCommand);
//		this.addKeyListener('g', collideDroneCommand);
//		this.addKeyListener('t', tickCommand);
		
		// Add commands to buttons
		accelerate.setCommand(accelerateCommand);
		left.setCommand(leftTurnCommand);
		brake.setCommand(brakeCommand);
		right.setCommand(rightTurnCommand);
//		colliNPC.setCommand(collideNPCCommand);
//		colliBase.setCommand(collideBaseCommand);
//		colliDrone.setCommand(collideDroneCommand);
//		colliEnergyStation.setCommand(collideEnergyStationCommand);
//		tick.setCommand(tickCommand);
		strategy.setCommand(strategyCommand);
		
		soundOption.setCommand(sound);
		paused.setCommand(pauseCommand);
		position.setCommand(positionCommand);
		position.setEnabled(false);

		// Game Title
		this.setTitle("Sili-Challenge Game");
		
		this.show();
		
		// game space
		int myHeight = mv.getHeight();
		int myWidth = mv.getWidth();
		gw.setSpaceH(myHeight);
		gw.setSpaceW(myWidth);
		
		// start
		gw.init();
		
		gw.createSounds();
		//gw.getBGSound().setVol(0);
		gw.soundEffect(false);
		gw.getBGSound().play();
				
		// Timer
		timer = new UITimer(this);
		timer.schedule(20, true, this);
	}
	
	@Override
	public void run() {
		if(gw != null) {
			gw.tick(20);
		}
	}
		
	public boolean isPaused() {
		return pause;
	}
	
	// Toggles pause on the game
	public void pause() {
		if(!pause && gw.getGameSound()) {
			gw.sound();
			pause = true;
			position.setEnabled(true);
			
			left.setEnabled(false);
			right.setEnabled(false);
			accelerate.setEnabled(false);
			sideMenuAccelerate.setEnabled(false);
			brake.setEnabled(false);
			this.removeKeyListener('a', accelerateCommand);
			this.removeKeyListener('b', brakeCommand);
			this.removeKeyListener('l', leftTurnCommand);
			this.removeKeyListener('r', rightTurnCommand);
			
			timer.cancel();
		} else if(!pause && !gw.getGameSound()) {
			pause = true;
			position.setEnabled(true);
			
			left.setEnabled(false);
			right.setEnabled(false);
			accelerate.setEnabled(false);
			sideMenuAccelerate.setEnabled(false);
			brake.setEnabled(false);
			this.removeKeyListener('a', accelerateCommand);
			this.removeKeyListener('b', brakeCommand);
			this.removeKeyListener('l', leftTurnCommand);
			this.removeKeyListener('r', rightTurnCommand);
			
			timer.cancel();
		} else {
			if(gw.getGameSound() != gw.getUserSound()) {
				gw.sound();
			}
			pause = false;
			position.setEnabled(false);
			
			left.setEnabled(true);
			right.setEnabled(true);
			accelerate.setEnabled(true);
			sideMenuAccelerate.setEnabled(true);
			brake.setEnabled(true);
			this.addKeyListener('a', accelerateCommand);
			this.addKeyListener('b', brakeCommand);
			this.addKeyListener('l', leftTurnCommand);
			this.addKeyListener('r', rightTurnCommand);
			
			timer.schedule(20, true, this);
		}
		if(pause) {
			paused.setText("Play");
			paused.setDark();
		} else {
			paused.setText("Pause");
			paused.setButtonDefault();
		}
	}
}
