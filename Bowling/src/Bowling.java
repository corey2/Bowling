import java.util.ArrayList;
import java.util.Scanner;

import com.exceptions.InvalidRollException;
import com.model.Frame;
import com.model.Game;

public class Bowling {
	public static void main(String[] args) {
		System.out.println("Welcome to Corey's bowling application!");
		System.out.println("How to use: Every time you roll the ball, you must enter your score for that roll.");
		System.out.println("Acceptable Input Values: 1, 2, 3, 4, 5, 6, 7, 8, 9, Miss, Strike, Spare");
		System.out.println("If you input an unacceptable value, the program will prompt you to re-enter an acceptable value");
		Scanner console = new Scanner(System.in);
		Game game = new Game();
		int f = 1;
		int r = 1;
		while (f<=10) {
			System.out.println("Starting Frame "+f);
			Frame frame = new Frame(f);
			while (r<=2) {
				System.out.println("Enter the result of roll "+r);
				String result = console.nextLine();	
				//https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
				if (result.matches("-?([1-9]\\d*)")) {
					int pinsHit = Integer.parseInt(result);
					if (pinsHit >= 1 && pinsHit <= 9) {
						r = safeRoll(frame, pinsHit, r);
					} else {
						System.out.println("The number you entered is not between 1 and 9.");
					}
				} else if (result.equalsIgnoreCase("Miss")) {
					r = safeRoll(frame, 0, r);
				} else if (result.equalsIgnoreCase("Strike")) {
					if (r == 1) {
						r = safeRoll(frame, 10, r);
						r=5; //The while loop is broken and you start the next frame if you get a strike 
					} else {
						System.out.println("Your second roll cannot be a strike.");
					}
				} else if (result.equalsIgnoreCase("Spare")) {
					if (r == 2) {
						r = safeRoll(frame, frame.getPins(), r);
					} else {
						System.out.println("Your first roll cannot be a spare.");
					}
				} else {
					System.out.println("You did not enter a valid input.");
				}
				System.out.println("Pins remaining: "+frame.getPins());
			}	
			r=1;
			game.addFrame(frame);
			f++;
		}
		game.print();
		console.close();
	}
	
	
	//Performs a roll, updates the roll number, and automatically deals with any possible exceptions in one method
	public static int safeRoll(Frame frame, int pinsHit, int r) {
		try {
			frame.addRoll(pinsHit);
			frame.print();
			r++;
		} catch (InvalidRollException e) {
			System.out.println(e.getMessage());
		}
		return r;
	}
	
}
			