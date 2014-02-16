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
		int toReturn = 0;
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
				toReturn = -2;
				break;
			}
			if(move.equalsIgnoreCase("pass")) {
				//return -1 to pass move
				toReturn = -1;
				break;
			}
			passedChecks = processMove(moves, board);
		
			if(passedChecks) performMove(moves, board, numberOfDice);
		}
		
		in.close();
		
		return toReturn;
	}
	
	/**
	 * Performs checks on moves.
	 * 
	 * @param moves 
	 * 				String array of users moves
	 * @return
	 * 				A boolean indicating whether the moves failed or passed the checks.
	 */
	private boolean processMove(String[] moves, Board board) {
		
		if(moves.length > board.numberOfDice()) {	//check no more than 4 moves entered.
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
	private void performMove(String[] moves, Board board, int numberOfDice) {
		int[] positions = new int[numberOfDice + 1];
		int[] spacesToMove = new int[numberOfDice + 1];
		
		for(int i = 0; i < numberOfDice; i++) {
			int hyphon = moves[i].indexOf("-");
			positions[i] = Integer.parseInt(moves[i].substring(0, hyphon));
			spacesToMove[i] = Integer.parseInt(moves[i].substring(hyphon + 1));
			board.makeAMove(positions[i], spacesToMove[i], playerSymbol);
		}
	}
	
}
