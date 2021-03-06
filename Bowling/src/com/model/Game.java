package com.model;
import java.util.ArrayList;

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
		frames.add(frame);
		int framesSize = frames.size();
		Frame latestFrame = frames.get(framesSize-1);
		buildFrameLine(latestFrame);
		buildRollLine(latestFrame);
		buildScoreLine(latestFrame);
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public void print() {
		System.out.println(frameLine);
		System.out.println(rollLine);
		System.out.println(scoreLine);
	}
	
	private void buildFrameLine(Frame f) {
		int frameNumber = f.frameNumber;
		frameLine = frameLine+" "+frameNumber+" |";
	}
	
	private void buildRollLine(Frame f) {
		String roll1Display = f.getRoll1Display();
		String roll2Display = f.getRoll2Display();
		
		if (f.getClass() == Frame.class) { 
			rollLine = rollLine+" "+roll1Display+" "+roll2Display+" |";
		}
		
		if (f.getClass() == LastFrame.class) {
			LastFrame lf = (LastFrame) f;
			String roll3Display = lf.getRoll3Display();
			//System.out.println("roll3Display: "+roll3Display);
			
			if (lf.roll1 == 10 && lf.roll2 == 10) {
				roll2Display = "X";
			}
			
			//if (!lf.qualify() && !roll3Display.equals("X") && !roll3Display.equals("/")) {
			//	roll3Display = "N/A";
			//}
			
			
			rollLine = rollLine+" "+roll1Display+" "+roll2Display+" "+roll3Display+ " |";
		}
			
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
		
		if (f.getClass() == Frame.class) {
			calculateScore(strike, spare, frameScore);
		}
			
		if (f.getClass() == LastFrame.class) {
			LastFrame lf = (LastFrame) f;
			if (lf.roll3 < 0) {
				calculateScore(strike, spare, frameScore);
			} else {
				calculateFinalScore(lf);
			}
		}
	}
	
	private void calculateStrike(Frame f) {
		int roll1 = f.getRoll1();
		int roll2 = f.getRoll2();
		int bonus = roll1 + roll2;
		int strikeScore = 30*(strikeCount-1);
		//System.out.println("strikeScore: "+strikeScore);
		//System.out.println("calculatedScore: "+calculatedScore);
		//System.out.println("totalScore: "+totalScore);
		
		
		if (f.getPins() > 0) {
			//System.out.println("You got a strike in the last frame and in this frame you have "+f.getPins()+" pins left standing");
			totalScore = totalScore + bonus;
			String scoreString = String.valueOf(this.totalScore);
			scoreLine = this.scoreLine.replace("?", scoreString);
			strikeCount = 0;
		} else {
			//System.out.println("You knocked down all the pins more than once in a row after a strke!");
			if (roll1 == 10) {
				int questionMarkCount = 0;
				for (int i=0; i<scoreLine.length(); i++) {
					if (scoreLine.charAt(i) == '?') {
						questionMarkCount++;
					}
				}
				//System.out.println("Question mark count: "+questionMarkCount);
				
				String input = strikeScore+calculatedScore+"";
				if (questionMarkCount >= 2) {
					scoreLine = scoreLine.replaceFirst("\\?", input);
				}	
			} else {
				//System.out.println("It was a spare");
				totalScore = totalScore + bonus;
				String scoreString = String.valueOf(totalScore);
				scoreLine = scoreLine.replace("?", scoreString);
				strikeCount = 0;
				//this.spareCount = 1;
			}
		}
		
	}
	
	private void calculateSpare(Frame f) {
		int roll1 = f.getRoll1();
		if (f.getPins() > 0) {
			totalScore = totalScore + roll1;
			//System.out.println("You got a spare in the previous frame and in this frame you have "+f.getPins()+" pins left standing");		
			String scoreString = String.valueOf(this.totalScore);
			scoreLine = scoreLine.replace("?", scoreString);
			spareCount = 0;
		} else {
			//System.out.println("You knocked down all the pins more than once in a row after a spare!");
			if (roll1 == 10) {
				//System.out.println("It was a strike");
				totalScore = totalScore + roll1;
				String scoreString = String.valueOf(this.totalScore);
				scoreLine = scoreLine.replace("?", scoreString);
				spareCount = 0;
				//this.strikeCount = 1;
			} else {
				//System.out.println("It was a spare");
				totalScore = totalScore + roll1;
				String scoreString = String.valueOf(this.totalScore);
				scoreLine = scoreLine.replace("?", scoreString);
			}
		}
	}
	
	
	private void calculateScore(boolean strike, boolean spare, int frameScore) {
		//if (spare && strike) {
		//	throw new InvalidRollException("You cannot get a spare and a strike in a single frame");
		//}
		
		totalScore = totalScore + frameScore;
		
		//System.out.println("Frame Score: "+frameScore);
		//System.out.println("Total Score: "+this.totalScore);
		
		if (!strike && !spare) {
			scoreLine = scoreLine+" "+totalScore+" |";
			calculatedScore = totalScore;
		} else {
			scoreLine = scoreLine+" "+"? |";
			//System.out.println("Total score after Strike or Spare: "+this.totalScore);
		}
		
		if (strike) {
			strikeCount++;
		} 
		if (spare) {
			spareCount++;
		}
		
	}
	
	private void calculateFinalScore(LastFrame lf) {
		//System.out.println("Total Score Before: "+this.totalScore);
		String roll1Display = lf.getRoll1Display();
		String roll2Display = lf.getRoll2Display();
		String roll3Display = lf.getRoll3Display();
		int frameScore = convertDisplayToInt(roll1Display) + convertDisplayToInt(roll2Display) + convertDisplayToInt(roll3Display);
		totalScore = totalScore + frameScore;
		//System.out.println("Total Score After: "+this.totalScore);
		
		scoreLine = scoreLine+" "+totalScore+" |";
		
		
	}
	
	private int convertDisplayToInt(String display) {
		int score;
		if (display.matches("-?([1-9]\\d*)")) {
			score = Integer.parseInt(display);
		} else if (display.equals("X") || display.equals("/")) {
			score = 10;
		} else if (display.equals("-")) {
			score = 0;
		} else { //Something went wrong
			score = -1;
		}
		return score;
	}
	
	
	
}
