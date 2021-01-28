package com.model;

import com.exceptions.InvalidRollException;

public class LastFrame extends Frame {
	
	private int roll3;
	
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
			} else if (this.roll2 < 0 || this.roll1 == 10) {
				if (roll1 == 10) {
					this.pins = 10;
				}
				this.roll2 = pinsHit;
				this.pins = this.pins - pinsHit;
				checkPins(2, safetyPins);
			} else if (this.qualify()) {
				System.out.println("I made it to the bonus round in the last frame");
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
	
}
