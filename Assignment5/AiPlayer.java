/*
 * Team Name: CLKBG
 * @author Laurence Quinn 12473478, Ciarán O'Niell 12432672, Kai-Uwe Rathjen 12343046
 * Version: 1.0
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AiPlayer
{

	private int		playerId;
	private Board	gameBoard;
	private Dice	gameDice;
	private boolean	FirstMove;

	AiPlayer(int setPlayerId, Board setBoard, Dice setDice)
	{
		playerId = setPlayerId;
		gameBoard = setBoard;
		gameDice = setDice;
		FirstMove = true;
		return;
	}

	public int getPlayerId()
	{
		return playerId;
	}

	/**
	 * This method returns the enemy ID
	 * 
	 * @return The enemy Id
	 */
	private int getEnemyId()
	{

		return (playerId == Board.X_PLAYER_ID) ? Board.O_PLAYER_ID
				: Board.X_PLAYER_ID;

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
		if (check < 19 && check != 0)
			return false;
		else
			return true;

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
			if (compareBoards(moves.three_One, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.four_One, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.five_One, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_One, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.three_Two, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.four_Two, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.five_Two, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_Two, allBoardsList.get(index), playerId))
			{
				return index;
			}
			if (compareBoards(moves.four_Three, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.five_Three, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_Three, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.five_Four, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_Four, allBoardsList.get(index),
					playerId))
			{
				return index;
			}
			if (compareBoards(moves.six_Five, allBoardsList.get(index),
					playerId))
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
		else if (compareBoards(countermoves.six_Four_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.six_Four_alt2, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.six_Three_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.six_Two_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.six_Two_alt2, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.five_Four_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.five_Three_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.five_Two_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.five_Two_alt2, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.five_One_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.five_One_alt2, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.four_Three_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.four_Three_alt2, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.four_One_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.four_One_alt2, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.three_Two_alt, gameBoard,
				getEnemyId()))
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
		else if (compareBoards(countermoves.two_One_alt, gameBoard,
				getEnemyId()))
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
		ArrayList<Play> tempPlayList = gameBoard.allPossiblePlays(playerId,
				gameDice);
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
		 * Try for a six prime first
		 */
		if (sixPrimeIndex != -1)
		{
			return sixPrimeIndex;
		}
		/**
		 * Try for a five prime second
		 */
		else if (fivePrimeIndex != -1)
		{
			return fivePrimeIndex;
		}
		/**
		 * Try for four prime
		 */
		else if (fourPrimeIndex != -1)
		{
			return fourPrimeIndex;
		}
		/**
		 * Make 5 point if possible
		 */
		else if (make5Point != -1)
		{
			return make5Point;
		}
		/**
		 * Make the enemy's 5 point
		 */
		else if (make20Point != -1)
		{
			return make20Point;
		}
		/**
		 * Try to make a point or wall up
		 */
		else if (makePointIndex != -1)
		{
			return makePointIndex;
		}
		/**
		 * Try to hit
		 */
		else if (hitIndex != -1)
		{
			return hitIndex;
		}
		/**
		 * If none of the above, just race
		 */
		else
		{
			return raceIndex;
		}
	}

	/**
	 * This method checks if you can make your own 5 point
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the prime moves, or -1 if not
	 */
	private int make5PointIndex(ArrayList<Board> allBoardsList)
	{
		class StoreIndex
		{
			private int	index;
			private int	blots;

			StoreIndex(int index, int blots)
			{
				this.index = index;
				this.blots = blots;
			}

			public int getIndex()
			{
				return index;
			}

			public int getBlots()
			{
				return blots;
			}
		}
		ArrayList<StoreIndex> make5pointList = new ArrayList<StoreIndex>();

		for (int index = 0; index < allBoardsList.size(); index++)
		{
			if (allBoardsList.get(index).checkers[playerId][20] > 1
					&& gameBoard.checkers[playerId][20] <= 1)
			{
				StoreIndex temp = new StoreIndex(index,
						numberOfBlots(allBoardsList.get(index)));
				make5pointList.add(temp);
			}
		}

		if (make5pointList.size() == 0)
		{
			return -1;
		}
		else
		{
			Collections.sort(make5pointList, new Comparator<StoreIndex>()
			{
				public int compare(StoreIndex firstObject,
						StoreIndex secondObject)
				{
					return firstObject.getBlots() - secondObject.getBlots();
				}

			});

			return make5pointList.get(0).getIndex();
		}
	}

	/**
	 * This method checks if you can make the enemy's 5 point
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the prime moves, or -1 if not
	 */
	private int make20PointIndex(ArrayList<Board> allBoardsList)
	{
		class StoreIndex
		{
			private int	index;
			private int	blots;

			StoreIndex(int index, int blots)
			{
				this.index = index;
				this.blots = blots;
			}

			public int getIndex()
			{
				return index;
			}

			public int getBlots()
			{
				return blots;
			}
		}
		ArrayList<StoreIndex> make20pointList = new ArrayList<StoreIndex>();

		for (int index = 0; index < allBoardsList.size(); index++)
		{
			if (allBoardsList.get(index).checkers[playerId][5] > 1
					&& gameBoard.checkers[playerId][5] <= 1)
			{
				StoreIndex temp = new StoreIndex(index,
						numberOfBlots(allBoardsList.get(index)));
				make20pointList.add(temp);
			}
		}

		if (make20pointList.size() == 0)
		{
			return -1;
		}
		else
		{
			Collections.sort(make20pointList, new Comparator<StoreIndex>()
			{
				public int compare(StoreIndex firstObject,
						StoreIndex secondObject)
				{
					return firstObject.getBlots() - secondObject.getBlots();
				}

			});

			return make20pointList.get(0).getIndex();
		}
	}

	/**
	 * This method checks if you can make a six prime, and makes all the moves
	 * after the six prime is made
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the prime moves, or -1 if not
	 */
	private int sixPrime(ArrayList<Board> allBoardsList)
	{
		class StoreIndex
		{
			private int	index;
			private int	blots;

			StoreIndex(int index, int blots)
			{
				this.index = index;
				this.blots = blots;
			}

			public int getIndex()
			{
				return index;
			}

			public int getBlots()
			{
				return blots;
			}
		}

		ArrayList<StoreIndex> sixPrimeList = new ArrayList<StoreIndex>();
		for (int index = 0; index < allBoardsList.size(); index++)
		{
			int[] temp = allBoardsList.get(index).checkers[playerId];
			int count = 0;
			boolean prime4 = false;
			for (int index2 = 1; index2 < Board.NUM_PIPS - 1; index2++)
			{
				if (temp[index2] > 2)
				{
					count++;
				}
				else
				{
					count = 0;
				}

				if (count == 6)
				{
					prime4 = true;
				}
			}

			if (prime4 == true)
			{
				StoreIndex tempStore = new StoreIndex(index,
						numberOfBlots(allBoardsList.get(index)));
				sixPrimeList.add(tempStore);
			}
		}

		if (sixPrimeList.size() == 0)
		{
			return -1;
		}
		else
		{
			Collections.sort(sixPrimeList, new Comparator<StoreIndex>()
			{
				public int compare(StoreIndex firstObject,
						StoreIndex secondObject)
				{
					return firstObject.getBlots() - secondObject.getBlots();
				}

			});

			return sixPrimeList.get(0).getIndex();
		}
	}

	/**
	 * This method checks if you can make a five prime, and makes all the moves
	 * after the five prime is made
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the prime moves, or -1 if not
	 */
	private int fivePrime(ArrayList<Board> allBoardsList)
	{
		class StoreIndex
		{
			private int	index;
			private int	blots;

			StoreIndex(int index, int blots)
			{
				this.index = index;
				this.blots = blots;
			}

			public int getIndex()
			{
				return index;
			}

			public int getBlots()
			{
				return blots;
			}
		}

		ArrayList<StoreIndex> fivePrimeList = new ArrayList<StoreIndex>();
		for (int index = 0; index < allBoardsList.size(); index++)
		{
			int[] temp = allBoardsList.get(index).checkers[playerId];
			int count = 0;
			boolean prime4 = false;
			for (int index2 = 1; index2 < Board.NUM_PIPS - 1; index2++)
			{
				if (temp[index2] > 2)
				{
					count++;
				}
				else
				{
					count = 0;
				}

				if (count == 5)
				{
					prime4 = true;
				}
			}

			if (prime4 == true)
			{
				StoreIndex tempStore = new StoreIndex(index,
						numberOfBlots(allBoardsList.get(index)));
				fivePrimeList.add(tempStore);
			}
		}

		if (fivePrimeList.size() == 0)
		{
			return -1;
		}
		else
		{
			Collections.sort(fivePrimeList, new Comparator<StoreIndex>()
			{
				public int compare(StoreIndex firstObject,
						StoreIndex secondObject)
				{
					return firstObject.getBlots() - secondObject.getBlots();
				}

			});

			return fivePrimeList.get(0).getIndex();
		}
	}

	/**
	 * This method checks if you can make a four prime, and makes all the moves
	 * after the four prime is made
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the prime moves, or -1 if not
	 */
	private int fourPrime(ArrayList<Board> allBoardsList)
	{
		class StoreIndex
		{
			private int	index;
			private int	blots;

			StoreIndex(int index, int blots)
			{
				this.index = index;
				this.blots = blots;
			}

			public int getIndex()
			{
				return index;
			}

			public int getBlots()
			{
				return blots;
			}
		}

		ArrayList<StoreIndex> fourPrimeList = new ArrayList<StoreIndex>();
		for (int index = 0; index < allBoardsList.size(); index++)
		{
			int[] temp = allBoardsList.get(index).checkers[playerId];
			int count = 0;
			boolean prime4 = false;
			for (int index2 = 1; index2 < Board.NUM_PIPS - 1; index2++)
			{
				if (temp[index2] > 2)
				{
					count++;
				}
				else
				{
					count = 0;
				}

				if (count == 4)
				{
					prime4 = true;
				}
			}

			if (prime4 == true)
			{
				StoreIndex tempStore = new StoreIndex(index,
						numberOfBlots(allBoardsList.get(index)));
				fourPrimeList.add(tempStore);
			}
		}

		if (fourPrimeList.size() == 0)
		{
			return -1;
		}
		else
		{
			Collections.sort(fourPrimeList, new Comparator<StoreIndex>()
			{
				public int compare(StoreIndex firstObject,
						StoreIndex secondObject)
				{
					return firstObject.getBlots() - secondObject.getBlots();
				}

			});

			return fourPrimeList.get(0).getIndex();
		}
	}

	/**
	 * This method sees if you can make a point, i.e. wall up
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index to make a point, or -1 if no point can be made
	 */
	private int findMakePointIndex(ArrayList<Board> allBoardsList)
	{
		class StoreIndex
		{
			private int	index;
			private int	numberOfPoints;

			StoreIndex(int index, int numberOfPoints)
			{
				this.index = index;
				this.numberOfPoints = numberOfPoints;
			}

			public int returnIndex()
			{
				return index;
			}

			public int retrunNumberPoints()
			{
				return numberOfPoints;
			}
		}
		ArrayList<StoreIndex> PointList = new ArrayList<StoreIndex>();

		for (int index = 0; index < allBoardsList.size(); index++)
		{
			int numberPoints = numberOfWalls(gameBoard)
					- numberOfWalls(allBoardsList.get(index));
			if (numberPoints > 0)
			{
				StoreIndex temp = new StoreIndex(index, numberPoints);
				PointList.add(temp);
			}
		}

		if (PointList.size() == 0)
		{
			return -1;
		}
		else
		{
			Collections.sort(PointList, new Comparator<StoreIndex>()
			{
				public int compare(StoreIndex firstObject,
						StoreIndex secondObject)
				{
					return firstObject.retrunNumberPoints()
							- secondObject.retrunNumberPoints();
				}

			});

			return PointList.get(0).returnIndex();
		}
	}

	/**
	 * This method returns the number of walls
	 * 
	 * @param board
	 *            The current board
	 * @return The number of walls
	 */
	private int numberOfWalls(Board board)
	{
		int count = 0;
		for (int index = 0; index < board.checkers[playerId].length; index++)
		{
			if (board.checkers[playerId][index] > 1)
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * This method looks for hits based on the following rules:
	 * 
	 * 1- When not in home board- Can you hit? Then, which leaves less blots.
	 * 
	 * 2- When in Home can you hit, and leave no blot?
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best hit, or -1 if no hit
	 */
	private int findHitIndex(ArrayList<Board> allBoardsList)
	{

		int currentEnemyOff = gameBoard.checkers[this.getEnemyId()][Board.BAR];
		int mostHits = currentEnemyOff, index = 0;
		for (index = 0; index < allBoardsList.size(); index++)
		{
			if (allBoardsList.get(index).checkers[this.getEnemyId()][Board.BAR] > currentEnemyOff)
			{
				mostHits = allBoardsList.get(index).checkers[this.getEnemyId()][Board.BAR];
			}
		}

		if (currentEnemyOff == mostHits)
		{
			return -1;
		}

		ArrayList<Board> mostHitBoards = new ArrayList<Board>();
		for (index = 0; index < allBoardsList.size(); index++)
		{
			if (allBoardsList.get(index).checkers[this.getEnemyId()][Board.BAR] == mostHits)
			{
				mostHitBoards.add(allBoardsList.get(index));
			}
		}

		int leastBlots = this.numberOfBlots(mostHitBoards.get(0));
		int leastBlotsIndex = 0;
		for (index = 1; index < mostHitBoards.size(); index++)
		{
			if (leastBlots > this.numberOfBlots(mostHitBoards.get(index)))
			{
				leastBlots = this.numberOfBlots(mostHitBoards.get(index));
				leastBlotsIndex = index;
			}
		}

		return allBoardsList.indexOf(mostHitBoards.get(leastBlotsIndex));

	}

	/**
	 * This method finds the best move to race with
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int findRaceIndex(ArrayList<Board> allBoardsList)
	{
		class StoreIndex
		{
			private int	index;
			private int	numberOfBlots;
			private int	lastChecker;

			StoreIndex(int index, int numberOfBlots, int lastChecker)
			{
				this.index = index;
				this.numberOfBlots = numberOfBlots;
				this.lastChecker = lastChecker;
			}

			public int returnIndex()
			{
				return index;
			}

			public int returnBlots()
			{
				return numberOfBlots;
			}

			public int returnLast()
			{
				return lastChecker;
			}
		}
		ArrayList<StoreIndex> RaceList = new ArrayList<StoreIndex>();

		for (int index = 0; index < allBoardsList.size(); index++)
		{
			StoreIndex temp = new StoreIndex(index,
					numberOfBlots(allBoardsList.get(index)),
					lastOccurence(allBoardsList.get(index)));
			RaceList.add(temp);
		}

		Collections.sort(RaceList, new Comparator<StoreIndex>()
		{
			public int compare(StoreIndex firstObject, StoreIndex secondObject)
			{
				int check = firstObject.returnLast()
						- secondObject.returnLast();

				if (check == 0)
				{
					return firstObject.returnBlots()
							- secondObject.returnBlots();
				}

				return check;
			}

		});

		return RaceList.get(0).returnIndex();
	}

	/**
	 * This function returns the last occurrence of the players checker
	 * 
	 * @param board
	 *            The current board
	 * @return The index of last occurrence
	 */
	private int lastOccurence(Board board)
	{
		for (int index = 0; index < board.checkers[playerId].length; index++)
		{
			if (board.checkers[playerId][index] != 0)
			{
				return index;
			}
		}
		return 0;
	}

	/**
	 * This method finds the number of blots
	 * 
	 * @param board
	 *            The current board
	 * @return The number of blots
	 */
	private int numberOfBlots(Board board)
	{
		int count = 0;
		for (int index = 0; index < board.checkers[playerId].length; index++)
		{
			if (board.checkers[playerId][index] == 1)
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * This method applys the bearing off strategy
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the best move
	 */
	private int bearOffMove(ArrayList<Board> allBoardsList)
	{

		int bestOff = allBoardsList.get(0).checkers[playerId][Board.OFF];
		for (int index = 1; index < allBoardsList.size(); index++)
		{
			if (allBoardsList.get(index).checkers[playerId][Board.OFF] > bestOff)
			{
				bestOff = allBoardsList.get(index).checkers[playerId][Board.OFF];
			}
		}

		ArrayList<Integer> bestOffIndexes = new ArrayList<Integer>();
		for (int index = 0; index < allBoardsList.size(); index++)
		{
			if (allBoardsList.get(index).checkers[playerId][Board.OFF] == bestOff)
			{
				bestOffIndexes.add(index);
			}
		}

		if (gameDice.getDiceList().size() == bestOff
				- gameBoard.checkers[playerId][Board.OFF])
		{
			return bestOffIndexes.get(0);
		}

		int leastEmpties = emptySpaces(allBoardsList.get(bestOffIndexes.get(0)));
		int leastEmptiesIndex = bestOffIndexes.get(0);
		for (int index = 1; index < bestOffIndexes.size(); index++)
		{
			if (emptySpaces(allBoardsList.get(bestOffIndexes.get(index))) < leastEmpties)
			{
				leastEmptiesIndex = bestOffIndexes.get(index);
			}
		}

		return leastEmptiesIndex;

	}

	/**
	 * Determines the number of empty spaces on the board
	 * 
	 * @param board
	 *            The board in question
	 * @return The number of empty spaces for the player
	 */
	private int emptySpaces(Board board)
	{
		int count = 0;
		for (int index = 1; index < board.checkers[playerId][Board.BAR]; index++)
		{
			if (board.checkers[playerId][index] == 0)
			{
				count++;
			}
		}
		return count;
	}

	public Play getPlay()
	{
		ArrayList<Play> allPlayList;
		ArrayList<Board> allBoardsList = new ArrayList<Board>();
		int bestBoard;
		Play chosenPlay;

		allPlayList = gameBoard.allPossiblePlays(playerId, gameDice);
		if (!allPlayList.isEmpty())
		{
			for (int i = 0; i < allPlayList.size(); i++)
			{
				allBoardsList.add(new Board(gameBoard));
				allBoardsList.get(i).doPlay(playerId, allPlayList.get(i));
			}
			bestBoard = findBestBoard(allBoardsList);
			chosenPlay = allPlayList.get(bestBoard);
		}
		else
		{
			chosenPlay = new Play();
		}

		return chosenPlay;
	}

	/**
	 * This class just has all 15 starting moves saved in it
	 * 
	 */
	class FirstMoves
	{
		int[]	intTwo_One			=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 1, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 };
		Board	two_One				= copyCheckers(intTwo_One);

		int[]	intTwo_One_alt		=
									{ 0, 0, 0, 0, 0, 1, 4, 0, 3, 0, 0, 1, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	two_One_alt			= copyCheckers(intTwo_One_alt);

		int[]	intThree_One		=
									{ 0, 0, 0, 0, 0, 2, 4, 0, 2, 0, 0, 0, 0, 5,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	three_One			= copyCheckers(intThree_One);

		int[]	intFour_One			=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 1, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 };
		Board	four_One			= copyCheckers(intFour_One);

		int[]	intFour_One_alt		=
									{ 0, 0, 0, 0, 0, 1, 4, 0, 3, 0, 0, 0, 0, 5,
											0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 };
		Board	four_One_alt		= copyCheckers(intFour_One_alt);

		int[]	intFour_One_alt2	=
									{ 0, 0, 0, 0, 0, 1, 4, 0, 3, 1, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	four_One_alt2		= copyCheckers(intFour_One_alt2);

		int[]	intFive_One			=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 4, 0, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 };
		Board	five_One			= copyCheckers(intFive_One);

		int[]	intFive_One_alt		=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5,
											0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };
		Board	five_One_alt		= copyCheckers(intFive_One_alt);

		int[]	intFive_One_alt2	=
									{ 0, 0, 0, 0, 0, 1, 4, 0, 4, 0, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_One_alt2		= copyCheckers(intFive_One_alt2);

		int[]	intSix_One			=
									{ 0, 0, 0, 0, 0, 0, 5, 2, 2, 0, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	six_One				= copyCheckers(intSix_One);

		int[]	intThree_Two		=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 1, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0 };
		Board	three_Two			= copyCheckers(intThree_Two);

		int[]	intThree_Two_alt	=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 1, 1, 0, 3,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	three_Two_alt		= copyCheckers(intThree_Two_alt);

		int[]	intFour_Two			=
									{ 0, 0, 0, 0, 2, 0, 4, 0, 2, 0, 0, 0, 0, 5,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	four_Two			= copyCheckers(intFour_Two);

		int[]	intFive_Two			=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 4, 0, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0 };
		Board	five_Two			= copyCheckers(intFive_Two);

		int[]	intFive_Two_alt		=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 4, 0, 0, 1, 0, 3,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_Two_alt		= copyCheckers(intFive_Two_alt);

		int[]	intFive_Two_alt2	=
									{ 0, 0, 0, 0, 1, 0, 4, 0, 4, 0, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_Two_alt2		= copyCheckers(intFive_Two_alt2);

		int[]	intSix_Two			=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 1, 0, 4,
											0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Two				= copyCheckers(intSix_Two);

		int[]	intSix_Two_alt		=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5,
											0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Two_alt			= copyCheckers(intSix_Two_alt);

		int[]	intSix_Two_alt2		=
									{ 0, 0, 0, 0, 0, 1, 5, 0, 3, 0, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	six_Two_alt2		= copyCheckers(intSix_Two_alt2);

		int[]	intFour_Three		=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5,
											0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 };
		Board	four_Three			= copyCheckers(intFour_Three);

		int[]	intFour_Three_alt	=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 1, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0 };
		Board	four_Three_alt		= copyCheckers(intFour_Three_alt);

		int[]	intFour_Three_alt2	=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 1, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 };
		Board	four_Three_alt2		= copyCheckers(intFour_Three_alt2);

		int[]	intFive_Three		=
									{ 0, 0, 0, 2, 0, 0, 4, 0, 2, 0, 0, 0, 0, 5,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_Three			= copyCheckers(intFive_Three);

		int[]	intFive_Three_alt	=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 4, 0, 1, 0, 0, 3,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_Three_alt		= copyCheckers(intFive_Three_alt);

		int[]	intSix_Three		=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 1, 0, 0, 4,
											0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Three			= copyCheckers(intSix_Three);

		int[]	intSix_Three_alt	=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5,
											0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Three_alt		= copyCheckers(intSix_Three_alt);

		int[]	intFive_Four		=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 4, 0, 0, 0, 0, 4,
											0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 };
		Board	five_Four			= copyCheckers(intFive_Four);

		int[]	intFive_Four_alt	=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 4, 1, 0, 0, 0, 3,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_Four_alt		= copyCheckers(intFive_Four_alt);

		int[]	intSix_Four			=
									{ 0, 0, 2, 0, 0, 0, 4, 0, 2, 0, 0, 0, 0, 5,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	six_Four			= copyCheckers(intSix_Four);

		int[]	intSix_Four_alt		=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 1, 0, 0, 0, 4,
											0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Four_alt		= copyCheckers(intSix_Four_alt);

		int[]	intSix_Four_alt2	=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5,
											1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Four_alt2		= copyCheckers(intSix_Four_alt2);

		int[]	intSix_Five			=
									{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 6,
											0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Five			= new Board(copyCheckers(intSix_Five));
	}
}
