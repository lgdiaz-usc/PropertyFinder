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
		return false;
	}

	/**
	 * Returns whether the current Account is the PropertyManager which owns this
	 * Property
	 * 
	 * @param currentAccount The username of the current Account
	 * @return Whether or not the current Account is the owner of this Property
	 */
	public boolean isManager(String currentAccount) {
		return false;
	}

	/**
	 * Returns whether or not the current Account is a renter of this Property
	 * 
	 * @param renterName The username of the current Account
	 * @return Whether or not the current Account is a renter of this Property
	 */
	public boolean isRenter(String renterName) {
		return false;
	}

	/**
	 * Adds a renter to renters
	 * 
	 * @param renter         The username of the User being added
	 * @param currentAccount The current Account
	 */
	public void addRenter(String renter, String currentAccount) {
		if (renters.size() < capacity) {
			renters.add(renter);
		} else {
			System.out.println("ERROR: Property already at maximum capacity!");
		}
	}

	/**
	 * Removes a renter from renters
	 * 
	 * @param renter         The username of the User being removed
	 * @param currentAccount The current Account
	 */
	public void removeRenter(String renter, String currentAccount) {
		renters.remove(renter);
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
		for (Unit unit : units) {
			if (unit.getAddressModifier().equals(addressModifier)) {
				unit.addUnitRenter(renter, addressModifier, currentAccount);
				unitExist = true;
				break;
			}
		}
		if (!unitExist) {
			System.out.println("Unit doesn't exists. Cannot Add Unit Renter.");
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
		for (Unit unit : units) {
			if (unit.getAddressModifier().equals(addressModifier)) {
				unit.removeUnitRenter(renter, addressModifier, currentAccount);
				unitExist = true;
				break;
			}
		}
		if (!unitExist) {
			System.out.println("Unit doesn't exists. Cannot remove Unit Renter.");
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

		units.add(new Unit(addressModifier, capacity));
	}

	/**
	 * Removes a Unit from units
	 * 
	 * @param addressModifier The addressModifier of the Unit being removed
	 * @param currentAccount  The current Account
	 */
	public void removeUnit(String addressModifier, String currentAccount) {
		boolean unitExist = false;
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

	/**
	 * Adds an extra fee to extraFees
	 * 
	 * @param currentAccount The current Account
	 * @param name           The name of the fee (water, electricity, internet,
	 *                       etc.)
	 * @param fee            The dollar amount of the fee
	 */
	public void addFee(String currentAccount, String name, double fee) {

	}

	/**
	 * Removes an extra fee from extraFees
	 * 
	 * @param currentAccount The current Account
	 * @param name           The name of the fee
	 */
	public void removeFee(String currentAccount, String name) {

	}

	/**
	 * Updates title
	 * 
	 * @param currentAccount The current Account
	 * @param title          The new title
	 */
	public void updateTitle(String currentAccount, String title) {

	}

	/**
	 * Updates address
	 * 
	 * @param currentAccount The current Account
	 * @param address        The new address
	 */
	public void updateAddress(String currentAccount, String address) {

	}

	/**
	 * Updates description
	 * 
	 * @param currentAccount The current Account
	 * @param description    The new description
	 */
	public void updateDescription(String currentAccount, String description) {

	}

	/**
	 * Updates the maximum capacity
	 * 
	 * @param currentAccount The current Account
	 * @param capacity       The new capacity
	 */
	public void updateCapacity(String currentAccount, int capacity) {

	}

	/**
	 * Updates the base rent
	 * 
	 * @param currentAccount The current Account
	 * @param baseRent       The new base rent
	 */
	public void updateRent(String currentAccount, double baseRent) {

	}

	/**
	 * Updates the maximum capacity of a requested Unit
	 * 
	 * @param currentAccount  The current Account
	 * @param addressModifier The addressModifier of the Unit being requested
	 * @param capacity        The new maximum capacity
	 */
	public void updateUnitCapacity(String currentAccount, String addressModifier, int capacity) {

	}

	/**
	 * Updates the addressModifier of a requested Unit
	 * 
	 * @param currentAccount  The current Account
	 * @param addressModifier The addressModifier of the Unit being requested
	 * @param newModifier     The new addressModifier
	 */
	public void updateUnitAddressModifier(String currentAccount, String addressModifier, String newModifier) {

	}

	/**
	 * Updates a renter's username
	 * 
	 * @param currentAccount The username of the renter being updated
	 * @param newRenterName  The renter's new username
	 */
	public void updateRenterUsername(String currentAccount, String newRenterName) {

	}

	/**
	 * Updates the username of the PropertyManager that owns this Property
	 * 
	 * @param currentAccount The old username of the owner
	 * @param newManager     The owner's new username
	 */
	public void updateManagerUsername(String currentAccount, String newManager) {

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
		for (String fee : extraFees)
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