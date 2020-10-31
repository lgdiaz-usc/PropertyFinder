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
	 * 
	 * @param manager     The username of the PropertyManager that owns this Property
	 * @param title       The title of this Property listing
	 * @param description The description of the Property listing
	 * @param address     The address of this Property
	 * @param capacity    The maximum capacity of this Property
	 * @param baseRent    The base amount of rent for this Property
	 */
	public Property(String manager, String title, String description, String address, int capacity, double baseRent) {
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
	 * Creates and adds a Review object to the reviews array of a specified Unit
	 * 
	 * @param rating          The rating of the Review (out of 10)
	 * @param title           The title of the Review
	 * @param description     The description for the Review
	 * @param author          The username of the Account that made the Review
	 * @param addressModifier The addressModifier corresponding to the specified
	 *                        Unit
	 */
	public void addUnitReview(int rating, String title, String description, String author, String addressModifier) {
		for (Unit unit : units) {
			if (unit.getAddressModifier().equals(addressModifier)) {
				unit.addReview(rating, title, description, author);
				return;
			}
		}
		System.out.println("Error: This unit does not exist!");
	}

	/**
	 * Accessor that returns title
	 * 
	 * @return title.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Returns the average rating for all Review objects is reviews
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
	 * Returns the description of the Property
	 * @return The description of the Property
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Returns the average rating for all Review objects in the reviews array of a
	 * specified Unit
	 * 
	 * @param addressModifier The addressModifier which identifies the requested
	 *                        Unit
	 * @return The average rating
	 */
	public int getUnitAverageRating(String addressModifier) {
		for (Unit unit : units) {
			if (unit.getAddressModifier().equals(addressModifier)) {
				return unit.getAverageRating();
			}
		}
		System.out.println("ERROR: This unit does not exist!");
		return -1;
	}

	/**
	 * Returns whether or not the Property is available for rent
	 * 
	 * @return if the Property is available for rent
	 */
	public boolean isAvailable() {
		return renters.size() < capacity;
	}

	/**
	 * Returns whether the current Account is the PropertyManager which owns this
	 * Property
	 * 
	 * @param currentAccount The username of the current Account
	 * @return Whether or not the current Account is the owner of this Property
	 */
	public boolean isManager(String currentAccount) {
		return manager.equals(currentAccount);
	}

	/**
	 * Returns whether or not the current Account is a renter of this Property
	 * 
	 * @param renterName The username of the current Account
	 * @return Whether or not the current Account is a renter of this Property
	 */
	public boolean isRenter(String renterName) {
		return renters.contains(renterName);
	}

	/**
	 * Adds a renter to renters
	 * 
	 * @param renter         The username of the User being added
	 * @param currentAccount The current Account
	 */
	public void addRenter(String renter, String currentAccount) {
		if(isManager(currentAccount)){
			if (isAvailable()) {
				renters.add(renter);
			} else {
				System.out.println("ERROR: Property already at maximum capacity!");
			}
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Removes a renter from renters
	 * 
	 * @param renter         The username of the User being removed
	 * @param currentAccount The current Account
	 */
	public void removeRenter(String renter, String currentAccount) {
		if(isManager(currentAccount)){
			renters.remove(renter);
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Adds a renter to the renters array of a requested Unit
	 * 
	 * @param renter          The username of the User being added
	 * @param addressModifier The addressModifier of the Unit being edited
	 * @param currentAccount  The current Account
	 */
	public void addUnitRenter(String renter, String addressModifier, String currentAccount) {
		boolean unitExist = false;
		if(isManager(currentAccount)) {
			for (Unit unit : units) {
				if (unit.getAddressModifier().equals(addressModifier)) {
					unit.addUnitRenter(renter);
					unitExist = true;
					break;
				}
			}
			if (!unitExist) {
				System.out.println("Unit doesn't exists. Cannot Add Unit Renter.");
			}
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * removes a renter from the renters array of a requested Unit
	 * 
	 * @param renter          The username of the User being removed
	 * @param addressModifier The addressModifier of the Unit being edited
	 * @param currentAccount  The current Account
	 */
	public void removeUnitRenter(String renter, String addressModifier, String currentAccount) {
		boolean unitExist = false;
		if(isManager(currentAccount)) {
			for (Unit unit : units) {
				if (unit.getAddressModifier().equals(addressModifier)) {
					unit.removeUnitRenter(renter);
					unitExist = true;
					break;
				}
			}
			if (!unitExist) {
				System.out.println("Unit doesn't exists. Cannot remove Unit Renter.");
			}
		}
		else {
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Creates and adds a Unit to units
	 * 
	 * @param addressModifier The addressModifier of the Unit
	 * @param capacity        The maximum capacity of the Unit
	 * @param currentAccount  The current Account
	 */
	public void addUnit(String addressModifier, int capacity, String currentAccount) {
		if(isManager(currentAccount)){
			units.add(new Unit(addressModifier, capacity));
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Removes a Unit from units
	 * 
	 * @param addressModifier The addressModifier of the Unit being removed
	 * @param currentAccount  The current Account
	 */
	public void removeUnit(String addressModifier, String currentAccount) {
		boolean unitExist = false;
		if(isManager(currentAccount)) {
			for (Unit unit : units) {
				String unitName = unit.getAddressModifier();
				if (unitName.equals(addressModifier)) {
					units.remove(unit);
					unitExist = true;
				}
			}
			if (unitExist) {
				System.out.println("Unit Removed.");
			} else {
				System.out.println("Unit doesn't exist. Cannot remove unit");
			}
		}
		else {
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Adds an extra fee to extraFees
	 * 
	 * @param currentAccount The current Account
	 * @param name           The name of the fee (water, electricity, internet,
	 *                       etc.)
	 * @param fee            The dollar amount of the fee
	 */
	public void addFee(String currentAccount, String name, double fee) {
		if(isManager(currentAccount)){
			extraFees.add(name + ": $" + fee);
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Removes an extra fee from extraFees
	 * 
	 * @param currentAccount The current Account
	 * @param name           The name of the fee
	 */
	public void removeFee(String currentAccount, String name) {
		if(isManager(currentAccount)){
			for(int i=0; i < extraFees.size(); i++){
				if(name.equals(extraFees.get(i).split(":")[0])){
					extraFees.remove(i);
					System.out.println("Fee \"" + name + "\" deleted!");
					return;
				}
			}
			System.out.println("ERROR: Fee \"" + name + "\" does not exist!");
		}
		else {
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Updates title
	 * 
	 * @param currentAccount The current Account
	 * @param title          The new title
	 */
	public void updateTitle(String currentAccount, String title) {
		if(isManager(currentAccount)){
			this.title = title;
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Updates address
	 * 
	 * @param currentAccount The current Account
	 * @param address        The new address
	 */
	public void updateAddress(String currentAccount, String address) {
		if(isManager(currentAccount)){
			this.address = address;
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Updates description
	 * 
	 * @param currentAccount The current Account
	 * @param description    The new description
	 */
	public void updateDescription(String currentAccount, String description) {
		if(isManager(currentAccount)){
			this.description = description;
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Updates the maximum capacity
	 * 
	 * @param currentAccount The current Account
	 * @param capacity       The new capacity
	 */
	public void updateCapacity(String currentAccount, int capacity) {
		if(isManager(currentAccount)){
			this.capacity = capacity;
			if(!isAvailable()){
				System.out.println("Error: There too many renters for this capacity, adjusting " +
						"capacity to fit.");
				capacity = renters.size();
			}
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Updates the base rent
	 * 
	 * @param currentAccount The current Account
	 * @param baseRent       The new base rent
	 */
	public void updateRent(String currentAccount, double baseRent) {
		if(isManager(currentAccount)){
			this.baseRent = baseRent;
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Updates the maximum capacity of a requested Unit
	 * 
	 * @param currentAccount  The current Account
	 * @param addressModifier The addressModifier of the Unit being requested
	 * @param capacity        The new maximum capacity
	 */
	public void updateUnitCapacity(String currentAccount, String addressModifier, int capacity) {
		if(isManager(currentAccount)){
			for(Unit unit : units){
				if(unit.getAddressModifier().equals(addressModifier)){
					unit.updateCapacity(capacity);
					return;
				}
			}
			System.out.println("Error: This unit doesn't exist!");
		}
		else{
			System.out.println("Error: You do not own this property!");
		}
	}

	/**
	 * Updates the addressModifier of a requested Unit
	 * 
	 * @param currentAccount  The current Account
	 * @param addressModifier The addressModifier of the Unit being requested
	 * @param newModifier     The new addressModifier
	 */
	public void updateUnitAddressModifier(String currentAccount, String addressModifier, String newModifier) {
		if(isManager(currentAccount)){
			for(Unit unit : units){
				if(unit.getAddressModifier().equals(addressModifier)){
					unit.updateAddressModifier(newModifier);
					return;
				}
			}
			System.out.println("ERROR: This unit doesn't exist!");
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Updates a renter's username
	 * 
	 * @param currentAccount The username of the renter being updated
	 * @param newRenterName  The renter's new username
	 */
	public void updateRenterUsername(String currentAccount, String newRenterName) {
		if(isRenter(currentAccount)){
			if(renters.contains(currentAccount)){
				renters.remove(currentAccount);
				renters.add(newRenterName);
			}

			for(Unit unit : units){
				if(unit.renters.contains(currentAccount)){
					unit.renters.remove(currentAccount);
					unit.renters.add(newRenterName);
				}
			}
		}
		else{
			System.out.println("ERROR: You do not rent this property.");
		}
	}

	/**
	 * Updates the username of the PropertyManager that owns this Property
	 * 
	 * @param currentAccount The old username of the owner
	 * @param newManager     The owner's new username
	 */
	public void updateManagerUsername(String currentAccount, String newManager) {
		if(isManager(currentAccount)){
			manager = newManager;
		}
		else{
			System.out.println("ERROR: You do not own this property.");
		}
	}

	/**
	 * Returns a description of the Property listing as a String
	 * 
	 * @return -> The description of the Property listing
	 */
	public String toString() {
		String output = "";
		output = output.concat(title);
		output = output.concat("\n" + address);
		output = output.concat("\nManaged by: " + manager);
		output = output.concat("\n" + description);
		output = output.concat("\nMaximum capacity: " + capacity);
		output = output.concat("\nUnits:");
		for (Unit unit : units) {
			output = output.concat(unit.toString(address));
		}
		output = output.concat("\nRenters:");
		for (String renter : renters) {
			output = output.concat("\n - " + renter);
		}
		output = output.concat("\nRent: $" + baseRent);
		output = output.concat("\nAdditional fees: ");
		for (String fee : extraFees){
			output = output.concat("\n\t" + fee);
		}
		output = output.concat("\nAverage rating: " + getAverageRating());
		output = output.concat("\nReviews:");
		for (Review review : reviews) {
			output = output.concat(review.toString());
		}

		return output;
	}

	/**
	 * Return the contents of this Property in the JSON format
	 * 
	 * @return -> The contents of this Property
	 */
	public JSONObject toJSON() {
		JSONObject property = new JSONObject();
		property.put("manager", manager);
		property.put("title", title);
		property.put("description", description);
		property.put("address", address);
		property.put("capacity", capacity);
		property.put("base rent", baseRent);

		JSONArray renterArray = new JSONArray();
		for (String renter : renters) {
			renterArray.add(renter);
		}

		JSONArray feeArray = new JSONArray();
		for (String fee : extraFees) {
			feeArray.add(fee);
		}

		JSONArray unitArray = new JSONArray();
		for (Unit unit : units) {
			unitArray.add(unit.toJSON());
		}

		JSONArray reviewArray = new JSONArray();
		for (Review review : reviews) {
			reviewArray.add(review.toJSON());
		}

		property.put("renters", renterArray);
		property.put("extra fees", feeArray);
		property.put("reviews", reviewArray);
		property.put("units", unitArray);

		return property;
	}

}