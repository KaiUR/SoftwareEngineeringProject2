package assignment2Backgammon;

/**
 * A Tester class
 * 
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen 12343046
 * @version 0.01, 14 FEB 2014
 * @see assignment2Backgammon;
 * 
 */
public class HumanPlayerTest {
	
	public static void main(String[] args) {
		
		Board myBoard = new Board();
		HumanPlayer player1 = new HumanPlayer(0, myBoard);
		HumanPlayer player2 = new HumanPlayer(1, myBoard);
		while (true)
		{
			if (player1.makeMove() == -2)
			{
				break;
			}
			if (player2.makeMove() == -2)
			{
				break;
			}
		}
		player1.closeScanner();
		player2.closeScanner();
	}
	
}
