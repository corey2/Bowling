package com.model;
import java.util.ArrayList;

import com.exceptions.InvalidRollException;

public class Game {
	
	private ArrayList<Frame> frames;
	private String frameLine;
	private String rollLine;
	private String scoreLine;
	private int totalScore;
	private boolean prevStrike;
	private boolean prevSpare;
	
	
	public Game() {
		this.frames = new ArrayList<Frame>();
		this.frameLine = "| Frame |";
		this.rollLine = "| Rolls |";
		this.scoreLine = "| Score |";
		this.totalScore = 0;
		this.prevSpare = false;
		this.prevStrike = false;
		
	}
	
	public void addFrame(Frame frame) {
		this.frames.add(frame);
	}
	
	public int getTotalScore() {
		return this.totalScore;
	}
	
	public void print() {
		for (Frame f: this.frames) {
			buildFrameLine(f);
			buildRollLine(f);
			buildScoreLine(f);
		}
		System.out.println(this.frameLine);
		System.out.println(this.rollLine);
		System.out.println(this.scoreLine);
		
	}
	
	private void buildFrameLine(Frame f) {
		int frameNumber = f.getFrameNumber();
		this.frameLine = this.frameLine+" "+frameNumber+" |";
	}
	
	private void buildRollLine(Frame f) {
		String roll1Display = f.getRoll1Display();
		String roll2Display = f.getRoll2Display();
		this.rollLine = this.rollLine+" "+roll1Display+" "+roll2Display + " |";
	}
	
	private void buildScoreLine(Frame f) {
		String roll1Display = f.getRoll1Display();
		String roll2Display = f.getRoll2Display();
		boolean strike = false;
		boolean spare = false;
		int frameScore = 0;
		
		if (roll1Display.matches("-?([1-9]\\d*)")) {
			frameScore = Integer.parseInt(roll1Display);
		} else if (roll1Display.equals("X")) {
			frameScore = 10;
			strike = true;
		} else if (roll1Display.equals("-")) {
			frameScore = 0;
		} //else {
		//	throw new InvalidRollException("Your frame object returned an invalid string");
		//}
		
		if (roll2Display.matches("-?([1-9]\\d*)")) {
			frameScore = frameScore + Integer.parseInt(roll2Display);
		} else if (roll2Display.equals("/")) {
			frameScore = 10;
			spare = true;
		} else if (roll2Display.equals("-")) {	
		} //else {
		//  throw new InvalidRollException("Your frame object returned an invalid string");
		//}
		
		if (this.prevStrike) {
			calculateStrike(f);
		}
		if (this.prevSpare) {
			calculateSpare(f);
		}
		
		calculateScore(strike, spare, frameScore);
		
	}
	
	private void calculateStrike(Frame f) {
		int roll1 = f.getRoll1();
		int roll2 = f.getRoll2();
		int bonus = roll1 + roll2;
		this.totalScore = this.totalScore + bonus;
		System.out.println();
		System.out.println("In calculate strike method");
		System.out.println(this.frameLine);
		System.out.println(this.rollLine);
		System.out.println(this.scoreLine);
	}
	
	private void calculateSpare(Frame f) {
		
	}
	
	
	private void calculateScore(boolean strike, boolean spare, int frameScore) {
		//if (spare && strike) {
		//	throw new InvalidRollException("You cannot get a spare and a strike in a single frame");
		//}
		this.totalScore = this.totalScore + frameScore;
		if (strike) {
			this.prevStrike = true;
		} 
		if (spare) {
			this.prevSpare = true;
		}
		if (!strike && !spare) {
			this.scoreLine = this.scoreLine+" "+this.totalScore+" |";
		} else {
			this.scoreLine = this.scoreLine+" "+"TBD |";
		}
		
	}
	
	
	
}
