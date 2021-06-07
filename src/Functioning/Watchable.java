package Functioning;

/**
 * Represents a video object that can be watched
 *
 * @author Haochen Liu
 * @version 1.0
 */
public interface Watchable {
	
	/**
	 * Plays the video to the user
	 * @pre isValid()
	 */
	public void watch();

	/**
	 * Indicates whether the Functioning.Watchable element is ready to be played.
	 * 
	 * @return true if the file path points to an existing location that can be read and is not a directory
	 */
	public boolean isValid();

	/**
	 * Get the title of this Watchable object.
	 *
	 * @return the title
	 */
	public String getTitle();

	/**
	 * Get the language of this Watchable object.
	 *
	 * @return the language
	 */
	public Language getLanguage();

	/**
	 * Get the publishing studio of this Watchable object.
	 *
	 * @return the publishing studio
	 */
	public String getStudio();
	
}