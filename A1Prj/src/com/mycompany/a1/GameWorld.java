package com.mycompany.a1;

import java.util.ArrayList;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

import java.util.Random;

public class GameWorld {
	//4 bases, 2 drones, 1 cyborg, 2 energy stations
	private ArrayList<GameObject> myGameObjects = new ArrayList<GameObject>(); 
	private Random rand = new Random();
	private int lives = 3;
	private int clock = 0;
	
	public void init() {
		clock = 0;
		myGameObjects.clear();
		//create initial game objects/setup
		//rest of game objects random size 10~50
		
		//Drone objects, randomly positioned with a randomly-generated heading and speed
		//EnergyStation objects with random location and with random sizes
		
		// create energy station, initialize, and add to array
		EnergyStation energyStation[] = new EnergyStation[2];
		for (int i = 0; i < 2; i++) {
			energyStation[i] = new EnergyStation(randSize(), randLoc());
			myGameObjects.add(energyStation[i]);
		}
		
		// create each base and initialize it and add to array
		Base base[] = new Base[4];
		base[0] = new Base(new Point(100, 100), 1);
		base[1] = new Base(new Point(200, 100), 2);
		base[2] = new Base(new Point(450, 50), 3);
		base[3] = new Base(new Point(999, 999), 4);
		for(int i = 0; i < 4; i++) {
			myGameObjects.add(base[i]);
		}
			
		// create drone, initialize, and add to array
		Drone drone[] = new Drone[2];
		for (int i = 0; i < 2; i++) {
			drone[i] = new Drone(randSize(), randLoc(), 5 + rand.nextInt(6), rand.nextInt(360));
			myGameObjects.add(drone[i]);
		}
		
		// create player, initialize, add to array
		Cyborg player = new Cyborg(new Point(100, 100));
		myGameObjects.add(player);
		
		
		
	}
	
	// to generate random locations
	private Point randLoc() {
		float x = rand.nextInt(1001);
		float y = rand.nextInt(1001);
		return new Point(x, y);
	}
	
	// generate random sizes
	private int randSize() {
		int randSize = 10 + rand.nextInt(41);
		return randSize;
	}
	
	//additional methods to manipulate world objects and related game state data
	public void map() {
		// to check if everything is added
		System.out.println("Displaying all game objects in the world");
		System.out.println("--------------------------------------------------------------------------------");
		for (int i = 0; i< myGameObjects.size(); i++) {
			System.out.println(myGameObjects.get(i));
		}
		System.out.println("--------------------------------------------------------------------------------");
	}
	
	// display player's status
	public void display() {
		Cyborg player = getPlayer();
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
	
	// get cyborg info
	public Cyborg getPlayer() {
		Cyborg player = null;
		for(int i = 0; i < myGameObjects.size(); i++) {
			if(myGameObjects.get(i) instanceof Cyborg) {
				player = (Cyborg) myGameObjects.get(i);
			}
		}
		return player;
	}
	
	// exit game
	public void exit() {
		System.exit(0);
	}
	
	// turn left
	public void steerLeft(int amount) {
		Cyborg player = getPlayer();
		player.steerLeft(amount);
		System.out.println("steering left by -5");
	}
	
	// turn right
	public void steerRight(int amount) {
		Cyborg player = getPlayer();
		player.steerRight(amount);
		System.out.println("steering right by 5");
	}
	
	// increase speed
	public void accelerate() {
		Cyborg player = getPlayer();
		player.setSpeed(5);
		System.out.println("accelerated by 5");
	}
	
	// slow speed
	public void brake() {
		Cyborg player = getPlayer();
		player.setSpeed(-5);
		System.out.println("brake by 5");
	}
	
	//collided with base
	public void baseColli(int num) {
		System.out.println("Collided with Base " + num);
		Cyborg player = getPlayer();
		int currentBase = player.getBase();
		if (num-currentBase == 1) { //collide in sequential order
			player.updateBase();
			System.out.println("Last base reached has been updated: " + num);
			if (num == 4) {
				System.out.println("Congratulation! You won! Time Lapse: " + clock);
				exit();
			}
		}
		
	}
	
	// collided with cyborg
	public void cyborgColli() {
		System.out.println("Collide with Cyborg");
		Cyborg player = getPlayer();
		player.addDamageLevel(2); //add damage and dim color
		player.setSpeed(0); // enforce max limit
		status();
	}
	
	// check lives, damage, energy
	public void status() {
		Cyborg player = getPlayer();
			if (player.getDamage() == 10 || player.getEnergyLevel() == 0) {
				lives--;
				System.out.println("You lost a life.");
				if(lives == 0) {
					System.out.println("Game over! You're dead.");
					exit();
				} else {
					init();
				}
			}
	}
	
	// collided with energy station
	public void energyColli() {
		Cyborg player = getPlayer();
		EnergyStation es = getRandEnergyStation();
		System.out.println("Collide with energy station ");
		player.addEnergyLevel(es.getCapacity()); // gives energy to cyborg
		es.setCapacity(0); // no energy left
		es.setColor(ColorUtil.rgb(0, 110, 0));// fade color energy station
		EnergyStation newES = new EnergyStation(randSize(), randLoc());
		myGameObjects.add(newES);
	}
	
	// gives random non-empty energy stations
	private EnergyStation getRandEnergyStation() {
		EnergyStation energyStation[] = new EnergyStation[2];
		int count = 0;
		for(int i = 0; i< myGameObjects.size(); i++) {
			if (myGameObjects.get(i) instanceof EnergyStation) {
				EnergyStation station = (EnergyStation) myGameObjects.get(i);
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
		Cyborg player = getPlayer();
		player.addDamageLevel(); // add damage and dim color
		brake(); // reduce speed
		player.setSpeed(0); // enforce max limit
		status(); // checking lives, damage, energy
	}
	
	// game clock
	public void tick() {
		Cyborg player = getPlayer();
		System.out.println("Updates: "
				+ "\n1) if the player cyborg moves, then the cyborg’s heading should be incremented or decremented by the cyborg’s steeringDirection"
				+ "\n2) Drones  also  update  their  heading  as  indicated  above"
				+ "\n3) all  moveable  objects are told to update their positions according to their current heading and speed"
				+ "\n4) the cyborg’s energy level is reduced by the amount indicated by its energyConsumptionRate"
				+ "\n5) the elapsed time game clock is incremented by one");
		int steeringAmount = player.getSteeringDirection();
		// if player cyborg moves, increment heading by steeringDirection
		if (player.getDamage() < 10 || player.getEnergyLevel() > 0 || player.getSpeed() > 0) { 
			player.changeHeading(steeringAmount);
		}

		for (int i = 0; i < myGameObjects.size(); i++) {
			if (myGameObjects.get(i) instanceof Moveable) { // get all moveable to update location
				Moveable moving = (Moveable) myGameObjects.get(i);
				moving .move(); // drones' heading updated in move()
			}
		}
		player.updateEnergyLvl(); // reduce energy
		clock++; // increase clock
	}
}
