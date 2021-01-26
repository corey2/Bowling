package com.model;
import java.util.ArrayList;

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
			//buildRollLine(f);
			//buildScoreLine(f);
		}
		System.out.println(this.frameLine);
		
	}
	
	private void buildFrameLine(Frame f) {
		int frameNumber = f.getFrameNumber();
		this.frameLine = this.frameLine + " "+frameNumber+" |";
	}
	
	private void buildRollLine(Frame f) {
		String roll1Display = f.getRoll1Display();
		String roll2Display = f.getRoll2Display();
		
		
		
		
	}
	
	private void buildScoreLine(Frame f) {
		
	}
	
	
}
