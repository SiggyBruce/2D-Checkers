import java.io.*;
import javax.sound.sampled.*;
/**
 * Audio.java - APR-24-2016 - ITEC 220: Project 5 -
 * This class contains methods that control the music and sounds for the game.
 * @author Tre Haga
 * @version 1.0
 */
public class Audio
{
	/**
	 * The Audio class instance variables.
	 * Declares an instance of the Clip class. (Javax library audio player that handles the audio for the game).
	 * Declares a boolean variable. (Music switch).
	 */
    private Clip clip;
    private boolean isMusicPlaying;
    /**
     * The Audio class constructor.
     * Initializes a new instance of the Clip class.
     * Initializes a new boolean variable.
     */
    public Audio()
    {
        isMusicPlaying = false;
        clip = null;
    }
    /**
     * The playBackgroundMusic method locates a specific audio file and if found, continuously plays the background music.
     * The audio file is in the Waveform Audio file format (.wav).
     */
    public void playBackgroundMusic()
    {
    	AudioInputStream input = null;
        try
        {
        	input = AudioSystem.getAudioInputStream(new File("audio/background_music.wav").getAbsoluteFile());
            this.clip = AudioSystem.getClip();
            this.clip.open(input);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.isMusicPlaying = true;
        }
        catch (IOException exception)
        {
        	System.out.println("Failed to load the background music.");
        }
        catch (UnsupportedAudioFileException exception)
        {
        	System.out.println("Unsupported audio format.");
		}
        catch (LineUnavailableException exception)
        {
        	System.out.println("The line for the file is currently unavailable.");
		}
    }
    /**
     * The playWaitingMusic method locates a specific audio file and if found, continuously plays the waiting music.
     * The audio file is in the Waveform Audio file format (.wav).
     */
    public void playWaitingMusic()
	{
		AudioInputStream input = null;
        try
        {
        	input = AudioSystem.getAudioInputStream(new File("audio/waiting_music.wav").getAbsoluteFile());
            this.clip = AudioSystem.getClip();
            this.clip.open(input);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (IOException exception)
        {
        	System.out.println("Failed to load the waiting music.");
        }
        catch (UnsupportedAudioFileException exception)
        {
        	System.out.println("Unsupported audio format.");
		}
        catch (LineUnavailableException exception)
        {
        	System.out.println("The line for the file is currently unavailable.");
		}
	}
    /**
     * The playClickSoundEffect method locates a specific audio file and if found, plays the click sound effect.
     * The audio file is in the Waveform Audio file format (.wav).
     */
    public void playClickSoundEffect()
	{
		AudioInputStream input = null;
		Clip clip = null;
        try
        {
        	input = AudioSystem.getAudioInputStream(new File("audio/click_sound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        }
        catch (IOException exception)
        {
        	System.out.println("Failed to load the click sound effect.");
        }
        catch (UnsupportedAudioFileException exception)
        {
        	System.out.println("Unsupported audio format.");
		}
        catch (LineUnavailableException exception)
        {
        	System.out.println("The line for the file is currently unavailable.");
		}
	}
    /**
     * The playKingSoundEffect method locates a specific audio file and if found, plays the king sound effect.
     * The audio file is in the Waveform Audio file format (.wav).
     */
	public void playKingSoundEffect()
	{
		AudioInputStream input = null;
		Clip clip = null;
        try
        {
        	input = AudioSystem.getAudioInputStream(new File("audio/king_sound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        }
        catch (IOException exception)
        {
        	System.out.println("Failed to load the king sound effect.");
        }
        catch (UnsupportedAudioFileException exception)
        {
        	System.out.println("Unsupported audio format.");
		}
        catch (LineUnavailableException exception)
        {
        	System.out.println("The line for the file is currently unavailable.");
		}
	}
	/**
	 * The stopBackgroundMusic method stops the background music audio from playing.
	 * Initializes the isMusicPlaying variable to false.
	 */
    public void stopBackgroundMusic()
    {
        this.clip.stop();
        this.isMusicPlaying = false;
    }
    /**
	 * The stopWaitingMusic method stops the waiting music audio from playing.
	 * Initializes the isMusicPlaying variable to false.
	 */
	public void stopWaitingMusic()
	{
		this.clip.stop();
		this.isMusicPlaying = false;
	}
    /**
     * The isMusicPlaying returns this instance of the isMusicPlaying.
     * Returns the current state of the variable.
     * @return isMusicPlaying
     */
    public boolean isMusicPlaying()
    {
        return this.isMusicPlaying;
    }
}