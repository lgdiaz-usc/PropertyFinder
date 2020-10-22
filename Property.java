package PropertyFinder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * Contains data and methods related to Property listings
 */
public class Property {
    private String manager;
    private String title;
    private String description;
    private String address;
    private ArrayList<String> renters;
    private int capacity;
    private ArrayList<Unit> units;
    private ArrayList<Review> reviews;
    private double baseRent;
    private ArrayList<String> extraFees;

    /**
     * Parameterized constructor for Property
     * @param manager The username of the PropertyManager that owns this Property
     * @param title The title of this Property listing
     * @param description The description of the Property listing
     * @param address The address of this Property
     * @param capacity The maximum capacity of this Property
     * @param baseRent The base amount of rent for this Property
     */
    public Property(String manager, String title, String description, String address,
                    int capacity, double baseRent) {
    	this.manager = manager;
    	this.title = title;
    	this.description = description; 
    	this.address = address;
        this.capacity = capacity; 
        this.baseRent = baseRent;
        renters = new ArrayList<String>();
        units = new ArrayList<Unit>();
        reviews = new ArrayList<Review>();
        extraFees = new ArrayList<String>();
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
     * Creates and adds a Review object to the reviews array of a specified Unit
     * @param rating The rating of the Review (out of 10)
     * @param title The title of the Review
     * @param description The description for the Review
     * @param author The username of the Account that made the Review
     * @param addressModifier The addressModifier corresponding to the specified Unit
     */
    public void addUnitReview(int rating, String title, String description, String author,
                              String addressModifier){

    }

    /**
     * Returns the average rating for all Review objects is reviews
     * @return The average rating
     */
    public int getAverageRating(){
        return 0;
    }

    /**
     * Returns the average rating for all Review objects in the reviews array of a specified Unit
     * @param addressModifier The addressModifier which identifies the requested Unit
     * @return The average rating
     */
    public int getUnitAverageRating(String addressModifier){
        return 0;
    }

    /**
     * Returns whether or not the Property is available for rent
     * @return if the Property is available for rent
     */
    public boolean isAvailable() {
        return false;
    }

    /**
     * Returns whether the current Account is the PropertyManager which owns this Property
     * @param currentAccount The username of the current Account
     * @return Whether or not the current Account is the owner of this Property
     */
    public boolean isManager(String currentAccount) {
        return false;
    }

    /**
     * Returns whether or not the current Account is a renter of this Property
     * @param renterName The username of the current Account
     * @return Whether or not the current Account is a renter of this Property
     */
    public boolean isRenter(String renterName) {
        return false;
    }

    /**
     * Adds a renter to renters
     * @param renter The username of the User being added
     * @param currentAccount The current Account
     */
    public void addRenter(String renter, String currentAccount) {

    }

    /**
     * Adds a renter to the renters array of a requested Unit
     * @param renter The username of the User being added
     * @param addressModifier The addressModifier of the Unit being edited
     * @param currentAccount The current Account
     */
    public void addUnitRenter(String renter, String addressModifier, String currentAccount) {

    }

    /**
     * Removes a renter from renters
     * @param renter The username of the User being removed
     * @param currentAccount The current Account
     */
    public void removeRenter(String renter, String currentAccount) {

    }

    /**
     * removes a renter from the renters array of a requested Unit
     * @param renter The username of the User being removed
     * @param addressModifier The addressModifier of the Unit being edited
     * @param currentAccount The current Account
     */
    public void removeUnitRenter(String renter, String addressModifier, String currentAccount) {

    }

    /**
     * Creates and adds a Unit to units 
     * @param addressModifier The addressModifier of the Unit
     * @param capacity The maximum capacity of the Unit
     * @param currentAccount The current Account
     */
    public void addUnit(String addressModifier, int capacity, String currentAccount) {

    }

    /**
     * Removes a Unit from units
     * @param addressModifier The addressModifier of the Unit being removed
     * @param currentAccount The current Account
     */
    public void removeUnit(String addressModifier, String currentAccount) {

    }

    /**
     * Adds an extra fee to extraFees
     * @param currentAccount The current Account
     * @param name The name of the fee (water, electricity, internet, etc.)
     * @param fee The dollar amount of the fee
     */
    public void addFee(String currentAccount, String name, double fee) {

    }

    /**
     * Removes an extra fee from extraFees
     * @param currentAccount The current Account
     * @param name The name of the fee
     */
    public void removeFee(String currentAccount, String name) {
        
    }

    /**
     * Updates title
     * @param currentAccount The current Account
     * @param title The new title
     */
    public void updateTitle(String currentAccount, String title) {

    }

    /**
     * Updates address
     * @param currentAccount The current Account
     * @param address The new address
     */
    public void updateAddress(String currentAccount, String address) {

    }

    /**
     * Updates description
     * @param currentAccount The current Account
     * @param description The new description
     */
    public void updateDescription(String currentAccount, String description) {

    }

    /**
     * Updates the maximum capacity
     * @param currentAccount The current Account
     * @param capacity The new capacity
     */
    public void updateCapacity(String currentAccount, int capacity) {

    }

    /**
     * Updates the base rent
     * @param currentAccount The current Account
     * @param baseRent The new base rent
     */
    public void updateRent(String currentAccount, double baseRent) {

    }

    /**
     * Updates the maximum capacity of a requested Unit
     * @param currentAccount The current Account
     * @param addressModifier The addressModifier of the Unit being requested
     * @param capacity The new maximum capacity
     */
    public void updateUnitCapacity(String currentAccount, String addressModifier, int capacity){

    }

    /**
     * Updates the addressModifier of a requested Unit
     * @param currentAccount The current Account
     * @param addressModifier The addressModifier of the Unit being requested
     * @param newModifier The new addressModifier
     */
    public void updateUnitAddressModifier(String currentAccount, String addressModifier,
                                          String newModifier) {

    }

    /**
     * Updates a renter's username
     * @param currentAccount The username of the renter being updated
     * @param newRenterName The renter's new username
     */
    public void updateRenterUsername(String currentAccount, String newRenterName) {

    }

    /**
     * Updates the username of the PropertyManager that owns this Property
     * @param currentAccount The old username of the owner
     * @param newManager The owner's new username
     */
    public void updateManagerUsername(String currentAccount, String newManager) {

    }

    /**
     * Returns a description of the Property listing as a String
     * @return -> The description of the Property listing
     */
    public String toString(){
        return null;
    }

    /**
     * Return the contents of this Property in the JSON format
     * @return -> The contents of this Property
     */
    public JSONObject toJSON(){
        JSONObject property = new JSONObject();
        property.put("manager", manager);
        property.put("title", title);
        property.put("description", description);
        property.put("address", address);
        property.put("capacity", capacity);
        property.put("base rent", baseRent);
        property.put("extra fees", extraFees);
        property.put("renters", renters);

        JSONArray unitArray = new JSONArray();
        for(Unit unit : units){
            unitArray.add(unit.toJSON());
        }

        JSONArray reviewArray = new JSONArray();
        for(Review review : reviews){
            reviewArray.add(review.toJSON());
        }

        property.put("reviews", reviewArray);
        property.put("units", unitArray);

        return property;
    }
}