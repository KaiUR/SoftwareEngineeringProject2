package Assignment1Backgammon;

import java.util.Random;

/**
 * A class representing a Backgammon Board
 * 
 * @author Laurence Quinn, Ciarán O'Niell, Kai-Uwe Rathjen
 * @version 0.05, 26 JAN 2014
 * @see Assignment1Backgammon
 * 
 */
public class Board
{
	/**
	 * An array of strings to hold the positions of the different checkers on
	 * the board, O representing one player and X representing the other. The
	 * array is initialized to the default starting positions by the
	 * constructor.
	 */
	private String[] positions;

	/**
	 * This is to keep track if a checker is on the bar or not.
	 */
	private int[] bar = new int[2];

	/**
	 * This variable controls the checkers off the board, 0 index for one player
	 * and 1 for other
	 */
	private int[] off = new int[2];

	/**
	 * A variable to hold information for the top section of the board,
	 * this is for the headings of the board only.
	 */
	private final static String topOfBoard = "13---++---++---++---++---18   BAR   19---++---++---++---++---24   OFF\n";

	/**
	 * A variable to hold information for the bottom section of the board,
	 * this is for the headings of the board only.
	 */
	private final static String bottomOfBoard = "12---++---++---++---++---07   BAR   06---++---++---++---++---01   OFF\n\n";

	/**
	 * This two element array holds the information for the four dice in the
	 * Backgammon game. The four dice help to represent doubles, if not double
	 * the last two will be set to zero.
	 */
	private int[] dice = new int[4];
	
	/**
	 * This defines the index of player 1
	 */
	public final static int PLAYER1 = 0;
	
	/**
	 * This defines the index of player 2
	 */
	public final static int PLAYER2 = 1;
	
	/**
	 * This defines the symbol of player 1, must be only one letter or didgt
	 */
	private final static char PLAYER1_SYMBOL = 'O';
	
	/**
	 * This defines the symbol of player 2, must be only one letter or didgt
	 */
	private final static char PLAYER2_SYMBOL = 'X';
	
	/**
	 * This defines the symbol for empty spaces on the board
	 * 
	 */
	private final static String EMPTY_SPACE_SYMBOL = "||";

	/**
	 * Constructor method for class Board This method prints out the Board for
	 * the initial time, The method resetBoard is called.
	 * 
	 * @see #resetBoard()
	 */
	Board()
	{
		resetBoard();
	}

	/**
	 * This method is used to return the value at a position.
	 * 
	 * @param index
	 *            This is the input value to this method, it represents the
	 *            index, the exact position in the positions array
	 * @return The value at the position that was requested
	 */
	public String getValueatPossition(int index)
	{
		return positions[index];
	}

	/**
	 * This Method returns a string containing all of the board data.
	 * 
	 * @return A string with all of the positions of the checkers on the Board
	 */
	public String[] getPositions()
	{
		return positions;
	}

	/**
	 * This method is used to put a checker at a given position. This method can
	 * also be used to delete a checker.
	 * 
	 * @param index
	 *            The position where the checker should be placed
	 * @param elem
	 *            The amount of checkers at this new position.
	 */
	public void setPosition(int index, String elem)
	{
		positions[index] = elem;
		return;
	}

	/**
	 * This method is used to print out the game board
	 * 
	 */
	public void printBoard()
	{

		System.out.print(topOfBoard);

		printCorner(1);

		printBar(PLAYER2);

		printCorner(2);
		
		printOff(PLAYER1);

		printCorner(3);

		printBar(PLAYER1);

		printCorner(4);
		
		printOff(PLAYER2);

		System.out.print(bottomOfBoard);
		
		return;
	}

	/**
	 * This method is used for printing the different sections of the board,
	 * i.e. printing the off table.
	 * 
	 * @param teamoff
	 *            This variable represents which sections is to be printed.
	 */
	private void printOff(int teamoff)
	{
		switch(teamoff)
		{
		case PLAYER1:
			if (off[PLAYER1]!=0)
			{
				System.out.print(PLAYER1_SYMBOL  +"-"+off[PLAYER1]);
			}
			System.out.print("\n\n");
			break;
			
		case PLAYER2:
			if (off[PLAYER2]!=0)
			{
				System.out.print(PLAYER2_SYMBOL + "-"+off[PLAYER2]);
			}
			System.out.print("\n");
			break;
		}
	}
	
	/**
	 * This method is used for printing the different sections of the board,
	 * i.e. The bar.
	 * 
	 * @param teambar
	 *            This variable represents which sections is to be printed.
	 */
	private void printBar(int teambar)
	{
		switch(teambar)
		{
		case PLAYER1:
			if (bar[PLAYER1]!=0)
			{
				System.out.print(PLAYER1_SYMBOL  +"-"+bar[PLAYER1]+"   ");
			}
			else System.out.print("      ");
			break;
			
		case PLAYER2:
			if (bar[PLAYER2]!=0)
			{
				System.out.print(PLAYER2_SYMBOL + "-"+bar[PLAYER2]+"   ");
			}
			else System.out.print("      ");
			break;
		}
	}

	/**
	 * This method is used for printing the different sections of the board,
	 * i.e. corners.
	 * 
	 * @param corner
	 *            This variable represents which sections is to be printed.
	 */
	private void printCorner(int corner)
	{

		int index = 0;

		switch (corner)
		{

		case 1:
			for (index = 12; index < 18; index++)
			{
				System.out.print(positions[index] + "   ");
			}
			break;
		case 2:
			for (index = 18; index < 24; index++)
			{
				System.out.print(positions[index] + "   ");
			}
			break;
		case 3:
			for (index = 11; index > 5; index--)
			{
				System.out.print(positions[index] + "   ");
			}
			break;
		case 4:
			for (index = 5; index >= 0; index--)
			{
				System.out.print(positions[index] + "   ");
			}
			break;
		default:
			break;

		}

	}

