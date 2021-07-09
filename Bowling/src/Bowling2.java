import java.util.Scanner;

import com.exceptions.InvalidFrameException;
import com.exceptions.InvalidRollException;
import com.model.FinalFrame;
import com.model.Frame;
import com.model.Game;

public class Bowling2 {
	public static void main(String[] args) throws InvalidFrameException {
		System.out.println("Welcome to Corey's bowling application!");
		System.out.println("How to use: Every time you roll the ball, you must enter your score for that roll.");
		System.out.println("Acceptable Input Values: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, Miss, Strike, Spare");
		System.out.println("If you input an unacceptable value, the program will prompt you to re-enter an acceptable value");
		Scanner console = new Scanner(System.in);
		Game game = new Game();
		int f = 1;
		int r = 1;
		while (f<=9) {
			System.out.println("Starting Frame "+f);
			Frame frame = new Frame();
			while (r<=2 && frame.getPins()>0) {
				int pinsHit = convertRollInput(console, frame, r);
				try {
					frame.addRoll(pinsHit);
					//System.out.println("Pins hit: "+pinsHit);
					//System.out.println("Pins remaining: "+frame.getPins());
					if (r==1) {
						System.out.println("Current Score:");
						System.out.println(game.getDisplayWithRoll(pinsHit));
					}
					r++;
				} catch (InvalidRollException e) {
					System.out.println(e.getMessage());
				}
			}	
			game.addFrame(frame);
			System.out.println();
			System.out.println("Current Score:");
			System.out.println(game.getDisplay());
			r=1;
			f++;
		}
		
		
		System.out.println("Starting Frame 10");
		FinalFrame finalFrame = new FinalFrame();
		
		while (r<=3) {
			if (r<=2) {
				int pinsHit = convertRollInput(console, finalFrame, r);
				try {
					finalFrame.addRoll(pinsHit);
					if (r==1) {
						System.out.println("Current Score:");
						System.out.println(game.getDisplayWithRoll(pinsHit));
					}
					r++;
				} catch (InvalidRollException e) {
					System.out.println(e.getMessage());
				}
			} else {
				if (finalFrame.qualify()) {
					try {
						System.out.println("Current Score:");
						System.out.println(game.getDisplayWith2Rolls(finalFrame.getRoll1(), finalFrame.getRoll2()));
					} catch (InvalidRollException e) {
						System.out.println(e.getMessage());
					}
					System.out.println("You qualify for a bonus roll!");
					int pinsHit = convertRollInput(console, finalFrame, r);
					try {
						finalFrame.addRoll(pinsHit);
						r++;
					} catch (InvalidRollException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		
		game.addFrame(finalFrame);
		System.out.println();
		System.out.println("Final Score:");
		System.out.println(game.getDisplay());
			
		
		console.close();
	}
	
	
	public static int convertRollInput(Scanner console, Frame frame, int r) {
		boolean flag = false;
		int pinsHit = -1;
		while (!flag) {
			System.out.println("Enter the result of roll "+r);
			String rollInput = console.nextLine();
			//https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
			if (rollInput.matches("-?([0-9]\\d*)")) {
				pinsHit = Integer.parseInt(rollInput);
				flag = true;
			} else if (rollInput.equalsIgnoreCase("Miss")) {
				pinsHit = 0;
				flag = true;
			} else if (rollInput.equalsIgnoreCase("Strike")) {
				if (frame.getPins()==10) {
					pinsHit = 10;
					flag = true;
				} else {
					System.out.println("Invalid Input: You cannot roll a strike when there are less than 10 pins left");
				}
			} else if (rollInput.equalsIgnoreCase("Spare")) {
				//System.out.println("Roll1: "+frame.getRoll1());
				//System.out.println("Roll2: "+frame.getRoll2());
				if (frame.getRoll1()<0) {
					System.out.println("Invalid Input: You cannot roll a spare on your first roll");
				} else if (frame.getRoll2()<0) {
					if (frame.getRoll1()!=10) {  //This should only be possible on the final frame
						pinsHit = 10 - frame.getRoll1();
						flag = true;
					} else {
						System.out.println("Invalid Input: You cannot roll a spare immediately after rolling a strike");
					}
				} else {
					try {
						FinalFrame finalFrame = (FinalFrame) frame;
						if (finalFrame.getRoll3()<0) {
							if (frame.getRoll1() == 10) {
								pinsHit = 10 - frame.getRoll2();
								flag = true;
							} else {
								System.out.println("Invalid Input: The third roll cannot be a spare if you already got a spare in this frame");
							}
						}	
					} catch (ClassCastException e) {}
				}
			} else {
				System.out.println("Invalid Input: You did not enter a number or acceptable word");
			}
		}
		return pinsHit;
	}
	
}
			