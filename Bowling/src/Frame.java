import java.util.ArrayList;

public class Frame {
	
	private int frameNumber;
	private int firstRoll;
	private int secondRoll;
	private int score;
	
	
	public Frame() {
		this.frameNumber = 1;
		this.score = 0;
	}
	
	public Frame(int frameNumber) {
		this.frameNumber = frameNumber;
		this.score = 0;
	}
	
	public void addFirstRoll(int pinsHit) {
		this.firstRoll = pinsHit;
		this.score = this.score + pinsHit;
	}
	
	public void addSecondRoll(int pinsHit) {
		this.secondRoll = pinsHit;
		this.score = this.score + pinsHit;
	}
	
	public int getFirstRoll() {
		return this.firstRoll;
	}
	
	public int getSecondRoll() {
		return this.secondRoll;
	}
	
}
