package PropertyFinder;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        users = new ArrayList<User>();
        propertyManagers = new ArrayList<PropertyManager>();
        properties = new ArrayList<Property>();
    };

    /**
     * Parameterized constructor for System
     * @param properties A preexisting list of Property's
     * @param users A preexisting list of User's
     * @param propertyManagers A preexisting list of PropertyManager's
     */
    public PSystem(ArrayList<Property> properties, ArrayList<User> users,
                  ArrayList<PropertyManager> propertyManagers){
    	this.properties = properties;
    	this.users = users;
    	this.propertyManagers = propertyManagers;
    }

    /**
     * Creates a new User object and adds it to users
     * @param username The username for the User
     * @param password The password for the User
     * @param name The name of the User
     * @param dateOfBirth The date of birth of the User
     * @param homeAddress The home address of the User
     * @param email The email address of the User
     * @param phoneNumber The phone number of the User
     * @param StudentID The student ID of the User
     */
    public void createUserAccount(String username, String password, String name,
                                  String dateOfBirth, String homeAddress, String email,
                                  String phoneNumber, String StudentID){
        //Checks if username exists in any other Account
        boolean exists = false;
        for(User user : users){
            if(username == user.username){
                exists = true;
                break;
            }
        }
        for(PropertyManager manager : propertyManagers){
            if(username == manager.username || exists){
                exists = true;
                break;
            }
        }

        if(!exists) {
            users.add(new User(username, password, name, dateOfBirth, homeAddress, email, phoneNumber,
                    StudentID));
        }
        else{
            System.out.println("Sorry! An account with that username already exists!");
        }
    }

    /**
     * Creates a new PropertyManager object and adds it to propertyManagers
     * @param username The username for the PropertyManager
     * @param password The password for the PropertyManager
     * @param name The name of the PropertyManager
     * @param dateOfBirth The date of birth of the PropertyManager
     * @param homeAddress The home address of the PropertyManager
     * @param email The email address of the PropertyManager
     * @param phoneNumber The phone number of the PropertyManager
     */
    public void createManagerAccount(String username, String password, String name,
                                  String dateOfBirth, String homeAddress, String email,
                                  String phoneNumber){
        //Checks if username exists in any other Account
        boolean exists = false;
        for(User user : users){
            if(username.equals(user.username)){
                exists = true;
                break;
            }
        }
        for(PropertyManager manager : propertyManagers){
            if(username.equals(manager.username) || exists){
                exists = true;
                break;
            }
        }
        if(!exists) {
            propertyManagers.add(new PropertyManager(username, password, name, dateOfBirth,
                    homeAddress, email, phoneNumber));
        }
        else{
            System.out.println("Sorry! An account with that username already exists!");
        }
    }

    /**
     * Allow the user to log in to their Account
     * @param username The username of the Account
     * @param Password The password of the Account
     * @return 1 if user is signed in, 2 if manager is signed in, and 0 if login failure
     */
    public int login(String username, String Password) {
	   	currentAccount = null;
	   	boolean access = false;
	   	boolean userMenu = false;
	   	boolean pmMenu = false; 
	   	int menu = 0;
	   		//Searches through users for username and password.
		     for(User user : users)
		     {
		         if(username.equals(user.username) && Password.equals(user.password)) 
		         {
		             access = true;
		             userMenu = true;
		             break;
		         }
		     }
		     //Searches through property managers for username and password.
		     for(PropertyManager manager : propertyManagers)
		     {
		         if(username.equals(manager.username) && Password.equals(manager.password)) 
		         {
		             access = true;
		             pmMenu = true;
		             break;
		         }
		     }
		     //Login Successful for User
		     if(access && userMenu) 
		     {
		     	currentAccount = username;
		      	System.out.println("Welcome " + currentAccount + ", you are logged in.");
		      	menu = 1;
		     }
		     //Login Successful for Manager
		     else if(access && pmMenu) 
		     {
		     	currentAccount = username;
		      	System.out.println("Welcome " + currentAccount + ", you are logged in.");
		      	menu = 2;
		     }
		     //Login Failed
		     else 
		     {
		    	 System.out.println("Incorrect username or password");	    	 
		     }
			return menu;
		     
}    

    /**
     * Allows the user to log out of their Account
     */
    public void logout() {
    	System.out.println("Logging out...");
    	currentAccount = null;
    }

    /**
     * Returns the Terms of Service
     * @return The Terms of Service
     */
    public String getTOS(){
        return "Program is made by that particular Digital Document File with or without " +
                "Modifications,\n" +
                " and/or any respective portions of your own, or to endorse or promote products " +
                "derived\n" +
                " from this software and to permit persons to whom the Software without " +
                "restriction,\n" +
                " including Compiled Works generated from a Contributor which are necessarily " +
                "infringed\n" +
                " by the terms of Sections 1 and 2 above provided that the Source Code " +
                "distribution\n" +
                " titled \"LEGAL\" that describes the claim in sufficient detail that a recipient" +
                " of\n" +
                " ordinary skill at computer programming to be covered by this License; and (b) " +
                "otherwise\n" +
                " make Covered Code and/or Modifications of Original Code or previous " +
                "Modifications.\n" +
                " Code\"../ means the individual(s) or organization(s) named in the Work through " +
                "the\n" +
                " means of an unequivocal list it might be impossible for you if you modify it. " +
                "For\n" +
                " an executable program, either on an unmodified basis or as an addendum to the " +
                "author\n" +
                " to ask you to deprive anyone else from sharing it farther. Redistribution and " +
                "use\n" +
                " in the case (e.g., if a Distributor Fee, and with or without modifications, and" +
                " in\n" +
                " any derivative version prepared by Licensee.\n" +
                "\n" +
                "BeOpen is making the claim in sufficient detail that a recipient will know whom " +
                "to contact.\n" +
                " If Contributor obtains such knowledge after you make an `intention " +
                "announcement' as\n" +
                " described in Exhibit A in each file of the Package, do not, by themselves, " +
                "cause the\n" +
                " direction or management of such combination). Recipient relating to the extent " +
                "necessary\n" +
                " to enable you to infringe any patents or other broadcasting content and " +
                "products\n" +
                " consisting of \"commercial computer software\" and \"commercial computer\n" +
                " software\" and \"commercial computer software\" and \"commercial computer " +
                "software documentation,\n" +
                "\"../ as such parties remain in full force and effect.\n" +
                "\n" +
                "Notwithstanding the provision set forth herein, no assurances are provided by " +
                "any\n" +
                " other Contributor to the version of the Contribution of such Contributor, if " +
                "any,\n" +
                " in a manner equivalent to the Recipient a non-exclusive, royalty-free, " +
                "world-wide\n" +
                " license to reproduce, analyze, test, perform and/or display publicly, prepare\n" +
                " derivative works, in source and free culture, all users contributing to " +
                "Wikimedia\n" +
                " projects are required to allow Recipient to distribute the same media as an\n" +
                " executable program, either on an unmodified basis or as an executable program" +
                ".\n" +
                " Code\" means (a) the power, direct or contributory patent infringement, then " +
                "this\n" +
                " License, you may distribute the Program (including its Contributions) under the" +
                " \n" +
                "terms of any change. You must immediately discontinue any use of the date such litigation is filed.";
    }

    /**
     * Searches properties for Property's the match query
     * @param query The terms to be searched for
     * @return A list of all Property's that matched the query
     */
    public ArrayList<Property> searchProperty(String query){
        return null;
    }

    /**
     * Searches propertyManagers for PropertyManager's the match query
     * @param query The terms to be searched for
     * @return A list of all PropertyManager's that matched the query
     */
    public ArrayList<PropertyManager> searchManager(String query){
        ArrayList<PropertyManager> results = new ArrayList<PropertyManager>();

        //1st Priority search - Exact match
        for(PropertyManager manager : propertyManagers){
            if(manager.username.equalsIgnoreCase(query) || manager.name.equalsIgnoreCase(query)){
                results.add(manager);
            }
        }

        //2nd Priority search - Partial match
        for(PropertyManager manager : propertyManagers){
            if(!results.contains(manager)){
                for(String part : query.split(" ")){
                    if(!results.contains(manager) && (manager.username.contains(part) || manager.name.contains(part))){
                        results.add(manager);
                    }
                }
            }
        }
        return results;
    }

    /**
     * Searches users for User's the match query
     * @param query The terms to be searched for
     * @return A list of all User's that matched the query
     */
    public ArrayList<User> searchUser(String query){
        ArrayList<User> results = new ArrayList<User>();

        //1st Priority search - Exact match
        for(User user : users){
            if(user.username.equalsIgnoreCase(query) || user.name.equalsIgnoreCase(query)){
                results.add(user);
            }
        }

        //2nd Priority search - Partial match
        for(User user : users){
            if(!results.contains(user)){
                for(String part : query.split(" ")){
                    if(!results.contains(user) && (user.username.contains(part) || user.name.contains(part))){
                        results.add(user);
                    }
                }
            }
        }
        return results;
    }

    /**
     * Sorts a list of Property's by title alphabetically
     * @param properties The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of Property's
     */
    public ArrayList<Property> sortPropertyByTitle(ArrayList<Property> properties, boolean descending){
        return null;
    }

    /**
     * Sorts a list of Property's by price
     * @param properties The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of Property's
     */
    public ArrayList<Property> sortPropertyByPrice(ArrayList<Property> properties, boolean descending){
        return null;
    }

    /**
     * Sorts a list of Property's by average rating
     * @param properties The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of Property's
     */
    public ArrayList<Property> sortPropertyByRating(ArrayList<Property> properties,
                                                    boolean descending){
        return null;
    }

    /**
     * Sorts a list of PropertyManger's by name alphabetically
     * @param managers The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of PropertyManager's
     */
    public ArrayList<PropertyManager> sortManagerByName(ArrayList<PropertyManager> managers,
                                                        boolean descending){
        return null;
    }

    /**
     * Sorts a list of PropertyManger's by average rating
     * @param managers The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of PropertyManager's
     */
    public ArrayList<PropertyManager> sortManagerByRating(ArrayList<PropertyManager> managers,
                                                          boolean descending){
        return null;
    }

    /**
     * Sorts a list of User's by name alphabetically
     * @param users The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of User's
     */
    public ArrayList<User> sortUserByName(ArrayList<User> users, boolean descending){
        return null;
    }

    /**
     * Sorts a list of User's by average rating
     * @param users The list of Property's to be sorted
     * @param descending Whether or not to sort by descending
     * @return The sorted list of User's
     */
    public ArrayList<User> sortUserByRating(ArrayList<User> users, boolean descending){
        return null;
    }

    /**
     * Reverses the order of a list.
     * @param list The lis to be reversed
     * @return The reversed list
     */
    private ArrayList descendSort(ArrayList list){
        return null;
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
        return null;
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
        return null;
    }

    /**
     * Returns the current Account if it is a PropertyManager
     * @return The current Account
     */
    public PropertyManager getManager(){
        return null;
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
    public JSONObject toJSON(){
        JSONObject pSystem = new JSONObject();

        JSONArray userArray = new JSONArray();
        for(User user : users){
            userArray.add(user.toJSON());
        }

        JSONArray managerArray = new JSONArray();
        for(PropertyManager manager : propertyManagers){
            managerArray.add(manager.toJSON());
        }

        JSONArray propertyArray = new JSONArray();
        for(Property property : properties){
            propertyArray.add(property.toJSON());
        }

        pSystem.put("users", userArray);
        pSystem.put("property managers", managerArray);
        pSystem.put("properties", propertyArray);

        return pSystem;
    }
}