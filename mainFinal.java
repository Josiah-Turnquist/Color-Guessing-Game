// Program: Final Exam for 5/23/2019
// Author: Josiah Turnquist (ID : 1562276)
// IDE: Eclipse
// Program summary: A fun game of chance and guessing.

import java.util.*;

public class mainFinal
{
	final static Scanner keyboard = new Scanner(System.in);
	public static void main (String [] args)
	{
		// declarations
		GameLoop game = new GameLoop(keyboard);
		game.welcome();
		
		// game loop asks
		game.mainLoop();
		
		// end program
		System.out.print("\nProgram terminating.");
		keyboard.close();
		System.exit(0);
	}
}