import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * GameBoard.java - APR-24-2016 - ITEC 220: Project 5 -
 * This class contains the graphics and visual display of the game board and all of its components.
 * Inherits methods from the JPanel class.
 * @author Tre Haga
 * @version 1.0
 */
public class GameBoard extends JPanel
{
	/**
	 * The GameBoard class instance variables.
	 * Declares and instance of the GameData class. (Access to the GameData instance variables).
	 * Declares instances of the BufferedImage class. (The four checker images: black checker, red checker, black king, and red king).
	 * Declares three boolean and two integer variables. (Selection detection switch, valid move switch, jumping move switch, selected rows, and selected columns).
	 */
	private GameData gameData;
	private boolean isSelected, isValid, isJump;
	private int selectedRow, selectedColumn;
	private BufferedImage blackChecker, redChecker, blackKing, redKing;
	/**
	 * The GameBoard class constructor.
	 * Initializes a new instance of the GameData class.
	 * Initializes new instances of the BufferedImage class.
	 * Initializes three new boolean and two new integer variables.
	 */
	public GameBoard()
	{
		gameData = new GameData();
		isSelected = false;
		isValid = false;
		isJump = false;
		selectedRow = 0;
		selectedColumn = 0;
		
		try
		{
			blackChecker = ImageIO.read(new File("images/black_checker_basic.png"));
			redChecker = ImageIO.read(new File("images/red_checker_basic.png"));
			blackKing = ImageIO.read(new File("images/black_checker_king.png"));
			redKing = ImageIO.read(new File("images/red_checker_king.png"));
		}
		catch (IOException exception)
		{
			System.out.print("Failed to load all image files.");
		}
		
		this.setVisible(true);
		this.setBackground(Color.BLACK);
	}
	/**
	 * The paintComponent method visually displays the graphics for the game board.
	 * Creates an 8 x 8 grid of colored squares.
	 * Places and draws the instances of the BufferedImages (checker pieces) at the appropriate locations.
	 * Highlights the selected square in a green box using data gathered by the Controller class.
	 * Highlights all possible legal moves the selected square can have with a cyan colored box.
	 */
	public void paintComponent(Graphics graphics)
	{
		int verticalSize = 0;
		int horizontalSize = 0;
		
		for (int row = 0; row < 8; row++)
		{
			for (int column = 0; column < 8; column++)
			{
				if (row % 2 == column % 2)
				{
					graphics.setColor(Color.GRAY);
				}
				else
				{
					graphics.setColor(Color.LIGHT_GRAY);
				}
				
				graphics.fillRect(horizontalSize, verticalSize, 100, 100);
				graphics.setColor(Color.BLACK);
				graphics.drawRect(horizontalSize, verticalSize, 100, 100);
				
				if (this.gameData.getCheckerType(column, row) == 1)
				{
					graphics.drawImage(this.blackChecker, horizontalSize + 1, verticalSize + 1, null);
				}
				else if (this.gameData.getCheckerType(column, row) == 2)
				{
					graphics.drawImage(this.redChecker, horizontalSize + 1, verticalSize + 1, null);
				}
				else if (this.gameData.getCheckerType(column, row) == 3)
				{
					graphics.drawImage(this.blackKing, horizontalSize + 1, verticalSize + 1, null);
				}
				else if (this.gameData.getCheckerType(column, row) == 4)
				{
					graphics.drawImage(this.redKing, horizontalSize + 1, verticalSize + 1, null);
				}
				verticalSize += 100;
			}
			verticalSize = 0;
			horizontalSize += 100;
		}
		if (this.isSelected && this.gameData.getCheckerType(this.selectedRow, this.selectedColumn) != 5)
		{
			graphics.setColor(Color.GREEN);
	        graphics.drawRect(1 + this.selectedColumn * 100, 1 + this.selectedRow * 100, 98, 98);
	        graphics.drawRect(2 + this.selectedColumn * 100, 2 + this.selectedRow * 100, 96, 96);
	        graphics.drawRect(3 + this.selectedColumn * 100, 3 + this.selectedRow * 100, 94, 94);
	        graphics.drawRect(4 + this.selectedColumn * 100, 4 + this.selectedRow * 100, 92, 92);

	        if (this.gameData.getCheckerType(this.selectedRow, this.selectedColumn) == 1)
	        {
	        	if (this.selectedRow >= 0 && this.selectedColumn <= 7)
        		{
	        		if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) == 0 && this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) != 5)
	        		{
	        			graphics.setColor(Color.CYAN);
	        	        graphics.drawRect(1 + (this.selectedColumn + 1) * 100, 1 + (this.selectedRow + 1) * 100, 98, 98);
	        	        graphics.drawRect(2 + (this.selectedColumn + 1) * 100, 2 + (this.selectedRow + 1) * 100, 96, 96);
	        	        graphics.drawRect(3 + (this.selectedColumn + 1) * 100, 3 + (this.selectedRow + 1) * 100, 94, 94);
	        	        graphics.drawRect(4 + (this.selectedColumn + 1) * 100, 4 + (this.selectedRow + 1) * 100, 92, 92);
	        	        this.isValid = true;
	        		}
        	        if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) == 0 && this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) != 5)
        	        {
            	        graphics.setColor(Color.CYAN);
            			graphics.drawRect(1 + (this.selectedColumn - 1) * 100, 1 + (this.selectedRow + 1) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn - 1) * 100, 2 + (this.selectedRow + 1) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn - 1) * 100, 3 + (this.selectedRow + 1) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn - 1) * 100, 4 + (this.selectedRow + 1) * 100, 92, 92);
            	        this.isValid = true;
        	        }
        	        if (this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn - 2) == 0 && this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn - 2) != 5)
        			{
        	        	if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) == 2)
        	        	{
                			graphics.setColor(Color.CYAN);
                	        graphics.drawRect(1 + (this.selectedColumn - 2) * 100, 1 + (this.selectedRow + 2) * 100, 98, 98);
                	        graphics.drawRect(2 + (this.selectedColumn - 2) * 100, 2 + (this.selectedRow + 2) * 100, 96, 96);
                	        graphics.drawRect(3 + (this.selectedColumn - 2) * 100, 3 + (this.selectedRow + 2) * 100, 94, 94);
                	        graphics.drawRect(4 + (this.selectedColumn - 2) * 100, 4 + (this.selectedRow + 2) * 100, 92, 92);
                	        this.isValid = true;
                	        this.isJump = true;
        	        	}
        			}
	        		if (this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn + 2) == 0 && this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn + 2) != 5)
        			{
	        			if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) == 2)
	        			{
	            			graphics.setColor(Color.CYAN);
	            			graphics.drawRect(1 + (this.selectedColumn + 2) * 100, 1 + (this.selectedRow + 2) * 100, 98, 98);
	            	        graphics.drawRect(2 + (this.selectedColumn + 2) * 100, 2 + (this.selectedRow + 2) * 100, 96, 96);
	            	        graphics.drawRect(3 + (this.selectedColumn + 2) * 100, 3 + (this.selectedRow + 2) * 100, 94, 94);
	            	        graphics.drawRect(4 + (this.selectedColumn + 2) * 100, 4 + (this.selectedRow + 2) * 100, 92, 92);
	            	        this.isValid = true;
	            	        this.isJump = true;
	        			}
        			}
        		}
        	}
	        else if (this.gameData.getCheckerType(this.selectedRow, this.selectedColumn) == 2) 
	        {
        		if (this.selectedRow >= 0 && this.selectedColumn <= 7)
        		{
        			if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) == 0 && this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) != 5)
        			{
            			graphics.setColor(Color.CYAN);
            	        graphics.drawRect(1 + (this.selectedColumn - 1) * 100, 1 + (this.selectedRow - 1) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn - 1) * 100, 2 + (this.selectedRow - 1) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn - 1) * 100, 3 + (this.selectedRow - 1) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn - 1) * 100, 4 + (this.selectedRow - 1) * 100, 92, 92);
            	        this.isValid = true;
        			}
        			if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) == 0 && this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) != 5)
        	        {
        				graphics.setColor(Color.CYAN);
        				graphics.drawRect(1 + (this.selectedColumn + 1) * 100, 1 + (this.selectedRow - 1) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn + 1) * 100, 2 + (this.selectedRow - 1) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn + 1) * 100, 3 + (this.selectedRow - 1) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn + 1) * 100, 4 + (this.selectedRow - 1) * 100, 92, 92);
            	        this.isValid = true;
        	        }
        			if (this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn - 2) == 0 && this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn - 2) != 5)
        			{
        	        	if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) == 1)
        	        	{
                			graphics.setColor(Color.CYAN);
                	        graphics.drawRect(1 + (this.selectedColumn - 2) * 100, 1 + (this.selectedRow - 2) * 100, 98, 98);
                	        graphics.drawRect(2 + (this.selectedColumn - 2) * 100, 2 + (this.selectedRow - 2) * 100, 96, 96);
                	        graphics.drawRect(3 + (this.selectedColumn - 2) * 100, 3 + (this.selectedRow - 2) * 100, 94, 94);
                	        graphics.drawRect(4 + (this.selectedColumn - 2) * 100, 4 + (this.selectedRow - 2) * 100, 92, 92);
                	        this.isValid = true;
                	        this.isJump = true;
        	        	}
        			}
	        		if (this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn + 2) == 0 && this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn + 2) != 5)
        			{
	        			if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) == 1)
	        			{
	            			graphics.setColor(Color.CYAN);
	            			graphics.drawRect(1 + (this.selectedColumn + 2) * 100, 1 + (this.selectedRow - 2) * 100, 98, 98);
	            	        graphics.drawRect(2 + (this.selectedColumn + 2) * 100, 2 + (this.selectedRow - 2) * 100, 96, 96);
	            	        graphics.drawRect(3 + (this.selectedColumn + 2) * 100, 3 + (this.selectedRow - 2) * 100, 94, 94);
	            	        graphics.drawRect(4 + (this.selectedColumn + 2) * 100, 4 + (this.selectedRow - 2) * 100, 92, 92);
	            	        this.isValid = true;
	            	        this.isJump = true;
	        			}
        			}
        		}
        	}
	        if (this.gameData.getCheckerType(this.selectedRow, this.selectedColumn) == 3)
	        {
	        	if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) == 0 && this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) != 5)
        		{
        			graphics.setColor(Color.CYAN);
        	        graphics.drawRect(1 + (this.selectedColumn + 1) * 100, 1 + (this.selectedRow + 1) * 100, 98, 98);
        	        graphics.drawRect(2 + (this.selectedColumn + 1) * 100, 2 + (this.selectedRow + 1) * 100, 96, 96);
        	        graphics.drawRect(3 + (this.selectedColumn + 1) * 100, 3 + (this.selectedRow + 1) * 100, 94, 94);
        	        graphics.drawRect(4 + (this.selectedColumn + 1) * 100, 4 + (this.selectedRow + 1) * 100, 92, 92);
        	        this.isValid = true;
        		}
    	        if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) == 0 && this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) != 5)
    	        {
        	        graphics.setColor(Color.CYAN);
        			graphics.drawRect(1 + (this.selectedColumn - 1) * 100, 1 + (this.selectedRow + 1) * 100, 98, 98);
        	        graphics.drawRect(2 + (this.selectedColumn - 1) * 100, 2 + (this.selectedRow + 1) * 100, 96, 96);
        	        graphics.drawRect(3 + (this.selectedColumn - 1) * 100, 3 + (this.selectedRow + 1) * 100, 94, 94);
        	        graphics.drawRect(4 + (this.selectedColumn - 1) * 100, 4 + (this.selectedRow + 1) * 100, 92, 92);
        	        this.isValid = true;
    	        }
    	        if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) == 0 && this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) != 5)
    			{
        			graphics.setColor(Color.CYAN);
        	        graphics.drawRect(1 + (this.selectedColumn - 1) * 100, 1 + (this.selectedRow - 1) * 100, 98, 98);
        	        graphics.drawRect(2 + (this.selectedColumn - 1) * 100, 2 + (this.selectedRow - 1) * 100, 96, 96);
        	        graphics.drawRect(3 + (this.selectedColumn - 1) * 100, 3 + (this.selectedRow - 1) * 100, 94, 94);
        	        graphics.drawRect(4 + (this.selectedColumn - 1) * 100, 4 + (this.selectedRow - 1) * 100, 92, 92);
        	        this.isValid = true;
    			}
    			if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) == 0 && this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) != 5)
    	        {
    				graphics.setColor(Color.CYAN);
    				graphics.drawRect(1 + (this.selectedColumn + 1) * 100, 1 + (this.selectedRow - 1) * 100, 98, 98);
        	        graphics.drawRect(2 + (this.selectedColumn + 1) * 100, 2 + (this.selectedRow - 1) * 100, 96, 96);
        	        graphics.drawRect(3 + (this.selectedColumn + 1) * 100, 3 + (this.selectedRow - 1) * 100, 94, 94);
        	        graphics.drawRect(4 + (this.selectedColumn + 1) * 100, 4 + (this.selectedRow - 1) * 100, 92, 92);
        	        this.isValid = true;
    	        }
    			if (this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn - 2) == 0 && this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn - 2) != 5)
    			{
    	        	if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) == 2 || this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) == 4)
    	        	{
            			graphics.setColor(Color.CYAN);
            	        graphics.drawRect(1 + (this.selectedColumn - 2) * 100, 1 + (this.selectedRow + 2) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn - 2) * 100, 2 + (this.selectedRow + 2) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn - 2) * 100, 3 + (this.selectedRow + 2) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn - 2) * 100, 4 + (this.selectedRow + 2) * 100, 92, 92);
            	        this.isValid = true;
            	        this.isJump = true;
    	        	}
    			}
        		if (this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn + 2) == 0 && this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn + 2) != 5)
    			{
        			if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) == 2 || this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) == 4)
        			{
            			graphics.setColor(Color.CYAN);
            			graphics.drawRect(1 + (this.selectedColumn + 2) * 100, 1 + (this.selectedRow + 2) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn + 2) * 100, 2 + (this.selectedRow + 2) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn + 2) * 100, 3 + (this.selectedRow + 2) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn + 2) * 100, 4 + (this.selectedRow + 2) * 100, 92, 92);
            	        this.isValid = true;
            	        this.isJump = true;
        			}
    			}
        		if (this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn - 2) == 0 && this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn - 2) != 5)
    			{
    	        	if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) == 2 || this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) == 4)
    	        	{
            			graphics.setColor(Color.CYAN);
            	        graphics.drawRect(1 + (this.selectedColumn - 2) * 100, 1 + (this.selectedRow - 2) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn - 2) * 100, 2 + (this.selectedRow - 2) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn - 2) * 100, 3 + (this.selectedRow - 2) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn - 2) * 100, 4 + (this.selectedRow - 2) * 100, 92, 92);
            	        this.isValid = true;
            	        this.isJump = true;
    	        	}
    			}
        		if (this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn + 2) == 0 && this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn + 2) != 5)
    			{
        			if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) == 2 || this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) == 4)
        			{
            			graphics.setColor(Color.CYAN);
            			graphics.drawRect(1 + (this.selectedColumn + 2) * 100, 1 + (this.selectedRow - 2) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn + 2) * 100, 2 + (this.selectedRow - 2) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn + 2) * 100, 3 + (this.selectedRow - 2) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn + 2) * 100, 4 + (this.selectedRow - 2) * 100, 92, 92);
            	        this.isValid = true;
            	        this.isJump = true;
        			}
    			}
	        }
	        if (this.gameData.getCheckerType(this.selectedRow, this.selectedColumn) == 4)
	        {
	        	if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) == 0 && this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) != 5)
        		{
        			graphics.setColor(Color.CYAN);
        	        graphics.drawRect(1 + (this.selectedColumn + 1) * 100, 1 + (this.selectedRow + 1) * 100, 98, 98);
        	        graphics.drawRect(2 + (this.selectedColumn + 1) * 100, 2 + (this.selectedRow + 1) * 100, 96, 96);
        	        graphics.drawRect(3 + (this.selectedColumn + 1) * 100, 3 + (this.selectedRow + 1) * 100, 94, 94);
        	        graphics.drawRect(4 + (this.selectedColumn + 1) * 100, 4 + (this.selectedRow + 1) * 100, 92, 92);
        	        this.isValid = true;
        		}
    	        if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) == 0 && this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) != 5)
    	        {
        	        graphics.setColor(Color.CYAN);
        			graphics.drawRect(1 + (this.selectedColumn - 1) * 100, 1 + (this.selectedRow + 1) * 100, 98, 98);
        	        graphics.drawRect(2 + (this.selectedColumn - 1) * 100, 2 + (this.selectedRow + 1) * 100, 96, 96);
        	        graphics.drawRect(3 + (this.selectedColumn - 1) * 100, 3 + (this.selectedRow + 1) * 100, 94, 94);
        	        graphics.drawRect(4 + (this.selectedColumn - 1) * 100, 4 + (this.selectedRow + 1) * 100, 92, 92);
        	        this.isValid = true;
    	        }
    	        if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) == 0 && this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) != 5)
    			{
        			graphics.setColor(Color.CYAN);
        	        graphics.drawRect(1 + (this.selectedColumn - 1) * 100, 1 + (this.selectedRow - 1) * 100, 98, 98);
        	        graphics.drawRect(2 + (this.selectedColumn - 1) * 100, 2 + (this.selectedRow - 1) * 100, 96, 96);
        	        graphics.drawRect(3 + (this.selectedColumn - 1) * 100, 3 + (this.selectedRow - 1) * 100, 94, 94);
        	        graphics.drawRect(4 + (this.selectedColumn - 1) * 100, 4 + (this.selectedRow - 1) * 100, 92, 92);
        	        this.isValid = true;
    			}
    			if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) == 0 && this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) != 5)
    	        {
    				graphics.setColor(Color.CYAN);
    				graphics.drawRect(1 + (this.selectedColumn + 1) * 100, 1 + (this.selectedRow - 1) * 100, 98, 98);
        	        graphics.drawRect(2 + (this.selectedColumn + 1) * 100, 2 + (this.selectedRow - 1) * 100, 96, 96);
        	        graphics.drawRect(3 + (this.selectedColumn + 1) * 100, 3 + (this.selectedRow - 1) * 100, 94, 94);
        	        graphics.drawRect(4 + (this.selectedColumn + 1) * 100, 4 + (this.selectedRow - 1) * 100, 92, 92);
        	        this.isValid = true;
    	        }
    			if (this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn - 2) == 0 && this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn - 2) != 5)
    			{
    	        	if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) == 1 || this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn - 1) == 3)
    	        	{
            			graphics.setColor(Color.CYAN);
            	        graphics.drawRect(1 + (this.selectedColumn - 2) * 100, 1 + (this.selectedRow + 2) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn - 2) * 100, 2 + (this.selectedRow + 2) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn - 2) * 100, 3 + (this.selectedRow + 2) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn - 2) * 100, 4 + (this.selectedRow + 2) * 100, 92, 92);
            	        this.isValid = true;
            	        this.isJump = true;
    	        	}
    			}
        		if (this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn + 2) == 0 && this.gameData.getCheckerType(this.selectedRow + 2, this.selectedColumn + 2) != 5)
    			{
        			if (this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) == 1 || this.gameData.getCheckerType(this.selectedRow + 1, this.selectedColumn + 1) == 3)
        			{
            			graphics.setColor(Color.CYAN);
            			graphics.drawRect(1 + (this.selectedColumn + 2) * 100, 1 + (this.selectedRow + 2) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn + 2) * 100, 2 + (this.selectedRow + 2) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn + 2) * 100, 3 + (this.selectedRow + 2) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn + 2) * 100, 4 + (this.selectedRow + 2) * 100, 92, 92);
            	        this.isValid = true;
            	        this.isJump = true;
        			}
    			}
        		if (this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn - 2) == 0 && this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn - 2) != 5)
    			{
    	        	if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) == 1 || this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn - 1) == 3)
    	        	{
            			graphics.setColor(Color.CYAN);
            	        graphics.drawRect(1 + (this.selectedColumn - 2) * 100, 1 + (this.selectedRow - 2) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn - 2) * 100, 2 + (this.selectedRow - 2) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn - 2) * 100, 3 + (this.selectedRow - 2) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn - 2) * 100, 4 + (this.selectedRow - 2) * 100, 92, 92);
            	        this.isValid = true;
            	        this.isJump = true;
    	        	}
    			}
        		if (this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn + 2) == 0 && this.gameData.getCheckerType(this.selectedRow - 2, this.selectedColumn + 2) != 5)
    			{
        			if (this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) == 1 || this.gameData.getCheckerType(this.selectedRow - 1, this.selectedColumn + 1) == 3)
        			{
            			graphics.setColor(Color.CYAN);
            			graphics.drawRect(1 + (this.selectedColumn + 2) * 100, 1 + (this.selectedRow - 2) * 100, 98, 98);
            	        graphics.drawRect(2 + (this.selectedColumn + 2) * 100, 2 + (this.selectedRow - 2) * 100, 96, 96);
            	        graphics.drawRect(3 + (this.selectedColumn + 2) * 100, 3 + (this.selectedRow - 2) * 100, 94, 94);
            	        graphics.drawRect(4 + (this.selectedColumn + 2) * 100, 4 + (this.selectedRow - 2) * 100, 92, 92);
            	        this.isValid = true;
            	        this.isJump = true;
        			}
    			}
	        }
        }
	}
	/**
	 * The newGame method calls a method from the GameData class to reset the 8 x 8 2D integer array to its original state.
	 * Randomly selects which players goes first and sets the current player value to the randomly selected number.
	 * Updates the graphics.
	 */
	public void newGame()
	{
		Random random = new Random();
		int playerValue = random.nextInt((2 - 1) + 1) + 1;
		this.gameData.createNewGameBoard();
		this.gameData.setPlayerValue(playerValue);
		this.repaint();
	}
	/**
	 * The getGameData method returns this instance of the gameData.
	 * @return gameData
	 */
	public GameData getGameData()
	{
		return this.gameData;
	}
	/**
	 * The isValidMove method returns the value of the isValid instance variable.
	 * @return isValid
	 */
	public boolean isValidMove()
	{
		return this.isValid;
	}
	/**
	 * The getJump method returns the value of the isJump instance variable.
	 * @return isJump
	 */
	public boolean getJump()
	{
		return this.isJump;
	}
	/**
	 * The setSelected method takes in a new choice value and sets the existing isSelected variable to the new value.
	 * @param choice - New choice value.
	 */
	public void setSelected(boolean choice)
	{
		this.isSelected = choice;
	}
	/**
	 * The setValidMove method takes in a new choice value and sets the existing isValid variable to the new value.
	 * @param choice - New choice value.
	 */
	public void setValidMove(boolean choice)
	{
		this.isValid = choice;
	}
	/**
	 * The setJump method takes in a new choice value and sets the existing isJump variable to the new value.
	 * @param choice - New choice value.
	 */
	public void setJump (boolean choice)
	{
		this.isJump = choice;
	}
	/**
	 * The setSelectedRow method takes in a new row value and sets the existing selectedRow variable to the new value.
	 * @param newRow - New row value.
	 */
	public void setSelectedRow(int newRow)
	{
		this.selectedRow = newRow;
	}
	/**
	 * The setSelectedColumn method takes in a new column value and sets the existing selectedColumn variable to the new value.
	 * @param newRow - New column value.
	 */
	public void setSelectedColumn(int newColumn)
	{
		this.selectedColumn = newColumn;
	}
	
	
}