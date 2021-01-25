package com.model;
import java.util.ArrayList;

import com.exceptions.InvalidRollException;

public class Frame {
	
	private int frameNumber;
	private int firstRoll;
	private int secondRoll;
	private int pins;
	
	
	public Frame() {
		this.frameNumber = 1;
		this.pins = 10;
	}
	
	public Frame(int frameNumber) {
		this.frameNumber = frameNumber;
		this.pins = 10;
	}
	
	public void addFirstRoll(int pinsHit) {
		if (pins != 10) {
			throw new InvalidRollException("This is not your first roll");
		}
		
		this.firstRoll = pinsHit;
		this.pins = this.pins - pinsHit;
		
		if (pins) 
		
		
	}
	
	public void addSecondRoll(int pinsHit) {
		this.secondRoll = pinsHit;
		this.pins = this.pins - pinsHit;
	}
	
	public int getFirstRoll() {
		return this.firstRoll;
	}
	
	public int getSecondRoll() {
		return this.secondRoll;
	}
	
}
