package PropertyFinder;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Contains the information for an account for a student.
 */
public class User extends Account {
	protected String studentID;
	protected int creditScore;
	protected ArrayList<String> disablities;

	/**
	 * Parameterized constructor for User
	 * 
	 * @param username     The username of the student
	 * @param password     The password of the student
	 * @param name         The name of The Student
	 * @param dateOfBirth  The date of birth of the student
	 * @param homeAddress  The home address of the student
	 * @param email        the email of the student
	 * @param phoneNumber  The phone number of the student
	 * @param StudentID    The student id of the student
	 */
	public User(String username, String password, String name, String dateOfBirth, String homeAddress, String email,
			String phoneNumber, String StudentID) {
		super(username, password, name, dateOfBirth, homeAddress, email, phoneNumber);
		this.studentID = StudentID;
		this.disablities = new ArrayList<String>();
	}

	/**
	 * Updates the credit score of the student
	 * 
	 * @param currentAccount  The account of the student
	 * @param creditScore     The credit score of the student
	 */
	public void updateCreditScore(String currentAccount, int creditScore) {
		if(authenticate(username)) {
			this.creditScore = creditScore;
		}
	}

	/**
	 * Adds a disability to disabilities list
	 * 
	 * @param currentAccount  The account of the student
	 * @param disability      The disability of the student
	 */
	public void addDisability(String currentAccount, String disability) {
		if(authenticate(currentAccount)){
			disablities.add(disability);
		}
	}
	 
	/**
	 * Removes a disability to disabilities list
	 * 
	 * @param currentAccount  The account of the student
	 * @param disability      The disability of the student
	 */
	public void removeDisability(String currentAccount, String disability) {
		if(authenticate(currentAccount)) {
			boolean exist = false;
			for (String d : disablities) {
				if (d.equals(disability)) {
					disablities.remove(disability);
					exist = true;
					break;
				}
			}
			if (exist)
				System.out.println("Disability Removed.");
			else {
				System.out.println("ERROR: The disability does not exist.");
			}
		}
	}

	/**
	 * Returns a String interpretation of the User
	 * 
	 * @param currentAccount The account of the student
	 * @return String        interpretation of User
	 */
	public String toString(String currentAccount) {
		String output = "";
		output = super.toString(currentAccount);
		output = output.concat("\nStudent ID: " + studentID);
		output = output.concat("\nCredit Score: " + creditScore);
		output = output.concat("\nDisabilities: ");
		for (String disability : disablities) {
			output = output.concat("\n - " + disability);
		}
		return output;
	}

	/**
	 * Returns the contents of User in JSON format
	 * 
	 * @return The JSON interpretation of the User contents
	 */
	public JSONObject toJSON() {
		JSONObject user = super.toJSON();
		user.put("student ID", studentID);
		user.put("credit score", creditScore);

		JSONArray disabilityArray = new JSONArray();
		for (String disability : disablities) {
			disabilityArray.add(disability);
		}

		user.put("disabilities", disabilityArray);

		return user;
	}
}