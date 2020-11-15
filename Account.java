package PropertyFinder;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 * @author Team Dolphin (Liam Diaz and Sincere Dixon)
 * Contains data and methods related to the User's account
 */
public abstract class Account {
	protected String name;
	protected String dateOfBirth;
	protected String homeAddress;
	protected String email;
	protected String phoneNumber;
	protected String username;
	protected String password;
	protected ArrayList<Review> reviews;
	protected ArrayList<Message> messages;

	/**
	 * Parameterized constructor for Account
	 * 
	 * @param username    The username for the User
	 * @param password    The password for the User
	 * @param name        The name of the User
	 * @param dateOfBirth The date of birth of the User
	 * @param homeAddress The home address of the User
	 * @param email       The email of the User
	 * @param phoneNumber The phone number of the User
	 */
	public Account(String username, String password, String name, String dateOfBirth, String homeAddress, String email,
			String phoneNumber) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
		this.email = email;
		this.phoneNumber = phoneNumber;
		reviews = new ArrayList<Review>();
		messages = new ArrayList<Message>();
	}

	/**
	 * Authenticates the User's account.
	 * 
	 * @param currentAccount The account being authenticated
	 * @return Whether or not currentAccount matches username
	 */
	public boolean authenticate(String currentAccount) {
		boolean check = false;
		if (currentAccount != null && currentAccount.equals(username)) {
			check = true;
		} else {
			check = false;
		}
		return check;
	}

	/**
	 * Updates the name of User
	 * 
	 * @param currentAccount The account of the User
	 * @param name           The name of the User
	 */
	public void updateName(String currentAccount, String name) {
		if (authenticate(currentAccount)) {
			this.name = name;
		}
	}

	/**
	 * Updates the date of birth of the User
	 * 
	 * @param currentAccount The account of the User
	 * @param dateOfBirth    The date of birth of the User
	 */
	public void updateDateOfBirth(String currentAccount, String dateOfBirth) {
		if (authenticate(currentAccount)) {
			this.dateOfBirth = dateOfBirth;
		}
	}

	/**
	 * Updates the home address of the User
	 * 
	 * @param currentAccount The account of the User
	 * @param homeAddress    The home address of the User
	 */
	public void updateHomeAddress(String currentAccount, String homeAddress) {
		if (authenticate(currentAccount)) {
			this.homeAddress = homeAddress;
		}
	}

	/**
	 * Updates the email of the User
	 * 
	 * @param currentAccount The account of the User
	 * @param email          The email of the User
	 */
	public void updateEmail(String currentAccount, String email) {
		if (authenticate(currentAccount)) {
			this.email = email;
		}
	}

	/**
	 * Updates the phone number of the User
	 * 
	 * @param currentAccount The account of the User
	 * @param phoneNumber    The phone number of the User
	 */
	public void updatePhoneNumber(String currentAccount, String phoneNumber) {
		if (authenticate(currentAccount)) {
			this.phoneNumber = phoneNumber;
		}
	}

	/**
	 * Updates the username of the User
	 * 
	 * @param currentAccount The account of the User
	 * @param username       The username of the User
	 */
	public void updateUsername(String currentAccount, String username) {
		if (authenticate(currentAccount)) {
			this.username = username;
		}
	}

	/**
	 * Updates the password of the User
	 * 
	 * @param currentAccount The account of the User
	 * @param password       The password of the User
	 */
	public void updatePassword(String currentAccount, String password) {
		if (authenticate(currentAccount)) {
			this.password = password;
		}
	}

	/**
	 * Creates and adds a Review to reviews.
	 * 
	 * @param rating      Rating of the Review (out of 10)
	 * @param title       The title of the Review
	 * @param description The description of the Review
	 * @param author      The author of the Review
	 */
	public void addReview(int rating, String title, String description, String author) {
		reviews.add(new Review(rating, title, description, author));
	}

	/**
	 * Return the average rating for all Reviews in reviews
	 * 
	 * @return The average rating
	 */
	public int getAverageRating() {
		int averageRating = 0;
		for (Review review : reviews) {
			averageRating += review.getRating();
		}
		if (reviews.size() > 0) {
			averageRating /= reviews.size();
		}
		return averageRating;
	}

	/**
	 * Returns a String interpretation of the Account.
	 * 
	 * @param currentAccount The account of the user
	 * @return String interpretation of the account of the User.
	 */
	public String toString(String currentAccount) {
		String output = "";
		output = output.concat(name);
		output = output.concat("\nUsername: " + username);
		if (authenticate(currentAccount)) {
			output = output.concat("\nPassword: " + password);
		}
		output = output.concat("\nE-mail address: " + email);
		output = output.concat("\nPhone number: " + phoneNumber);
		if (authenticate(currentAccount)) {
			output = output.concat("\nAddress: " + homeAddress);
			output = output.concat("\nDate of birth: " + dateOfBirth);
		}
		output = output.concat("\nAverage rating: " + getAverageRating() + "/10");
		output = output.concat("\nReviews: ");
		for (Review review : reviews) {
			output = output.concat(review.toString());
		}

		return output;
	}

	/**
	 * Creates and adds a Message to messages
	 * 
	 * @param currentAccount The account of the user
	 * @param description    The description of the message
	 */
	public void addMessage(String currentAccount, String description) {
		messages.add(new Message(currentAccount, description));
	}

	/**
	 * Returns a String representation of all Messages in messages
	 * 
	 * @param currentAccount The account of the user
	 * @return String representation of the messages of the User.
	 */
	public String getMessages(String currentAccount) {
		if(authenticate(currentAccount)) {
			String output = "";
			for (Message message : messages) {
				output = output.concat(message.toString() + "\n");
			}
			return output;
		}
		return "ERROR: This is not your account!";
	}

	/**
	 * Returns the contents of Account in JSON format 
	 * 
	 * @return The JSON interpretation of the Account content
	 */
	public JSONObject toJSON() {
		JSONObject account = new JSONObject();
		account.put("username", username);
		account.put("password", password);
		account.put("name", name);
		account.put("date of birth", dateOfBirth);
		account.put("home address", homeAddress);
		account.put("e-mail", email);
		account.put("phone number", phoneNumber);

		JSONArray reviewArray = new JSONArray();
		for (Review review : reviews) {
			reviewArray.add(review.toJSON());
		}

		JSONArray messageArray = new JSONArray();
		for (Message message : messages) {
			messageArray.add(message.toJSON());
		}

		account.put("reviews", reviewArray);
		account.put("messages", messageArray);

		return account;
	}
}
