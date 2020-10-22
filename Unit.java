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
     * @param addressModifier The addressModifier for Unit (e.g. apartment number)
     * @param capacity The maximum capacity of the Unit
     */
    Unit(String addressModifier, int capacity) {
    	this.addressModifier = addressModifier;
    	this.capacity = capacity;
    	renters = new ArrayList<String>();
    	reviews = new ArrayList<Review>();
    }

    /**
     * Creates and adds a Review object to reviews
     * @param rating The rating of the Review (out of 10)
     * @param title The title of the Review
     * @param description The description for the Review
     * @param author The username of the Account that made the Review
     */
    public void addReview(int rating, String title, String description, String author){

    }

    /**
     * Returns the average rating of all Review objects in reviews
     * @return The average rating
     */
    public int getAverageRating() {
        return 0;
    }

    /**
     * Returns whether or not this Unit is available to rent
     * @return Whether or not this Unit is available to rent
     */
    public boolean isAvailable() {
        return false;
    }

    /**
     * Returns the addressModifier for this Unit
     * @return addressModifier
     */
    public String getAddressModifier() {
        return null;
    }

    /**
     * Updates addressModifier
     * @param addressModifier The new addressModifier
     */
    public void updateAddressModifier(String addressModifier) {

    }

    /**
     * Updates the new maximum capacity for this Unit
     * @param capacity The new maximum capacity
     */
    public void updateCapacity(int capacity) {

    }

    /**
     * Returns a description of this Unit as a String
     * @return The description of this Unit
     */
    public String toString() {
        return null;
    }

    /**
     * Returns the contents of this Unit in the JSON format
     * @return The contents of this Unit
     */
    public JSONObject toJSON() {
        JSONObject unit = new JSONObject();
        unit.put("address modifier", addressModifier);
        unit.put("capacity", capacity);
        unit.put("renters", renters.toArray());

        JSONArray reviewArray = new JSONArray();
        for(Review review : reviews){
            reviewArray.add(review.toJSON());
        }

        unit.put("reviews", reviewArray);

        return unit;
    }
}