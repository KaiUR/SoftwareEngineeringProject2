package assignment4Backgammon;

import java.util.Scanner;

/**
 * A class representing a Human Player in Backgammon
 * 
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen 12343046
 * @version 1.01, 4 APR 2014
 * @see assignment4Backgammon;
 * 
 */
public class HumanPlayer {
	
	/**
	 * Field to hold the player symbol, so we know which checkers to move.
	 */
	private int playerSymbol;
	
	/**
	 * Field to hold player character
	 */
	private char playerChar;
	
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
	 * 
	 */
	public int getPlayerSymbol()
	{
		return playerSymbol;
	}
	
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
		playerChar = (playerSymbol == Board.PLAYER1) ? Board.PLAYER1_SYMBOL : Board.PLAYER2_SYMBOL;

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
		
		System.out.println();
		board.printBoard(playerSymbol);
		int numberOfDice = board.numberOfDice();
		
		String[] allPlays = board.findAllPlays(getPlayerSymbol());
		if (allPlays.length == 0)
		{
			System.out.print("\nSorry you have no possible moves");
			return 0;
		}
		for (String play : allPlays)
		{
			String temp_count = play;
			int count = temp_count.length()
					- temp_count.replace("-", "").length();
			String temp = "";
			int hyphon = play.indexOf("-", 0), temp_int;
			for (int i = 1; i <= count; i++)
			{
				if (play.substring(hyphon - 1, hyphon).equals("r"))
				{
					temp += play.substring(hyphon - 3, hyphon + 2);
				}
				else
				{
					if (i == 1)
					{
						temp_int = Integer.parseInt(play.substring(0, hyphon)) + 1;
						temp += temp_int + play.substring(hyphon, hyphon + 2);
					}
					else
					{
						temp_int = Integer.parseInt(play.substring(play.indexOf(" ", hyphon - 3) + 1, hyphon)) + 1;
						temp += temp_int + play.substring(hyphon, hyphon + 2);
					}
				}
				if (i < count)
				{
					temp += " ";
					hyphon = play.indexOf("-", hyphon + 1);
				}
			}
			System.out.print(temp + ", ");
		}
		System.out.println("\n");
		
		while(!passedChecks)
		{
			System.out.println("Current Player: " + playerChar);
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
				&& board.check_possible(moves.length, allPlays)
				&& checkForBearOffMoves(moves))
			{
				System.out
						.println("Error - Please enter a valid number of moves");
				passedChecks = false;
			}
		
			passedChecks = singleMoveHighestCheck(allPlays, passedChecks, moves);
		
		
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
		else return board.winner(checkForWin, playerSymbol);
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
				else if ((moves[i].charAt(1) == '-')&&(moves[i].length()==3))
				{
					location_input = moves[i].substring(0, 1);
					dice_input = moves[i].substring(2, 3);
				}
				else if ((moves[i].charAt(2) == '-')&&(moves[i].length()==4))
				{
					location_input = moves[i].substring(0, 2);
					dice_input = moves[i].substring(3, 4);
				}
				else if ((moves[i].charAt(3) == '-')&&(moves[i].length()==5)&&(moves[0].substring(0, 3).equals("bar")))
				{
					location_input = "bar";
					dice_input = moves[i].substring(4, 5);
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
				if (spacesToMove[index] == board.returnDice(index_2))
				{
					board.editDice(0, index_2);
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
		if (board.returnBar(playerSymbol)  != 0 && position != -2)
		{
			System.out.println("Error - You need to move off of the bar first");
			return false;
		}

		/* Checks if checker exists */
		if (playerSymbol == Board.PLAYER1 && position != -2)
		{
			if (board.getValueAtPosition(position).charAt(0) != Board.PLAYER1_SYMBOL)
			{
				System.out.println("Error - There is no valid checker here");
				return false;
			}
		}
		else if (playerSymbol == Board.PLAYER2&& position != -2)
		{
			if (board.getValueAtPosition(position).charAt(0) != Board.PLAYER2_SYMBOL)
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
			if (board.returnDice(index) == move)
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
	private boolean checkForBearOffMoves(String[] moves)
	{
		int check = -1 * (board.returnOff(playerSymbol) - 15); //number of checkers left

		for(String m : moves) if(m.equalsIgnoreCase("pass")) return false;
		
		if(check == 1 && moves.length == 1) return false;
		if(check == 2 && moves.length == 2) return false;
		else if(check == 3 && moves.length == 3) return false;

		return true;
	}
	
	/**
	 * 
	 * @param allPlays
	 * @param passedChecks
	 * @param moves
	 * @return
	 */
	private boolean singleMoveHighestCheck(String[] allPlays,
			boolean passedChecks, String[] moves)
	{
		int lowest = 0;
		for (String temp : allPlays)
		{
			String temp_count = temp;
			int count = temp_count.length()
					- temp_count.replace("-", "").length();
			if (lowest > count)
			{
				lowest = count;
			}
		}

		if (lowest == 1)
		{
			if (moves.length > 1)
			{
				System.out.println("You only have one possible dice to move");
			}

			for (String temp : allPlays)
			{
				for (String temp_temp : moves)
				{
					if (temp.equals(temp_temp))
					{
						return passedChecks;
					}
				}
				return false;
			}
		}

		return passedChecks;
	}
	
	/**
	 * This is used to close the scanner used in this class
	 */
	public void closeScanner()
	{
		in.close();
	}
	
}
