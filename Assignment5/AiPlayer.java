/*
 * Team Name: CLKBG
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen 12343046
 * Version: 1.0
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	 * This method returns the enemy ID
	 * 
	 * @return The enemy Id
	 */
	private int getEnemyId()
	{
	
		return (playerId == Board.X_PLAYER_ID) ? Board.O_PLAYER_ID : Board.X_PLAYER_ID;

	
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
	 * Checks if two boards have the same positions for the current player
	 * 
	 * @param board1
	 *            The first board
	 * @param board2
	 *            The second board
	 * @return True if the boards have the same moves as the player
	 */
	private boolean compareBoards(Board board1, Board board2, int playerId)
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
	 * This method is used to check if the player is opening first or if the
	 * player is going second
	 *
	 * @return True if the player is going first
	 */
	private boolean checkGoingFirst()
	{
		return compareBoards(gameBoard, new Board(), getEnemyId());
	}
	
	/**
	 * This method is used to check if the player is bearing off
	 * 
	 * @return True if the player is bearing off
	 */
	private boolean checkBearOff()
	{
		
		int check = this.lastOccurence(gameBoard);
		if(check < 19 && check != 0) return false;
		else return true;
		
	}

	/**
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
		FirstMoves moves = new FirstMoves();
		for (int index = 0; index < allBoardsList.size(); index++)
		{
			if (compareBoards(moves.two_One, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.three_One, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.four_One, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.five_One, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_One, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.three_Two, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.four_Two, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.five_Two, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_Two, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.four_Three, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.five_Three, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_Three, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.five_Four, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_Four, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_Five, allBoardsList.get(index), playerId))
			{
				return index;
			}
		}

		return 0;
	}

	/**
	 * This sets certain positions on the board
	 * 
	 * @param inputSequence
	 *            The initialiser
	 * @return The new Board
	 */
	private Board copyCheckers(int[] inputSequence)
	{
		Board temp = new Board();

		for (int index = 0; index < Board.NUM_PIPS; index++)
		{
			temp.checkers[playerId][index] = inputSequence[index];
			temp.checkers[getEnemyId()][index] = inputSequence[index];
		}

		return temp;
	}

	/**
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
		Play fakeplay = null;
		FirstMoves countermoves = new FirstMoves();
		if (compareBoards(countermoves.six_Five, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(8, 5, 8, 5, 6, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 1, 13, 4);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 1, 13, 5);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 5, 6, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 13, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(8, 6, 6, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_Four, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 24, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 9, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(6, 1, 13, 5);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 7, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 5, 6, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 13, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(8, 6, 6, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_Four_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 22, 2, 20, 2, 18, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(13, 3, 13, 3, 10, 3, 10, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(8, 5, 8, 5, 6, 5, 6, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 2, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 6, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 18, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 6);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 6, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 6, 24, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(13, 6, 6, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_Four_alt2, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(11, 2, 11, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(8, 5, 8, 5, 6, 5, 6, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 1, 13, 5);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 4, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 24, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 5, 6, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 13, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 13, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_Three, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(13, 3, 13, 3, 10, 3, 10, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 4, 9, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 6, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 24, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 5, 6, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 6, 24, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(13, 6, 6, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_Three_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(13, 3, 13, 3, 6, 3, 6, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 11, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 3, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 3, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 7, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 24, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 5, 13, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 13, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 13, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_Two, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(13, 3, 13, 3, 10, 3, 10, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 4, 9, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 6, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 24, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 5, 6, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 6, 24, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 6, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 18, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(13, 6, 6, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_Two_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 11, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(8, 3, 8, 3, 6, 3, 6, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(13, 4, 13, 4, 9, 4, 9, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 2, 22, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 3, 10, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 4, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 24, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 5, 6, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 6, 24, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 13, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_Two_alt2, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 22, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(8, 3, 8, 3, 6, 3, 6, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 3, 21, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 4, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(6, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 5, 6, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 6, 7, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.six_One, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(13, 6, 13, 6, 8, 6, 8, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 22, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 5, 6, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 6);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(13, 6, 13, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_Four, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(8, 3, 8, 3, 6, 3, 6, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(13, 4, 13, 4, 9, 4, 9, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(8, 5, 8, 5, 6, 5, 6, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(13, 6, 13, 6, 24, 6, 24, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 7, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 5, 8, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 3, 13, 6);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(6, 4, 8, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_Four_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 24, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 6, 3, 6, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 20, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 18, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 18, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_Three, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 3, 6, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 13, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(8, 6, 6, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_Three_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 3, 6, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 13, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_Two, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(6, 3, 6, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 9, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 24, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 6, 7, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_Two_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(6, 3, 6, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 18, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_Two_alt2, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 24, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 2, 22, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 3, 20, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 7, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 6);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 18, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_One, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 2, 22, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(6, 4, 2, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 18, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 24, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 13, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 6);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(8, 6, 6, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_One_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(13, 3, 13, 3, 10, 3, 10, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 8, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 4, 9, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 24, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 24, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 6);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 6, 24, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(13, 6, 6, 5);
				}
			}
		}
		else if (compareBoards(countermoves.five_One_alt2, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 22, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 3, 21, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 4, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(6, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 18, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 24, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 6);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.four_Three, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(6, 1, 5, 1, 6, 1, 5, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(8, 3, 8, 3, 6, 3, 6, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(13, 4, 13, 4, 9, 4, 9, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(6, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 3, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(6, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 7, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 3, 24, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 6, 7, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(6, 4, 8, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 13, 5);
				}
			}
		}
		else if (compareBoards(countermoves.four_Three_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(6, 1, 5, 1, 6, 1, 5, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 20, 4, 8, 4, 8, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(6, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(6, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 18, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 8, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 6, 7, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 4, 9, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.four_Three_alt2, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 5, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(8, 3, 8, 3, 6, 3, 6, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(13, 4, 13, 4, 9, 4, 9, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 7, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 4, 8, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 10, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(8, 6, 6, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.four_Two, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 24, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(6, 3, 6, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 20, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 3, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 3, 24, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(6, 4, 8, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.four_One, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 24, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 20, 4, 6, 4, 6, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(6, 4, 2, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 3, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 18, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 6, 4);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 6, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(6, 4, 8, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.four_One_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 22, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(8, 3, 8, 3, 6, 3, 6, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(13, 4, 13, 4, 9, 4, 9, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 6, 6, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 3, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 4, 24, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 7, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 4, 8, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 3, 6, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 8, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.four_One_alt2, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 22, 1, 21, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 22, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 20, 4, 8, 4, 8, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 3, 21, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 4, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 18, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 4, 24, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 21, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.three_Two, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(6, 1, 6, 1, 5, 1, 5, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 20, 4, 8, 4, 8, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(8, 5, 8, 5, 6, 5, 6, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(6, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 3, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(6, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(8, 4, 24, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 6, 7, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 5, 8, 4);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 18, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.three_Two_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(8, 1, 8, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 20, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 2, 13, 3);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 4, 13, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 18, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.three_One, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 24, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(13, 4, 13, 4, 9, 4, 9, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 3);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 4, 24, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 13, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(13, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 18, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.two_One, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 6, 1, 6, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(13, 2, 13, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 13, 3, 13, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(6, 5, 6, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 2, 22, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(8, 3, 6, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(6, 4, 2, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(8, 4, 6, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 6, 13, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(13, 4, 24, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 3, 13, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 13, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 6, 18, 4);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		else if (compareBoards(countermoves.two_One_alt, gameBoard, getEnemyId()))
		{
			if (gameDice.isDouble())
			{
				if (gameDice.getDie(0) == 1)
				{
					fakeplay = new Play(24, 1, 23, 1, 22, 1, 21, 1);
				}
				else if (gameDice.getDie(0) == 2)
				{
					fakeplay = new Play(24, 2, 22, 2, 6, 2, 6, 2);
				}
				else if (gameDice.getDie(0) == 3)
				{
					fakeplay = new Play(24, 3, 24, 3, 8, 3, 8, 3);
				}
				else if (gameDice.getDie(0) == 4)
				{
					fakeplay = new Play(24, 4, 24, 4, 13, 4, 13, 4);
				}
				else if (gameDice.getDie(0) == 5)
				{
					fakeplay = new Play(13, 5, 13, 5, 8, 5, 8, 5);
				}
				else if (gameDice.getDie(0) == 6)
				{
					fakeplay = new Play(24, 6, 24, 6, 13, 6, 13, 6);
				}
			}
			else
			{
				if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 2)
						|| (gameDice.getDie(0) == 2 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 2, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 3, 21, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(24, 4, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 5, 24, 1);
				}
				else if ((gameDice.getDie(0) == 1 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 1))
				{
					fakeplay = new Play(13, 6, 8, 1);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 3)
						|| (gameDice.getDie(0) == 3 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 3, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 4, 13, 2);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(24, 2, 13, 5);
				}
				else if ((gameDice.getDie(0) == 2 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 2))
				{
					fakeplay = new Play(13, 6, 24, 2);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 4)
						|| (gameDice.getDie(0) == 4 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 4, 24, 3);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(6, 3, 8, 5);
				}
				else if ((gameDice.getDie(0) == 3 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 3))
				{
					fakeplay = new Play(24, 6, 18, 3);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 5)
						|| (gameDice.getDie(0) == 5 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 13, 5);
				}
				else if ((gameDice.getDie(0) == 4 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 4))
				{
					fakeplay = new Play(24, 4, 20, 6);
				}
				else if ((gameDice.getDie(0) == 5 && gameDice.getDie(1) == 6)
						|| (gameDice.getDie(0) == 6 && gameDice.getDie(1) == 5))
				{
					fakeplay = new Play(24, 6, 18, 5);
				}
			}
		}
		return findboard(allBoardsList, fakeplay);
	}

	private int findboard(ArrayList<Board> allBoardsList, Play temp)
	{
		ArrayList<Play> tempPlayList = gameBoard.allPossiblePlays(playerId, gameDice);
		if (temp != null)
		{
			for (int index = 0; index < tempPlayList.size(); index++)
			{
				if (temp.toString().equals(tempPlayList.get(index).toString()))
				{
					return index;
				}
			}
		}
		return normalMove(allBoardsList);
	}

	/**
	 * 
	 * This method applys the strategy after the players first move
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int normalMove(ArrayList<Board> allBoardsList)
	{
		int hitIndex = findHitIndex(allBoardsList);
		int makePointIndex = findMakePointIndex(allBoardsList);
		int fourPrimeIndex = fourPrime(allBoardsList);
		int fivePrimeIndex = fivePrime(allBoardsList);
		int sixPrimeIndex = sixPrime(allBoardsList);
		int raceIndex = findRaceIndex(allBoardsList);
		int make5Point = make5PointIndex(allBoardsList);
		int make20Point = make20PointIndex(allBoardsList);

	
		/**
		 * Try for a
