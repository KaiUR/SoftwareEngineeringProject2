/*
 * Team Name: CLKBG
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
	private boolean	primePrepared;
	private boolean	inPrime;
	
	
	AiPlayer (int setPlayerId, Board setBoard, Dice setDice) {
		playerId = setPlayerId;
		gameBoard = setBoard;
		gameDice = setDice;
		FirstMove = true;
		primePrepared = false;
		inPrime = false;
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
		return compareBoards(gameBoard, new Board(), playerId);
	}
	
	/**
	 * This method is used to check if the player is bearing off
	 * 
	 * @return True if the player is bearing off
	 */
	private boolean checkBearOff()
	{
		
		if(this.lastOccurence(gameBoard) < 19) return false;
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
		return 0;
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
		int fivePrimeIndex = fivePrime(allBoardsList);
		int sixPrimeIndex = sixPrime(allBoardsList);
		int raceIndex = findRaceIndex(allBoardsList);
		int preparePrimeIndex = preparePrime(allBoardsList);

		if (preparePrimeIndex != -1)
		{
			return preparePrimeIndex;
		}
		/**
		 * Try for a six prime first
		 */
		else if (sixPrimeIndex != -1)
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
	 * UNIMPLEMENTED: Kai - Helper method for normalMove()
	 * 
	 * This method checks if you can prepare a prime move and sets flags
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the prime moves, or -1 if not
	 */
	private int preparePrime(ArrayList<Board> allBoardsList)
	{
		return -1;
	}

	/**
	 * UNIMPLEMENTED: Kai - Helper method for normalMove()
	 * 
	 * This method checks if you can make a six prime, and makes all the moves
	 * after the six prime is made
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the prime moves, or -1 if not
	 */
	private int sixPrime(ArrayList<Board> allBoardsList)
	{
		return -1;
	}

	/**
	 * UNIMPLEMENTED: Kai - Helper method for normalMove()
	 * 
	 * This method checks if you can make a five prime, and makes all the moves
	 * after the five prime is made
	 * 
	 * @param allBoardsList
	 *            The list of all plays
	 * @return The index of the prime moves, or -1 if not
	 */
	private int fivePrime(ArrayList<Board> allBoardsList)
	{
		return -1;
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
		int mostHits = 0, index = 0;
		for(index = 0; index < allBoardsList.size(); index++){
			if(allBoardsList.get(index).checkers[this.getEnemyId()][Board.BAR] > currentEnemyOff){
				mostHits = allBoardsList.get(index).checkers[this.getEnemyId()][Board.BAR];
			}
		}
		
		ArrayList<Board> mostHitBoards = new ArrayList<Board>();
		for(index = 0; index < allBoardsList.size(); index++){
			if(allBoardsList.get(index).checkers[this.getEnemyId()][Board.BAR] == mostHits){
				mostHitBoards.add(allBoardsList.get(index));
			}
		}
		
		int leastBlots = this.numberOfBlots(mostHitBoards.get(0));
		int leastBlotsIndex = 0;
		for(index = 1; index < mostHitBoards.size(); index++){
			if(leastBlots > this.numberOfBlots(mostHitBoards.get(index))){
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
		
		int bestOff = allBoardsList.get(0).checkers[playerId][Board.BAR];
		int bestOffPosition = 0;
		for(int i = 1; i < allBoardsList.size(); i++){
			if(bestOff < allBoardsList.get(i).checkers[playerId][Board.BAR]){
				bestOff = allBoardsList.get(i).checkers[playerId][Board.BAR];
				bestOffPosition = i;
			}
		}
		
		return bestOffPosition;
		
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
	
	/**
	 * This class just has all 15 starting moves saved in it
	 * 
	 */
	class FirstMoves
	{
		int[]	intTwo_One		=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 1, 0, 4, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 };
		Board	two_One			= copyCheckers(intTwo_One);

		int[]	intThree_One	=
								{ 0, 0, 0, 0, 0, 2, 4, 0, 2, 0, 0, 0, 0, 5, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	three_One		= copyCheckers(intThree_One);

		int[]	intFour_One		=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 1, 0, 0, 0, 4, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 };
		Board	four_One		= copyCheckers(intFour_One);

		int[]	intFive_One		=
								{ 0, 0, 0, 0, 0, 1, 4, 0, 4, 0, 0, 0, 0, 4, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_One		= copyCheckers(intFive_One);

		int[]	intSix_One		=
								{ 0, 0, 0, 0, 0, 0, 5, 2, 2, 0, 0, 0, 0, 4, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	six_One			= copyCheckers(intSix_One);

		int[]	intThree_Two	=
								{ 0, 0, 0, 0, 2, 0, 4, 0, 3, 0, 0, 1, 0, 4, 0,
										0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0 };
		Board	three_Two		= copyCheckers(intThree_Two);

		int[]	intFour_Two		=
								{ 0, 0, 0, 0, 2, 0, 4, 0, 2, 0, 0, 0, 0, 5, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	four_Two		= copyCheckers(intFour_Two);

		int[]	intFive_Two		=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 4, 0, 0, 1, 0, 3, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_Two		= copyCheckers(intFive_Two);

		int[]	intSix_Two		=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0,
										0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Two			= copyCheckers(intSix_Two);

		int[]	intFour_Three	=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0,
										0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 };
		Board	four_Three		= copyCheckers(intFour_Three);

		int[]	intFive_Three	=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 4, 0, 1, 0, 0, 3, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 };
		Board	five_Three		= copyCheckers(intFive_Three);

		int[]	intSix_Three	=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0,
										1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Three		= copyCheckers(intSix_Three);

		int[]	intFive_Four	=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0,
										1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	five_Four		= copyCheckers(intFive_Four);

		int[]	intSix_Four		=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 1,
										0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Four		= copyCheckers(intSix_Four);

		int[]	intSix_Five		=
								{ 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 6, 0,
										0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		Board	six_Five		= new Board(copyCheckers(intSix_Five));
	}
}
