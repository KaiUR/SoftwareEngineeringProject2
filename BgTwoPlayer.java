package assignment3Backgammon;

/**
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen
 *         12343046
 * @version 1.01, 22 MAR 2014
 * @see assignment3Backgammon;
 * 
 */
public class BgTwoPlayer 
{
	
	/*
	 * Create initial board and two player objects
	 */ 
	private static Board board = new Board();
	private static HumanPlayer player1;
	private static HumanPlayer player2;
	
	/**
	 * This is the main method that runs the game.
	 * 
	 * @param args This is not used in this program
	 */
	public static void main(String[] args) {
		int stateCheck;
		boolean firstMove = true;
		
		decideWhoFirst();
		
		while(true) {
			if(firstMove) firstMove = false;
			else board.rollDice();
			stateCheck = player1.makeMove();
			if(stateCheck == -2) 
			{
				System.out.println("You have chosen to exit the game. Goodbye.");
				player1.closeScanner();
				player2.closeScanner();
				System.exit(0);
			}
			else if(stateCheck == -3) 
			{
				player1.closeScanner();
				player2.closeScanner();
				System.exit(0);
			}
			board.rollDice();
			stateCheck = player2.makeMove();
			if(stateCheck == -2) 
			{
				System.out.println("You have chosen to exit the game. Goodbye.");
				player1.closeScanner();
				player2.closeScanner();
				System.exit(0);
			}
			else if(stateCheck == -3)
			{
				player1.closeScanner();
				player2.closeScanner();
				System.exit(0);
			}
		}
		
	}
	
	/**
	 *  This method is used to check which player starts the game first
	 */
	private static void decideWhoFirst() {
		do {
			board.rollDice();
		}while(board.dice[0] == board.dice[1]);
		if(board.dice[0] > board.dice[1]) {
			player1 = new HumanPlayer(Board.PLAYER1, board);
			player2 = new HumanPlayer(Board.PLAYER2, board);
			System.out.println("Player " + Board.PLAYER1_SYMBOL
					+ " will start first!\n");
		}
		else 
		{
			player1 = new HumanPlayer(Board.PLAYER2, board);
			player2 = new HumanPlayer(Board.PLAYER1, board);
			System.out.println("Player " + Board.PLAYER2_SYMBOL
					+ " will start first!\n");
		}
	}
	
}
