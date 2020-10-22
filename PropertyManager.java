package PropertyFinder;

/**
 * Contains methods and data for property manager accounts
 */
public class PropertyManager extends Account{
    /**
     * Parameterized constructor for PropertyManager
     * @param username The username of the PropertyManager
     * @param password The password of the PropertyManager
     * @param name The name of the PropertyManager
     * @param dateOfBirth The date of birth of the PropertyManager
     * @param homeAddress The home address of the PropertyManager
     * @param email The email address of th ePropertyManager
     * @param phoneNumber The phone number of the PropertyManager
     */
    public PropertyManager(String username, String password, String name, String dateOfBirth,
                           String homeAddress, String email, String phoneNumber){
        super(username, password, name, dateOfBirth, homeAddress, email, phoneNumber);
    }
}