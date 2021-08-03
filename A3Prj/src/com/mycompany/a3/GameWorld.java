package com.mycompany.a3;

import java.util.Observable;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import java.util.Random;

public class GameWorld extends Observable{
	//4 bases, 2 drones, 1 player, 3 energy stations, 3 NPC
	private static GameObjectCollection collection = new GameObjectCollection();
	private static Random rand = new Random();
	private int lives = 3;
	private int clock = 0;
	private PlayerCyborg player = PlayerCyborg.getPlayer();	// create player
	private static int height;
	private static int width;
	private NonPlayerCyborg npc[];
	
	// sound stuff
	private boolean gameSound = false;
	private boolean userSound = false;
	private static Sound energySound;
	private static Sound baseSound;
	private static Sound cyborgSound;
	private static Sound deadSound;
	private BGSound bgSound;

	public void init() {
		clock = 0;
		collection.clear();
		player.clear();
		//create initial game objects/setup
		
		// create energy station, initialize, and add to collection
		EnergyStation energyStation[] = new EnergyStation[3];
		for (int i = 0; i < 3; i++) {
			energyStation[i] = new EnergyStation(randSize(), randLoc());
			collection.add(energyStation[i]);
		}
		
		// create each base and initialize it and add to collection
		Base base[] = new Base[4];
		base[0] = new Base(new Point(100, 100), 1);
		base[1] = new Base(new Point(1250, 100), 2);
		base[2] = new Base(new Point(450, 500), 3);
		base[3] = new Base(new Point(999, 1000), 4);
		for(int i = 0; i < 4; i++) {
			collection.add(base[i]);
		}
			
		// create drone, initialize, and add to collection
		Drone drone[] = new Drone[2];
		for (int i = 0; i < 2; i++) {
			drone[i] = new Drone(randSize(80, 100), randLoc(), 50 + rand.nextInt(101), rand.nextInt(360));
			collection.add(drone[i]);
		}
		
		// add player to collection
		collection.add(player);
		
		// create NPC and add to collection
		npc = new NonPlayerCyborg[3];
		npc[0] = new NonPlayerCyborg(new Point(200, 100));
		npc[0].setStrategy(new BaseStrategy(npc[0], this));
		npc[1] = new NonPlayerCyborg(new Point(300, 110));
		npc[1].setStrategy(new PlayerStrategy(npc[1], player));
		npc[2] = new NonPlayerCyborg(new Point(90, 300));
		npc[2].setStrategy(new BaseStrategy(npc[2], this));
		for(int i = 0; i < 3; i++) {
			collection.add(npc[i]);
		}
		
		//createSounds();
		
		setChanged();
		notifyObservers();
	}
	
	//init sound
	public void createSounds() {
		energySound = new Sound("ESPARK1.wav");
		baseSound = new Sound("correct.wav");
		cyborgSound = new Sound("sm-hurt.wav");
		deadSound = new Sound("Explosion.wav");
		bgSound = new BGSound("Bubbling.mp3");
		
		//soundEffect(userSound);
	}
	
	public void soundEffect(boolean vol) {
		if (vol) {
			energySound.setVol(50);
			baseSound.setVol(50);
			cyborgSound.setVol(50);
			deadSound.setVol(50);
			bgSound.setVol(20);
			
		} else {
			energySound.setVol(0);
			baseSound.setVol(0);
			cyborgSound.setVol(0);
			deadSound.setVol(0);
			bgSound.setVol(0);
		}
	}
	
	public static Sound getEnergySound() {
		return energySound;
	}
	
	public static Sound getBaseSound() {
		return baseSound;
	}
	
	public static Sound getCyborgSound() {
		return cyborgSound;
	}
	
	public static Sound getDeadSound() {
		return deadSound;
	}
	
	public BGSound getBGSound() {
		return bgSound;
	}
	
	// to generate random locations
	private static Point randLoc() {
		float x = rand.nextInt(1001);
		float y = rand.nextInt(1001);
		return new Point(x, y);
	}
	
	// generate random sizes
	private static int randSize() {
		int randSize = 10 + rand.nextInt(41);
		return randSize;
	}
	
