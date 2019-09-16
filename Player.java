/*
-------------------------------------------------------
Player Class
-------------------------------------------------------
- name : String
- guess : char
- points : int
- wins : int
- history : ArrayList<Character> (holds user's guess history)
- bot : boolean
- ran : Random
-------------------------------------------------------
+ Player (name : String, bot : boolean)
+ addPoint : void
+ subPoints : void
+ guess : void
+ getGuess : char
+ getName : String
+ getPoint : int
+ addWins : void
+ getWins : int
+ getGuessHistory : ArrayList<Character> (returns copy of player's
       current guess history)
+ getGuesString : String
+ getGuessGistory : String
+ getWinHistory : String
-------------------------------------------------------
*/

import java.util.*;

public class Player 
{
	// data
	String name;
	char guess;
	int points = 0;
	int wins = 0;
	ArrayList<Character> history = new ArrayList<Character>();
	boolean bot; // is this player a bot?
	Random ran = new Random();
	
	// constructor
	Player (String name, boolean bot)
	{
		this.name = name;
		this.bot = bot;
	}
	
	// methods
	public void addPoint()
	{
		++points;
		++wins;
	}
	public void subPoint()
	{
		--points;
	}
	public void guess(Scanner kbd)
	{
		if (bot)
		{
			int num = ran.nextInt(2);
			if (num == 0)
				guess = 'B';
			else if (num == 1)
				guess = 'R';		
		}
		else
		{
			char input = '-';
			System.out.printf("%s, what is your guess? [R] red or [B] black: ", this.name);
			input = kbd.nextLine().toUpperCase().charAt(0);
			while (input != 'R' && input != 'B')
			{
				System.out.print("Invalid input -- only enter [R] for red or [B] for black."
						+ "\n < enter > : ");
				input = kbd.nextLine().toUpperCase().charAt(0);
			}			
			guess = input;
		}
		history.add(guess);
	}
	
	public char getGuess()
	{
		return guess;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public int getWins()
	{
		return wins;
	}
	
	public String getGuessString()
	{
		if (guess == 'B')
			return "black";
		else if (guess == 'R')
			return "red";	
		else
			return "ERROR";
	}
	
	public String getGuessHistory()
	{
		String his = "";
		
		for (char c : history)
		{
			his += c;
		}
		return his;	
	}
	
	public String getWinHistory(Deck deck)
	{
		String his = "";
		
		for (int i = 0; i < history.size(); ++i)
		{
			if (history.get(i) == deck.getCardAt(i))
				his += "W";
			else
				his += "-";
		}
		return his;
	}
}