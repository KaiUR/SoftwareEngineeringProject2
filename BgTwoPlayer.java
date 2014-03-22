package assignment3Backgammon;

public class BgTwoPlayer {
	
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
			if(stateCheck == -2) {
				System.out.println("You have chosen to exit the game. Goodbye.");
				return;
			}
			else if(stateCheck == -3) {
				return;
			}
			board.rollDice();
			stateCheck = player2.makeMove();
			if(stateCheck == -2) {
				System.out.println("You have chosen to exit the game. Goodbye.");
				return;
			}
			else if(stateCheck == -3) {
				return;
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
			player1 = new HumanPlayer(0, board);
			player2 = new HumanPlayer(1, board);
			System.out.println("Player " + Board.PLAYER1_SYMBOL
					+ " will start first!\n");
		}
		else 
		{
			player1 = new HumanPlayer(1, board);
			player2 = new HumanPlayer(0, board);
			System.out.println("Player " + Board.PLAYER1_SYMBOL
					+ " will start first!\n");
		}
	}
	
}
