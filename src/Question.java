/**
 * Question class holds a question object with question and artist instance
 * variables
 * @author Michela Marchini
 * @version 12/9/18
 */

public class Question{
	private String question;
	private String artist;

	/**
	 * constructor for a Question that only has a question associated with it
	 * @param question to be assigned to instance variable question
	 */
	public Question(String question){
		this.question = question;
	}

	/**
	 * constructor for a Question with both a question and an artist associated
	 * with it (a leaf node on the tree)
	 * @param question to be assigned to instance variable question
	 * @param artist to be assigned to instance variable artist
	 */
	public Question(String question, String artist){
		this.question = question;
		this.artist = artist;
	}

	/**
	 * getQuestion returns the question instance variable
	 * @return this.question
	 */
	public String getQuestion(){
		return question;
	}

	/**
	 * getArtist returns the artist instance variable
	 * @return this.artist
	 */
	public String getArtist(){
		return artist;
	}

}
