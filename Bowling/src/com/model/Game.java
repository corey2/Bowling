package com.model;
import java.util.ArrayList;

import com.exceptions.InvalidRollException;

public class Game {
	
	private ArrayList<Frame> frames;
	private String frameLine;
	private String rollLine;
	private String scoreLine;
	private int totalScore;
	
	public Game() {
		this.frames = new ArrayList<Frame>();
		this.totalScore = 0;
		this.frameLine = "| Frame |";
		this.rollLine = "| Rolls |";
		this.scoreLine = "| Score |";
		
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
			//buildScoreLine(f);
		}
		System.out.println(this.frameLine);
		System.out.println(this.rollLine);
		
		
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
		
		
	}
	
	
}
