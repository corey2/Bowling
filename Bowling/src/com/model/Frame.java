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
		this.firstRoll = -1;
		
	}
	
	public Frame(int frameNumber) {
		this.frameNumber = frameNumber;
		this.rolls = new ArrayList<Integer>();
		int
	}
	
	public void addRoll(int pinsHit) {
		this.rolls.add(pinsHit);
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
