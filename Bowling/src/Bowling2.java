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
				int pinsHit = getRollInput(r, console, frame.getRoll1());
				try {
					frame.addRoll(pinsHit);
					//System.out.println("Pins hit: "+pinsHit);
					//System.out.println("Pins remaining: "+frame.getPins());
					if (r==1  && frame.getPins()>0) {
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
		
	}
	
	
	public static int getRollInput(int r, Scanner console, int firstRoll) {
		boolean flag = false;
		int pinsHit = -1;
		while (!flag) {
			System.out.println("Enter the result of roll "+r);
			String result = console.nextLine();
			//https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
			if (result.matches("-?([0-9]\\d*)")) {
				pinsHit = Integer.parseInt(result);
				flag = true;
			} else if (result.equalsIgnoreCase("Miss")) {
				pinsHit = 0;
				flag = true;
			} else if (result.equalsIgnoreCase("Strike")) {
				if (r == 1) {
					pinsHit = 10;
					flag = true;
				} else {
					System.out.println("Your second roll cannot be a strike.");
				}
			} else if (result.equalsIgnoreCase("Spare")) {
				if (r == 2) {
					System.out.println("First Roll: "+firstRoll);
					pinsHit = 10 - firstRoll;
					flag = true;
				} else {
					System.out.println("Your first roll cannot be a spare.");
				}
			} else {
				System.out.println("You did not enter a valid input.");
			}
		}
		return pinsHit;
	}
	
}
			