package Assignment1Backgammon;

/**
 * A tester class used to test the Board class
 * 
 * @author Laurence Quinn, Cairan O'Niell, Kai-Uwe Rathjen
 * @version 0.03, 26 JAN 2014
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

		Board myBoard = new Board();
		
		int[] dice = new int[4];
		
		myBoard.printBoard();
		dice = myBoard.diceRoll();
		myBoard.makeAMove(0, dice[0]);
		myBoard.printBoard();
		myBoard.makeAMove(0, dice[1]);
		myBoard.printBoard();
		
		System.exit(0);
	}

}
