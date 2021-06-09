package com.model;
import java.util.ArrayList;
import java.util.LinkedList;

import com.exceptions.InvalidFrameException;
import com.exceptions.InvalidRollException;

public class Game {
	
	private LinkedList<Frame> frames;
	private String frameLine;
	private String rollLine;
	private String scoreLine;
	private boolean strike;
	private boolean spare;
	private boolean doubleStrike;
	private int currentFrame;
	
	
	public Game() {
		frames = new LinkedList<Frame>();
		frameLine = "| Frame |";
		rollLine = "| Rolls |";
		scoreLine = "| Score |";
		currentFrame = 1;
	}
	
	public void addFrame(Frame frame) throws InvalidFrameException {
		if (frames.size() > 0) {
			if (frames.get(frames.size()-1).getClass() == FinalFrame.class) {
				throw new InvalidFrameException("This game already has a final frame so you cannot add any more frames");
			}
		}
		
		if (frame.roll1 < 0 || frame.roll2 < 0) {
			throw new InvalidFrameException("You cannot add an incomplete frame to the game");
		}
		
		if (doubleStrike) {
			calculateDoubleStrike(frame);
			//System.out.println("Doublestrike");
		}
		
		if (spare) {
			calculatePrevSpare(frame);
			//System.out.println("Spare");
		}
		
		if (strike) {
			calculatePrevStrike(frame);
			//System.out.println("Strike");
		}
		
		if (frame.getClass() == FinalFrame.class) {
			calculateFinalFrame((FinalFrame) frame);
		} else {
			if (frame.pins > 0) {
				//calculate the score now
				frame.frameScore = frame.roll1+frame.roll2;
			} else {
				frame.frameScore = 0;
				//Check if the frame has a strike or a spare so we can calculate it's score in the next frame
				if (frame.roll1 == 10) {
					strike = true;
				} else {
					spare = true;
				}
			}
		}
		
		frames.add(frame);
		buildFrameLine();
		buildRollLine(frame);
		buildScoreLine(frame);
		
		currentFrame++;
	}
	
	private void calculatePrevSpare(Frame frame) {
		Frame prevFrame = frames.get(frames.size()-1);
		int spareBonus = frame.roll1;
		prevFrame.frameScore = 10 + spareBonus;
		spare = false;
	}
	
	private void calculatePrevStrike(Frame frame) {
		Frame prevFrame = frames.get(frames.size()-1);
		int strikeBonus = frame.roll1 + frame.roll2;
		prevFrame.frameScore = 10 + strikeBonus;
		strike = false;
				
		if (frame.roll1 == 10 && prevFrame.roll1 == 10) {
			doubleStrike = true;
		}
	}
	
	private void calculateDoubleStrike(Frame frame) {
		Frame twoFramesAgo = frames.get(frames.size()-2);
		twoFramesAgo.frameScore = 20+frame.roll1;
		if (frame.roll1 < 10) {
			doubleStrike = false;
		}
	}
	
	
	private void calculateFinalFrame(FinalFrame finalFrame) {
		if (finalFrame.roll3 == -1) {
			finalFrame.frameScore = finalFrame.roll1+finalFrame.roll2;
		} else {
			finalFrame.frameScore = finalFrame.roll1+finalFrame.roll2+finalFrame.roll3;
		}
	}
	
	
	public int getTotalScore() {
		int totalScore = 0;
		//int i = 1;
		for (Frame f: frames) {
			//System.out.println("Frame "+i+": "+f.frameScore);
			totalScore = totalScore + f.frameScore;
			//i++;
		}
		//System.out.println("Total Score: "+totalScore);
		//System.out.println();
		return totalScore;
	}
	
	public ArrayList<Integer> getScoreForEachFrame() {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for (Frame f: frames) {
			scores.add(f.frameScore);
		}
		return scores;
	}
	
	
	private void buildFrameLine() {
		frameLine = frameLine+" "+currentFrame+" |";
	}
	
	private void buildRollLine(Frame f) {
		String roll1Display = f.getRoll1Display();
		String roll2Display = f.getRoll2Display();
		
		if (f.getClass() == Frame.class) { 
			rollLine = rollLine+" "+roll1Display+" "+roll2Display+" |";
		}
		
		if (f.getClass() == FinalFrame.class) {
			FinalFrame ff = (FinalFrame) f;
			String roll3Display = ff.getRoll3Display();
			//System.out.println("roll3Display: "+roll3Display);
			
			if (ff.roll1 == 10 && ff.roll2 == 10) {
				roll2Display = "X";
			}
			
			//if (!ff.qualify() && !roll3Display.equals("X") && !roll3Display.equals("/")) {
			//	roll3Display = "N/A";
			//}
			
			
			rollLine = rollLine+" "+roll1Display+" "+roll2Display+" "+roll3Display+ " |";
		}
			
	}
	
	private void buildScoreLine(Frame f) {
		ArrayList<Integer> scores = getScoreForEachFrame();
		scoreLine = "| Score |";
		int totalScore = 0;
		for (int i=0; i<scores.size(); i++) {
			totalScore = totalScore + scores.get(i);
			if (f.getPins() == 0 && i == scores.size()-1) {  //The player got a strike or spare on the current frame so their total score can't be calculated until some point in time after the next frame starts
				scoreLine = scoreLine+" ? |";
			} else {
				scoreLine = scoreLine+" "+totalScore+" |";
			}
		}
	}
	
	
	public void print() {
		System.out.println(frameLine);
		System.out.println(rollLine);
		System.out.println(scoreLine);
		System.out.println();
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
	
	
	
	
	
	/*
	public Game(boolean prefilled) {
		frames = new LinkedList<Frame>();
		for (int i=1; i<=9; i++) {
			Frame frame = new Frame();
			frames.add(frame);
		}
		FinalFrame finalFrame = new FinalFrame();
		frames.add(finalFrame);
		currentFrame = 1;
	}
	
	
	public void addRoll(int pinsHit) throws InvalidRollException {
		Frame f = frames.get(currentFrame);
		f.addRoll(pinsHit);
		if (f.roll2 >= 0) {
			currentFrame++;
		}
		
	}
	*/
	
}
