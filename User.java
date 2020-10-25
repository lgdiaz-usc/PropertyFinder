package PropertyFinder;

import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 * Contains the information for an account for a student. 
 */
public class User extends Account{
	protected  String studentID;
	protected  int creditScore;
	protected  ArrayList<String> disablities;
	
	/**
	 * Parameterized constructor for User
	 * @param username -> The username of the student
	 * @param password -> The password of the student
	 * @param name -> The name of The Student
	 * @param dateOfBirth -> The date of birth of the student
	 * @param homeAddress -> The home address of the student
	 * @param email -> the email of the student
	 * @param phoneNumber -> the phone number of the student
	 * @param StudentID -> the student id of the student
	 */
	public User(String username, String password, String name, String dateOfBirth, String homeAddress, String email, String phoneNumber, String StudentID) {
		super(username, password, name, dateOfBirth, homeAddress, email, phoneNumber);
		this.studentID = StudentID;
		this.disablities = new ArrayList<String>();
	}
	
	/**
	 * Updates the credit score of the student
	 * @param currentAccount -> The account of the student
	 * @param creditScore -> The credit score of the student
	 */
	public void updateCreditScore(String currentAccount, int creditScore) {
		
	}
	
	/**
	 * Adds a disability to disabilities list
	 * @param currentAccount -> The account of the student
	 * @param disability -> The disability of the student
	 */
	public void addDisability(String currentAccount, String disability) {
		
	}
	
	/**
	 * Removes a disability to disabilities list
	 * @param currentAccount -> The account of the student
	 * @param disability -> The disability of the student
	 */
	public void removeDisability(String currentAccount, String disability) {
		
	}
	
	/**
	 * Returns a String interpretation of the User
	 * @param currentAccount -> The account of the student
	 * @return String interpretation of User
	 */
	public String toString(String currentAccount) {
		String output = "";
		output = super.toString(currentAccount);
		output = output.concat("\nStudent ID: " + studentID);
		output = output.concat("\nCredit Score: " + creditScore);
		output = output.concat("\nDisabilities: ");
		for(String disability : disablities){
			output = output.concat("\n - " + disability);
		}
		return output;
	}
	
	/**
	 * Returns the contents of User in JSON format
	 * @return The JSON interpretation of the User contents
	 */
	public JSONObject toJSON() {
		JSONObject user = super.toJSON();
		user.put("student ID", studentID);
		user.put("credit score", creditScore);
		user.put("disabilities", disablities);

		return user;
	}
}