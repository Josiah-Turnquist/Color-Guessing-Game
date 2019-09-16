/*-------------------------------------------------------
Deck
-------------------------------------------------------
- curr : char
- history : ArrayList<Character>
- ran : Random
-------------------------------------------------------
+ getCard : char
+ nextCard : char
+ getHistory : String
+ clear : void
+ getCardAt : char
-------------------------------------------------------
*/

import java.util.*;

public class Deck
{
	// data
	Random ran = new Random();
	ArrayList<Character> history = new ArrayList<Character>();
	char curr;
	
	// constructor
	Deck()
	{
		// empty
	}
	
	// methods
	public char getCard()
	{
		return curr;
	}
	
	public void nextCard()
	{
		int num = ran.nextInt(2);
		if (num == 0)
			curr = 'B';
		else if (num == 1)
			curr = 'R';
		history.add(curr);
	}
	
	public String getHistory()
	{
		String his = "";
		
		for (char c : history)
		{
			his += c;
		}
		return his;
	}
	
	public void clear()
	{
		history.clear();
	}
	public char getCardAt(int i)
	{
		return history.get(i);
	}
}