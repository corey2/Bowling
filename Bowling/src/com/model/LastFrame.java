package com.model;

import com.exceptions.InvalidRollException;

public class LastFrame extends Frame {
	
	protected int roll3;
	
	public LastFrame() {
		super(10);
		this.roll3 = -1;
	}
	
	public void addRoll(int pinsHit) throws InvalidRollException {
		if (this.pins > 0) {
			int safetyPins = this.pins;
			if (this.roll1 < 0) {
				this.roll1 = pinsHit;
				this.pins = this.pins - pinsHit;
				checkPins(1, safetyPins);
				if (this.pins == 0) {  //Reset the pins after rolling the first strike
					this.pins = 10;
				}
			} else if (this.roll2 < 0) {
				this.roll2 = pinsHit;
				this.pins = this.pins - pinsHit;
				checkPins(2, safetyPins);
				if (this.pins == 0) {  //Reset the pins after rolling the second strike or spare
					this.pins = 10;
				}
			} else if (this.qualify()) {
				System.out.println("I made it to the bonus round in the last frame");
				this.roll3 = pinsHit;
				this.pins = this.pins - pinsHit;
				
				//Special version of checkPins method
				if (this.pins < 0) {
					this.pins = safetyPins;
					this.roll3 = -1;
					throw new InvalidRollException("You can't knock down more than 10 pins per frame");
				}
	
			} else {
				throw new InvalidRollException("You did not qualify for the bonus round");
			}
		} else {
			throw new InvalidRollException("You already knocked down all the pins");
		}
	}
	
	//Checks whether the player qualifies for the bonus round
	public boolean qualify() {
		if (this.roll1 >= 0) {  //You completed the first roll
			if (this.roll1 == 10 || this.pins == 0 && this.roll2 >= 0) {  //You got a strike or a spare
				if (this.roll3 < 0) {  //You haven't already finished the bonus round
					return true;
				}
			}
		}
		return false;
	}
	
	protected int getRoll3() {
		return this.roll3;
	}
	
	protected String getRoll3Display() {
		String display = "";
		if (this.roll3 >= 0) {
			if (this.roll3 == 0) {  
				display = "-";
			} else if (this.pins == 0) {  
				if (this.roll2 == 10) {  //You got a strike on rolls 1 and 2 or you got a spare on roll 2 followed by a strike
					display = "X";
				} else {  //You got a strike on roll 1 and not a strike on roll 2 but you knocked down all the remaining pins on roll 3
					display = "/";
				}
			} else {  
				display = this.roll3+"";
			}
		}
		return display;
	}
	
}
