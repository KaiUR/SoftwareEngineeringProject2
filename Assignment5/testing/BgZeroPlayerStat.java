public class BgZeroPlayerStat
{

	private static final int RBOT_ID = Board.X_PLAYER_ID;
	private static final int BOT_ID = Board.O_PLAYER_ID;

	public static void main(String[] args)
	{
		int Owins = 0;
		int Xwins = 0;
		
		System.out.println("Processing ...");

		for (int index = 0; index < 10000; index++)
		{
			System.out.println(index);
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
			}
			while (winner == Dice.DRAW);

			do
			{
				if ((!firstMove) || (firstMove && (winner == BOT_ID)))
				{
					gameDice.rollDice();
					currentPlay = bot.getPlay();
					finished = gameBoard.doPlay(BOT_ID, currentPlay);
				}
				if (!finished)
				{
					gameDice.rollDice();
					currentPlay = rbot.getPlay();
					finished = gameBoard.doPlay(RBOT_ID, currentPlay);
				}
				firstMove = false;
			}
			while ((!finished));

			if (finished)
			{
				if (gameBoard.displayResult() == Board.O_PLAYER_ID)
				{
					Owins++;
				}
				else
				{
					Xwins++;
				}

			}

		}

		System.out.println("O wins: " + Owins / 100 + "% X wins: " + Xwins / 100 + "%");

		return;
	}

}