	// generate random sizes
	private int randSize(int min, int max) {
		int randSize = min + rand.nextInt((max + 1) - min);
		return randSize;
	}
	
	//additional methods to manipulate world objects and related game state data
	// shows details of all game objects
	public void map() {
		// to check if everything is added
		System.out.println("\nDisplaying all game objects in the world");
		System.out.println("--------------------------------------------------------------------------------");
		
		IIterator iterator = collection.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			System.out.println(object);
		}
		System.out.println("--------------------------------------------------------------------------------");
	}
	
	// display player's status
	public void display() {
		System.out.println("Displaying: \n1) the number of lives left"
				+ "\n2) the current clock value (elapsed time)"
				+ "\n3) the highest base number the cyborg has reached sequentially so far"
				+ "\n4) the cyborg’s current energy level"
				+ "\n5) cyborg’s current damage level");
		System.out.println("\nLives: " + lives +
				"\nClock: " + clock +
				"\nHighest base: " + player.getBase() +
				"\nEnergy Level: " + player.getEnergyLevel() +
				"\nDamage Level: " + player.getDamage());
	}
	
	// exit game
	public void exit() {
		boolean cancel = Dialog.show("Exit Confirmation", "Do you want to exit?", "Cancel", "Yes");
		if(!cancel) {
			Display.getInstance().exitApplication();
		} else {
			lives = 3;
			init();
		}
	}
	
	// turn left
	public void steerLeft(int amount) {
		player.steerLeft(amount);
		System.out.println("steering left by -15");
		setChanged();
		notifyObservers();
	}
	
	// turn right
	public void steerRight(int amount) {
		player.steerRight(amount);
		System.out.println("steering right by 15");
		setChanged();
		notifyObservers();
	}
	
	// increase speed
	public void accelerate() {
		player.setSpeed(5);
		System.out.println("accelerated by 5");
		setChanged();
		notifyObservers();
	}
	
	// slow speed
	public void brake() {
		player.setSpeed(-5);
		System.out.println("brake by 5");
		setChanged();
		notifyObservers();
	}
	
	//collided with base
	public void baseColli(int num) {
		Base base = getBase(num);
		if (base == null) {
			Dialog.show("Invalid Base", "That base does not exist.", "Okay", null);
		} else {
			System.out.println("Collided with Base " + num);
			int currentBase = player.getBase();
			if (num-currentBase == 1) { //collide in sequential order
				player.updateBase();
				System.out.println("Last base reached has been updated: " + num);
				if (num == 4) {
					//System.out.println("Congratulation! You won! Time Lapse: " + clock);
					winExit();
				}
			} else {
				Dialog.show("Invalid Base", "Try again.", "Okay", null);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	// collided with cyborg
	public void cyborgColli() {
		System.out.println("Collide with NPC");
		NonPlayerCyborg npc = getNPC();
		npc.addDamageLevel(2); // add damage
		player.addDamageLevel(2); //add damage and dim color
		player.dimColor();
		player.setSpeed(0); // enforce max limit
		
		setChanged();
		notifyObservers();
		
		status(); // checking
	}
	
	// check lives, damage, energy
	public void status() {
			if (player.getDamage() >= 10 || player.getEnergyLevel() <= 0) {
				lives--; // lose life
				
				if(getLives() == 0) { // check num of lives
					System.out.println("Game over! You're dead.");
					Dialog.show("Game Over!", "You're dead.", "Okay", null);
					exit();
				}
				
				// reset damage and energy
				System.out.println("You lost a life.");
				Dialog.show("You Died", "You lost a life.", "Okay", null);
				player.clear();
				init();
				
				setChanged();
				notifyObservers();
			}
	}
	
	// collided with energy station
	public static void energyColli(EnergyStation es) {
		//EnergyStation es = getRandEnergyStation();
		System.out.println("Collide with energy station ");
		//player.addEnergyLevel(es.getCapacity()); // gives energy to cyborg
		es.setCapacity(0); // no energy left
		es.setColor(ColorUtil.rgb(0, 100, 0));// fade color energy station
		EnergyStation newES = new EnergyStation(randSize(), randLoc());
		collection.add(newES);		
		
		//setChanged();
		//notifyObservers();
	}
	
	// gives random non-empty energy stations
	private EnergyStation getRandEnergyStation() {
		EnergyStation energyStation[] = new EnergyStation[2];
		int count = 0;
		IIterator iterator = collection.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if (object instanceof EnergyStation) {
				EnergyStation station = (EnergyStation) object;
				if (station.getCapacity() > 0) {
					energyStation[count] = station;
					count++;
				}
			}
		}
		return energyStation[rand.nextInt(2)];
	}
	
	// collided with drone
	public void droneColli() {
		System.out.println("Collide with Drone ");
		player.addDamageLevel(); // add damage and dim color
		player.dimColor();
		brake(); // reduce speed
		player.setSpeed(0); // enforce max limit
		status(); // checking lives, damage, energy
		
		setChanged();
		notifyObservers();
	}
	
	// game clock
	public void tick(float time) {
		System.out.println("Updates: "
				+ "\n1) if the player cyborg moves, then the cyborg’s heading should be incremented or decremented by the cyborg’s steeringDirection"
				+ "\n2) Drones  also  update  their  heading  as  indicated  above"
				+ "\n3) all  moveable  objects are told to update their positions according to their current heading and speed"
				+ "\n4) the cyborg’s energy level is reduced by the amount indicated by its energyConsumptionRate"
				+ "\n5) the elapsed time game clock is incremented by one");
		
		clock++; // increase clock
		int steeringAmount = player.getSteeringDirection();
		// if player cyborg moves, increment heading by steeringDirection
		if (player.getDamage() < 10 || player.getEnergyLevel() > 0 || player.getSpeed() > 0) { 
			player.changeHeading(steeringAmount);
		}
		player.setSteeringDirection(); // reset turning
		
		IIterator iterator = collection.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if (object instanceof Moveable) { // get all moveable to update location
				Moveable moving = (Moveable) object;
				if (moving instanceof NonPlayerCyborg) {
					((NonPlayerCyborg) moving).invokeStrategy();
				}
				moving.move(time); // drones' heading updated in move()
				
			}
		}
		
		// collision
		iterator.reset();
		while (iterator.hasNext()) { 
			GameObject otherObject = iterator.getNext();
			if (player != otherObject) {					// for player
				if (player.collidesWith(otherObject)) {
					otherObject.collidesWith(player);
					player.handleCollision(otherObject);
					otherObject.handleCollision(player);
				} 
			}
		}
		
		iterator.reset();
		for (int i = 0; i < npc.length; i++) {
			while(iterator.hasNext()) {
				GameObject otherObject = iterator.getNext();
				if (npc[i] != otherObject) {				// for npc
					if (npc[i].collidesWith(otherObject)) {
						otherObject.collidesWith(npc[i]);
						npc[i].handleCollision(otherObject);
						otherObject.handleCollision(npc[i]);
					}
				}
			}
			iterator.reset();
		}
		status(); //check
		player.updateEnergyLvl(); // reduce energy
		
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if (object instanceof NonPlayerCyborg) {
				((NonPlayerCyborg) object).updateEnergyLvl(); // reduce npc energy lvl
			}
		}
		setChanged();
		notifyObservers();
		status(); // checking
		if (player.getBase() == 4) {
			winExit();
		}
		
		for (int i = 0; i < npc.length; i++) {
			if (npc[i].getBase() == 4) { // check npc status
				loseExit();
			}
		}
		setChanged();
		notifyObservers();
	}
	
	// sound
	public boolean getGameSound() {
		return gameSound;
	}
	
	public boolean getUserSound() {
		return userSound;
	}
	
	public void setUserSound() {
		userSound = !userSound;
	}
	
	//lives
	public int getLives() {
		return lives;
	}
	
