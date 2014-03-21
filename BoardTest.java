package Assignment1Backgammon;

/**
 * A tester class used to test the Board class
 * 
 * @author Laurence Quinn, Ciarán O'Niell, Kai-Uwe Rathjen
 * @version 0.04, 31 JAN 2014
 * @see Assignment1Backgammon
 */
public class BoardTest
{

	/**
	 * The Main method of this tester class
	 * 
	 * @param args
	 *            Not used in this program
	 */
	public static void main(String[] args)
	{

		int a = Board.PLAYER1, b = BOARD.PLAYER2;
		Board myBoard = new Board();
		myBoard.makeAMove(1, -1, Board.PLAYER1);
		myBoard.printBoard(a);
		myBoard.makeAMove(6, 5, Board.PLAYER2);
		myBoard.printBoard(b);
		myBoard.makeAMove(-1, 1, Board.PLAYER1);
		myBoard.printBoard(a);
		
		System.exit(0);
	}

}
