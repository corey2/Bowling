import com.exceptions.InvalidFrameException;
import com.exceptions.InvalidRollException;
import com.model.Frame;
import com.model.Game;
import com.model.LastFrame;

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
		LastFrame frame10 = new LastFrame();
		
		
		//Test 1
		/*
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
		*/
		
		//Test 2
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
		
		
		// Test 3
		/*
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		
		frame.addRoll();
		frame.addRoll();
		frame.addRoll();
		*/
		
		// Perfect Game
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
		
		game.addFrame(frame1);
		System.out.println("After Frame 1: "+game.getTotalScore());
		game.addFrame(frame2);
		System.out.println("After Frame 2: "+game.getTotalScore());
		game.addFrame(frame3);
		System.out.println("After Frame 3: "+game.getTotalScore());
		game.addFrame(frame4);
		System.out.println("After Frame 4: "+game.getTotalScore());
		game.addFrame(frame5);
		System.out.println("After Frame 5: "+game.getTotalScore());
		game.addFrame(frame6);
		System.out.println("After Frame 6: "+game.getTotalScore());
		game.addFrame(frame7);
		System.out.println("After Frame 7: "+game.getTotalScore());
		game.addFrame(frame8);
		System.out.println("After Frame 8: "+game.getTotalScore());
		game.addFrame(frame9);
		System.out.println("After Frame 9: "+game.getTotalScore());
		game.addFrame(frame10);
		System.out.println("After Frame 10: "+game.getTotalScore());
		
	}

}
