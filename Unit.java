package PropertyFinder;

import java.util.ArrayList;

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

    }

    /**
     * Creates and adds a Review object to reviews
     * @param rating The rating of the Review (out of 10)
     * @param title The title of the Review
     * @param description The description for the Review
     * @param author The username of the Account that made the Review
     */
    public void addReview(int rating, String title, String description, Account author){

    }

    /**
     * Returns the average rating of all Review objects in reviews
     * @return The average rating
     */
    public int getAverageRating() {

    }

    /**
     * Returns whether or not this Unit is available to rent
     * @return Whether or not this Unit is available to rent
     */
    public boolean isAvailable() {

    }

    /**
     * Returns the addressModifier for this Unit
     * @return addressModifier
     */
    public String getAddressModifier() {

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

    }

    /**
     * Returns the contents of this Unit as a String in the JSON format
     * @return The contents of this Unit
     */
    public String toJSON() {

    }
}
