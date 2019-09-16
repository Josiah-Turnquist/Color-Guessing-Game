/*-------------------------------------------------------
GameLoop
-------------------------------------------------------
- players : ArrayList<Player>
- deck : Deck
- winningScore : int
- round : int
- kbd : Scanner
-------------------------------------------------------
+ GameLoop (keyboard : Scanner)
+ welcome : void
+ mainLoop : void
-------------------------------------------------------
*/

import java.util.*;

public class GameLoop
{
	// data
	ArrayList<Player> players = new ArrayList<Player>();
	Deck deck = new Deck();
	int winningScore;
	int round = 0;
	static Scanner kbd;
		
	// constructors
	GameLoop (Scanner keyboard)
	{
		kbd = keyboard;
	}
	
	// methods
	public void welcome()
	{
		System.out.println("Welcome, this is a game of chance and guessing.");
	}
	
	public void mainLoop()
	{
		char input = '0';
		while (input != 'q')
		{
			// simulation, or play the game?
			System.out.print("\nEnter one of the following:"
					+ "\n[P] Play the game"
					+ "\n[S] Play the simulation (standard for the assignment)"
					+ "\n[Q] Quit"
					+ "\n < enter > : ");
			input = kbd.nextLine().toLowerCase().charAt(0);
			while (input != 'p' && input != 's' && input != 'q')
			{
				System.out.print("Invalid input -- only enter [P] to play the game, [S] to play the simulation, or [Q] to quit the game."
						+ "\n < enter > : ");
				input = kbd.nextLine().toLowerCase().charAt(0);
			}
			
			// PLAY THE GAME 
			if (input == 'p')
				{
				// difficulty level
				System.out.print("\nThe game is on...\n"
						+ "\nWhat difficulty level would you like?"
						+ "\n[E] Easy : 2 players, win by reaching 2 points"
						+ "\n[R] Regular : 3 players, win by reaching 4 points"
						+ "\n[D] Difficult : 4 players, win by reaching 6 points"
						+ "\n < input > : ");
				input = kbd.nextLine().toLowerCase().charAt(0);
				while (input != 'e' && input != 'r' && input != 'd')
				{
					System.out.print("Invalid input -- enter [E] for easy, [R] for regular, or [D] for difficult : ");
					input = kbd.nextLine().toLowerCase().charAt(0);
				}
				
				// set statistics based on game difficulty
				int playerC;
				switch (input)
				{
				case 'e':
					playerC = 2;
					winningScore = 2;
					break;
				case 'r':
					playerC = 3;
					winningScore = 4;
					break;
				case 'd':
					playerC = 4;
					winningScore = 6;
					break; 
				default:
					playerC = 0;
					System.out.println("ERROR IN DIFFICULTY LEVEL - PLEASE CONTACT THE DEVELOPERS.");
					break;
				}
				
				for (int i = 1; i <= playerC; ++i)
				{
					// get name
					boolean bot = false;
					System.out.printf("\nWhat is the name of player %d? : ", i);
					String name = kbd.nextLine();
					
					// is this player a bot?
					System.out.printf("Is %s a bot? [Y/N] : ", name);
					input = kbd.nextLine().toLowerCase().charAt(0);
					while (input != 'y' && input != 'n')
					{
						System.out.print("Invalid input -- only enter [Y] for yes or [N] for no."
								+ "\n < enter > : ");
						input = kbd.nextLine().toLowerCase().charAt(0);
					}
					bot = (input == 'y' ? true : false); // bot = true / false to parallel y / n
					Player p = new Player(name, bot);
					players.add(p);
				}
			}
			// PLAY THE SIMULATION INSTEAD -- creates three random players
			else if (input == 's')
			{
				players.add(new Player("Blake", true));
				players.add(new Player("Craig", true));
				players.add(new Player("John", true));
				winningScore = 4;
			}
			else
			{
				continue;
			}
			
			System.out.println();
			// ------- actual guessing game loop starts here ------- 
			boolean noWinners = true;
			while (noWinners) 
			{
				++round;
				System.out.printf("Round #%d\n", round);

				deck.nextCard(); // flip card over.
				
				// check for any winners at the end of the round
				for (Player p : players)
				{
					boolean correct = false;
					p.guess(kbd);
					if (p.getGuess() == deck.getCard())
					{
						p.addPoint();
						correct = true;
					}					
					else
					{
						p.subPoint();
					}
					
					// print info for player
					System.out.printf("\t> %s guessed %s and was %s.  ", p.getName(), p.getGuessString(), (correct == true ? "correct" : "incorrect"));
					System.out.printf("%s's points: %d.\n", p.getName(), p.getPoints());
					
					if (p.getPoints() >= winningScore)
					{
						noWinners = false;
					}
				}
			}
			
			// locate top score
			int bestScore = 0;
			for (Player p : players)
			{
				if (bestScore < p.getWins())
					bestScore = p.getWins();
			}
			
			
			System.out.println();
			// print statistics
			for (Player p : players)
			{
				System.out.printf("Overall results for %s: \n", p.getName());
				System.out.printf("\t> %s <-- %s's guesses\n", p.getGuessHistory(), p.getName());
				System.out.printf("\t> %s <-- Card reveals\n", deck.getHistory());
				System.out.printf("\t> %s <-- wins \n", p.getWinHistory(deck), p.getName());
				System.out.printf("\t> %s's win percentage: %.2f%% \n", p.getName(), (double) p.getWins() / round * 100);
				if (p.getWins() == bestScore)
					System.out.println("\t> WINNER!");

			}
			
			// play again?
			System.out.print("\nWould you like to [P] play again or [Q] quit?  [P / Q] : ");
			input = kbd.nextLine().toLowerCase().charAt(0);
			while (input != 'q' && input != 'p')
			{
				System.out.print("Invalid input -- enter [P] to play again or [Q] to quit : ");
				input = kbd.nextLine().toLowerCase().charAt(0);
			}
			
			if (input == 'p')
			{
				players.clear();
				deck.clear();
				round = 0;
			}
			else
			{
				break;
			}
		}
	}
}