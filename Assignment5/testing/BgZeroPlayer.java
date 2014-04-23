public class BgZeroPlayer
{

	private static final int RBOT_ID = Board.X_PLAYER_ID;
	private static final int BOT_ID = Board.O_PLAYER_ID;

	public static void main(String[] args)
	{

		Board gameBoard = new Board();
		Dice gameDice = new Dice();
		Play currentPlay = new Play();
		RandomPlayer rbot = new RandomPlayer(RBOT_ID, gameBoard, gameDice);
		AiPlayer bot = new AiPlayer(BOT_ID, gameBoard, gameDice);
		int winner = 2;
		boolean finished = false, firstMove = true;

		do
		{
			winner = gameDice.rollDieEach();
			gameDice.displayDieEach();
		}
		while (winner == Dice.DRAW);

		do
		{
			if (firstMove)
			{
				gameBoard.displayBoard(RBOT_ID);
			}
			if ((!firstMove) || (firstMove && (winner == BOT_ID)))
			{
				gameDice.rollDice();
				gameDice.displayDice(BOT_ID);
				currentPlay = bot.getPlay();
				currentPlay.displayPlay(BOT_ID);
				finished = gameBoard.doPlay(BOT_ID, currentPlay);
				gameBoard.displayBoard(RBOT_ID);
			}
			if (!finished)
			{
				gameDice.rollDice();
				gameDice.displayDice(RBOT_ID);
				currentPlay = rbot.getPlay();
				currentPlay.displayPlay(RBOT_ID);
				finished = gameBoard.doPlay(RBOT_ID, currentPlay);
				gameBoard.displayBoard(BOT_ID);
			}
			firstMove = false;
		}
		while ((!finished));

		if (finished)
		{
			gameBoard.displayResult();
		}

		System.out.println("Game Complete");

		return;
	}

}
