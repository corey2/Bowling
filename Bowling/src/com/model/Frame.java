package com.model;

import com.exceptions.InvalidRollException;

public class Frame {
	
	int frameNumber;
	int roll1;
	int roll2;
	int pins;
	
	
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
			int safetyPins = pins;
			if (roll1 < 0) {
				roll1 = pinsHit;
				pins = pins - pinsHit;
				checkPins(1, safetyPins);
			} else if (roll2 < 0) {
				roll2 = pinsHit;
				pins = pins - pinsHit;
				checkPins(2, safetyPins);
			} else {
				throw new InvalidRollException("You can't roll the ball more than twice per frame");
			}
			
		} else {
			throw new InvalidRollException("You already knocked down all the pins");
		}
	}
	
	public int getPins() {
		return pins;
	}
	
	public int getFrameNumber() {
		return frameNumber;
	}
	
	public void print() {
		System.out.println("Frame Progress");
		printRow("Frame", Integer.toString(frameNumber));
		
		String roll1Display = getRoll1Display();
		String roll2Display = getRoll2Display();
		String rolls = roll1Display + " " + roll2Display;
		printRow("Rolls", rolls);
		
		String score = Integer.toString(10 - pins);
		if (score.equals("10")) {
			score = "?";
		}
		printRow("Score", score);
		System.out.println("Pins remaining: "+getPins());
		
	}
	
	void printRow(String title, String input) {
		System.out.print("| "+title+" |");
		System.out.println(" "+input+" |");
	}
	
	//If the number of pins falls below 0, this method will undo the roll and throw an exception.
	void checkPins(int roll, int safetyPins) throws InvalidRollException {
		if (pins < 0) {
			pins = safetyPins;
			if (roll == 1) {
				roll1 = -1;
			}
			if (roll == 2) {
				roll2 = -1;
			}
			throw new InvalidRollException("You can't knock down more than 10 pins per frame");
		}
	}
	
	protected int getRoll1() {
		return roll1;
	}
	
	protected int getRoll2() {
		return roll2;
	}
	
	protected String getRoll1Display() {
		String display = "";
		if (roll1 >= 0) {
			if (roll1 == 0) {  //First roll miss
				display = "-";
			} else if (roll1 == 10) {  //Strike
				display = "X";
			} else {  //First Roll 1-9
				display = roll1+"";
			}
		}
		return display;
	}
	
	protected String getRoll2Display() {
		String display = "";
		if (roll2 >= 0) {
			//System.out.println("Print Check:");
			//System.out.println(this.roll2);
			//System.out.println(this.pins);
			if (roll2 == 0) {  //Second roll miss
				display = "-";
			} else if (pins == 0) {  //Spare
				display = "/";
			} else if (getClass() == LastFrame.class && pins == 10) {
				display = "X";
			} else {  //Second Roll 1-9
				display = roll2+"";
			}
		}
		return display;
	}
}
