/*
 * Team Name: CLKBG
 * Version: 1.0
 *
 */

import java.util.ArrayList;

public class AiPlayer {

	private int playerId;
	private Board gameBoard;
	private Dice gameDice;
	
	
	AiPlayer (int setPlayerId, Board setBoard, Dice setDice) {
		playerId = setPlayerId;
		gameBoard = setBoard;
		gameDice = setDice;
	    return;
	}
	
	
	public int getPlayerId () {
		return playerId;
	}
	
	
	/**
	 * This method will select the best move possible
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int findBestBoard(ArrayList<Board> allBoardsList)
	{
		int bestBoard = 0;

		if (FirstMove == true)
		{
			FirstMove = false;

			if (checkGoingFirst())
			{
				bestBoard = goingFirst(allBoardsList);
			}
			else
			{
				bestBoard = goingSecond(allBoardsList);
			}
		}
		else
		{
			bestBoard = normalMove(allBoardsList);
		}

		return bestBoard;
	}

	/**
	 * UNIMPLEMENTED
	 * 
	 * This method is used to check if the player is opening first or if the
	 * player is going second
	 * 
	 * Suggestion: Check if gameBoard.checkers[playerId][0 to 26] matches the
	 * starting board, if yes that player is going first.
	 * 
	 * @return True if the player is going first
	 */
	private boolean checkGoingFirst()
	{
		return true;
	}

	/**
	 * UNIMPLEMENTED
	 * 
	 * This method is used to make the first move if the player is going first
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int goingFirst(ArrayList<Board> allBoardsList)
	{
		return 0;
	}

	/**
	 * UNIMPLEMENTED
	 * 
	 * This method is used to make the first move if the player is going second
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int goingSecond(ArrayList<Board> allBoardsList)
	{
		return 0;
	}

	/**
	 * UNIMPLEMENTED
	 * 
	 * This method applys the strategy after the players first move
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int normalMove(ArrayList<Board> allBoardsList)
	{
		return 0;
	}

	
	
	public Play getPlay () {
		ArrayList<Play> allPlayList;
		ArrayList<Board> allBoardsList = new ArrayList<Board>();
		int bestBoard;
		Play chosenPlay;
		
		allPlayList = gameBoard.allPossiblePlays (playerId, gameDice);
		if (!allPlayList.isEmpty()) {
			for (int i=0; i<allPlayList.size(); i++) {
				allBoardsList.add(new Board(gameBoard));
				allBoardsList.get(i).doPlay(playerId, allPlayList.get(i));
			}
			bestBoard = findBestBoard(allBoardsList);
			chosenPlay = allPlayList.get(bestBoard);
		}
		else {
			chosenPlay = new Play();
		}
		
		return chosenPlay;
	}
		
	
}
