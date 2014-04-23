import java.util.ArrayList;

public class RandomPlayer {

	private int playerId;
	private Board gameBoard;
	private Dice gameDice;
	
	
	RandomPlayer (int setPlayerId, Board setBoard, Dice setDice) {
		playerId = setPlayerId;
		gameBoard = setBoard;
		gameDice = setDice;
	    return;
	}
	
	
	public int getPlayerId () {
		return playerId;
	}
	
	
	public Play getPlay () {
		ArrayList<Play> allPlayList;
		int chosenPlayIndex;
		Play chosenPlay;
		
		allPlayList = gameBoard.allPossiblePlays (playerId, gameDice);
		if (!allPlayList.isEmpty()) {
			chosenPlayIndex = (int)(Math.random() * allPlayList.size());   
			chosenPlay = allPlayList.get(chosenPlayIndex);
		}
		else {
			chosenPlay = new Play();
		}
		
		return chosenPlay;
	}
		
	
}
