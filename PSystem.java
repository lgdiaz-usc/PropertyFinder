package PropertyFinder;

import java.util.ArrayList;

/**
 * Contains the methods and data used by the driver to interact with user accounts and property listings.
 */
public class PSystem {
    protected ArrayList<Property> properties;
    protected ArrayList<User> users;
    protected ArrayList<PropertyManager> propertyManagers;
    protected String currentAccount;

    /**
     * Default constructor for System.
     */
    public PSystem(){

    }

    /**
     * Parameterized constructor for System
     * @param properties A preexisting list of Property's
     * @param users A preexisting list of User's
     * @param propertyManagers A preexisting list of PropertyManager's
     */
    public System(ArrayList<Property> properties, ArrayList<User> users,
                  ArrayList<PropertyManager> propertyManagers){

    }

    /**
     * Creates a new User object and adds it to users
     * @param username The username for the User
     * @param password The password for the User
     * @param name The name of the User
     * @param datOfBirth The date of birth of the User
     * @param homeAddress The home address of the User
     * @param email The email address of the User
     * @param phoneNumber The phone number of the User
     * @param StudentID The student ID of the User
     */
    public void createUserAccount(String username, String password, String name,
                                  String datOfBirth, String homeAddress, String email,
                                  String phoneNumber, String StudentID){

    }

    /**
     * Creates a new PropertyManager object and adds it to propertyManagers
     * @param username The username for the PropertyManager
     * @param password The password for the PropertyManager
     * @param name The name of the PropertyManager
     * @param datOfBirth The date of birth of the PropertyManager
     * @param homeAddress The home address of the PropertyManager
     * @param email The email address of the PropertyManager
     * @param phoneNumber The phone number of the PropertyManager
     */
    public void createManagerAccount(String username, String password, String name,
                                  String datOfBirth, String homeAddress, String email,
                                  String phoneNumber){

    }

    /**
     * Allow the user to log in to their Account
     * @param username The username of the Account
     * @param Password The password of the Account
     */
    public void login(String username, String Password){

    }

    /**
     * Allows the user to log out of their Account
     */
    public void logout(){

    }

    /**
     * Returns the Terms of Service
     * @return The Terms of Service
     */
    public String getTOS(){

    }

    /**
     * Searches properties for Property's the match query
     * @param query The terms to be searched for
     * @return A list of all Property's that matched the query
     */
    public ArrayList<Property> searchProperty(String query){

    }

    /**
     * Searches propertyManagers for PropertyManager's the match query
     * @param query The terms to be searched for
     * @return A list of all PropertyManager's that matched the query
     */
    public ArrayList<PropertyManager> searchManager(String query){

    }

    /**
     * Searches users for User's the match query
     * @param query The terms to be searched for
     * @return A list of all User's that matched the query
     */
    public ArrayList<User> searchUser(String query){

    }

    /**
     * Sorts a list of Property's by title alphabetically
     * @param properties The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of Property's
     */
    public ArrayList<Property> sortPropertyByTitle(ArrayList<Property> properties, boolean descending){

    }

    /**
     * Sorts a list of Property's by price
     * @param properties The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of Property's
     */
    public ArrayList<Property> sortPropertyByPrice(ArrayList<Property> properties, boolean descending){

    }

    /**
     * Sorts a list of Property's by average rating
     * @param properties The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of Property's
     */
    public ArrayList<Property> sortPropertyByRating(ArrayList<Property> properties,
                                                    boolean descending){

    }

    /**
     * Sorts a list of PropertyManger's by name alphabetically
     * @param managers The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of PropertyManager's
     */
    public ArrayList<PropertyManager> sortManagerByName(ArrayList<PropertyManager> managers,
                                                        boolean descending){

    }

    /**
     * Sorts a list of PropertyManger's by average rating
     * @param managers The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of PropertyManager's
     */
    public ArrayList<PropertyManager> sortManagerByRating(ArrayList<PropertyManager> managers,
                                                          boolean descending){

    }

    /**
     * Sorts a list of User's by name alphabetically
     * @param users The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of User's
     */
    public ArrayList<User> sortUserByName(ArrayList<User> users, boolean descending){

    }

    /**
     * Sorts a list of User's by average rating
     * @param users The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of User's
     */
    public ArrayList<User> sortUserByRating(ArrayList<User> users, boolean descending){

    }

    /**
     * Reverses the order of a list.
     * @param list The lis to be reversed
     * @return The reversed list
     */
    private ArrayList descendSort(ArrayList list){

    }

