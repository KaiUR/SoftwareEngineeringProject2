package assignment3Backgammon;

public class BgTwoPlayer {
	
	public static void main(String[] args) {
		
		//Create initial board and two player objects
		Board aBoard = new Board();
		HumanPlayer player1 = new HumanPlayer(0, aBoard);
		HumanPlayer player2 = new HumanPlayer(1, aBoard);
		
		int quit; //checks if player has quit
		
		while(true) {
			quit = 0;
			
			quit = player1.makeMove();
			if(quit == -2) {
				return;
			}
			
			quit = player2.makeMove();
			if(quit == -2) {
				return;
			}
		}
		
	}
	
}
