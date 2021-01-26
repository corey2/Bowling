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
		if (this.pins > 0) {
			int safetyPins = this.pins;
			if (roll1 < 0) {
				this.roll1 = pinsHit;
				this.pins = this.pins - pinsHit;
				checkPins(1, safetyPins);
				
			} else if (roll2 < 0) {
				this.roll2 = pinsHit;
				this.pins = this.pins - pinsHit;
				checkPins(2, safetyPins);
			} else {
				throw new InvalidRollException("You can't roll the ball more than twice per frame");
			}
			
		} else {
			throw new InvalidRollException("You already knocked down all the pins");
		}
	}
	
	public int getPins() {
		return this.pins;
	}
	
	
	public void print() {
		printRow("Frame", Integer.toString(this.frameNumber));
		
		String roll1Display = getRoll1Display();
		String roll2Display = getRoll2Display();
		String rolls = roll1Display + " " + roll2Display;
		printRow("Rolls", rolls);
		
		String score = Integer.toString(10 - this.pins);
		if (score.equals("10")) {
			score = "TBD";
		}
		printRow("Score", score);
		
	}
	
	private void printRow(String title, String input) {
		System.out.print("| "+title+" |");
		System.out.println(" "+input+" |");
	}
	
	//If the number of pins falls below 0, this method will undo the roll and throw an exception.
	private void checkPins(int roll, int safetyPins) throws InvalidRollException {
		if (this.pins < 0) {
			this.pins = safetyPins;
			if (roll == 1) {
				this.roll1 = -1;
			}
			if (roll == 2) {
				this.roll2 = -1;
			}
			throw new InvalidRollException("You can't knock down more than 10 pins per frame");
		}
	}
	
	int getFrameNumber() {
		return this.frameNumber;
	}
	
	String getRoll1Display() {
		String display = "";
		if (this.roll1 >= 0) {
			if (this.roll1 == 0) {  //First roll miss
				display = "-";
			} else if (this.roll1 == 10) {  //Strike
				display = "X";
			} else {  //First Roll 1-9
				display = this.roll1+"";
			}
		}
		return display;
	}
	
	String getRoll2Display() {
		String display = "";
		if (this.roll2 >= 0) {
			//System.out.println("Print Check:");
			//System.out.println(this.roll2);
			//System.out.println(this.pins);
			if (this.roll2 == 0) {  //Second roll miss
				display = "-";
			} else if (this.pins == 0) {  //Spare
				display = "/";
			} else {  //Second Roll 1-9
				display = this.roll2+"";
			}
		}
		return display;
	}
}
