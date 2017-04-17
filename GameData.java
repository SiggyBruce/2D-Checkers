/**
 * GameData.java - APR-24-2016 - ITEC 220: Project 5 -
 * This class contains all of the game data and used for the game.
 * @author Tre Haga
 * @version 1.0
 */
public class GameData
{
	/**
	 * The GameData class instance variables.
	 * Declares two instances of the Player class. (The two players).
	 * Declares a 2D array of integers. (The 8 x 8 checker grid).
	 * Declares an integer variable. (Player value).
	 * Declares six constant integer variables. (Checker piece values).
	 */
	private Player playerOne, playerTwo;
	private int[][] checkers;
	private int playerValue;
	private final int EMPTY_CHECKER, BLACK_CHECKER, RED_CHECKER, BLACK_KING, RED_KING, UNPLAYABLE;
	/**
	 * The GameData class constructor.
	 * Initializes two new instances of the Player class.
	 * Initializes a new 2D array of integers.
	 * Initializes a new integer variable.
	 * Initializes six new constant integer variables.
	 */
	public GameData()
	{
		playerOne = new Player("Player 1");
		playerTwo = new Player("Player 2");
		checkers = new int[8][8];
		playerValue = 0;
		EMPTY_CHECKER = 0;
		BLACK_CHECKER = 1;
		RED_CHECKER = 2;
		BLACK_KING = 3;
		RED_KING = 4;
		UNPLAYABLE = 5;
	}
	/**
	 * The createNewGameBoard method creates a new game with each checker piece in their starting positions.
	 * Sets up the 8 x 8 checker grid 2D array to the default starting positions.
	 */
	public void createNewGameBoard()
	{
		for (int row = 0; row <= 7; row++)
		{
			for (int column = 0; column <= 7; column++)
			{
				if (row % 2 == column % 2)
				{
					if (row < 3)
					{
						this.checkers[row][column] = this.BLACK_CHECKER;
					}
					else if (row > 4)
					{
						this.checkers[row][column] = this.RED_CHECKER;
					}
					else
					{
						this.checkers[row][column] = this.EMPTY_CHECKER;
					}
				}
				else
				{
					this.checkers[row][column] = this.UNPLAYABLE;
				}
			}
		}
    }
	/**
	 * The checkKing method checks to see if there are any potential checker pieces that can become kings.
	 * It assigns the checker pieces in the 2D array that qualify to become a king.
	 * Returns if a king was created or not.
	 * @return isKing
	 */
	public boolean checkKing()
	{
		boolean isKing = false;
		
		for (int row = 0; row < this.checkers.length; row++)
		{
			for (int column = 0; column < this.checkers[row].length; column++)
			{
				if (row == 7 && this.checkers[row][column] == this.BLACK_CHECKER)
				{
					this.checkers[row][column] = this.BLACK_KING;
					isKing = true;
				}
				else if (row == 0 && this.checkers[row][column] == this.RED_CHECKER)
				{
					this.checkers[row][column] = this.RED_KING;
					isKing = true;
				}
			}
		}
		return isKing;
	}
	/**
	 * The checkGameOver method checks to see if the game has ended.
	 * If the game has ended it returns the player value of the player that is the winner, otherwise it returns 0.
	 * @return playerValue
	 */
	public int checkGameOver() 
	{
		int playerValue = 0;
		int blackCheckerCount = 0;
		int redCheckerCount = 0;
		
		for (int row = 0; row < this.checkers.length; row++)
		{
			for (int column = 0; column < this.checkers[row].length; column++)
			{
				if (this.checkers[row][column] != 2 && this.checkers[row][column] != 4)
				{
					redCheckerCount++;
				}
				if (this.checkers[row][column] != 1 && this.checkers[row][column] != 3)
				{
					blackCheckerCount++;
				}
			}
		}
		if (blackCheckerCount == 64)
		{
			playerValue = 2;
		}
		else if (redCheckerCount == 64)
		{
			playerValue = 1;
		}
		return playerValue;
	}
	/**
	 * The getCheckerType method takes a row and column value.
	 * Returns the type of checker that is at the specific spot in the 2D array.
	 * @param row - Row value.
	 * @param column - Column value.
	 * @return checkerType
	 */
	public int getCheckerType(int row, int column)
	{
		int checkerType = 0;
		
		if (column >= 0 && column < 8 && row >= 0 && row < 8)
		{
			checkerType = this.checkers[row][column];
		}
		else
		{
			checkerType = this.UNPLAYABLE;
		}
		return checkerType;
	}
	/**
	 * The setNewCheckerType method takes in a row, column, and checker type value.
	 * Assigns the new checker type value to the value at the row and column position of the 2D array.
	 * @param row
	 * @param column
	 * @param checkerType
	 */
	public void setNewCheckerType(int row, int column, int checkerType)
	{
		this.checkers[row][column] = checkerType;
	}
	/**
	 * The getPlayerOne method returns this instance of the playerOne.
	 * @return playerOne
	 */
	public Player getPlayerOne()
	{
		return this.playerOne;
	}
	/**
	 * The getPlayerTwo method returns this instance of the playerTwo.
	 * @return playerTwo
	 */
	public Player getPlayerTwo()
	{
		return this.playerTwo;
	}
	/**
	 * The getPlayerValue method returns this instance of the playerValue.
	 * @return playerValue
	 */
	public int getPlayerValue() 
	{
		return this.playerValue;
	}
	/**
	 * The setPlayerValue method takes a new player value and sets the existing player value variable to the new value.
	 * @param newPlayerValue - Player value.
	 */
	public void setPlayerValue(int newPlayerValue)
	{
		this.playerValue = newPlayerValue;
	}
	
}