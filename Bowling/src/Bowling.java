import java.util.ArrayList;
import java.util.Scanner;

import com.exceptions.InvalidRollException;
import com.model.Frame;
import com.model.Game;

public class Bowling {
	public static void main(String[] args) throws InvalidRollException {
		System.out.println("Welcome to Corey's bowling application!");
		System.out.println("How to use: Every time you roll the ball, you must enter your score for that roll.");
		System.out.println("Acceptable Input Values: 1, 2, 3, 4, 5, 6, 7, 8, 9, Miss, Spare, Strike");
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
				if (result.matches("-?(0|[1-9]\\d*)")) {
					int pinsHit = Integer.parseInt(result);
					if (pinsHit >= 1 && pinsHit <= 9) {
						frame.addRoll(pinsHit);
						r++;
					} else {
						System.out.println("The number you input is not between 1 and 9.");
					}
				} else if (result.equalsIgnoreCase("Miss")) {
					frame.addRoll(0);
					r++;
				} else if (result.equalsIgnoreCase("Strike")) {
					if (r == 1) {
						frame.addRoll(10);
						r++;
					} else {
						System.out.println("Your second roll cannot be a strike.");
					}
				} else if (result.equalsIgnoreCase("Spare")) {
					if (r == 2) {
						frame.addRoll(10);
						r++;
					} else {
						System.out.println("Your first roll cannot be a spare.");
					}
				}
				System.out.println("Pins remaining: "+frame.getPins());
			}	
			r=1;
			f++;
		}
	
	
	}
}
			