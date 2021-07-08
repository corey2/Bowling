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
				System.out.println("Enter the result of roll "+r);
				String rollInput = console.nextLine();
				int pinsHit = convertRollInput(rollInput, frame);
				if (pinsHit>=0) {
					try {
						frame.addRoll(pinsHit);
						//System.out.println("Pins hit: "+pinsHit);
						//System.out.println("Pins remaining: "+frame.getPins());
						if (r==1 && frame.getPins()>0) {
							System.out.println("Current Score:");
							System.out.println(game.getDisplayWithRoll(pinsHit));
						}
						r++;
					} catch (InvalidRollException e) {
						System.out.println(e.getMessage());
					}
				}
			}	
			game.addFrame(frame);
			System.out.println();
			System.out.println("Current Score:");
			System.out.println(game.getDisplay());
			r=1;
			f++;
		}
		
		/*
		System.out.println("Starting Frame 10");
		FinalFrame finalFrame = new FinalFrame();
		
		boolean flag = false;
		int pinsHit = -1;
		while (!flag) {
			System.out.println("Enter the result of roll 1");
			String result = console.nextLine();
			if (result.matches("-?([0-9]\\d*)")) {
				pinsHit = Integer.parseInt(result);
				flag = true;
			} else if (result.equalsIgnoreCase("Miss")) {
				pinsHit = 0;
				flag = true;
			} else if (result.equalsIgnoreCase("Strike")) {
				pinsHit = 10;
				flag = true;
			} else if (result.equalsIgnoreCase("Spare")) {
				System.out.println("Your first roll cannot be a spare");
			} else {
				System.out.println("You did not enter a valid input");
			}
		}
		try {
			finalFrame.addRoll(pinsHit);
			System.out.println("Current Score:");
			System.out.println(game.getDisplayWithRoll(pinsHit));
		} catch (InvalidRollException e) {
			System.out.println(e.getMessage());
		}
		
		flag = false;
		pinsHit = -1;
		while (!flag) {
			System.out.println("Enter the result of roll 2");
			String result = console.nextLine();
			if (result.matches("-?([0-9]\\d*)")) {
				pinsHit = Integer.parseInt(result);
				flag = true;
			} else if (result.equalsIgnoreCase("Miss")) {
				pinsHit = 0;
				flag = true;
		*/
		
		console.close();
	}
	
	
	public static int convertRollInput(String rollInput, Frame frame) {
		boolean flag = false;
		int pinsHit = -1;
			//https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
			if (rollInput.matches("-?([0-9]\\d*)")) {
				pinsHit = Integer.parseInt(rollInput);
			} else if (rollInput.equalsIgnoreCase("Miss")) {
				pinsHit = 0;
			} else if (rollInput.equalsIgnoreCase("Strike")) {
				if (frame.getPins()==10) {
					pinsHit = 10;
				} else {
					System.out.println("You cannot roll a strike when there are less than 10 pins left");
				}
			} else if (rollInput.equalsIgnoreCase("Spare")) {
				if (frame.getRoll1()<0) {
					System.out.println("You cannot roll a spare on your first roll.");
				} else if (frame.getRoll2()<0) {
					pinsHit = 10 - frame.getRoll1();
				} else {
					try {
						FinalFrame finalFrame = (FinalFrame) frame;
						if (finalFrame.getRoll3()<0) {
							if (frame.getRoll1() == 10) {
								pinsHit = 10 - frame.getRoll2();
							} else {
								System.out.println("The third roll cannot be a spare if you already got a spare in this frame.");
							}
						}	
					} catch (ClassCastException e) {}
				}
			} else {
				System.out.println("You did not enter a valid input.");
			}
		return pinsHit;
	}
	
}
			