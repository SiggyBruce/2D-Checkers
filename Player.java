/**
 * Player.java - APR-24-2016 - ITEC 220: Project 5 -
 * This class contains all of the data about a player playing the game.
 * @author Tre Haga
 * @version 1.0
 */
public class Player
{
	/**
	 * The Player class instance variables.
	 * Declares a string variable. (Player name).
	 */
	private String name;
	/**
	 * The Player class constructor.
	 * Takes in a new name value and initializes the new string variable to the new name value.
	 * @param newName - Name value.
	 */
	public Player(String newName)
	{
		name = newName;
	}
	/**
	 * The getName method returns this instance of the name.
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 * The setName method takes in a new name value and sets the existing name variable to the new value.
	 * @param newName - Name value.
	 */
	public void setName(String newName)
	{
		this.name = newName;
	}
}