package PropertyFinder;

import org.json.simple.JSONObject;

/**
 * Contains data and methods related to User's messages
 */
public class Message {
	private String author;
	private String description;

	/**
	 * Parameterized constructor for Message
	 * 
	 * @param author  The author of the message
	 * @param message The message of the author
	 */
	public Message(String author, String message) {
		this.author = author;
		this.description = message;
	}

	/**
	 * Returns a String interpretation of the Message
	 */
	public String toString() {
		String output = "";
		output = output.concat("\nFrom: " + author);
		output = output.concat("\n\t" + description);
		return output;
	}

	/**
	 * Returns the contents of Message in JSON format
	 * 
	 * @return The JSON interpretation of the Message contents
	 */
	public JSONObject toJSON() {
		JSONObject message = new JSONObject();
		message.put("author", author);
		message.put("description", description);
		return message;
	}
}