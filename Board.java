package assignment3Backgammon;

import java.util.Random;

/**
 * A class representing a Backgammon Board
 * 
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen 12343046
 * @version 1.01, 22 MAR 2014
 * @see assignment3Backgammon;
 * 
 */
public class Board
{
	/**
	 * An array of strings to hold the positions of the different checkers on
	 * the board, PLAYER1_SYMBOL representing one player and PLAYER2_SYMBOL representing the other. 
	 * The array is initialized to the default starting positions by the
	 * constructor.
	 */
	public String[] positions;

	/**
	 * This is to keep track if a checker is on the bar or not.
	 */
	public int[] bar = new int[2];

	/**
	 * This variable controls the checkers off the board, 0 index for one player
	 * and 1 for other
	 */
	public int[] off = new int[2];

	/**
	 * A variable to hold information for the top section of the board,
	 * this is for the headings of the board only.
	 */
	private final static String topOfBoard = "13---++---++---++---++---18   BAR   19---++---++---++---++---24   OFF\n";

	/**
	 * A variable to hold information for the bottom section of the board,
	 * this is for the headings of the board only.
	 */
	private final static String bottomOfBoard = "12---++---++---++---++---07   BAR   06---++---++---++---++---01   OFF\n";

	/**
	 * This four element array holds the information for the four dice in the
	 * Backgammon game. The four dice help to represent doubles, if not double
	 * the last two will be set to zero.
	 */
	public int[] dice = new int[4];
	
	/**
	 * This defines the index of player 1. This definition can be used in a different 
	 * class by using Board.PLAYER1
	 */
	public final static int PLAYER1 = 0;
	
	/**
	 * This defines the index of player 2. This definition can be used in a different 
	 * class by using Board.PLAYER2
	 */
	public final static int PLAYER2 = 1;
	
	/**
	 * This defines the symbol of player 1, must be only one letter or digit
	 */
	public final static char PLAYER1_SYMBOL = 'O';
	
	/**
	 * This defines the symbol of player 2, must be only one letter or digit
	 */
	public final static char PLAYER2_SYMBOL = 'X';
	
	/**
	 * This defines the symbol for empty spaces on the board.
	 * 
	 */
	private final static String EMPTY_SPACE_SYMBOL = "||";

	/**
	 * Constructor method for class Board. This method prints out the Board for
	 * the initial time, The method resetBoard is called.
	 * 
	 * @see #resetBoard()
	 */
	Board()
	{
		System.out.println("Welcome to the world of Backgammon! Good Luck!\n");
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
	public void printBoard(int player)
	{

		if(player==PLAYER1)
		{
			System.out.print(topOfBoard);
			printCorner(1);
			printBar(PLAYER2);
			printCorner(2);
			printOff(PLAYER1);
			System.out.print('\n');
			printCorner(3);
			printBar(PLAYER1);
			printCorner(4);
			printOff(PLAYER2);
			System.out.print(bottomOfBoard+'\n');
			
		}
		else
		{
			System.out.print(bottomOfBoard);
			printCorner(3);
			printBar(PLAYER1);
			printCorner(4);
			printOff(PLAYER2);
			System.out.print('\n');
			printCorner(1);
			printBar(PLAYER2);
			printCorner(2);
			printOff(PLAYER1);
			System.out.print(topOfBoard+'\n');
		}
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
			System.out.print("\n");
			break;
			
		case PLAYER2:
			if (off[PLAYER2]!=0)
			{
				System.out.print(PLAYER2_SYMBOL + "-"+off[PLAYER2]);
			}
			System.out.print("\n");
			break;
		}
		return;
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
		return;
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
		return;

	}

	/**
	 * This method is used to reset the board. The Strung positions is initilised using the different
	 * definitions for the Blank spaces, player symbols and the numbers representing the 
	 * amount if checkers. The bar and off varibles are set to 0.
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

		return;
	}

	/**
	 * This method is used to roll the dice. It always returns four dice, but if
	 * a double is not rolled then the last two dice are set to zero.
	 * 
	 * @return The values of the dice
	 */
	public int[] rollDice()
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
	 * Method to check if doubles have been rolled or not
	 * 
	 * @return
	 * 			the number of dice in the current play
	 */
	
	public int numberOfDice() {
		int count = 0;
		for(int d : dice) {
			if(d > 0) count++;
		}
		return count;
	}
	
	/**
	 * 
	 * @param playerSymbol
	 * @return
	 */
	public int doPlay(int playerSymbol)
	{
		if (off[playerSymbol] == 25)
		{
			return locateFurthestPip(playerSymbol);
		}
		return -1;
	}
	
