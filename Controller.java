import java.awt.event.*;
import javax.swing.*;
/**
 * Controller.java - APR-24-2016 - ITEC 220: Project 5 -
 * This class contains methods that control the flow and the logic of the game.
 * Implements the ActionListener and MouseListener interfaces.
 * @author Tre Haga
 * @version 1.0
 */
public class Controller implements ActionListener, MouseListener
{
	/**
	 * The Controller class instance variables.
	 * Declares an instance of the View and Audio classes.
	 * Declares two boolean and integer variables.
	 */
	private View view;
	private Audio audio;
	private boolean isSelected, playSounds;
	private int savedRow, savedColumn;
	/**
	 * The Controller class constructor.
	 * Initializes a new instance of the View and Audio classes. (Access to the View and Audio class instance variables).
	 * Initializes two new boolean and integer variables. (Selection detection switch, sound switch, saved row and column values).
	 * Assigns each button component from the View class a new ActionListener.
	 * Assigns the game board from the View class a new MouseListener.
	 * Creates a new game, plays the music, and displays the greeting message.
	 */
	public Controller()
	{
		view = new View();
		audio = new Audio();
		isSelected = false;
		playSounds = true;
		savedRow = 0;
		savedColumn = 0;
		
		this.view.getNewGameButton().addActionListener(this);
		this.view.getPlayerNamesButton().addActionListener(this);
		this.view.getSoundButton().addActionListener(this);
		this.view.getMusicButton().addActionListener(this);
		this.view.getGameBoard().addMouseListener(this);
		this.view.getGameBoard().newGame();
		this.audio.playWaitingMusic();
		this.view.displayGreetingDialog();
		this.view.displayPlayerNamesDialog();
		this.audio.stopWaitingMusic();
		this.audio.playBackgroundMusic();
		
		if (this.view.getGameBoard().getGameData().getPlayerValue() == 1)
    	{
			this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + "'s turn!");
    	}
    	else if (this.view.getGameBoard().getGameData().getPlayerValue() == 2)
    	{
    		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + "'s turn!");
    	}
	}
	/**
	 * The actionPerformed method takes an event and checks to see if a specific event has occurred.
	 * Once a specific event has been found, it then executes the action assigned to that event.
	 */
	public void actionPerformed(ActionEvent event)
	{
		Object eventSource = event.getSource();
		
		if (eventSource == this.view.getNewGameButton())
		{
			if (this.audio.isMusicPlaying())
            {
                this.audio.stopBackgroundMusic();
                this.audio.playWaitingMusic();
    			this.view.displayNewGameDialog();
    			this.audio.stopWaitingMusic();
    			this.audio.playBackgroundMusic();
            }
			else
			{
				this.view.displayNewGameDialog();
			}
		}
		if (eventSource == this.view.getPlayerNamesButton())
		{
			if (this.audio.isMusicPlaying())
            {
                this.audio.stopBackgroundMusic();
                this.audio.playWaitingMusic();
                this.view.displayPlayerNamesDialog();
    			this.audio.stopWaitingMusic();
    			this.audio.playBackgroundMusic();
            }
			else
			{
				this.view.displayPlayerNamesDialog();
			}
		}
		if (eventSource == this.view.getSoundButton())
		{
			if (this.playSounds)
			{
				this.playSounds = false;
				this.view.getSoundButton().setText("PLAY SOUNDS");
			}
			else
			{
				this.playSounds = true;
				this.view.getSoundButton().setText("STOP SOUNDS");
			}
		}
		if (eventSource == this.view.getMusicButton())
		{
			if (this.audio.isMusicPlaying())
            {
                this.audio.stopBackgroundMusic();
                this.view.getMusicButton().setText("PLAY MUSIC");
            }
            else
            {
                this.audio.playBackgroundMusic();
                this.view.getMusicButton().setText("STOP MUSIC");
            }
		}
	}
	/**
	 * The mousePressed method takes an event and checks to see if a specific event has occurred when the mouse button has been pressed.
	 * If the mouse has been pressed, it then executes the action for that specific location where the mouse button has been pressed.
	 * Gathers and records the exact position on the game board where the mouse button has been pressed.
	 * Manages the logic for all legal checker moves and executes methods tied to those actions accordingly.
	 */
	public void mousePressed(MouseEvent event)
	{
		int column = (event.getX()) / 100;
        int row = (event.getY()) / 100;

    	if (this.view.getGameBoard().getGameData().getPlayerValue() == 1)
        {
        	if (row >= 0 && row <= 7 && column >= 0 && column <= 7 && this.view.getGameBoard().getGameData().getCheckerType(row, column) == 1 || this.view.getGameBoard().getGameData().getCheckerType(row, column) == 3)
        	{
        		if (this.playSounds)
        		{
        			this.audio.playClickSoundEffect();
        		}
        		if (this.view.getGameBoard().getGameData().getCheckerType(row, column) != 5 && this.view.getGameBoard().getGameData().getCheckerType(row, column) != 2)
        		{
        			this.savedRow = row;
                    this.savedColumn = column;
                    this.isSelected = true;
                    this.view.getGameBoard().setSelected(true);
                    this.view.getGameBoard().setSelectedRow(row);
                    this.view.getGameBoard().setSelectedColumn(column);
                    this.view.getGameBoard().repaint();
                    
                    if (this.view.getGameBoard().isValidMove())
            		{
                    	this.view.getGameBoard().setValidMove(false);
            		}
        		}
        	}
        	else if (row >= 0 && row <= 7 && column >= 0 && column <= 7 && this.view.getGameBoard().getGameData().getCheckerType(row, column) == 0)
        	{
        		if (this.isSelected && this.view.getGameBoard().isValidMove())
            	{
            		if (row >= 0 && row <= 7 && column >= 0 && column <= 7 && view.getGameBoard().getGameData().getCheckerType(row, column) != 1)	
                    {
            			if (this.view.getGameBoard().getGameData().getCheckerType(this.savedRow, this.savedColumn) == 1)
            			{
            				if (row - 1 == this.savedRow && column - 1 == this.savedColumn || row - 2 == this.savedRow && column - 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow + 1, this.savedColumn + 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 1);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 1);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
                				
                				this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(2);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + "'s turn!");
                            	
                            	if (this.view.getGameBoard().getGameData().checkKing() && this.playSounds)
                    			{
                            		this.audio.playKingSoundEffect();
                    			}
                            	if (this.view.getGameBoard().getGameData().checkGameOver() == 1)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + " is the winner!");
                            	}
                			}
                			else if (row - 1 == this.savedRow && column + 1 == this.savedColumn || row - 2 == this.savedRow && column + 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow + 1, this.savedColumn - 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 1);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 1);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
            					
                            	this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(2);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + "'s turn!");
                            	
                            	if (this.view.getGameBoard().getGameData().checkKing() && this.playSounds)
                    			{
                            		this.audio.playKingSoundEffect();
                    			}
                            	if (this.view.getGameBoard().getGameData().checkGameOver() == 1)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + " is the winner!");
                            	}
                			}
                			else
                			{
                				this.view.getGameMessageLabel().setText("Invalid move!");
                			}
            			}
            			else if (this.view.getGameBoard().getGameData().getCheckerType(this.savedRow, this.savedColumn) == 3)
            			{
            				if (row - 1 == this.savedRow && column - 1 == this.savedColumn || row - 2 == this.savedRow && column - 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow + 1, this.savedColumn + 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 3);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 3);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
                				
                				this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(2);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + "'s turn!");
                            	
                            	if (this.view.getGameBoard().getGameData().checkGameOver() == 1)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + " is the winner!");
                            	}
                			}
                			else if (row - 1 == this.savedRow && column + 1 == this.savedColumn || row - 2 == this.savedRow && column + 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow + 1, this.savedColumn - 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 3);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 3);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
            					
                            	this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(2);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + "'s turn!");
                            	
                            	if (this.view.getGameBoard().getGameData().checkGameOver() == 1)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + " is the winner!");
                            	}
                			}
                			else if (row + 1 == this.savedRow && column - 1 == this.savedColumn || row + 2 == this.savedRow && column - 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow - 1, this.savedColumn + 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 3);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 3);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
                				
                				this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(2);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + "'s turn!");
                            	
                                if (this.view.getGameBoard().getGameData().checkGameOver() == 1)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + " is the winner!");
                            	}
                			}
                			else if (row + 1 == this.savedRow && column + 1 == this.savedColumn || row + 2 == this.savedRow && column + 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow - 1, this.savedColumn - 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 3);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 3);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
            					
                            	this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(2);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + "'s turn!");
                            	
                                if (this.view.getGameBoard().getGameData().checkGameOver() == 1)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + " is the winner!");
                            	}
                			}
                			else
                			{
                				this.view.getGameMessageLabel().setText("Invalid move!");
                			}
            			}
                    }
            	}
        	}
        }
        if (this.view.getGameBoard().getGameData().getPlayerValue() == 2)
        {
        	if (row >= 0 && row <= 7 && column >= 0 && column <= 7 && this.view.getGameBoard().getGameData().getCheckerType(row, column) == 2 || this.view.getGameBoard().getGameData().getCheckerType(row, column) == 4)
        	{
        		if (this.playSounds)
        		{
        			this.audio.playClickSoundEffect();
        		}
        		if (this.view.getGameBoard().getGameData().getCheckerType(row, column) != 5 && this.view.getGameBoard().getGameData().getCheckerType(row, column) != 1)
        		{
        			this.savedRow = row;
                    this.savedColumn = column;
                    this.isSelected = true;
                    this.view.getGameBoard().setSelected(true);
                    this.view.getGameBoard().setSelectedRow(row);
                    this.view.getGameBoard().setSelectedColumn(column);
                    this.view.getGameBoard().repaint();
                    
                    if (this.view.getGameBoard().isValidMove())
            		{
                    	this.view.getGameBoard().setValidMove(false);
            		}
        		}
        	}
        	else if (row >= 0 && row <= 7 && column >= 0 && column <= 7 && this.view.getGameBoard().getGameData().getCheckerType(row, column) == 0)
        	{
        		if (this.isSelected && this.view.getGameBoard().isValidMove())
            	{
            		if (row >= 0 && row <= 7 && column >= 0 && column <= 7 && view.getGameBoard().getGameData().getCheckerType(row, column) != 2)	
                    {
            			if (this.view.getGameBoard().getGameData().getCheckerType(this.savedRow, this.savedColumn) == 2)
            			{
            				if (row + 1 == this.savedRow && column - 1 == this.savedColumn || row + 2 == this.savedRow && column - 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow - 1, this.savedColumn + 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 2);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 2);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
                				
                				this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(1);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + "'s turn!");
                            	
                            	if (this.view.getGameBoard().getGameData().checkKing() && this.playSounds)
                    			{
                            		this.audio.playKingSoundEffect();
                    			}
                            	if (this.view.getGameBoard().getGameData().checkGameOver() == 2)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + " is the winner!");
                            	}
                			}
                			else if (row + 1 == this.savedRow && column + 1 == this.savedColumn || row + 2 == this.savedRow && column + 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow - 1, this.savedColumn - 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 2);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 2);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
            					
                            	this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(1);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + "'s turn!");
                            	
                            	if (this.view.getGameBoard().getGameData().checkKing() && this.playSounds)
                    			{
                            		this.audio.playKingSoundEffect();
                    			}
                            	if (this.view.getGameBoard().getGameData().checkGameOver() == 2)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + " is the winner!");
                            	}
                			}
                			else
                			{
                				this.view.getGameMessageLabel().setText("Invalid move!");
                			}
            			}
            			else if (this.view.getGameBoard().getGameData().getCheckerType(this.savedRow, this.savedColumn) == 4)
            			{
            				if (row - 1 == this.savedRow && column - 1 == this.savedColumn || row - 2 == this.savedRow && column - 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow + 1, this.savedColumn + 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 4);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 4);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
                				
                				this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(1);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + "'s turn!");
                            	
                            	if (this.view.getGameBoard().getGameData().checkGameOver() == 2)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + " is the winner!");
                            	}
                			}
                			else if (row - 1 == this.savedRow && column + 1 == this.savedColumn || row - 2 == this.savedRow && column + 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow + 1, this.savedColumn - 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 4);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 4);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
            					
                            	this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(1);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + "'s turn!");
                            	
                            	if (this.view.getGameBoard().getGameData().checkGameOver() == 2)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + " is the winner!");
                            	}
                			}
                			else if (row + 1 == this.savedRow && column - 1 == this.savedColumn || row + 2 == this.savedRow && column - 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow - 1, this.savedColumn + 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 4);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 4);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
                				
                				this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(1);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + "'s turn!");
                            	
                                if (this.view.getGameBoard().getGameData().checkGameOver() == 2)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + " is the winner!");
                            	}
                			}
                			else if (row + 1 == this.savedRow && column + 1 == this.savedColumn || row + 2 == this.savedRow && column + 2 == this.savedColumn && this.view.getGameBoard().getJump())
                			{
                				if (this.view.getGameBoard().getJump())
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow - 1, this.savedColumn - 1, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 4);
                                	this.view.getGameBoard().setJump(false);
                				}
                				else
                				{
                					this.view.getGameBoard().getGameData().setNewCheckerType(row, column, 4);
                                	this.view.getGameBoard().getGameData().setNewCheckerType(this.savedRow, this.savedColumn, 0);
                				}
            					
                            	this.view.getGameBoard().setSelected(false);
                            	this.view.getGameBoard().repaint();
                            	this.isSelected = false;
                            	this.view.getGameBoard().getGameData().setPlayerValue(1);
                            	this.view.getGameBoard().setValidMove(false);
                            	this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerOne().getName() + "'s turn!");
                            	
                                if (this.view.getGameBoard().getGameData().checkGameOver() == 2)
                            	{
                            		this.view.getGameMessageLabel().setText(this.view.getGameBoard().getGameData().getPlayerTwo().getName() + " is the winner!");
                            	}
                			}
                			else
                			{
                				this.view.getGameMessageLabel().setText("Invalid move!");
                			}
            			}
                    }
            	}
        	}
        }
	}
	/**
	 * The mouseClicked method takes and event and checks to see if the mouse button has been clicked.
	 * A part of the MouseListener interface agreement and is not used for this program.
	 */
	public void mouseClicked(MouseEvent event)
	{
		
	}
	/**
	 * The mouseEntered method takes and event and checks to see if the mouse cursor has entered at a specific spot.
	 * A part of the MouseListener interface agreement and is not used for this program.
	 */
	public void mouseEntered(MouseEvent event)
	{
		
	}
	/**
	 * The mouseExited method takes and event and checks to see if the mouse cursor has exited at a specific spot.
	 * A part of the MouseListener interface agreement and is not used for this program.
	 */
	public void mouseExited(MouseEvent event)
	{
		
	}
	/**
	 * The mouseReleased method takes and event and checks to see if the mouse button has been released.
	 * A part of the MouseListener interface agreement and is not used for this program.
	 */
	public void mouseReleased(MouseEvent event)
	{
		
	}
}