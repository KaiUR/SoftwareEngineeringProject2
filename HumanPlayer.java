package assignment1Backgammon;

import java.util.Scanner;

/**
 * A class representing a Human Player in Backgammon
 * 
 * @author Laurence Quinn, Ciarán O'Niell, Kai-Uwe Rathjen
 * @version 0.01, 14 FEB 2014
 * @see assignment1Backgammon;
 * 
 */
public class HumanPlayer {
	
	/**
	 * Field to hold the player symbol, so we know which checkers to move.
	 */
	private char playerSymbol;
	
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
	public HumanPlayer(int team) {
		playerSymbol = team;
		board = PlayerBoard;
	}
	
	/**
	 * Function called on HumanPlayer to make a move. This function calls three sub
	 * functions. 
	 * If processMoves returns true, all checks have been passed and 
	 * performMoves is called to perform the moves on the board.
	 * If processMoves returns false, the whole function makeMove is restarted.
	 * 
	 */
	public void makeMove() {
		boolean passedChecks = false;
		Scanner in = new Scanner(System.in);
		
		board.rollDice();
		int numberOfDice = board.numberOfDice();
		System.out.print("Dice: ");
		board.printDice(4);
		System.out.println();
		
		while(!passedChecks) {
			System.out.print("Enter a move: ");
			String move = in.nextLine();
			String[] moves = move.split("\\s");		//read in move
			
			if(move.equalsIgnoreCase("quit")) {
				//return -2 to quit the game
				return -2;
			}
			if(move.equalsIgnoreCase("pass")) {
				//return -1 to pass move
				return -1;
			}
			passedChecks = processMove(moves);
		
			if(passedChecks) performMove(moves, numberOfDice);
		}
		
		board.printBoard();
		
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
		
		if(moves.length > board.numberOfDice()) {	//check no more than 4 moves entered.
			System.out.println("Error: Please enter a valid number of moves.");
			return false;
		}
		else if((moves.length == 1) && (moves[0].equals(""))) {	//check if no moves entered.
			System.out.println("Error: Please enter a valid number of moves.");
			return false;
		}else if (moves.length == 1 || moves.length == 3)
		{
			System.out
					.println("Error: Please enter a valid number of moves.\n");
			return false;
		}

		boolean passedSyntax = checkSyntax(moves);

		return true && passedSyntax;
	}
	
	/**
	 *  Ciaran Do!
	 * 
	 * @param moves
	 * @return
	 */
	private boolean checkSyntax(String[] moves)
	{
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
		
		for(int i = 0; i < numberOfDice; i++) {
			int hyphon = moves[i].indexOf("-");
			if (moves[index].substring(0, 3).equals("bar"))
			{
				positions[index] = -1;
			} else
			{
				positions[index] = Integer.parseInt(moves[index].substring(0,
						hyphon));
			}
			spacesToMove[i] = Integer.parseInt(moves[i].substring(hyphon + 1));
			board.makeAMove(positions[i], spacesToMove[i], playerSymbol);
		}
	}
	
	public void closeScanner()
	{
		in.close();
	}
	
}
