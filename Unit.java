package PropertyFinder;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Contains data and methods relating to an individual unit listing
 */
public class Unit {
	private String addressModifier;
	private int capacity;
	public ArrayList<String> renters;
	private ArrayList<Review> reviews;

	/**
	 * Parameterized constructor for Unit
	 * 
	 * @param addressModifier The addressModifier for Unit (e.g. apartment number)
	 * @param capacity        The maximum capacity of the Unit
	 */
	Unit(String addressModifier, int capacity) {
		this.addressModifier = addressModifier;
		this.capacity = capacity;
		renters = new ArrayList<String>();
		reviews = new ArrayList<Review>();
	}

	/**
	 * 
	 * @param renter          The username of the renter
	 * @param addressModifier The addressModifier for Unit (e.g. apartment number)
	 * @param currentAccount  The current account of the user
	 */
	public void addUnitRenter(String renter, String addressModifier, String currentAccount) {
		renters.add(renter);
	}

	/**
	 * 
	 * @param renter          The username of the renter
	 * @param addressModifier The addressModifier for Unit (e.g. apartment number)
	 * @param currentAccount  The current account of the user
	 */
	public void removeUnitRenter(String renter, String addressModifier, String currentAccount) {
		renters.remove(renter);
	}

	/**
	 * Creates and adds a Review object to reviews
	 * 
	 * @param rating      The rating of the Review (out of 10)
	 * @param title       The title of the Review
	 * @param description The description for the Review
	 * @param author      The username of the Account that made the Review
	 */
	public void addReview(int rating, String title, String description, String author) {
		reviews.add(new Review(rating, title, description, author));
	}

	/**
	 * Returns the average rating of all Review objects in reviews
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
	 * Returns whether or not this Unit is available to rent
	 * 
	 * @return Whether or not this Unit is available to rent
	 */
	public boolean isAvailable() {
		return false;
	}

	/**
	 * Returns the addressModifier for this Unit
	 * 
	 * @return addressModifier
	 */
	public String getAddressModifier() {
		return addressModifier;
	}

	/**
	 * Updates addressModifier
	 * 
	 * @param addressModifier The new addressModifier
	 */
	public void updateAddressModifier(String addressModifier) {

	}

	/**
	 * Updates the new maximum capacity for this Unit
	 * 
	 * @param capacity The new maximum capacity
	 */
	public void updateCapacity(int capacity) {

	}

	/**
	 * Returns a description of this Unit as a String
	 * 
	 * @return The description of this Unit
	 */
	public String toString(String address) {
		String output = "";
		output = output.concat("\n\t" + address + " " + addressModifier);
		output = output.concat("\n\tMaximum capacity: " + capacity);
		output = output.concat("\n\tRenters:");
		for (String renter : renters) {
			output = output.concat("\n\t - " + renter);
		}
		output = output.concat("\n\tAverage rating: " + getAverageRating());
		output = output.concat("\n\tReviews:");
		for (Review review : reviews) {
			output = output.concat(review.toString().replaceAll("\n", "\n\t"));
		}

		return output;
	}

	/**
	 * Returns the contents of this Unit in the JSON format
	 * 
	 * @return The contents of this Unit
	 */
	public JSONObject toJSON() {
		JSONObject unit = new JSONObject();
		unit.put("address modifier", addressModifier);
		unit.put("capacity", capacity);

		JSONArray renterArray = new JSONArray();
		for (String renter : renters) {
			renterArray.add(renter);
		}

		JSONArray reviewArray = new JSONArray();
		for (Review review : reviews) {
			reviewArray.add(review.toJSON());
		}

		unit.put("renters", renterArray);
		unit.put("reviews", reviewArray);

		return unit;
	}
}