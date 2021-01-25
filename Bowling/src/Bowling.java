import java.util.ArrayList;
import java.util.Scanner;

import com.model.Frame;
import com.model.Game;



public class Bowling {
	public static void main(String[] args) {
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
			if (f != 10) {
				int result1 = rollFirstBall(game, frame, console);
				if (result1 != 10) {
					int result2 = rollSecondBall(game, frame, console);
				}
			} else {
				System.out.println("You made it to the 10th frame.");
			}
		}	
	}
		
	public static int rollFirstBall(Game game, Frame frame, Scanner console) {
		int pinsHit = 0;
		boolean flag = true;
		while (flag) {
			flag = false;
			System.out.println("Enter the result of your first roll:");
			String result = console.nextLine();
				
			//https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
			if (result.matches("-?(0|[1-9]\\d*)")) {
				pinsHit = Integer.parseInt(result);
				if (pinsHit < 1 || pinsHit > 9) {
					System.out.println("You must enter a number bewteen 1 and 9");
					flag = true;
				}
			} else if (result.equalsIgnoreCase("Miss")) {
				pinsHit = 0;
			} else if (result.equalsIgnoreCase("Strike")) {
				pinsHit = 10;
			} else if (result.equalsIgnoreCase("Spare")) {
			    System.out.println("You cannot get a spare on your first roll");
			    flag = true;
			}
		}
				
		frame.addFirstRoll(pinsHit);
		
		return pinsHit;
	}
		
	public static void rollSecondBall() {
		
	}
	
}
			