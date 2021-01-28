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
			if (roll1 < 0) {
				this.roll1 = pinsHit;
				this.pins = this.pins - pinsHit;
				checkPins(1, safetyPins);
			} else if (roll2 < 0 || roll1 == 10) {
				if (roll1 == 10) {
					this.pins = 10;
				}
				this.roll2 = pinsHit;
				this.pins = this.pins - pinsHit;
				checkPins(2, safetyPins);
			} else if (this.pins == 0 && roll3 < 0 || roll1 == 10) {
				System.out.println("I made it to the bonus round");
			} else {
				throw new InvalidRollException("You did not qualify for the bonus round");
			}
			
		} else {
			throw new InvalidRollException("You already knocked down all the pins");
		}
	}
	
}
