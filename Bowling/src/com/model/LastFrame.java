package com.model;

import com.exceptions.InvalidRollException;

public class LastFrame extends Frame {
	
	protected int roll3;
	
	public LastFrame() {
		super(10);
		this.roll3 = -1;
	}
	
	public void addRoll(int pinsHit) throws InvalidRollException {
		if (pins > 0) {
			int safetyPins = pins;
			if (roll1 < 0) {
				roll1 = pinsHit;
				pins = pins - pinsHit;
				checkPins(1, safetyPins);
				if (pins == 0) {  //Reset the pins after rolling the first strike
					pins = 10;
				}
			} else if (roll2 < 0) {
				roll2 = pinsHit;
				pins = pins - pinsHit;
				checkPins(2, safetyPins);
				if (pins == 0) {  //Reset the pins after rolling the second strike or spare
					pins = 10;
				}
			} else if (qualify()) {
				//System.out.println("I made it to the bonus round in the last frame");
				roll3 = pinsHit;
				pins = pins - pinsHit;
				
				//Special version of checkPins method
				if (pins < 0) {
					pins = safetyPins;
					roll3 = -1;
					throw new InvalidRollException("You can't knock down more than 10 pins per frame");
				}
				
			} else {
				throw new InvalidRollException("You did not qualify for the bonus round");
			}
			//System.out.println("Roll1: "+roll1);
			//System.out.println("Roll2: "+roll2);
			//System.out.println("Roll3: "+roll3);
			//System.out.println("Pins Left: "+pins);
			
			
		} else {
			throw new InvalidRollException("You already knocked down all the pins");
		}
	}
	
	//Checks whether the player qualifies for the bonus round
	public boolean qualify() {
		//System.out.println("In qualify method");
		if (roll1 == 10 || roll1 + roll2 == 10) { //You got a strike on the first roll or a spare on the second roll
			if (roll3 < 0) {  //You haven't done your third roll yet
				return true;
			}
		}
		return false;
	}
	
	public int getRoll3() {
		return roll3;
	}
	
	protected String getRoll3Display() {
		String display = "";
		if (roll3 >= 0) {
			if (roll3 == 0) {  
				display = "-";
			} else if (pins == 0) {  
				if (roll2 == 10) {  //You got a strike on rolls 1 and 2 or you got a spare on roll 2 followed by a strike
					display = "X";
				} else {  //You got a strike on roll 1 and not a strike on roll 2 but you knocked down all the remaining pins on roll 3
					display = "/";
				}
			} else {  
				display = roll3+"";
			}
		}
		return display;
	}
	
}
