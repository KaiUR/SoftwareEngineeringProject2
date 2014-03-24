package assignment3Backgammon;

import java.util.Scanner;

/**
 * A class representing a Human Player in Backgammon
 * 
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen 12343046
 * @version 1.01, 22 MAR 2014
 * @see assignment3Backgammon;
 * 
 */
public class HumanPlayer {
	
	/**
	 * Field to hold the player symbol, so we know which checkers to move.
	 */
	private int playerSymbol;
	
	/**
	 * This saves the current state of the board.
	 */
	private Board	board;
	
	/**
	 * 
	 */
	private boolean	bearOff	= false;
	
	/**
	 * This scanner is for standard input.
	 */
	private Scanner	in	= new Scanner(System.in);
	
	/**
	 * This is the constructor
	 * 
	 * @param team 
	 * 				Pass in the character of the team which is then assigned
	 * 				to field playerSymbol. This is to know which checkers to
	 * 				move for this HumanPlayer.
	 * 		
	 */
	public HumanPlayer(int team, Board playerBoard) {
		playerSymbol = team;
		board = playerBoard;
	}
	
	/**
	 * Function to inform us of this players symbol.
	 * 
	 * @return		'O' for player1, 'X' for player2 or ' ' for any other 'invalid' input
	 */
	public char toPlayerChar() 
	{
		if(playerSymbol == Board.PLAYER1) 
		{
			return Board.PLAYER1_SYMBOL;
		}
		else if(playerSymbol == 1)
		{
			return Board.PLAYER2_SYMBOL;
		}
		else return ' ';
	}
	
	/**
	 * Function called on HumanPlayer to make a move. This function calls three sub
	 * functions. 
	 * If processMoves returns true, all checks have been passed and 
	 * performMoves is called to perform the moves on the board.
	 * If processMoves returns false, the whole function makeMove is restarted.
	 * 
	 */
	public int makeMove() {
		boolean passedChecks = false;
		
		board.printBoard(playerSymbol);
		int numberOfDice = board.numberOfDice();
		
		while(!passedChecks) 
		{
			System.out.println("Current Player: " + toPlayerChar());
			System.out.print("Dice: ");
			board.printDice(4);
			System.out.print("Enter a move: ");
			String move = in.nextLine();
			/*
			 * read in move
			 */
			String[] moves = move.split("\\s");		
			
			for(String m : moves) {
				if(m.equalsIgnoreCase("quit")) {
					return -2;
				}
			}
			
			passedChecks = processMove(moves);
			
			if (moves.length != board.numberOfDice()
				&& !checkForBearOffMoves(moves.length))
			{
				System.out
						.println("Error - Please enter a valid number of moves");
				passedChecks = false;
			}
		
			if(passedChecks) 
			{
				passedChecks = performMove(moves, numberOfDice);
			}
			if(!passedChecks)
			{
				board.printBoard(playerSymbol);
			}
			
		}
		
		int checkForWin = board.doPlay(playerSymbol);
		if(checkForWin == -1) {
			return 0;
		}
		else return winner(checkForWin);
	}
	
	/**
	 * Function to print winner message depending on player and type of win
	 * 
	 * @param location furthest back pip
	 * @return -3 to inform game to end
	 */
	private int winner(int location) {
		String winType = "";
		if(playerSymbol == Board.PLAYER1) 
		{
			if(location < 6) winType = "Single";
			else if(location >= 6 && location < 18) winType = "Gammmon";
			else winType = "Backgammon";
			
			System.out.println();
			board.printBoard(playerSymbol);
			System.out.println("Congratulations, Player " + toPlayerChar());
			System.out.println("You have won with a " + winType);
			
			return -3;
		}
		else 
		{
			if(location >= 18) winType = "Single";
			else if(location >= 6 && location < 18) winType = "Gammmon";
			else winType = "Backgammon";
			
			System.out.println();
			board.printBoard(playerSymbol);
			System.out.println("Congratulations, Player " + toPlayerChar());
			System.out.println("You have won with a " + winType);
			
			return -3;
		}
	}
	
