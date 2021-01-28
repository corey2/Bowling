import java.util.ArrayList;
import java.util.Scanner;

import com.exceptions.InvalidRollException;
import com.model.Frame;
import com.model.Game;
import com.model.LastFrame;

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
		while (f<=9) {
			System.out.println("Starting Frame "+f);
			Frame frame = new Frame(f);
			while (r<=2) {
				r = doBusinessLogic(frame, f, r, console);
			}	
			r=1;
			game.addFrame(frame);
			//if (f % 2 == 0)
			System.out.println();
			System.out.println("Here are the results after Frame "+f);
			game.print();
			f++;
		}
		//System.out.println("Here is the final score:");
		//game.print();
		System.out.println("Final Frame: "+f);
		LastFrame lastFrame = new LastFrame();
		
		while (r<=3) {
			if (r<3) {
				r = doBusinessLogic(lastFrame, f, r, console);
			} else if (lastFrame.qualify()) {  //r==3
				System.out.println("Enter the result of your bonus roll");
				String result = console.nextLine();
				if (result.matches("-?([1-9]\\d*)")) {
					int pinsHit = Integer.parseInt(result);
					if (pinsHit >= 1 && pinsHit <= 9) {
						r = safeRoll(lastFrame, pinsHit, r);
					} else {
						System.out.println("The number you entered is not between 1 and 9.");
					}
				}
				System.out.println("You finished the game and the bonus round");
			} else {
				System.out.println("You finished the game and did not qualify for the bonus round");
				r++;
			}
		}
		
		console.close();
	}
	
	
	public static int doBusinessLogic(Frame frame, int f, int r, Scanner console) {
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
				r=3; //You start the next frame if you get a strike 
				if (f==10) {  //Unless you are on the last frame
					r++;
				}
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
		return r;
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
			