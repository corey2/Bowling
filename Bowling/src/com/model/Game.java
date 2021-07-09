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
		
		/*
		if (frame.roll1 < 0 || frame.roll2 < 0) {
			throw new InvalidFrameException("You cannot add an incomplete frame to the game");
		}
		*/
		
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
		buildRollLine();
		buildScoreLine();
		
		currentFrame++;
	}
	
	private void calculatePrevSpare(Frame frame) {
		Frame prevFrame = frames.get(frames.size()-1);
		int spareBonus = frame.roll1;
		prevFrame.frameScore = 10 + spareBonus;
		spare = false;
	}
	
	private void calculatePrevStrike(Frame frame) {
		//System.out.println("Calculating previous strike");
		Frame prevFrame = frames.get(frames.size()-1);
		//System.out.println("Roll 1: "+frame.roll1);
		//System.out.println("Roll 2: "+frame.roll2);
		//System.out.println();
		if (frame.roll2 >= 0) {
			int strikeBonus = frame.roll1 + frame.roll2;
			prevFrame.frameScore = 10 + strikeBonus;
		} 
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
			//System.out.println("Frame Score: "+f.frameScore);
			scores.add(f.frameScore);
		}
		return scores;
	}
	
	
	private void buildFrameLine() {
		frameLine = frameLine+" "+currentFrame+" |";
	}
	
	private void buildRollLine() {
		Frame f = frames.get(frames.size()-1);
		String roll1Display = f.getRoll1Display();
		String roll2Display = f.getRoll2Display();
		
		if (f.getClass() == Frame.class) { 
			if (roll1Display.equals("X")) {  //Doesn't add a space for the second roll if the first roll is a strike
				rollLine = rollLine+" "+roll1Display+" |";
			} else {
				rollLine = rollLine+" "+roll1Display+" "+roll2Display+" |";
			}
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
	
	private void buildScoreLine() {
		/*
		ArrayList<Integer> scores = getScoreForEachFrame();
		System.out.println("Current Frame: "+currentFrame);
		System.out.println("Scores: ");
		for (int score:scores) {
			System.out.println(score);
		}
		System.out.println();
		*/
		
		scoreLine = "| Score |";
		int totalScore = 0;
		for (int i=0; i<frames.size(); i++) {
			Frame f = frames.get(i);
			int score = f.frameScore;
			totalScore = totalScore + score;
			if (f.pins == 0 && score == 0) {  //The player got a strike or spare and the score for the frame hasn't been calculated yet
				scoreLine = scoreLine+" ? |";
			} else {
				scoreLine = scoreLine+" "+totalScore+" |";
			}
		}
	}
	
	
	public String getDisplay() {
		//System.out.println("Display");
		String display = frameLine+"\n"+rollLine+"\n"+scoreLine+"\n";
		return display;
	}
		
	
	public String getDisplayWithRoll(int pinsHit) throws InvalidRollException {
		//System.out.println("Display With Roll");
		String tempRollLine = "";
		if (pinsHit > 0) {
			tempRollLine = rollLine+" "+pinsHit;
		} else {
			tempRollLine = rollLine+" -";
		}
		String display = frameLine+"\n"+tempRollLine+"\n"+scoreLine+"\n";
		return display;
		
		//System.out.println("Roll Line: "+rollLine);
		//System.out.println();
		//Frame f = frames.get(frames.size()-1);
		//f.addRoll(pinsHit);
		
		//buildRollLine(f);
		//rollLine = trimLine(rollLine);
		
		//scoreLine = trimLine(scoreLine);
		//buildScoreLine(f);
		
	}
	
	public String getDisplayWith2Rolls(int pinsHit1, int pinsHit2) throws InvalidRollException {
		//System.out.println("Display With Roll");
		String tempRollLine = "";
		if (pinsHit1 > 0 && pinsHit2 > 0) {
			tempRollLine = rollLine+" "+pinsHit1+" "+pinsHit2;
		} else if (pinsHit1 > 0) {
			tempRollLine = rollLine+" "+pinsHit1+" -";
		} else if (pinsHit2 > 0) {
			tempRollLine = rollLine+" - "+pinsHit2;
		} else {
			tempRollLine = rollLine+" - -";
		}
		String display = frameLine+"\n"+tempRollLine+"\n"+scoreLine+"\n";
		return display;
	}
	
	
	
	/*
	public String trimLine(String line) {
		line = line.substring(0, line.length()-1);
		while (line.charAt(line.length()-1) != '|') {
			line = line.substring(0, line.length()-1);
		}
		return line;
	}
	
	

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