	/**
	 * Performs checks on moves.
	 * 
	 * @param moves 
	 * 				String array of users moves
	 * @return
	 * 				A boolean indicating whether the moves failed or passed the checks.
	 */
	private boolean processMove(String[] moves) {
		
		/*
		 * Check no more than 4 moves entered.
		 */
		if(moves.length > board.numberOfDice()) {	
			System.out.println("Error: Please enter a valid number of moves.");
			return false;
		}
		/*
		 * check if no moves entered.
		 */
		else if((moves.length == 1) && (moves[0].equals(""))) {	
			System.out.println("Error: Please enter a valid number of moves.");
			return false;
		}

		boolean passedSyntax = checkSyntax(moves);
		if(!passedSyntax)
		{
			System.out.println("Error: Please enter a valid move.\n");
		}

		return true && passedSyntax;
	}
	
	/**
	 *  Method checks if the syntax of the moves arguments is correct and in the format *-* or **-*
	 *  Where an asterisk represents a number
	 * 
	 * @param moves			Array of strings to check syntax on.
	 * 				Either 2 or 4 Strings, depending on doubles or not.
	 * 
	 * @return			returns true if the syntax is correct.
	 * 				returns false otherwise.
	 */
	private boolean checkSyntax(String[] moves)
	{
		String dice_input = "", location_input = "";
		for(int i = 0; i<moves.length; i++)
		{
			try
			{
				if(moves[i].equalsIgnoreCase("pass"))
				{
					return true;
				}
				else if (moves[i].charAt(1) == '-')
				{
					location_input = moves[i].substring(0, 1);
					dice_input = moves[i].substring(2, 3);
				}
				else if (moves[i].charAt(2) == '-')
				{
					location_input = moves[i].substring(0, 2);
					dice_input = moves[i].substring(3, 4);
				}
				else if (moves[i].charAt(3) == '-')
				{
					if (moves[0].substring(0, 3).equals("bar"))
					{
						location_input = "bar";
						dice_input = moves[i].substring(4);
					}
				}
				else
				{
					return false;
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
				return false;
			}
			try
			{
				int dice = Integer.parseInt(dice_input);
				if(location_input.equals("bar"))
				{
					if(dice >= 1 && dice <= 6) {
						continue;
					}
					else return false;
				}
				
				int location = Integer.parseInt(location_input);
				if((dice<1||dice>6)||(location<1||location>24))
				{
					return false;
				}
			}
			catch (NumberFormatException e)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Perform users moves on the board
	 * 
	 * @param moves
	 * 			String array of users moves, these are valid.
	 */
	private boolean performMove(String[] moves, int numberOfDice) {
		int[] positions = new int[numberOfDice + 1];
		int[] spacesToMove = new int[numberOfDice + 1];
		
		for(int index = 0; index < moves.length; index++) {
			if(moves[index].equalsIgnoreCase("pass"))
			{
				return true;
			}
			int hyphon = moves[index].indexOf("-");
			if (moves[index].substring(0, 3).equals("bar"))
			{
				positions[index] = -1;
			} 
			else
			{
				positions[index] = Integer.parseInt(moves[index].substring(0,
						hyphon));
			}
			spacesToMove[index] = Integer.parseInt(moves[index].substring(hyphon + 1));
			if (!errorChecking(positions[index], spacesToMove[index]))
			{
				return false;
			}
			if (bearOff)
			{
				spacesToMove[index] = -1;
				bearOff = false;
			}
			for (int index_2 = 0; index_2 < 4; index_2++)
			{
				if (spacesToMove[index] == board.dice[index_2])
				{
					board.dice[index_2] = 0;
					break;
				}
			}
			board.makeAMove(positions[index], spacesToMove[index], playerSymbol);
		}
		return true;
	}
	
	/**
	 * This method preforms certain checks on the moves to see if they are valid,
	 * if they are valid then it will return true, otherwise it will return false
	 * 
	 * @param position The starting positon
	 * @param move The spaces to move
	 * @return True is valid, false if not
	 */
	private boolean errorChecking(int position, int move)
	{
		position --;

		/* checks if checker is on bar */
		if (board.bar[playerSymbol] != 0 && position != -2)
		{
			System.out.println("Error - You need to move off of the bar first");
			return false;
		}

		/* Checks if checker exists */
		if (playerSymbol == Board.PLAYER1 && position != -2)
		{
			if (board.positions[position].charAt(0) != Board.PLAYER1_SYMBOL)
			{
				System.out.println("Error - There is no valid checker here");
				return false;
			}
		}
		else if (playerSymbol == Board.PLAYER2&& position != -2)
		{
			if (board.positions[position].charAt(0) != Board.PLAYER2_SYMBOL)
			{
				System.out.println("Error - There is no valid checker here");
				return false;
			}
		}

		/*
		 * checks if ending block is enemy
		 * 
		 * -1 is returned from playerAtPosition() if a blank space is found
		 */
		if (position == -2)
		{
			if (playerSymbol == Board.PLAYER1)
			{
				position = -1;
			}
			else
			{
				position = 24;
			}
		}
		 
		int temp_move = move;
		if (playerSymbol == Board.PLAYER2)
		{
			temp_move = move * -1;
		}
		if (temp_move + position > -1 && temp_move + position < 24)
		{
			if (board.playerAtPosition(position + temp_move) != playerSymbol
					&& board.playerAtPosition(position + temp_move) != -1
					&& board.checkNotWall(position + temp_move) != true)
			{
				System.out
						.println("Error - You can not move on to an enemy checker");
				return false;
			}
		}

		/*
		 * checks if move matches a dice
		 */
		boolean check = false;
		for (int index = 0; index < 4; index++)
		{
			if (board.dice[index] == move)
			{
				check = true;
			}
		}
		if (!check)
		{
			System.out.println("Error - You must use a number from the dice");
			return false;
		}
		
		/*
		 * No need to check to move off if moving from the bar
		 */
		if (position == -2)
		{
			return true;
		}

		/*
		 * Checks if checker is allowed to be moved off with higher dice roll
		 */
		@SuppressWarnings("unused")
		boolean error = false;
		if (playerSymbol == Board.PLAYER1)
		{
			if (position + move > 23)
			{
				if (board.checkLastOccurence(playerSymbol) < position && position + move != 24)
				{
					error = true;
				}
				bearOff = true;
			}

		}
		else if (playerSymbol == Board.PLAYER2)
		{
			if (position - move < 0)
			{
				if (board.checkLastOccurence(playerSymbol) > position && position - move != 0)
				{
					error = true;
				}
				bearOff = true;
			}

		}

		/*
		 * Checks if bearing off is allowed
		 */
		if (bearOff)
		{
			if (playerSymbol == Board.PLAYER1)
			{
				if (board.checkLastOccurence(playerSymbol) <= 18)
				{
					System.out
							.println("Error - You must be in your finishing section with all checkers to bear off");
					bearOff = false;
					return false;
				}
			}
			else if (playerSymbol == Board.PLAYER2)
			{
				if (board.checkLastOccurence(playerSymbol) >= 7)
				{
					System.out
							.println("Error - You must be in your finishing section with all checkers to bear off");
					bearOff = false;
					return false;
				}
			}
			else if (error = true)
			{
				System.out.println("Error - You can not move this checker");
				bearOff = false;
				return false;
			}
		}

		return true;
	}
	
	/**
	 * This method is used to check if there is a number of moves entered less
	 * than the dice, but there is only that number of checkers left, ie. there
	 * is only one checker left, this method then skips the error message be
	 * returning false as that is valid.
	 * 
	 * @param movesLength
	 *            The amount of moves entered by the player
	 * @return True if it is a valid number of moves
	 */
	private boolean checkForBearOffMoves(int movesLength)
	{
		int check = -1 * board.off[playerSymbol] - 15;

		switch (check)
		{
			case 1:
				if (movesLength != 1)
				{
					return false;
				}
				break;

			case 2:
				if (movesLength != 2)
				{
					return false;
				}
				break;

			case 3:
				if (movesLength != 3)
				{
					return false;
				}
				break;

			default:
				return true;
		}

		return true;
	}
	
	/**
	 * This is used to close the scanner used in this class
	 */
	public void closeScanner()
	{
		in.close();
	}
	
}
