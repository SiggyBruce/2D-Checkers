import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * View.java - APR-24-2016 - ITEC 220: Project 5 -
 * This class contains methods that make up what the user visually sees for the game.
 * Inherits methods from the JPanel class.
 * @author Tre Haga
 * @version 1.0
 */
public class View extends JPanel
{
	/**
	 * The View class instance variables.
	 * Declares an instance of the JFrame, Border, and GameBoard classes. (The GUI frame, border styling, and access to GameBoard instance variables).
	 * Declares instances of the JButton and JLabel classes. (Game buttons and labels).
	 * Declares two constant integer variables. (The width and height of the game's GUI).
	 */
	private JFrame frame;
	private JButton newGameButton, playerNamesButton, soundButton, musicButton;
	private JLabel gameMessageLabel, playerOneLabel, playerTwoLabel;
	private Border border;
	private GameBoard gameBoard;
	private final int WIDTH, HEIGHT;
	/**
	 * The View class constructor.
	 * Initializes and formats a new instance of the JFrame, Border, and GameBoard classes.
	 * Initializes and formats new instances of the JButton and JLabel classes.
	 * Initializes two new constant integer variables.
	 */
	public View()
	{
		frame = new JFrame("2D Checkers v1.0");
        	newGameButton = new JButton("NEW GAME");
        	playerNamesButton = new JButton("PLAYER NAMES");
        	soundButton = new JButton("STOP SOUNDS");
        	musicButton = new JButton("STOP MUSIC");
        	gameMessageLabel = new JLabel("", SwingConstants.CENTER);
        	playerOneLabel = new JLabel("PLAYER 1", SwingConstants.CENTER);
        	playerTwoLabel = new JLabel("PLAYER 2", SwingConstants.CENTER);
        	border = BorderFactory.createLineBorder(Color.BLACK, 1);
        	gameBoard = new GameBoard();
		WIDTH = 1290;
		HEIGHT = 915;
		
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(new Color(0, 200, 200));
			
		this.frame.setVisible(true);
		this.frame.setContentPane(this);
		this.frame.pack();
		this.frame.setSize(WIDTH, HEIGHT);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.frame.setResizable(false);
	    
	    try
	    {
	    	this.frame.setIconImage(ImageIO.read(new File("images/icon.png")));
	    }
	    catch (IOException exception)
	    {
	    	System.out.println("Failed to load the icon image.");
	    }
	    
	    this.newGameButton.setVisible(true);
	    this.newGameButton.setSize(365, 60);
        this.newGameButton.setLocation(880, 40);
        this.newGameButton.setFocusable(false);
        this.newGameButton.setBorder(border);
        this.newGameButton.setFont(new Font("Courier New", Font.BOLD, 30));
        this.newGameButton.setOpaque(true);
        this.newGameButton.setBackground(Color.LIGHT_GRAY);
        this.add(newGameButton);
        
        this.playerNamesButton.setVisible(true);
        this.playerNamesButton.setSize(365, 60);
        this.playerNamesButton.setLocation(880, 140);
        this.playerNamesButton.setFocusable(false);
        this.playerNamesButton.setBorder(border);
        this.playerNamesButton.setFont(new Font("Courier New", Font.BOLD, 30));
        this.playerNamesButton.setOpaque(true);
        this.playerNamesButton.setBackground(Color.LIGHT_GRAY);
        this.add(playerNamesButton);
        
        this.playerOneLabel.setVisible(true);
        this.playerOneLabel.setSize(365, 60);
        this.playerOneLabel.setLocation(880, 240);
        this.playerOneLabel.setFocusable(false);
        this.playerOneLabel.setBorder(border);
        this.playerOneLabel.setOpaque(true);
        this.playerOneLabel.setBackground(Color.BLACK);
        this.playerOneLabel.setForeground(Color.WHITE);
        this.playerOneLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        this.add(playerOneLabel);
        
        this.gameMessageLabel.setVisible(true);
        this.gameMessageLabel.setSize(365, 282);
        this.gameMessageLabel.setLocation(880, 299);
        this.gameMessageLabel.setFocusable(false);
        this.gameMessageLabel.setBorder(border);
        this.gameMessageLabel.setOpaque(true);
        this.gameMessageLabel.setBackground(Color.LIGHT_GRAY);
        this.gameMessageLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        this.add(gameMessageLabel);
        
        this.playerTwoLabel.setVisible(true);
        this.playerTwoLabel.setSize(365, 60);
        this.playerTwoLabel.setLocation(880, 580);
        this.playerTwoLabel.setFocusable(false);
        this.playerTwoLabel.setBorder(border);
        this.playerTwoLabel.setOpaque(true);
        this.playerTwoLabel.setBackground(Color.RED);
        this.playerTwoLabel.setForeground(Color.WHITE);
        this.playerTwoLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        this.add(playerTwoLabel);
        
        this.soundButton.setVisible(true);
        this.soundButton.setSize(365, 60);
        this.soundButton.setLocation(880, 680);
        this.soundButton.setFocusable(false);
        this.soundButton.setBorder(border);
        this.soundButton.setFont(new Font("Courier New", Font.BOLD, 30));
        this.soundButton.setOpaque(true);
        this.soundButton.setBackground(Color.LIGHT_GRAY);
        this.add(soundButton);
        
        this.musicButton.setVisible(true);
        this.musicButton.setSize(365, 60);
        this.musicButton.setLocation(880, 780);
        this.musicButton.setFocusable(false);
        this.musicButton.setBorder(border);
        this.musicButton.setFont(new Font("Courier New", Font.BOLD, 30));
        this.musicButton.setOpaque(true);
        this.musicButton.setBackground(Color.LIGHT_GRAY);
        this.add(musicButton);
        
        this.gameBoard.setBounds(40, 40, 801, 801);
        this.add(gameBoard);
	}
	/**
	 * The displayGreetingDialog method displays the greeting dialog message upon first running the game.
	 * Contains all copyright information on items used for this program.
	 */
	public void displayGreetingDialog()
	{
		JComponent[] components = new JComponent[7];
		
		components[0] = new JLabel("Created by Treemont Haga - ITEC 220: Project 5", SwingConstants.CENTER);
    	components[1] = new JLabel("Music Credit:", SwingConstants.CENTER);
    	components[2] = new JLabel("Pixelland and Show Your Moves by Kevin MacLeod (http://incompetech.com)", SwingConstants.CENTER);
    	components[3] = new JLabel("Sound Effects Credit:", SwingConstants.CENTER);
    	components[4] = new JLabel("Tiny Button Push Sound (http://soundbible.com)", SwingConstants.CENTER);
    	components[5] = new JLabel("Winning Triumphal Fanfare Sound (http://soundbible.com)", SwingConstants.CENTER);
    	components[6] = new JLabel("Licensed under Creative Commons: By Attribution 3.0 License", SwingConstants.CENTER);
    	
		JOptionPane.showMessageDialog(this, components, "Welcome!", JOptionPane.PLAIN_MESSAGE);
	}
	/**
	 * The displayPlayerNamesDialog method displays a dialog window to gather user input on player names.
	 * Sets the player names for the game to what names you give the dialog window.
	 */
	public void displayPlayerNamesDialog()
    {
		JComponent[] components = new JComponent[4];
    	JTextField playerOneInput = new JTextField();
    	JTextField playerTwoInput = new JTextField();
    	
    	components[0] = new JLabel("Player 1:");
    	components[1] = playerOneInput;
    	components[2] = new JLabel("Player 2:");
    	components[3] = playerTwoInput;
    	
    	JOptionPane.showMessageDialog(this, components, "Player Names", JOptionPane.PLAIN_MESSAGE);
    	
    	this.getPlayerOneLabel().setText(playerOneInput.getText());
    	this.getPlayerTwoLabel().setText(playerTwoInput.getText());
    	this.gameBoard.getGameData().getPlayerOne().setName(playerOneInput.getText());
    	this.gameBoard.getGameData().getPlayerTwo().setName(playerTwoInput.getText());
    	
    	if (this.gameBoard.getGameData().getPlayerValue() == 1)
    	{
    		this.gameMessageLabel.setText(this.gameBoard.getGameData().getPlayerOne().getName() + "'s turn!");
    	}
    	else if (this.gameBoard.getGameData().getPlayerValue() == 2)
    	{
    		this.gameMessageLabel.setText(this.gameBoard.getGameData().getPlayerTwo().getName() + "'s turn!");
    	}
    }
	/**
	 * The displayNewGameDialog method displays a dialog window to gather user input on a new game.
	 * Prompts the user with a yes or no question on if they wish to reset the game to its original starting state.
	 */
	public void displayNewGameDialog()
	{
		int choice = JOptionPane.showOptionDialog(this, "Are you sure you want to start a new game?", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if (choice == JOptionPane.YES_OPTION)
		{
			this.gameBoard.newGame();
			
			if (this.gameBoard.getGameData().getPlayerValue() == 1)
	    	{
	    		this.gameMessageLabel.setText(this.gameBoard.getGameData().getPlayerOne().getName() + "'s turn!");
	    	}
	    	else if (this.gameBoard.getGameData().getPlayerValue() == 2)
	    	{
	    		this.gameMessageLabel.setText(this.gameBoard.getGameData().getPlayerTwo().getName() + "'s turn!");
	    	}
		}
	}
	/**
	 * The getNewGameButton method returns this instance of the newGameButton.
	 * @return newGameButton
	 */
	public JButton getNewGameButton()
	{
		return this.newGameButton;
	}
	/**
	 * The getPlayerNamesButton method returns this instance of the playerNamesButton.
	 * @return playerNamesButton
	 */
	public JButton getPlayerNamesButton()
	{
		return this.playerNamesButton;
	}
	/**
	 * The getSoundButton method returns this instance of the soundButton.
	 * @return soundButton
	 */
	public JButton getSoundButton()
	{
		return this.soundButton;
	}
	/**
	 * The getMusicButton method returns this instance of the musicButton.
	 * @return musicButton
	 */
	public JButton getMusicButton()
	{
		return this.musicButton;
	}
	/**
	 * The getPlayerOneLabel method returns this instance of the playerOneLabel.
	 * @return playerOneLabel
	 */
	public JLabel getPlayerOneLabel()
	{
		return this.playerOneLabel;
	}
	/**
	 * The getPlayerTwoLabel method returns this instance of the playerTwoLabel.
	 * @return playerTwoLabel
	 */
	public JLabel getPlayerTwoLabel()
	{
		return this.playerTwoLabel;
	}
	/**
	 * The getGameMessageLabel method returns this instance of the gameMessageLabel.
	 * @return gameMessageLabel
	 */
	public JLabel getGameMessageLabel()
	{
		return this.gameMessageLabel;
	}
	/**
	 * The getGameBoard method returns this instance of the gameBoard.
	 * @return playerOneLabel
	 */
	public GameBoard getGameBoard()
	{
		return this.gameBoard;
	}
}