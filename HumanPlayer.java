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
	 * This is the constructor
	 * 
	 * @param team 
	 * 				Pass in the character of the team which is then assigned
	 * 				to field playerSymbol. This is to know which checkers to
	 * 				move for this HumanPlayer.
	 * 		
	 */
	public HumanPlayer(char team) {
		playerSymbol = team;		
	}
	
	/**
	 * Function called on HumanPlayer to make a move. This function calls three sub
	 * functions. 
	 * If processMoves returns true, all checks have been passed and 
	 * performMoves is called to perform the moves on the board.
	 * If processMoves returns false, the whole function makeMove is restarted.
	 * 
	 */
	public void makeMove(Board board) {
		String[] moves = recieveMove();
		boolean passedChecks = processMove(moves);
		
		if(passedChecks) performMove(moves);
		else {
			System.out.println("TESTING: WE DID NOT MAKE IT TO PERFORMMOVE FUNCTION");
			makeMove(board);
		}
		
		return;
	}
	
	/**
	 * Asks the user to enter their move
	 * 
	 * @return
	 * 			All moves entered by player in a string array			
	 * 
	 */
	private String[] recieveMove() {
		Scanner in = new Scanner(System.in);
		String move = "";
		System.out.println();
		System.out.print("Enter your move: ");
		move = in.nextLine();
		in.close();
		String[] moves = move.split("\\s");
		return moves;
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
		
		if(moves.length > 4) {	//check no more than 4 moves entered.
			System.out.println("Error: Please enter a valid number of moves.");
			return false;
		}
		else if((moves.length == 1) && (moves[0].equals(""))) {	//check if no moves entered.
			System.out.println("Error: Please enter a valid number of moves.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Perform users moves on the board
	 * 
	 * @param moves
	 * 			String array of users moves, these are valid.
	 */
	private void performMove(String[] moves) {
		System.out.println("TESTING: WE MADE IT TO PERFORMMOVE FUNCTION");
	}
	
}