    /**
     * Creates a Message object and sends it to the specified PropertyManager object
     * @param managerName The username of the PropertyManager being contacted
     * @param message The contents of the message
     */
    public void contactManager(String managerName, String message){

    }

    /**
     * Creates a Message object and sends it to the specified User object
     * @param userName The username of the User being contacted
     * @param message The contents of the message
     */
    public void contactUser(String userName, String message) {

    }

    /**
     * Returns the String interpretations for all Message's in the Account corresponding to
     * currentAccount
     * @return The /string interpretation of the current user's Message's
     */
    public String getMessages(){

    }

    /**
     * Adds a User to a Property
     * @param propertyTitle The title of the Property being edited
     * @param renterName The username of the User being added to the Property
     */
    public void addRenter(String propertyTitle, String renterName){

    }

    /**
     * Removes a User to a Property
     * @param propertyTitle The title of the Property being edited
     * @param renterName The username of the User being removed to the Property
     */
    public void removeRenter(String propertyTitle, String renterName){

    }

    /**
     * Adds a User to a Unit
     * @param propertyTitle The title of the Property being edited
     * @param addressModifier The addressModifier that identifies the Unit being edited
     * @param renterName The username of the USer being added
     */
    public void addUnitRenter(String propertyTitle, String addressModifier, String renterName){

    }

    /**
     * Removes a User to a Unit
     * @param propertyTitle The title of the Property being edited
     * @param addressModifier The addressModifier that identifies the Unit being edited
     * @param renterName The username of the USer being removed
     */
    public void removeUnitRenter(String propertyTitle, String addressModifier, String renterName){

    }

    /**
     * Creates a Property object and adds it to properties
     * @param title ->The title of the Property
     * @param description The description of the Property
     * @param address The address of the Property
     * @param capacity The maximum capacity of the Property
     * @param baseRent The base amount of rent for the Property
     */
    public void addProperty(String title, String description, String address, int capacity,
                            double baseRent){

    }

    /**
     * Adds a Unit to a Property
     * @param propertyName The title of the Property to be edited
     * @param addressModifier The addressModifier of the Unit
     * @param capacity The maximum capacity of the Unit
     */
    public void addUnit(String propertyName, String addressModifier, int capacity){

    }

    /**
     * Removes a Property from properties
     * @param propertyName The title of the Property to be removed
     */
    public void removeProperty(String propertyName) {

    }

    /**
     * Removes a Unit from a Property
     * @param propertyName The title of Property to be edited
     * @param addressModifier The addressModifier of the Unit to be removed
     */
    public void removeUnit(String propertyName, String addressModifier){

    }

    /**
     * Edits a Property based on EditType
     * @param propertyName The title of the Property to be edited
     * @param change The replacement String
     * @param type Which String is to be replaced
     */
    public void editProperty(String propertyName, String change, EditType type){

    }

    /**
     * Edits a Property based on EditType
     * @param propertyName The title of the Property to be edited
     * @param addressModifier The addressModifier of the Unit to be edited
     * @param change The replacement String
     * @param type Which String is to be replaced
     */
    public void editUnit(String propertyName, String addressModifier, String change, EditType type){

    }

    /**
     * Adds an additional fee to a Property
     * @param propertyName The title of the Property to be edited
     * @param name The name of the fee
     * @param fee The dollar amount of the fee
     */
    public void addFee(String propertyName, String name, double fee){

    }

    /**
     * Removes an additional fee from a Property
     * @param propertyName The title of the Property to be edited
     * @param name The name of the fee to be removed
     */
    public void removeFee(String propertyName, String name){

    }

    /**
     * Adds a Review to an object based on ReviewType
     * @param rating Rating of the Review (out of 10)
     * @param title The title of the Review
     * @param description The description of the Review
     * @param reviewSubject the username/title/addressModifier of the object being reviewed
     * @param Type The type of object being reviewed
     */
    public void addReview(int rating, String title, String description, String reviewSubject,
                          ReviewType Type){

    }

    /**
     * Returns the current Account if it is a User
     * @return The current Account
     */
    public User getUser() {

    }

    /**
     * Returns the current Account if it is a PropertyManager
     * @return The current Account
     */
    public PropertyManager getManager(){

    }

    /**
     * Replaces a User in users with newUser
     * @param newUser
     */
    public void updateAccount(User newUser) {

    }

    /**
     * Replaces a PropertyManager in propertyManagers with newManager
     * @param newManager
     */
    public void updateAccount(PropertyManager newManager){

    }

    /**
     * Returns all of the data in users, propertyManagers, and properties in the JSON format
     * @return The Json interpretation of System's data
     */
    public String toJSON(){

    }
}
