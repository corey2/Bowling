import java.util.ArrayList;

import com.exceptions.InvalidFrameException;
import com.exceptions.InvalidRollException;
import com.model.FinalFrame;
import com.model.Frame;
import com.model.Game;

public class ScoringTest {

	public static void main(String[] args) throws InvalidRollException, InvalidFrameException  {
		Game game = new Game();
		Frame frame1 = new Frame();
		Frame frame2 = new Frame();
		Frame frame3 = new Frame();
		Frame frame4 = new Frame();
		Frame frame5 = new Frame();
		Frame frame6 = new Frame();
		Frame frame7 = new Frame();
		Frame frame8 = new Frame();
		Frame frame9 = new Frame();
		FinalFrame frame10 = new FinalFrame();
		
		
		//Test 1
		
		frame1.addRoll(10);
	
		frame2.addRoll(7);
		frame2.addRoll(3);
		
		frame3.addRoll(9);
		frame3.addRoll(0);
		
		frame4.addRoll(10);
		
		frame5.addRoll(0);
		frame5.addRoll(8);
		
		frame6.addRoll(8);
		frame6.addRoll(2);
		
		frame7.addRoll(0);
		frame7.addRoll(6);

		frame8.addRoll(10);
		
		frame9.addRoll(10);
		
		frame10.addRoll(10);
		frame10.addRoll(8);
		frame10.addRoll(1);
		
		
		
		//Test 2 -> https://www.bowl.com/Welcome/Welcome_Home/Keeping_Score/
		/*
		frame1.addRoll(8);
		frame1.addRoll(0);
		
		frame2.addRoll(7);
		frame2.addRoll(0);
		
		frame3.addRoll(5);
		frame3.addRoll(3);
		
		frame4.addRoll(9);
		frame4.addRoll(1);
		
		frame5.addRoll(9);
		frame5.addRoll(1);
		
		frame6.addRoll(10);
		
		frame7.addRoll(8);
		frame7.addRoll(0);
		
		frame8.addRoll(5);
		frame8.addRoll(1);
		
		frame9.addRoll(3);
		frame9.addRoll(7);
		
		frame10.addRoll(9);
		frame10.addRoll(0);
		*/
		
		
		//Test 3 -> https://milford.lib.de.us/2020/09/28/calculate-your-score-during-bowling/ (Ellen)
		/*
		frame1.addRoll(2);
		frame1.addRoll(3);
		
		frame2.addRoll(7);
		frame2.addRoll(3);
		
		frame3.addRoll(4);
		frame3.addRoll(2);

		frame4.addRoll(8);
		frame4.addRoll(0);
		
		frame5.addRoll(8);
		frame5.addRoll(1);
		
		frame6.addRoll(2);
		frame6.addRoll(5);
		
		frame7.addRoll(1);
		frame7.addRoll(8);
		
		frame8.addRoll(4);
		frame8.addRoll(4);
		
		frame9.addRoll(4);
		frame9.addRoll(0);
		
		frame10.addRoll(9);
		frame10.addRoll(1);
		frame10.addRoll(7);
		*/
		
		// Perfect Game
		/*
		frame1.addRoll(10);
		
		frame2.addRoll(10);
		
		frame3.addRoll(10);
		
		frame4.addRoll(10);
		
		frame5.addRoll(10);
		
		frame6.addRoll(10);
		
		frame7.addRoll(10);
		
		frame8.addRoll(10);
		
		frame9.addRoll(10);
		
		frame10.addRoll(10);
		frame10.addRoll(10);
		frame10.addRoll(10);
		*/
		
		
		game.addFrame(frame1);
		game.print();
		game.addFrame(frame2);
		game.print();
		game.addFrame(frame3);
		game.print();
		game.addFrame(frame4);
		game.print();
		game.addFrame(frame5);
		game.print();
		game.addFrame(frame6);
		game.print();
		game.addFrame(frame7);
		game.print();
		game.addFrame(frame8);
		game.print();
		game.addFrame(frame9);
		game.print();
		game.addFrame(frame10);
		game.print();
		
		

			
	}

	
	public static void addFrameToGame(Game game, Frame frame, int n) throws InvalidFrameException {
		game.addFrame(frame);
		System.out.println("After Frame "+n+":");
		ArrayList<Integer> scores = game.getScoreForEachFrame();
		System.out.print("Frame Scores: ");
		for (int score: scores) {
			System.out.print(score+", ");
		}
		System.out.println();
		System.out.println("Total Score: "+game.getTotalScore());
		System.out.println();
		
		game.print();
		System.out.println();
	}
}
