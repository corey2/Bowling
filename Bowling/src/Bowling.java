import java.util.ArrayList;
import java.util.Scanner;



public class Bowling {
	public static void main(String[] args) {
		System.out.println("Welcome to Corey's bowling application!");
		System.out.println("How to use: Every time you roll the ball, you must enter your score for that roll.");
		System.out.println("Acceptable Input Values: 1, 2, 3, 4, 5, 6, 7, 8, 9, Miss, Spare, Strike");
		Scanner console = new Scanner(System.in);
		Game game = new Game();
		int f = 1;
		while (f<=10) {
			System.out.println("Starting Frame "+f);
			Frame frame = new Frame(f);
			
			System.out.println("Enter the result of your roll:");
			String result = console.nextLine();
			
			//https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
			if (result.matches("-?(0|[1-9]\\d*)")) {
				int pinsHit = Integer.parseInt(result);
				System.out.println("Pins Hit");
				System.out.println(pinsHit);
				System.out.println();
				f = 11;
			}
			
			
		}
	}
	
}
			