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
	private boolean	FirstMove;
	
	
	AiPlayer (int setPlayerId, Board setBoard, Dice setDice) {
		playerId = setPlayerId;
		gameBoard = setBoard;
		gameDice = setDice;
		FirstMove = true;
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
		else if (checkBearOff())
		{
			bestBoard = bearOffMove(allBoardsList);
		}
		else
		{
			bestBoard = normalMove(allBoardsList);
		}

		return bestBoard;
	}
	
	/**
	 * 
	 * NOTE: This might be handy for the other methods. To apply a startegy you
	 * will have to find a move in the list of boards. To do this you need to
	 * make a temp board and the apply that move on the temp board. Then you can
	 * check if that resulting board is in the list of all boards, if it is then
	 * you have your move. This is what this method is for.
	 * 
	 * 
	 * 
	 * This method finds the index of a board.
	 * 
	 * @param allBoardsList
	 *            The list of all boards
	 * @param toFind
	 *            The board to find
	 * @return The index of the board, or -1 if it is not found
	 */
	private int findIndex(ArrayList<Board> allBoardsList, Board toFind)
	{
		for (int index = 0; index < allBoardsList.size(); index++)
		{
			if (compareBoards(allBoardsList.get(index), toFind))
			{
				return index;
			}
		}

		return -1;
	}

	/**
	 * Checks if two boards have the same positions for the current player
	 * 
	 * @param board1
	 *            The first board
	 * @param board2
	 *            The second board
	 * @return True if the boards have the same moves as the player
	 */
	private boolean compareBoards(Board board1, Board board2)
	{
		for (int index = 0; index < board1.checkers[playerId].length; index++)
		{
			if (board1.checkers[playerId][index] != board2.checkers[playerId][index])
			{
				return false;
			}
		}

		return true;
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
	 * This method is used to check if the player is bearing off
	 * 
	 * Suggestion: Check if gameBoard.checkers[playerId][] has all his checkers
	 * in the finishing section
	 * 
	 * @return True if the player is going first
	 */
	private boolean checkBearOff()
	{
		return true;
	}

	/**
	 * UNIMPLEMENTED
	 * 
	 * This method is used to make the first move if the player is going first
	 * 
	 * 
	 * Suggestion: Apply the opening moves according to:
	 * 
	 * http://www.bkgm.com/openings.html
	 * 
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
	 * Suggestion: Apply the opening response according to:
	 * 
	 * http://www.bkgm.com/openings/replies.html
	 * 
	 * There is a chart at the bottom of the site, click on the moves to see
	 * more information
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
	 * Suggestions:
	 * 
	 * http://www.bkgm.com/articles/Simborg/CheckerPlayForIdiots/
	 * http://www.bkgm.com/articles/Simborg/RuleOfThumb/
	 * 
	 * http://www.bkgm.com/articles/GOL/Jul99/rome.htm
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int normalMove(ArrayList<Board> allBoardsList)
	{
		return 0;
	}

	/**
	 * UNIMPLEMENTED
	 * 
	 * This method applys the bearing off strategy
	 * 
	 * Suggestion:
	 * 
	 * Read :
	 * 
	 * http://www.play65.com/bearing-off-in-backgammon.html
	 * http://www.backgammonlion.com/bear-off-strategies-and.html
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int bearOffMove(ArrayList<Board> allBoardsList)
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