	/**
	 * This method is used to reset the board
	 * 
	 */
	public void resetBoard()
	{

		String[] initialBoard =
		{ PLAYER1_SYMBOL + "2", EMPTY_SPACE_SYMBOL, EMPTY_SPACE_SYMBOL, EMPTY_SPACE_SYMBOL, EMPTY_SPACE_SYMBOL,
				PLAYER2_SYMBOL + "5", EMPTY_SPACE_SYMBOL, PLAYER2_SYMBOL + "3", EMPTY_SPACE_SYMBOL,
				EMPTY_SPACE_SYMBOL, EMPTY_SPACE_SYMBOL, PLAYER1_SYMBOL + "5", PLAYER2_SYMBOL + "5",
				EMPTY_SPACE_SYMBOL, EMPTY_SPACE_SYMBOL, EMPTY_SPACE_SYMBOL, PLAYER1_SYMBOL + "3",
				EMPTY_SPACE_SYMBOL, PLAYER1_SYMBOL + "5", EMPTY_SPACE_SYMBOL, EMPTY_SPACE_SYMBOL,
				EMPTY_SPACE_SYMBOL, EMPTY_SPACE_SYMBOL, PLAYER2_SYMBOL + "2" };

		positions = initialBoard;
		bar[PLAYER1] = 0;
		bar[PLAYER2] = 0;
		off[PLAYER1] = 0;
		off[PLAYER2] = 0;

		printBoard();

		return;
	}

	/**
	 * This method is used to roll the dice. It always returns four dice, but if
	 * a double is not rolled then the last two dice are set to zero.
	 * 
	 * @return The values of the dice
	 */
	public int[] diceRoll()
	{

		Random generator = new Random();

		dice[0] = generator.nextInt(6) + 1;
		dice[1] = generator.nextInt(6) + 1;

		if (dice[0] == dice[1])
		{
			dice[2] = dice[0];
			dice[3] = dice[0];
		}
		else
		{
			dice[2] = 0;
			dice[3] = 0;
		}

		return dice;
	}

	/**
	 * This method prints out the dice values depending on the input. If the
	 * input is either 0 or 1 or 2 or 3 then the corresponding dice is printed, if the
	 * input is 4 then both dice are printed
	 * 
	 * @param index
	 *            The variable to control the option, i.e. input
	 */
	public void printDice(int index)
	{
		if (index >= 0 && index <= 3)
			System.out.print(dice[index]);
		else if (index == 4)
		{
			System.out.print(dice[0] + "\t" + dice[1]);
			if(dice[0] == dice[1])
				System.out.print("\t" + dice[2] + "\t" + dice[3]);
		}
		System.out.print("\n");
		return;
	}

	/**
	 * This method is used to move checkers across the board First a function is
	 * called (decrementCounters()) to remove the counters from a position, then
	 * a function is called (incrementCounters()) to place the counters in the
	 * new position.
	 * 
	 * @param position
	 *            This variable is where the checkers is that is to be moved
	 * @param move
	 *            This is the number of spaces that the checkers going to be
	 *            moved
	 * @see #decrementCounters(int)
	 * @see #incrementCounters(int, char)
	 * 
	 */
	public void makeAMove(int position, int move,  int team_modifier)
	{
		position--;
		char team = ' ';
		if (team_modifier == PLAYER1)
			team = PLAYER1_SYMBOL;
		else if (team_modifier == PLAYER2)
			team = PLAYER2_SYMBOL;
		if (position == -1)
		{
			bar[team_modifier]--;
		}else if (positions[position].charAt(1) == '1')
		{
			positions[position] = EMPTY_SPACE_SYMBOL;
		}
		else
		{
			decrementCounters(position, team);
		}

		incrementCounters(position, move, team);

		return;
	}

	/**
	 * This method removes a checker from the board.
	 * 
	 * @param index
	 *            This keeps track of the position
	 */
	private void decrementCounters(int index, char team)
	{
		int currentInt = (int) positions[index].charAt(1);
		currentInt--;
		char newChar = (char) currentInt;
		positions[index] = "";
		positions[index] = positions[index] + team;
		positions[index] = positions[index] + newChar;
		return;
	}

	/**
	 * This method is used to place the checkers on the board
	 * 
	 * @param index
	 *            This keeps track of the position
	 * @param team
	 *            This is the player that is moving
	 */
	private void incrementCounters(int position, int move, char team)
	{
		int index = position + move;
		if (move == -1)
		{
			if (team == PLAYER1_SYMBOL)

				off[PLAYER1]++;
			else
				off[PLAYER2]++;
		} else if(team == PLAYER2_SYMBOL){index *= -1;}
		else if (positions[index] == EMPTY_SPACE_SYMBOL)
		{
			positions[index] = "";
			positions[index] = positions[index] + team + '1';
		}
		else if (positions[index].charAt(0) == team)
		{
			int currentInt = (int) positions[index].charAt(1);
			currentInt++;
			char newChar = (char) currentInt;
			positions[index] = "";
			positions[index] = positions[index] + team + newChar;
		}
		else if (positions[index].charAt(0) != team && positions[index].charAt(1) == '1')
		{
			positions[index] = "";
			positions[index] = positions[index] + team + '1';
			if (team == PLAYER1_SYMBOL)

				bar[PLAYER1]++;
			else
				bar[PLAYER2]++;
		}
		return;

	}
}