	/**
	 * Function to locate the position of the specified player's furthest back pip
	 * This will allow us to check for Single, Gammon or Backgammon
	 * 
	 * @return the location of the furthest back pip
	 */
	private int locateFurthestPip(int symbol) {
		if(symbol == 0) {
			for(int i = 0; i < 24; i++) {
				if(positions[i].charAt(0) == symbol) {
					return i;
				}
			}
		}
		else {
			for(int i = 23; i >= 0; i--) {
				if(positions[i].charAt(0) == symbol) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * This method prints out the dice values depending on the input. If the
	 * input is either 0 or 1 or 2 or 3 then the corresponding dice is printed, if the
	 * input is 4 then both dice are printed.
	 * 
	 * @param index
	 *            The variable to control the option, i.e. input
	 */
	public void printDice(int index)
	{
		if (index >= 0 && index <= 3)
		{
			System.out.print(dice[index]);
		}
		else if (index == 4)
		{
			for (int index_2 = 0; index_2 < 4; index_2++)
			{
				if (dice[index_2] > 0)
				{
					System.out.print(dice[index_2] + " ");
				}
			}
		}
		System.out.print("\n");
		return;
	}

	/**
	 * This method is used to move checkers across the board First a function is
	 * called (decrementCounters()) to remove the counters from a position, then
	 * a function is called (incrementCounters()) to place the counters in the
	 * new position. If -1 is passed to the position arrgument then this represents
	 * the checker to be on the BAR, and if -1 is passed to the move arrgument the checker
	 * will be moved off the board. If a checker is moved onto a single enemy checker it
	 * will remove this enemy checker and place it onto the Bar.
	 * 
	 * @param position
	 *            This variable is where the checkers is that is to be moved
	 * @param move
	 *            This is the number of spaces that the checkers going to be
	 *            moved
	 * @param team_modifier
	 *            This is the player that is moving
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
		else
			team = PLAYER2_SYMBOL;
		
		/*
		 * moving off the bar
		 */
		if (position == -2)	
		{
			bar[team_modifier]--;
			if (team_modifier == PLAYER1)
			{
				position = -1;
			} else
			{
				position = 24;
			}
		} 
		
		else if (positions[position].charAt(1) == '1')
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
	 * @param team
	 *            This is the player that is moving
	 * 
	 */
	private void decrementCounters(int index, char team)
	{
		int currentInt = (int) positions[index].charAt(1);
		currentInt--;
		char newChar = (char) currentInt;
		setPosition(index, "" + team + newChar);
		return;
	}

	/**
	 * This method is used to place the checkers on the board
	 * 
	 * @param position
	 *            This keeps track of the position
	 * @param team
	 *            This is the player that is moving
	 * @param move
	 *            This is the distance to move
	 */
	private void incrementCounters(int position, int move, char team)
	{
		int index = position + move;
		
		if(team == PLAYER2_SYMBOL)
		{
			index = position + (move * -1);	
			/*
			 * Player 2 moves anti-clockwise around the board
			 */
		}
		
		/*
		 * moving off the board
		 */
		if (move == -1)	
		{
			if (team == PLAYER1_SYMBOL)

				off[PLAYER1]++;
			else
				off[PLAYER2]++;
		}
		else if (positions[index] == EMPTY_SPACE_SYMBOL)
		{
			setPosition(index, "" + team + "1");
		}
		else if (positions[index].charAt(0) == team)
		{
			int currentInt = (int) positions[index].charAt(1);
			currentInt++;
			char newChar = (char) currentInt;
			setPosition(index, "" + team + newChar);
		}
		else if (positions[index].charAt(0) != team && positions[index].charAt(1) == '1')
		{
			setPosition(index, "" + team + "1");
			if (team == PLAYER1_SYMBOL)

				bar[PLAYER2]++;
			else
				bar[PLAYER1]++;
		}
		return;

	}
	
	/**
	 * This method converts the PLAYER_SYMBOL to a number representing the
	 * players
	 * 
	 * @param position
	 *            The position in the positions array
	 * @return The integer representing the player
	 */
	public int playerAtPosition(int position)
	{
		if (positions[position].charAt(0) == PLAYER1_SYMBOL)
		{
			return PLAYER1;
		}
		else if (positions[position].charAt(0) == PLAYER2_SYMBOL)
		{
			return PLAYER2;
		}
		return -1;
	}

	/**
	 * This method is used to check for the last occurrence of a checker
	 * belonging to a player
	 * 
	 * @param player
	 *            The player that is to be checked
	 * @return The index of the last occurrence
	 */
	public int checkLastOccurence(int player)
	{
		int last = 0;
		if (player == PLAYER1)
		{
			for (int index = 0; index < 24; index++)
			{
				if (positions[index].charAt(0) == PLAYER1_SYMBOL)
				{
					last = index;
				}
			}
		}
		else
		{
			for (int index = 23; index >= 0; index--)
			{
				if (positions[index].charAt(0) == PLAYER2_SYMBOL)
				{
					last = index;
				}
			}
		}
		return last + 1;
	}
	
	/**
	 * This method is used to check if a certain position has a wall or not
	 * 
	 * @param position
	 *            The index in the positions array
	 * @return True if wall and false if not
	 */
	public boolean checkNotWall(int position)
	{
		if (positions[position].charAt(1) == '1')
		{
			return true;
		}
		return false;
	}
}
