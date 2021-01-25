package com.model;
import java.util.ArrayList;

import com.exceptions.InvalidRollException;

public class Frame {
	
	private int frameNumber;
	private int roll1;
	private int roll2;
	private int pins;
	
	
	public Frame() {
		this.frameNumber = 1;
		this.roll1 = -1;
		this.roll2 = -1;
		this.pins = 10;
		
	}
	
	public Frame(int frameNumber) {
		this.frameNumber = frameNumber;
		this.roll1 = -1;
		this.roll2 = -1;
		this.pins = 10;
	}
	
	public void addRoll(int pinsHit) throws InvalidRollException {
		if (pins > 0) {
			int safetyPins = this.pins;
			if (roll1 < 0) {
				this.roll1 = pinsHit;
				this.pins = this.pins - pinsHit;
			} else if (roll2 < 0) {
				this.roll2 = pinsHit;
				this.pins = this.pins - pinsHit;
			} else {
				throw new InvalidRollException("You cannot roll the ball more than twice per frame");
			}
			
			if (pins < 0) {
				this.pins = safetyPins;
				throw new InvalidRollException("You can't knock down more than 10 pins per frame");
			}
		} else {
			throw new InvalidRollException("You already knocked down all the pins");
		}
	}
	
	public int getPins() {
		return this.pins;
	}
	
}
