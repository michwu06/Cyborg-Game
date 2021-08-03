package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Cyborg extends Moveable implements ISteerable {
	private int damageLevel = 0;			// Acts as the health level of the player, 0 is full health, 10 is completely damaged.
	private int steeringDirection = 0;		// The speed at which the cyborg can turn in degrees (in a game tick?). [0, 40]
	private int energyLevel = 30;			// Resource that is needed in order for the cyborg to move around.
	private int energyConsumptionRate = 2;	// Rate at which energy level declines per game tick.
	private int lastBaseReached = 1;		// The last base the cyborg reached, initialized to 1.
	private static final int SPEED_LIMIT = 100; // Absolute speed limit, used to calculate the actual speed limit when a cyborg takes damage.
	private int maximumSpeed = SPEED_LIMIT; // The modifiable speed limit.
	private static final int size = 30;
	
	// All cyborgs are the same color.
	private static int color = ColorUtil.rgb(0, 0, 200); // Blue

	public Cyborg(Point location) {
		super(size, location, color);
		setHeading(0);	// Face north
		setSpeed(0);	// Initially stationary
	}
	
	// Add to or subtract from the amount of steeringDirection
	public void setSteeringDirection(int myDirection) { 
		steeringDirection += myDirection;
		if(steeringDirection <= -40) {			// Enforce the steering limit of -40
			steeringDirection = -40;
		} else if(steeringDirection >= 40) {	// Enforce the steering limit of 40
			steeringDirection = 40;
		}
	}
	
	// Update speed as well as enforce the speed limit.
	// damageLevel is used as a scale for the SPEED_LIMIT.
	@Override
	public void setSpeed(int speed) {
		int newMaximumSpeed = SPEED_LIMIT - (int) (damageLevel*0.10*SPEED_LIMIT); // Calculate the max speed based on the SPEED_LIMIT
		setMaxSpeed(newMaximumSpeed); 			// update the maxspeed
		int newSpeed = speed + getSpeed(); 		// get current speed
		if (newSpeed <= 0) {                    // only positive values
			newSpeed = 0;
		}
		if(newSpeed > newMaximumSpeed) { 		// Enforce the speed limit if over
			super.setSpeed(newMaximumSpeed);	// Use super to avoid recursively calling setSpeed()
		} else {
			super.setSpeed(newSpeed);			// Set the speed since if it is valid
		}
		//setMaxSpeed(newMaximumSpeed);
	}

	@Override
	public void move() {
		if(energyLevel == 0 || damageLevel == 10) { // If cyborg has zero energy or it is fully damaged, it will not be able to move.
			super.setSpeed(0); // Force the speed to zero using super to call setSpeed() from Movable.
		} else { // to move
			float x = getLocation().getX();
			float y = getLocation().getY();
			float theta = (float) (Math.toRadians(90- getHeading()));
			float deltaX = (float) (Math.cos(theta) * getSpeed());
			float deltaY = (float) (Math.sin(theta)* getSpeed());
			float newX = (float) (x + deltaX);
			float newY = (float) (y + deltaY);
			setLocation(new Point(newX, newY));
		}
		//System.out.println("Checking!");
	}
	
	public void addDamageLevel() {
		if(damageLevel < 10) {
			damageLevel++;
		}
		dimColor();
	}
	
	public void addDamageLevel(int amount) {
		if(damageLevel < 10) {
			damageLevel += amount;
		}
		dimColor();
	}

	@Override
	public void changeHeading(int amount) { 
		// heading = heading + steerinDirection
		int newHeading = getHeading() + amount;
		if(newHeading < 0) {
			newHeading = newHeading + 360;
		} else if(newHeading >= 360) {
			newHeading = newHeading - 360;
		}
		setHeading(newHeading);
//		setHeading(getHeading() + amount);
//		// -55 -> 360 + (-55)
//		if(getHeading() < 0) {
//			setHeading(getHeading() + 360);
//			//heading += 360;
//		} else if (getHeading() > 360) {
//			setHeading(getHeading() - 360); 
//			//heading -= 360;
//		}
		//setHeading(heading);
	}
	
	public void updateEnergyLvl() {
		if(energyLevel - energyConsumptionRate >= 0) {
			energyLevel = energyLevel - energyConsumptionRate;
		}
		if(energyLevel == 0) {
			super.setSpeed(0);
		}
	}
	
	public void dimColor() {
		int newBlue = ColorUtil.blue(getColor()) - 5;
		super.setColor(ColorUtil.rgb(0, 0, newBlue));
		
	}

	public void steerLeft(int amount) { 
		//changeHeading(-amount);
		setSteeringDirection(-amount);
	}
	
	public void steerRight(int amount) { 
		//changeHeading(amount);
		setSteeringDirection(amount);
	}
	
	public int getSteeringDirection() {
		return steeringDirection;
	}
	
	public int getMaxSpeed() {
		return maximumSpeed;
	}
	
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	public int getConsumption() {
		return energyConsumptionRate;
	}
	
	public int getDamage() {
		return damageLevel;
	}
	
	public int getBase() {
		return lastBaseReached;
	}
	
	public void updateBase() {
		lastBaseReached++;
	}
	
	public void setDamage(int myDamage) {
		damageLevel = myDamage;
	}
	
	public void addEnergyLevel(int myEnergyLevel) {
		energyLevel += myEnergyLevel;
	}
	
	public void setMaxSpeed(int myMaxSpeed) {
		maximumSpeed = myMaxSpeed;
	}
	
	@Override
	public String toString() {
		String name = "\nCyborg: ";
		String head = "\nheading: " + getHeading() + "   damage level: " + getDamage() + "  speed: "+ getSpeed() + "  MaxSpeed: " + getMaxSpeed() + 
				"  energy level: " + getEnergyLevel() + "  Consumption: " + getConsumption() + "  last base: " + getBase();
		String dir = "  Steering Direction: " + getSteeringDirection();
		return name + super.toString() + head + dir;
	}
}
