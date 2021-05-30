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
		
		//System.out.println("Frame ?:");
		//System.out.println(frame.getRoll1());
		//System.out.println(frame.getRoll2());
		
		// Test 1
		/*
		frame1.addRoll(10);
	
		frame2.addRoll(7);
		frame2.addRoll(3);
		
		frame3.addRoll(9);
		frame3.addRoll(0);
		
		frame4.addRoll(10);
		
		frame5.addRoll(0);
		frame5.addRoll(8);
		*/
		
		frame1.addRoll(10);
		
		frame3.addRoll(3);
		frame3.addRoll(7);
		
		
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
		
		
	}

}
