package com.model;
import java.util.ArrayList;

import com.exceptions.InvalidRollException;

public class Game {
	
	private ArrayList<Frame> frames;
	private String frameLine;
	private String rollLine;
	private String scoreLine;
	private int totalScore;
	private int calculatedScore;
	private int strikeCount;
	private int spareCount;
	
	
	public Game() {
		this.frames = new ArrayList<Frame>();
		this.frameLine = "| Frame |";
		this.rollLine = "| Rolls |";
		this.scoreLine = "| Score |";
		this.totalScore = 0;
		this.calculatedScore = 0;
		this.strikeCount = 0;
		this.spareCount = 0;
		
	}
	
	public void addFrame(Frame frame) {
		this.frames.add(frame);
	}
	
	public int getTotalScore() {
		return this.totalScore;
	}
	
	public void print() {
		int framesSize = this.frames.size();
		Frame lastFrame = this.frames.get(framesSize-1);
		buildFrameLine(lastFrame);
		buildRollLine(lastFrame);
		buildScoreLine(lastFrame);
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
		
		if (strikeCount > 0) {
			calculateStrike(f);
		}
		if (spareCount > 0) {
			calculateSpare(f);
		}
		
		calculateScore(strike, spare, frameScore);
		
	}
	
	private void calculateStrike(Frame f) {
		int roll1 = f.getRoll1();
		int roll2 = f.getRoll2();
		int bonus = roll1 + roll2;
		int strikeScore = 30*(this.strikeCount-1);
		System.out.println("strikeScore: "+strikeScore);
		System.out.println("calculatedScore: "+this.calculatedScore);
		System.out.println("totalScore: "+this.totalScore);
		
		
		if (f.getPins() > 0) {
			//System.out.println("You got a strike in the last frame and in this frame you have "+f.getPins()+" pins left standing");
			this.totalScore = this.totalScore + bonus;
			String scoreString = String.valueOf(this.totalScore);
			this.scoreLine = this.scoreLine.replace("?", scoreString);
			this.strikeCount = 0;
		} else {
			//System.out.println("You knocked down all the pins more than once in a row after a strke!");
			if (roll1 == 10) {
				int questionMarkCount = 0;
				for (int i=0; i<this.scoreLine.length(); i++) {
					if (this.scoreLine.charAt(i) == '?') {
						questionMarkCount++;
					}
				}
				System.out.println("Question mark count: "+questionMarkCount);
				
				String input = strikeScore+this.calculatedScore+"";
				if (questionMarkCount >= 2) {
					this.scoreLine = this.scoreLine.replaceFirst("\\?", input);
				}
				
				/*
				int frameNumber = f.getFrameNumber();
				if (frameNumber >= 4) {
					Frame twoFramesAgo = this.frames.get(frameNumber-4);
					System.out.println("This frameNumber: "+f.getFrameNumber());
					System.out.println("Other frameNumber: "+twoFramesAgo.getFrameNumber());
				}
				*/
					
			} else {
				//System.out.println("It was a spare");
				this.totalScore = this.totalScore + bonus;
				String scoreString = String.valueOf(this.totalScore);
				this.scoreLine = this.scoreLine.replace("?", scoreString);
				this.strikeCount = 0;
				//this.spareCount = 1;
			}
		}
		
	}
	
	private void calculateSpare(Frame f) {
		int roll1 = f.getRoll1();
		if (f.getPins() > 0) {
			this.totalScore = this.totalScore + roll1;
			System.out.println("You got a spare in the last frame and in this frame you have "+f.getPins()+" pins left standing");		
			String scoreString = String.valueOf(this.totalScore);
			this.scoreLine = this.scoreLine.replace("?", scoreString);
			this.spareCount = 0;
		} else {
			System.out.println("You knocked down all the pins more than once in a row after a spare!");
			if (roll1 == 10) {
				System.out.println("It was a strike");
				this.totalScore = this.totalScore + roll1;
				String scoreString = String.valueOf(this.totalScore);
				this.scoreLine = this.scoreLine.replace("?", scoreString);
				this.spareCount = 0;
				//this.strikeCount = 1;
			} else {
				System.out.println("It was a spare");
				this.totalScore = this.totalScore + roll1;
				String scoreString = String.valueOf(this.totalScore);
				this.scoreLine = this.scoreLine.replace("?", scoreString);
			}
		}
	}
	
	
	private void calculateScore(boolean strike, boolean spare, int frameScore) {
		//if (spare && strike) {
		//	throw new InvalidRollException("You cannot get a spare and a strike in a single frame");
		//}
		
		this.totalScore = this.totalScore + frameScore;
		
		if (!strike && !spare) {
			this.scoreLine = this.scoreLine+" "+this.totalScore+" |";
			this.calculatedScore = this.totalScore;
		} else {
			this.scoreLine = this.scoreLine+" "+"? |";
			//System.out.println("Total score after Strike or Spare: "+this.totalScore);
		}
		
		if (strike) {
			this.strikeCount++;
		} 
		if (spare) {
			this.spareCount++;
		}
		
	}
	
	
	
}
