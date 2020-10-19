package PropertyFinder;

import java.util.ArrayList;

/**
 * Contains data and methods related to the User's account
 */
public class Account {
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
	 * @param username    -> The username for the User
	 * @param password    -> The password for the User
	 * @param name        -> The name of the User
	 * @param dateOfBirth -> The date of birth of the User
	 * @param homeAddress -> The home address of the User
	 * @param email       -> The email of the User
	 * @param phoneNumber -> The phone number of the User
	 */
	public Account(String username, String password, String name, String dateOfBirth, String homeAddress, String email,
			String phoneNumber) {

	}

	/**
	 * Authenticates the User's account.
	 * @param currentAccount -> The account being authenticated
	 * @return Whether or not currentAccount matches username
	 */
	public boolean authenticate(String currentAccount) {
	
	}

	/**
	 * Updates the name of User
	 * @param currentAccount -> The account of the User
	 * @param name           -> The name of the User
	 */
	public void updateName(String currentAccount, String name) {

	}

	/**
	 * Updates the date of birth of the User
	 * @param currentAccount -> The account of the User
	 * @param dateOfBirth    -> The date of birth of the User
	 */
	public void updateDateOfBirth(String currentAccount, String dateOfBirth) {

	}

	/**
	 * Updates the home address of the User
	 * @param currentAccount -> The account of the User
	 * @param homeAddress    -> The home address of the User
	 */
	public void updateHomeAddress(String currentAccount, String homeAddress) {

	}

	/**
	 * Updates the email of the User
	 * @param currentAccount -> The account of the User
	 * @param email          -> The email of the User
	 */
	public void updateEmail(String currentAccount, String email) {

	}

	/**
	 * Updates the phone number of the User
	 * @param currentAccount -> The account of the User
	 * @param phoneNumber    -> The phone number of the User
	 */
	public void updatePhoneNumber(String currentAccount, String phoneNumber) {

	}

	/**
	 * Updates the username of the User
	 * @param currentAccount -> The account of the User
	 * @param username       -> The username of the User
	 */
	public void updateUsername(String currentAccount, String username) {

	}

	/**
	 * Updates the password of the User
	 * @param currentAccount -> The account of the User
	 * @param password       -> The password of the User
	 */
	public void updatePassword(String currentAccount, String password) {

	}

	/**
	 * Creates and adds a Review to reviews.
	 * @param rating      -> Rating of the Review (out of 10)
	 * @param title       -> The title of the Review
	 * @param description -> The description of the Review
	 * @param author      -> The author of the Review
	 */
	public void addReview(int rating, String title, String description, Account author) {

	}

	/**
	 * Return the average rating for all Reviews in reviews
	 * @return The average rating
	 */
	public int getAverageRating() {

	}

	/**
	 * Returns a String interpretation of the Account.
	 * @param currentAccount -> The account of the user
	 * @return String interpretation of the account of the User.
	 */
	public String toString(String currentAccount) {

	}

	/**
	 * Creates and adds a Message to messages
	 * @param currentAccount -> The account of the user
	 * @param description    -> The description of the message
	 */
	public void addMessage(String currentAccount, String description) {

	}

	/**
	 * Returns a String representation of all Messages in messages
	 * @param currentAccount -> The account of the user
	 * @return String representation of the messages of the User.
	 */
	public String getMessages(String currentAccount) {

	}

	/**
	 * Returns the contents of Account in JSON format
	 * @return The JSON interpretation of the Account content
	 */
	public String toJSON() {

	}
}
