package assignment4Backgammon;

import java.util.Random;

/**
 * A class representing a Backgammon Board
 * 
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen 12343046
 * @version 1.01, 4 APR 2014
 * @see assignment4Backgammon;
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
	private final static String bottomOfBoard = "12---++---++---++---++---07   BAR   06---++---++---++---++---01   OFF\n";

	/**
	 * This four element array holds the information for the four dice in the
	 * Backgammon game. The four dice help to represent doubles, if not double
	 * the last two will be set to zero.
	 */
	private int[] dice = new int[4];
	
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
	public String getValueAtPosition(int index)
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
			if(d == 0) {}
			else count++;
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
		if (off[playerSymbol] == 15)
		{
			int opponent = (playerSymbol == PLAYER1) ? PLAYER2 : PLAYER1;
			return checkLastOccurence(opponent) - 1;
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
				if (dice[index_2] != 0)
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
	 * belonging to a player. This will allow us to check for Single, Gammon or
	 * Backgammon
	 * 
	 * @param player
	 *            The player that is to be checked
	 * @return The index of the last occurrence
	 */
	public int checkLastOccurence(int player)
	{
		if (player == PLAYER1)
		{
			for (int index = 0; index < 24; index++)
			{
				if (positions[index].charAt(0) == PLAYER1_SYMBOL)
				{
					return index + 1;
				}
			}
		}
		else
		{
			for (int index = 23; index >= 0; index--)
			{
				if (positions[index].charAt(0) == PLAYER2_SYMBOL)
				{
					return index + 1;
				}
			}
		}
		return 0;
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
	
	/**
	 * This function return the value in off
	 * 
	 * @param playerSymbol
	 *            The current player
	 * @return The value of the off
	 */
	public int returnOff(int playerSymbol)
	{
		return off[playerSymbol];
	}
	
	/**
	 * This method is used to return a specific dice
	 * 
	 * @param diceNumber
	 *            This is the specified dice
	 * @return The value on the dice
	 */
	public int returnDice(int diceNumber)
	{
		return dice[diceNumber];
	}
	
	/**
	 * This method allows you to edit a dice
	 * 
	 * @param input
	 *            The new value for the dice
	 * @param index
	 *            The index of the dice
	 */
	public void editDice(int input, int index)
	{
		dice[index] = input;
		return;
	}
	
	/**
	 * This method is used to return the value ont he bar for a player
	 * 
	 * @param playerSymbol
	 *            This is the current player
	 * @return The value on the bar for that player
	 */
	public int returnBar(int playerSymbol)
	{
		return bar[playerSymbol];
	}
	
	/**
	 * Main handler for finding all possible moves
	 * 
	 * @param player
	 * 			Current player
	 * @return
	 * 			String array of all moves
	 */
	public String[] findAllPlays(int player)
	{
		int[] diceCombo1 = new int[numberOfDice()];
		int[] diceCombo2 = new int[2];
		diceCombo1[0] = dice[0];
		diceCombo1[1] = dice[1];
		if(diceCombo1.length == 4)
		{
			diceCombo1[2] = dice[2];
			diceCombo1[3] = dice[3];
		}
		if(numberOfDice() == 2)
		{
			diceCombo2[0] = dice[1];
			diceCombo2[1] = dice[0];
		}
		
		if(player == PLAYER2)
		{
			diceCombo1 = minusDice(diceCombo1);
			diceCombo2 = minusDice(diceCombo2);
		}
		
		String[] locations = getLocations(player);
		
		String[] moves1 = movesForDice(player, diceCombo1, locations);
		String[] moves2 = new String[moves1.length + 10];
		if(numberOfDice() == 2)
		{
			moves2 = movesForDice(player, diceCombo2, locations);
		}
		
		String[] moves = removeNulls(mergeArrays(moves1, moves2));
		moves = removeNulls(allowForBar(moves, player));
		
		/**
		 * STILL NEED TO IMPLEMENT:
		 * 
		 * 1.	If you can only move one checker, make sure only higher dice is used
		 * 		if possible.
		 * 
		 */
		
		return moves;
	}
	
	/**
	 * Recursive function to find all moves.
	 * 
	 * @param player
	 * 			Current player
	 * @param diceCombo
	 * 			Dice this time
	 * @param locations
	 * 			Locations of players pieces
	 * @return
	 * 			Array of string moves
	 */
	private String[] movesForDice(int player, int[] diceCombo, String[] locations)
	{		
		String[] array1 = getSingleMoves(player, diceCombo, locations);
		
		String[] moves = new String[10];
		
		if(diceCombo.length == 1)
		{
			return array1;
		}
		else
		{
			for(String move : array1)
			{
				String[] updatedLocations = updateLocations(player, locations, move);
				String[] tempArray1 = movesForDice(player, cdr(diceCombo), updatedLocations);
				String[] tempArray2 = new String[tempArray1.length];
				
				for(int i = 0; i < tempArray2.length; i++)
				{
					if(i == 0)
					{
						tempArray2[i] = move;
						tempArray2[i] += " " + tempArray1[i];
					}
					else if(i > 0 && !tempArray1[i].equals(tempArray1[i - 1]))
					{
						tempArray2[i] = move;
						tempArray2[i] += " " + tempArray1[i];
					}
				}
				
				moves = mergeArrays(tempArray2, moves);
			}
			
			return removeNulls(moves);
		}
	}
	
	/**
	 * Uses the first dice in dice roll to get all possible moves with that dice
	 * from the players current locations
	 * 
	 * @param player
	 * 			Current Player
	 * @param localDice
	 * 			Dice available, only the first is used here
	 * @param stringLocations
	 * 			Locations of players checkers in string form.
	 * @return
	 * 			String array of the possible moves
	 */
	private String[] getSingleMoves(int player, int[] localDice, String[] stringLocations)
	{
		char playerSymbol = (player == PLAYER1) ? PLAYER1_SYMBOL : PLAYER2_SYMBOL;
		
		String[] possibleSinglePlays = new String[20];
		int possiblePlayCount = 0;
		
		int[] checkerLocations = new int[stringLocations.length];	
		for(int i = 0; i < stringLocations.length; i++)
		{
			int spaceIndex = stringLocations[i].indexOf(" ");
			if(stringLocations[i].substring(0, spaceIndex).equals("bar"))
			{
				checkerLocations[i] = (player == PLAYER1) ? -1 : 24;
			}
			else{
				checkerLocations[i] = Integer.parseInt(stringLocations[i].substring(0, spaceIndex));
			}
		}
		
		for(int location : checkerLocations)
		{
			if(location == -1 || location == 24)
			{
				if(positions[location + localDice[0]].equals(EMPTY_SPACE_SYMBOL) || positions[location + localDice[0]].charAt(1) == '1' || positions[location + localDice[0]].charAt(0) == playerSymbol)
				{
					possibleSinglePlays[possiblePlayCount] = "bar-" + Math.abs(localDice[0]);
					possiblePlayCount++;
				}
			}
			else if(location + localDice[0] >= 0 && location + localDice[0] < 24)
			{
				if(positions[location + localDice[0]].equals(EMPTY_SPACE_SYMBOL) || positions[location + localDice[0]].charAt(1) == '1' || positions[location + localDice[0]].charAt(0) == playerSymbol)
				{
					if(possiblePlayCount > 0 && possibleSinglePlays[possiblePlayCount - 1].equals(location + "-" + Math.abs(localDice[0]))) continue; /** This makes sure we don't duplicate moves */
					possibleSinglePlays[possiblePlayCount] = location + "-" + Math.abs(localDice[0]);
					possiblePlayCount++;
				}
			}
			else if(location + localDice[0] < 0 && location + localDice[0] >= 24)
			{
				boolean bearOff = true;
				
				for(int i = 0; i < stringLocations.length; i++)
				{
					int space = stringLocations[i].indexOf(" ");
					if(stringLocations[i].substring(0, space).equals("bar"))
					{
						bearOff = false;
						break;
					}
					else if(player == PLAYER1 && Integer.parseInt(stringLocations[i].substring(0, space)) < 18)
					{
						bearOff = false;
						break;
					}
					else if(player == PLAYER2 && Integer.parseInt(stringLocations[i].substring(0, space)) > 5)
					{
						bearOff = false;
						break;
					}
				}
				
				if(bearOff)
				{
					if(possiblePlayCount > 0 && possibleSinglePlays[possiblePlayCount - 1].equals(location + "-" + Math.abs(localDice[0]))) continue; /** This makes sure we don't duplicate moves */
					possibleSinglePlays[possiblePlayCount] = location + "-" + Math.abs(localDice[0]);
					possiblePlayCount++;
				}
			}
		}
		
		return removeNulls(possibleSinglePlays);
	}
	
	/**
	 * Removes all null entries from end of string array
	 * 
	 * @param array
	 * 			Array to remove nulls from
	 * @return
	 * 			Array without nulls
	 */
	private String[] removeNulls(String[] array)
	{
		int i = 0, j = 0;
		while(i < array.length)
		{
			if(array[i]!=null)
			{
				j++;
			}
			i++;
		}
		
		String[] noNullArray = new String[j];
		i = 0;
		j = 0;
		while(i < array.length)
		{
			if(array[i]!=null)
			{
				noNullArray[j] = array[i];
				j++;
			}
			i++;
		}
		
		return noNullArray;
	}
	
	/**
	 * Like in Scheme, returns all elements of array but the first.
	 * This is used for the recursion, the first dice is used every time
	 * so we don't include the first dice on the next recursion.
	 * 
	 * @param array
	 * 			Dice array
	 * @return
	 * 			Array without first element
	 */
	private int[] cdr(int[] array)
	{
		int[] cdrArray = new int[array.length - 1];
		for(int i = 0; i < cdrArray.length; i++)
		{
			cdrArray[i] = array[i + 1];
		}
		return cdrArray;
	}
	
	/**
	 * Merges arrays from different dice combinations in calling of movesForDice method
	 * 
	 * @param array
	 * 		First array
	 * @param array2
	 * 		Second array
	 * @return
	 * 		Merged array
	 */
	private String[] mergeArrays(String[] array, String[] array2)
	{
		if(array2.length == 0) return array;
		int i = 0;
		while(array2[i] != null)
		{
			i++;
			if(i == array2.length) array2 = expandArray(array2);
		}
		for(int j = 0; j < array.length; j++)
		{
			if(i == array2.length) array2 = expandArray(array2);
			array2[i] = array[j];
			i++;
		}
		return array2;
	}
	
	/**
	 * If an array is full, it is copied into a new array with a bigger size
	 * 
	 * @param array
	 * 		Array to be expanded
	 * @return
	 * 		Expanded array
	 */
	private String[] expandArray(String[] array)
	{
		String[] largerArray = new String[array.length * 2];
		for(int i = 0; i < array.length; i++)
		{
			largerArray[i] = array[i];
		}
		return largerArray;
	}
	
	/**
	 * Returns an int array of the indexes that contain pips belonging to player
	 * If the returned array contains -1, this player has a pip on the bar
	 * 
	 * Locations are in string form so that the first number is a location occupied,
	 * then a space and then the second number is the number of checkers in "fake"
	 * gameplay
	 * 
	 * @param player
	 * 			Player whose pips we are checking
	 * @return
	 * 			Array of indexes with this players pips.
	 */
	private String[] getLocations(int player)
	{
		/** null means no checker at this index. Index 0 for bar */
		char playerSymbol = (player == PLAYER1) ? PLAYER1_SYMBOL : PLAYER2_SYMBOL;
		String[] locations = new String[25];
			
		for(int i = 0; i < positions.length; i++) 
		{
			if(positions[i].charAt(0) == playerSymbol) 
			{
				locations[i] = i + " " + positions[i].charAt(1);
			}
		}
		
		/** Remove locations with no checkers */
		int size = 0;
		for(String location : locations) 
		{
			if(location != null) size++;
		}
		
		if(bar[player] != 0) size++;
		
		int j = 0;
		String[] occupiedLocations = new String[size];
		for(int i = 0; i < occupiedLocations.length; i++)
		{
			while(j < locations.length && locations[j] == null) j++;
			if(j == locations.length) break;
			occupiedLocations[i] = locations[j];
			j++;
		}
		if(bar[player] != 0)
		{
			occupiedLocations[size - 1] = "bar " + bar[player];
		}
		
		return occupiedLocations;
	}
	
	/**
	 * Updates "fake" locations after hypothetical move so we know that if the first
	 * move was from a space with one checker left, the second move can't be from
	 * the same space
	 * 
	 * @param player
	 * 			Current player
	 * @param previousLocations
	 * 			Previous locations, these are manipulated and then returned
	 * @param move
	 * 			Move we are examining, our changes are due to the contents
	 * 			of the move
	 * @return
	 * 			Updated locations after "hypothetical" move
	 */
	private String[] updateLocations(int player, String[] previousLocations, String move)
	{
		int spaceBefore = move.lastIndexOf(" ");
		spaceBefore++;
		int hyphonIndex = move.lastIndexOf("-");
		
		if(move.substring(spaceBefore, hyphonIndex).equals("bar"))
		{
			previousLocations = handleBar(player, previousLocations, move);
			return previousLocations;
		}
		
		int fromPosition = Integer.parseInt(move.substring(spaceBefore, hyphonIndex));
		
		int spacesToMove = (player == PLAYER1) ? Integer.parseInt(move.substring(hyphonIndex + 1)) : Integer.parseInt(move.substring(hyphonIndex + 1)) * -1;
		if(!contains(previousLocations, fromPosition + spacesToMove))
		{
			previousLocations = add(previousLocations, fromPosition + spacesToMove);
		}
		else
		{
			for(int i = 0; i < previousLocations.length; i++)
			{
				int spaceIndex2 = previousLocations[i].indexOf(" ");
				if(previousLocations[i].substring(0, spaceIndex2).equals(String.valueOf(fromPosition + spacesToMove)));
				{
					int checkers = Integer.parseInt(previousLocations[i].substring(spaceIndex2 + 1));
					previousLocations[i] = (fromPosition + spacesToMove) + " " + (checkers + 1);
				}
			}
		}
		for(int i = 0; i < previousLocations.length; i++)
		{
			int spaceIndex3 = previousLocations[i].indexOf(" ");
			
			if((previousLocations[i].substring(0, spaceIndex3)).equals("bar")) continue; 
			else if(Integer.parseInt(previousLocations[i].substring(0, spaceIndex3)) == fromPosition)
			{
				String maintain = previousLocations[i].substring(0, spaceIndex3 + 1);
				int checkers = Integer.parseInt(previousLocations[i].substring(spaceIndex3 + 1));
				
				if(checkers == 1)
				{
					previousLocations = remove(previousLocations, i);
				}
				else
				{
					previousLocations[i] = maintain + (checkers - 1);
				}
			}
		}
		
		for(int i = 0; i < previousLocations.length; i++)
		{
			int spaceIndex4 = previousLocations[i].indexOf(" ");
			if((previousLocations[i].substring(0, spaceIndex4)).equals("bar")) continue; 
			int newPosition = Integer.parseInt(previousLocations[i].substring(0, spaceIndex4));
			
			if(newPosition < 0 || newPosition > 23)
			{
				previousLocations = remove(previousLocations, i);
			}
		}
		
		return previousLocations;
	}
	
	/**
	 * Checks if a certain index is already in our locations array
	 * 
	 * @param locations
	 * 		Locations that our player is in
	 * @param movingTo
	 * 		Location that we want to see if is in locations already
	 * @return
	 */
	public boolean contains(String[] locations, int movingTo)
	{
		for(String location : locations)
		{
			int spaceIndex = location.indexOf(" ");
			if(location.substring(0, spaceIndex).equals("bar")) continue;
			if(movingTo == Integer.parseInt(location.substring(0, spaceIndex)))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds an element to the array
	 * 
	 * @param array
	 * 		Array
	 * @param elem
	 * 		Elem to be added
	 * @return
	 * 		New array with element added
	 */
	public String[] add(String[] array, int elem)
	{
		int i = 0;
		String[] newArray = new String[array.length + 1];
		
		while(i < array.length)
		{
			newArray[i] = array[i];
			i++;
		}
		
		newArray[i] = elem + " " + "1";
		return newArray;
	}
	
	/**
	 * Remove an element from array
	 * 
	 * @param array
	 * 		Array
	 * @param index
	 * 		Index to remove
	 * @return
	 * 		New array without element
	 */
	public String[] remove(String[] array, int index)
	{
		for(int i = index; i < array.length - 1; i++)
		{
			array[i] = array[i + 1];
		}
		String[] newArray = new String[array.length - 1];
		for(int i = 0; i < newArray.length; i++)
		{
			newArray[i] = array[i];
		}
		
		return newArray;
	}
	
	/**
	 * For updating locations, this manages the bar entry in our locations monitor array
	 * 
	 * @param player
	 * 			Current player to deal with
	 * @param locations
	 * 			Locations this player occupies under certain "fake" move conditions
	 * @param move
	 * 			Move we are dealing with
	 * @return
	 * 			Locations array that has been updated
	 */
	private String[] handleBar(int player, String[] locations, String move)
	{
		for(int i = 0; i < locations.length; i++)
		{
			int hyphon = move.indexOf("-");
			if(locations[i].substring(0, hyphon).equals("bar"))
			{
				int spacesToMove = (player == PLAYER1) ? Integer.parseInt(move.substring(hyphon + 1)) : 24 - (Integer.parseInt(move.substring(hyphon + 1)) * -1);
				int space = locations[i].indexOf(" ");
				int checkers = Integer.parseInt(locations[i].substring(space + 1));
				
				if(checkers == 1) 
				{
					locations = remove(locations, i);
				}
				else 
				{
					locations[i] = "bar " + (checkers - 1);
				}
				
				if(contains(locations, spacesToMove))
				{
					for(int j = 0; j < locations.length; j++)
					{
						int space2 = locations[j].indexOf(" ");
						if((Integer.parseInt(locations[j].substring(0, space2))) == spacesToMove)
						{
							int checkers2 = Integer.parseInt(locations[j].substring(space2 + 1));
							locations[j] = spacesToMove + " " + (checkers2 + 1);
							return locations;
						}
					}
				}
				else
				{
					locations = add(locations, spacesToMove);
					return locations;
				}
			}
		}
		return locations;
	}
	
	/**
	 * Makes the dice negative, for player2. Used when generating all moves.
	 * 
	 * @param diceCombo
	 * 			Dice
	 * @return
	 * 			All dice values but negative
	 */
	private int[] minusDice(int[] diceCombo)
	{
		int[] newDice = new int[diceCombo.length];
		for(int i = 0; i < newDice.length; i++)
		{
			newDice[i] = diceCombo[i] * -1;
		}
		return newDice;
	}
	
	/**
	 * This method checks if player is on bar and removes all options that do
	 * not move the player off of the bar
	 * 
	 * @param moves
	 *            The array of possible moves
	 * @param player
	 *            The current player
	 * @return
	 */
	String[] allowForBar(String[] moves, int player)
	{
		int count = 0;
		for (String temp : moves)
		{
			int bar = this.bar[player];
			for (int index = 0; index < numberOfDice(); index++)
			{
				if (bar > 0)
				{
					if (!("bar".equals(temp.substring(0, temp.indexOf('-')))))
					{
						moves[count] = null;
						break;
					}
				}
				if (!temp.contains(" "))
				{
					break;
				}
				temp = temp.substring(temp.indexOf(" ") + 2);
				bar--;
			}
			count++;
		}

		return moves;
	}
	
	/**
	 * 
	 * @param location
	 * @param playerSymbol
	 * @return
	 */
	public int winner(int location, int playerSymbol) {
		String winType = "";
		
		char playerChar = (playerSymbol == PLAYER1) ? PLAYER1_SYMBOL : PLAYER2_SYMBOL;
		
		if(playerSymbol == Board.PLAYER1) 
		{
			if(location < 6) winType = "Single";
			else if(location >= 6 && location < 18) winType = "Gammmon";
			else winType = "Backgammon";
			
			System.out.println();
			this.printBoard(playerSymbol);
			System.out.println("Congratulations, Player " + playerChar);
			System.out.println("You have won with a " + winType);
			
			return -3;
		}
		else 
		{
			if(location >= 18) winType = "Single";
			else if(location >= 6 && location < 18) winType = "Gammmon";
			else winType = "Backgammon";
			
			System.out.println();
			this.printBoard(playerSymbol);
			System.out.println("Congratulations, Player " + playerChar);
			System.out.println("You have won with a " + winType);
			
			return -3;
		}
	}
}
