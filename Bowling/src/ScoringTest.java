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
		
		System.out.println(game.getDisplay());
		frame1.addRoll(10);
		System.out.println(game.getDisplayWithRoll(10));
		game.addFrame(frame1);
		System.out.println(game.getDisplay());
		
		frame2.addRoll(7);
		System.out.println(game.getDisplayWithRoll(7));
		frame2.addRoll(3);
		System.out.println(game.getDisplayWithRoll(3));
		game.addFrame(frame2);
		System.out.println(game.getDisplay());
		
		frame3.addRoll(9);
		System.out.println(game.getDisplayWithRoll(9));
		frame3.addRoll(0);
		System.out.println(game.getDisplayWithRoll(0));
		game.addFrame(frame3);
		System.out.println(game.getDisplay());
		
		frame4.addRoll(10);
		//game.getDisplayWithRoll(10);
		game.addFrame(frame4);
		System.out.println(game.getDisplay());
		
		frame5.addRoll(0);
		//game.getDisplayWithRoll(0);
		frame5.addRoll(8);
		//game.getDisplayWithRoll(8);
		game.addFrame(frame5);
		System.out.println(game.getDisplay());
		
		frame6.addRoll(8);
		//game.getDisplayWithRoll(8);
		frame6.addRoll(2);
		//game.getDisplayWithRoll(2);
		game.addFrame(frame6);
		System.out.println(game.getDisplay());
		
		frame7.addRoll(0);
		//game.getDisplayWithRoll(0);
		frame7.addRoll(6);
		//game.getDisplayWithRoll(6);
		game.addFrame(frame7);
		System.out.println(game.getDisplay());

		frame8.addRoll(10);
		//game.getDisplayWithRoll(10);
		game.addFrame(frame8);
		System.out.println(game.getDisplay());
		
		frame9.addRoll(10);
		//game.getDisplayWithRoll(10);
		game.addFrame(frame9);
		System.out.println(game.getDisplay());
		
		frame10.addRoll(10);
		//game.getDisplayWithRoll(10);
		frame10.addRoll(8);
		//game.getDisplayWithRoll(8);
		frame10.addRoll(1);
		//game.getDisplayWithRoll(1);
		game.addFrame(frame10);
		System.out.println(game.getDisplay());
		
		
		
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
		
		
		/*
		System.out.println(game.getDisplay());
		game.addFrame(frame2);
		System.out.println(game.getDisplay());
		game.addFrame(frame3);
		System.out.println(game.getDisplay());
		game.addFrame(frame4);
		System.out.println(game.getDisplay());
		game.addFrame(frame5);
		System.out.println(game.getDisplay());
		game.addFrame(frame6);
		System.out.println(game.getDisplay());
		game.addFrame(frame7);
		System.out.println(game.getDisplay());
		game.addFrame(frame8);
		System.out.println(game.getDisplay());
		game.addFrame(frame9);
		System.out.println(game.getDisplay());
		game.addFrame(frame10);
		System.out.println(game.getDisplay());
		*/
	}

}
