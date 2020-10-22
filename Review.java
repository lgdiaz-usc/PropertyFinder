package PropertyFinder;

import org.json.simple.JSONObject;

/**
 * Contains data and methods related to reviews
 */
public class Review {
	private int rating;
	private String title;
	private String description;
	private String author;
	
	/**
	 * The parameterized constructor for Review
	 * @param rating -> The rating of the review
	 * @param title -> The title of the review
	 * @param description -> The description of the review
	 * @param author -> The username of the Account that wrote the review
	 */
	public Review(int rating, String title, String description, String author) {
		this.rating = rating;
		this.title = title;
		this.description = description;
		this.author = author;
	}
	
	/**
	 * Returns the rating of the review
	 * @return The rating of the review
	 */
	public int getRating() {
		return 0;
	}
	
	/**
	 * Returns a String interpretation of the User
	 */
	public String toString() {
		return null;
	}
	
	/**
	 * Returns the contents of Review in JSON format
	 * @return The JSON interpretation of the Review contents
	 */
	public JSONObject toJSON() {
		JSONObject review = new JSONObject();
		review.put("rating", rating);
		review.put("title", title);
		review.put("description", description);
		review.put("author", author);
		return review;
	}
}