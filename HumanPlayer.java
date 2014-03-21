package assignment2Backgammon;

import java.util.Scanner;

/**
 * A class representing a Human Player in Backgammon
 * 
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen 12343046
 * @version 0.01, 14 FEB 2014
 * @see assignment2Backgammon;
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
	 * Function called on HumanPlayer to make a move. This function calls three sub
	 * functions. 
	 * If processMoves returns true, all checks have been passed and 
	 * performMoves is called to perform the moves on the board.
	 * If processMoves returns false, the whole function makeMove is restarted.
	 * 
	 */
	public int makeMove() {
		boolean passedChecks = false;
		
		board.rollDice();
		int numberOfDice = board.numberOfDice();
		System.out.print("Dice: ");
		board.printDice(4);
		System.out.println();
		
		while(!passedChecks) {
			System.out.print("Enter a move: ");
			String move = in.nextLine();
			/*
			 * read in move
			 */
			String[] moves = move.split("\\s");		
			
			if(move.equalsIgnoreCase("quit")) {
				/*
				 * return -2 to quit the game
				 */
				return -2;
			}
			if(move.equalsIgnoreCase("pass")) {
				/* 
				 * return -1 to pass move
				 */
				return -1;
			}
			passedChecks = processMove(moves);
		
			if(passedChecks) performMove(moves, numberOfDice);
		}
		
		int a = Board.PLAYER1;
		board.printBoard(a);
		
		return 0;
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
		}else if (moves.length == 1 || moves.length == 3)
		{
			System.out
					.println("Error: Please enter a valid number of moves.\n");
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
			if(moves[i].charAt(1)=='-')
			{
				location_input = moves[i].substring(0, 1);
				dice_input = moves[i].substring(2, 3);
			}
			else if(moves[i].charAt(2)=='-')
			{
				location_input = moves[i].substring(0, 2);
				dice_input = moves[i].substring(3, 4);
			}
			else if(moves[i].charAt(3) == '-')
			{
				if(moves[0].substring(0, 3).equals("bar")) {
					location_input = "bar";
					dice_input = moves[i].substring(4);
				}
			}
			else
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
	private void performMove(String[] moves, int numberOfDice) {
		int[] positions = new int[numberOfDice + 1];
		int[] spacesToMove = new int[numberOfDice + 1];
		
		for(int index = 0; index < moves.length; index++) {
			int hyphon = moves[index].indexOf("-");
			if (moves[index].substring(0, 3).equals("bar"))
			{
				positions[index] = -1;
			} else
			{
				positions[index] = Integer.parseInt(moves[index].substring(0,
						hyphon));
			}
			spacesToMove[index] = Integer.parseInt(moves[index].substring(hyphon + 1));
			board.makeAMove(positions[index], spacesToMove[index], playerSymbol);
		}
	}
	
	public void closeScanner()
	{
		in.close();
	}
	
}