//	public void setLives(int myLives) {
//		lives = myLives;
//	}
	
	//clock
	public int getClock() {
		return clock;
	}
	
//	public void setClock(int myClock) {
//		clock = myClock;
//	}
	
	// game space
	public static int getSpaceH() {
		return height;
	}
	
	public void setSpaceH(int myHeight) {
		height = myHeight;
	}
	
	public static int getSpaceW() {
		return width;
	}
	
	public void setSpaceW(int myWidth) {
		width = myWidth;
	}
	
	// base next location
	public Point getNextBaseLocation(NonPlayerCyborg npc) {
		int nextBase = npc.getBase() + 1;
		Point baseLocation = null;
		IIterator iterator = collection.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if (object instanceof Base) {
				Base base = (Base) object;
				if(base.getSequenceNumber() == nextBase) {
					baseLocation = base.getLocation();
				}
			}
		}
		return baseLocation;
	}

	// displays me
	public void showAboutMenu() {
		Dialog.show("About This Game", "Michelle Wu\nCSC 133\nVersion A3", "Okay", null);
	}
	
	// collide base menu
	public void showBaseMenu() {
		Command collide = new Command("Collide");
		Command cancel = new Command("Cancel");
		Command[] cmd = new Command[] {collide, cancel};
		TextField text = new TextField();
		Command command = Dialog.show("Enter Base Number", text, cmd);
		if(command == collide) {
			String txt = text.getText();
			int baseNum = player.getBase();
			try {
				baseNum = Integer.parseInt(txt);
				baseColli(baseNum);
			} catch(Exception e) {
				Dialog.show("Invalid Base", "That base does not exist.", "Okay", null);
			}
		}
	}
	
	// get base info
	private Base getBase(int num) {
		Base target = null;
		IIterator iterator = collection.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if (object instanceof Base) {
				Base base = (Base) object;
				if(base.getSequenceNumber() == num) {
					target = base;
				}
			}
		}
		return target;
	}
	
	// get npc object
	private NonPlayerCyborg getNPC() {
		NonPlayerCyborg target = null;
		IIterator iterator = collection.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if (object instanceof NonPlayerCyborg) {
				NonPlayerCyborg npc = (NonPlayerCyborg) object;
				target = npc;
			}
		}
		return target;
	}
	
	// switches strategies
	public void changeStrategy() {
		IIterator iterator = collection.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if (object instanceof NonPlayerCyborg) {
				NonPlayerCyborg npc = (NonPlayerCyborg) object;
				npc.switchStrategy(this); // switch strategies and update base num
				if (npc.getBase() == 4) { // check if npc win first
					loseExit();
				}
			}
			/***************************************
			 * need to check npc base collision == 4
			 * ************************************/
		}
		setChanged();
		notifyObservers();
	}
	
	// command lists
	public void showHelpMenu() {
		String bindings = 
				"a - accelerate\n" + "b - brake\n" + "l - turn left\n" + "r - turn right\n"; 
		Dialog.show("Key Bindings", bindings, "Close", null);
	}
	
	// BG Sound
	public void sound() {
		gameSound = !gameSound;
		if (gameSound) {
			soundEffect(true);
			//bgSound.play();
		} else {
			soundEffect(false);
			//bgSound.pause();
		}
		setChanged();
		notifyObservers();
	}
	
	// exit after win
	public void winExit() {
		boolean cancel = Dialog.show("Congratulation! You Win!", "Time Lapse: " + clock + "\nPlay again?", "Cancel", "Yes");
		if(cancel) {
			Display.getInstance().exitApplication();
		} else {
			lives = 3;
			init();
			setChanged();
			notifyObservers();
		}
	}
	
	// exit after lose
	public void loseExit() {
		boolean cancel = Dialog.show("Game Over! NPC Wins!", "Time Lapse: " + clock + "\nPlay again?", "Cancel", "Yes");
		if(cancel) {
			Display.getInstance().exitApplication();
		} else {
			lives = 3;
			init();
			setChanged();
			notifyObservers();
		}
	}
	
	// getCollection
	public GameObjectCollection getCollection() {
		return collection;
	}
	
	// move selected to position
	public void moveSelected(Point pointer) {
		IIterator iterator = collection.getIterator();
		while (iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if (object instanceof ISelectable) {
				if ( ((ISelectable) object).isSelected()) {
					object.setLocation(pointer);
				} // end of ISelectable
			} // end of instanceof
		} // while
	} // end of method
}


