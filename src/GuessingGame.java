import javax.swing.JFrame;
//import java.awt.Color;

/** 
 * Main application to show an GuessingGame frame
 */
public class GuessingGame {
	/**
	 * Set up guessing game
	 */
	public static void main( String[] args ) {
		// create a new JFrame to hold GameMaker
	    JFrame gameFrame = new JFrame("20 Questions");
		
		// set size
		gameFrame.setSize( 600, 600 );

		gameFrame.add( new GameMaker() );

		// exit normally on closing the window
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// show frame
		gameFrame.setVisible(true);
	}
}
