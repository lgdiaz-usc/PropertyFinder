package PropertyFinder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Allows the user to interact with the system
 */
public class Driver {
	/**
	 * The starting point of the program. Lets the user choose which JSON to read from  and has
	 * the main UI loop
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean done = false;
		PSystem system = null;

		// Choosing which JSON database to use
		System.out.println("Would you like to use the default database? (y/n)");
		char choice = input.next().charAt(0);
		if (choice == 'y' || choice == 'Y') {
			system = DataReader.read("data.json");
			if (system == null) {
				system = new PSystem();
			}
		} else {
			boolean isValid = false;
			while (!isValid) {
				System.out.println("Which database would you like to use? (or \"default\" for " + "default)");
				String database = input.next();
				if (database.equals("default")) {
					system = DataReader.read("data.json");
					if (system == null) {
						system = new PSystem();
					}
					isValid = true;
				} else {
					system = DataReader.read(database);
					if (system == null) {
						System.out.println("Error: That database does not exist!");
					} else {
						isValid = true;
					}
				}
			}
		}

		// UI Loop for MAIN MENU command
		while (!done) {
			System.out.print("Please enter a command:" + "\n>");
			String command = input.next();

			switch (command) {
				case "help":
					System.out.println("The available commands are:"
							+ "\nhelp -     Displays available commands"
							+ "\nquit -     Quits the program"
							+ "\nlogin -    Login into account"
							+ "\nregister - Creates a new account"
							+ "\nsearch -   Searches for accounts and property listings"
							+ "\nrent -		Generates a lease agreement for a property listing");
					break;
				case "quit":
					done = true;
					break;
				case "login":
					int value = login(system);
					if (value == 1) {
						system = displayUserMenu(system);
						break;
					} else if (value == 2) {
						system = displayPMMenu(system);
						break;
					}
					break;
				case "register":
					system = createAccount(system);
					break;
				case "search":
					search(system);
					break;
				case "rent":
					System.out.println("Error: You need to create an account before you can start" +
							" renting properties!");
					break;
				default:
					System.out.println(
							"\"" + command + "\" is not a valid command! Please type " + "\"help\" for a list of commands");
			}
		}

		// Saves data upon completion
		System.out.println("Saving...");
		DataWriter.write(system);
		System.out.println("Goodbye!");
	}

	/**
	 * Contains the UI loop for when a student is logged in
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem displayUserMenu(PSystem system) {
		boolean done = false;
		Scanner input = new Scanner(System.in);
		System.out.println("*****STUDENT MENU*****");

		// UI Loop for USER commands
		while (!done) {
			System.out.print("\n" + "Please enter a command:" + "\n>");
			String command = input.next();

			switch (command) {
				case "help":
					System.out.println("The available commands are:" + "\nhelp - Displays available commands"
							+ "\nlogout   - Log out of account"
							+ "\nadd      - Adds a disability"
							+ "\nremove   - Removes a disability"
							+ "\nsearch   - Searches for accounts and property listings"
							+ "\ncontact  - Sends a message to another account"
							+ "\nmessages - Displays all of your messages"
							+ "\nreview   - Writes a review for an account or a property listing"
							+ "\nrent     - Generates a lease agreement for a property listing"
							+ "\nedit     - Edits your account");
					break;
				case "logout":
					system = logout(system);
					done = true;
					break;
				case "add":
					system = addDisability(system);
					break;
				case "remove":
					system = removeDisability(system);
					break;
				case "search":
					search(system);
					break;
				case "contact":
					system = contact(system);
					break;
				case "messages":
					System.out.println(system.getMessages());
					break;
				case "review":
					system = review(system);
					break;
				case "rent":
					system = rent(system);
					break;
				case "edit":
					system = editUser(system);
					break;
				default:
					System.out.println(
							"\"" + command + "\" is not a valid command! Please type " + "\"help\" for a list of commands");
			}
		}
		return system;
	}

	/**
	 * Contains the UI Loop for when a property manager is logged in
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem displayPMMenu(PSystem system) {
		boolean done = false;
		Scanner input = new Scanner(System.in);
		System.out.println("*****PROPERTY MANAGER MENU*****");

		// UI Loop for MANAGER commands
		while (!done) {
			System.out.print("\n" + "Please enter a command:" + "\n>");
			String command = input.next();

			switch (command) {
				case "help":
					System.out.println("The available commands are:" + "\nhelp - Displays available commands"
							+ "\nlogout -   Log out of account"
							+ "\nadd -      Adds a property, unit, renter or unit renter"
							+ "\nremove -   Removes a property, unit, renter, or unit renter"
							+ "\nsearch -   Searches for accounts and property listings"
							+ "\ncontact -  Sends a message to another account"
							+ "\nmessages - Displays the contents of all of your messages"
							+ "\nreview -   Write a review for an account or property listing"
							+ "\nedit -     Edits your account or a property listing you own");
					break;
				case "logout":
					system = logout(system);
					done = true;
					break;
				case "add":
					system = add(system);
					break;
				case "remove":
					system = remove(system);
					break;
				case "search":
					search(system);
					break;
				case "contact":
					system = contact(system);
					break;
				case "messages":
					System.out.println(system.getMessages());
					break;
				case "review":
					system = review(system);
					break;
				case "edit":
					system = editManager(system);
					break;
				default:
					System.out.println("\"" + command + "\" is not a valid command! Please type " + "\"help\" "
							+ "for a list of commands");
			}
		}

		return system;
	}

	/**
	 * Allows the user to create a new Account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem createAccount(PSystem system) {
		Scanner input = new Scanner(System.in);
		String accountType;

		// Choosing account type
		System.out.print("Are you a student or a property manager?" + "\n>");
		accountType = input.nextLine();

		// Inputting information
		if (accountType.equalsIgnoreCase("student") || accountType.equalsIgnoreCase("property " + "manager")) {
			String username;
			String password;
			String name;
			String dateOfBirth;
			String homeAddress;
			String email;
			String phoneNumber;

			System.out.print("Please input a username:" + "\n>");
			username = input.nextLine();
			System.out.print("Please input a password:" + "\n>");
			password = input.nextLine();
			System.out.print("Please input your name:" + "\n>");
			name = input.nextLine();
			System.out.print("Please input your date of birth:" + "\n>");
			dateOfBirth = input.nextLine();
			System.out.print("Please input your home address:" + "\n>");
			homeAddress = input.nextLine();
			System.out.print("Please input your e-mail address:" + "\n>");
			email = input.nextLine();
			System.out.print("Please input your phone number:" + "\n>");
			phoneNumber = input.nextLine();

			char acceptTOS;
			System.out.println("TERMS OF SERVICE:\n" + system.getTOS());
			System.out.print("\nDo you accept these terms? (y/n)" + "\n>");
			acceptTOS = input.nextLine().charAt(0);

			// Account creation
			if (acceptTOS == 'y' || acceptTOS == 'Y') {
				if (accountType.equalsIgnoreCase("student")) {
					String StudentID;
					System.out.print("Almost there! Please enter your student ID:" + "\n>");
					StudentID = input.nextLine();
					system.createUserAccount(username, password, name, dateOfBirth, homeAddress, email, phoneNumber,
							StudentID);
				} else {
					system.createManagerAccount(username, password, name, dateOfBirth, homeAddress, email, phoneNumber);
				}
				System.out.println("Account created!");
			} else {
				System.out.println("You have accept the Terms of Service in order to create an " + "account.");
			}
		} else {
			System.out.println("ERROR: \"" + accountType + "\" is not a valid account type!");
		}
		return system;
	}

	/**
	 * Allows the user to login to an existing Account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static int login(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		String username, password;
		System.out.println("Please log in:");
		System.out.print("Enter your username:" + "\n>");
		username = input.nextLine();
		System.out.print("Enter your password:" + "\n>");
		password = input.nextLine();

		// Login to Account
		return system.login(username, password);
	}

	/**
	 * Allows the user to log out of their account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem logout(PSystem system) {
		// Logout of Account
		system.logout();
		System.out.println("You are logged out.");

		// Return to the main menu
		return system;
	}

	/**
	 * Searches for property listings, student accounts, and property manager accounts
	 * @param system The main PSystem object
	 */
	private static void search(PSystem system) {
		Scanner input = new Scanner(System.in);
		String searchType;

		// Input search type
		System.out.print("What would you like to search for? (user/manager/property)" + "\n>");
		searchType = input.next();
		if (searchType.equalsIgnoreCase("user") || searchType.equalsIgnoreCase("manager")
				|| searchType.equalsIgnoreCase("property")) {
			// Input search terms
			input = new Scanner(System.in);
			System.out.print("Please enter your search terms:" + "\n>");
			String query = input.nextLine();

			int sortType;
			boolean isDescending;
			// Search and sort
			if (searchType.equalsIgnoreCase("user")) {
				ArrayList<User> results = system.searchUser(query);
				System.out.print("How would you like to sort the results?" + "\n1: By relevancy" + "\n2: By name"
						+ "\n3: By rating" + "\n>");
				sortType = input.nextInt();
				System.out.print("Would you like your results in descend order? (y/n)" + "\n>");
				String order = input.next();
				if (order.equalsIgnoreCase("y")) {
					isDescending = true;
				} else {
					isDescending = false;
				}

				if (sortType == 2) {
					results = system.sortUserByName(results, isDescending);
				} else if (sortType == 3) {
					results = system.sortUserByRating(results, isDescending);
				} else if (isDescending) {
					results = system.descendSort(results);
				}

				// Display User results
				for (User user : results) {
					System.out.println(user.toString(null) + "\n");
				}
			} else if (searchType.equalsIgnoreCase("manager")) {
				ArrayList<PropertyManager> results = system.searchManager(query);
				System.out.print("How would you like to sort the results?" + "\n1: By relevancy" + "\n2: By name"
						+ "\n3: By rating" + "\n>");
				sortType = input.nextInt();
				System.out.print("Would you like your results in descend order? (y/n)" + "\n>");
				String order = input.next();
				if (order.equalsIgnoreCase("y")) {
					isDescending = true;
				} else {
					isDescending = false;
				}

				if (sortType == 2) {
					results = system.sortManagerByName(results, isDescending);
				} else if (sortType == 3) {
					results = system.sortManagerByRating(results, isDescending);
				} else if (isDescending) {
					results = system.descendSort(results);
				}

				// Display Property Manager results
				for (PropertyManager manager : results) {
					System.out.println(manager.toString(null) + "\n");
				}
			} else {
				ArrayList<Property> results = system.searchProperty(query);
				System.out.print("How would you like to sort the results?" + "\n1: By relevancy" + "\n2: By title"
						+ "\n3: By rating" + "\n4: By price" + "\n>");
				sortType = input.nextInt();
				System.out.print("Would you like your results in descend order? (y/n)" + "\n>");
				String order = input.next();
				if (order.equalsIgnoreCase("y")) {
					isDescending = true;
				} else {
					isDescending = false;
				}

				if (sortType == 2) {
					results = system.sortPropertyByTitle(results, isDescending);
				} else if (sortType == 3) {
					results = system.sortPropertyByRating(results, isDescending);
				} else if (sortType == 4) {
					results = system.sortPropertyByPrice(results, isDescending);
				} else if (isDescending) {
					results = system.descendSort(results);
				}

				// Display Property Manager results
				for (Property property : results) {
					System.out.println(property.toString() + "\n");
				}
			}
		} else {
			System.out.println("ERROR: Invalid search type!");
		}
	}

	/**
	 * Allows property managers to add property listings, units, and renters
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem add(PSystem system) {
		Scanner input = new Scanner(System.in);
		int choice;
		// Inputting information
		System.out.print("What would you like to add? (Select a number)." + "\n1: Property" + "\n2: Unit"
				+ "\n3: Renter" + "\n4: Unit Renter" + "\n>");
		choice = input.nextInt();
		if (choice == 1) {
			system = addProperty(system);
		} else if (choice == 2) {
			system = addUnit(system);
		} else if (choice == 3) {
			system = addRenter(system);
		} else if (choice == 4) {
			system = addUnitRenter(system);
		} else {
			System.out.println("ERROR: Invalid add type");
		}
		return system;
	}

	/**
	 * Adds a new property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem addProperty(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.print("Please enter property title." + "\n>");
		String title = input.nextLine();
		System.out.print("Please enter a description." + "\n>");
		String description = input.nextLine();
		System.out.print("Please enter the address." + "\n>");
		String address = input.nextLine();
		System.out.print("Please enter property capacity." + "\n>");
		int capacity = input.nextInt();
		System.out.print("Please enter the base rent." + "\n>");
		double baseRent = input.nextDouble();

		// Adds property
		system.addProperty(title, description, address, capacity, baseRent);
		return system;
	}

	/**
	 * Adds a new unit to an existing property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem addUnit(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.print("Please enter property name." + "\n>");
		String propertyName = input.nextLine();
		System.out.print("Please enter unit." + "\n>");
		String addressModifier = input.next();
		System.out.print("Please enter unit capacity." + "\n>");
		int capacity = input.nextInt();

		// Adds Unit
		system.addUnit(propertyName, addressModifier, capacity);
		return system;
	}

	/**
	 * Adds a renter to an existing property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem addRenter(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.print("Please enter property name." + "\n>");
		String propertyName = input.nextLine();
		System.out.print("Please enter renter's username." + "\n>");
		String renterName = input.nextLine();

		// Adds Renter
		system.addRenter(propertyName, renterName);
		return system;
	}

	/**
	 * Adds a renter to an existing unit in an existing property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem addUnitRenter(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		 System.out.print("Please enter the property's name." + "\n>");
		 String propertyTitle = input.nextLine();
		 System.out.print("Please enter the unit." + "\n>");
		 String addressModifier = input.nextLine();
		 System.out.print("Please enter the renter's username." + "\n>");
		 String renterName = input.nextLine();

		// Add Unit Renter
		 system.addUnitRenter(propertyTitle, addressModifier, renterName);
		 return system;
	}

	/**
	 * Allows a property manager to remove an existing property listing, unit, or renter
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem remove(PSystem system) {
		Scanner input = new Scanner(System.in);
		int choice;
		// Inputting information
		System.out.print("What would you like to remove? (Select a number)." + "\n1: Property" + "\n2: Unit"
				+ "\n3: Renter" + "\n4: Unit Renter" + "\n>");
		choice = input.nextInt();
		if (choice == 1) {
			system = removeProperty(system);
		} else if (choice == 2) {
			system = removeUnit(system);
		} else if (choice == 3) {
			system = removeRenter(system);
		} else if (choice == 4) {
			system = removeUnitRenter(system);
		} else {
			System.out.println("ERROR: Invalid remove type");
		}
		return system;
	}

	/**
	 * Removes an existing property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem removeProperty(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.print("Enter the property name" + "\n>");
		String propertyName = input.nextLine();

		// Removes property listing
		system.removeProperty(propertyName);
		return system;
	}

	/**
	 * Removes an existing unit from an existing property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem removeUnit(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.print("Please enter property name." + "\n>");
		String propertyName = input.nextLine();
		System.out.print("Please enter unit." + "\n>");
		String addressModifier = input.nextLine();

		// Removes unit from property listing
		system.removeUnit(propertyName, addressModifier);
		return system;
	}

	/**
	 * Removes a renter from an existing property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem removeRenter(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.print("Please enter property name." + "\n>");
		String propertyName = input.nextLine();
		System.out.print("Please enter renter's username." + "\n>");
		String renterName = input.nextLine();

		// Removes renter from property listings.
		system.removeRenter(propertyName, renterName);
		return system;
	}

	/**
	 * Removes a renter from an existing unit from an existing property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem removeUnitRenter(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		 System.out.print("Please enter the property's name." + "\n>");
		 String propertyTitle = input.nextLine();
		 System.out.print("Please enter the unit." + "\n>");
		 String addressModifier = input.nextLine();
		 System.out.print("Please enter the renter's username." + "\n>");
		 String renterName = input.nextLine();

		// Removes Unit Renter
		 system.removeUnitRenter(propertyTitle, addressModifier, renterName);
		 return system;
	}

	/**
	 * Sends a message to an existing Account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem contact(PSystem system) {
		Scanner input = new Scanner(System.in);
		String recipientType;
		System.out.print("Would you like to contact a user, or a property manager? " + "(user/manager)" + "\n>");
		recipientType = input.next();

		if (recipientType.equalsIgnoreCase("user")) {
			String username, message;
			System.out.print("What is the username of the user you would like to contact?" + "\n>");
			username = input.next();

			input = new Scanner(System.in);
			System.out.print("What message would you like to send to that user?" + "\n>");
			message = input.nextLine();
			system.contactUser(username, message);
		} else if (recipientType.equalsIgnoreCase("manager")) {
			String username, message;
			System.out.print("What is the username of the property manager you would like to " + "contact?" + "\n>");
			username = input.next();

			input = new Scanner(System.in);
			System.out.print("What message would you like to send to that property manager?" + "\n>");
			message = input.nextLine();
			system.contactManager(username, message);
		} else {
			System.out.println("ERROR: \"" + recipientType + "\" is not a valid account type!");
		}

		return system;
	}

	/**
	 * Makes a review for an existing property listing, unit, student account, or property
	 * manager account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem review(PSystem system) {
		Scanner input = new Scanner(System.in);
		String reviewType;

		System.out.print("What are you reviewing? (user/manager/property/unit)" + "\n>");
		reviewType = input.next();
		if (reviewType.equalsIgnoreCase("user") || reviewType.equalsIgnoreCase("manager")) {
			System.out.print("What is the username of the account you want to review?" + "\n>");
			String username = input.next();
			System.out.print("What is the numerical rating of your review? (out of 10)" + "\n>");
			int rating = input.nextInt();

			input = new Scanner(System.in);
			System.out.print("What is the title of your review?" + "\n>");
			String title = input.nextLine();
			System.out.print("What is the description of your review?" + "\n>");
			String description = input.nextLine();

			if (reviewType.equalsIgnoreCase("manager")) {
				system.addReview(rating, title, description, username, ReviewType.PROPERTY_MANAGER);
			} else {
				system.addReview(rating, title, description, username, ReviewType.USER);
			}
		} else if (reviewType.equalsIgnoreCase("property") || reviewType.equalsIgnoreCase("unit")) {
			input = new Scanner(System.in);
			System.out.print("What is the title of the property you want to review?" + "\n>");
			String name = input.nextLine();
			System.out.print("What is the numerical rating of your review? (out of 10)" + "\n>");
			int rating = input.nextInt();

			input = new Scanner(System.in);
			System.out.print("What is the title of your review?" + "\n>");
			String title = input.nextLine();
			System.out.print("What is the description of your review?" + "\n>");
			String description = input.nextLine();

			if (reviewType.equalsIgnoreCase("unit")) {
				input = new Scanner(System.in);
				System.out.print("What is the address modifier of the unit you want to review?" + "\n>");
				name = name.concat("\t" + input.nextLine());
				system.addReview(rating, title, description, name, ReviewType.UNIT);
			} else {
				system.addReview(rating, title, description, name, ReviewType.PROPERTY);
			}
		} else {
			System.out.println("ERROR: Invalid review type!");
		}

		return system;
	}

	/**
	 * Generates a lease agreement for 1 or more student accounts and an existing property listing
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem rent(PSystem system){
		Scanner input = new Scanner(System.in);

		System.out.print("What is the title of the property you would like to rent?" + "\n>");
		String title = input.nextLine();
		input = new Scanner(System.in);

		System.out.print("How many other students would you like to sign your lease with (0 if " +
				"just you)?" + "\n>");
		int numCoRenters = input.nextInt();
		if(numCoRenters > 0){
			String[] coRenters = new String[numCoRenters];
			for(int i=0; i < numCoRenters; i++){
				System.out.println("Please enter the username for corenter #" + (i+1) + "." + "\n" + ">");
				coRenters[i] = input.next();
			}
			system.generateLease(title, coRenters);
		}
		else{
			system.generateLease(title);
		}
		return system;
	}

	/**
	 * Allows a User to edit their account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem editUser(PSystem system){
	Scanner input = new Scanner(System.in);
	System.out.print("What would you like to edit? " +
			"(name/dateofbirth/address/email/phonenumber/username/password/creditscore)" + "\n>");	
	String editChoice = input.nextLine();
	
	User newUser = system.getUser();
	if(editChoice.equalsIgnoreCase("name")){
		System.out.print("What would you like your new name to be?" + "\n>");
		String name = input.nextLine();
		newUser.updateName(newUser.username, name);
	}
	else if(editChoice.equalsIgnoreCase("dateofbirth")){
		System.out.print("What would you like your new date of birth to be?" + "\n>");
		String dateOfBirth = input.nextLine();
		newUser.updateDateOfBirth(newUser.username, dateOfBirth);
	}
	else if(editChoice.equalsIgnoreCase("address")){
		System.out.print("What would you like your new address to be?" + "\n>");
		String homeAddress = input.nextLine();
		newUser.updateHomeAddress(newUser.username, homeAddress);
	}
	else if(editChoice.equalsIgnoreCase("email")){
		System.out.print("What would you like your new email to be?" + "\n>");
		String email = input.nextLine();
		newUser.updateEmail(newUser.username, email);
	}
	else if(editChoice.equalsIgnoreCase("phonenumber")){
		System.out.print("What would you like your new phone number to be?" + "\n>");
		String phoneNumber = input.nextLine();
		newUser.updatePhoneNumber(newUser.username, phoneNumber);

	}
	else if(editChoice.equalsIgnoreCase("username")){
		System.out.print("What would you like your new username to be?" + "\n>");
		String username = input.nextLine();
		newUser.updateUsername(newUser.username, username);
		System.out.println("newUser.username is " + newUser.username);
	}
	
	else if(editChoice.equalsIgnoreCase("password")){
		System.out.print("What would you like your new password to be?" + "\n>");
		String password = input.nextLine();
		newUser.updatePassword(newUser.username, password);
		System.out.println("newUser.password is " + newUser.password);
	}
	else if(editChoice.equalsIgnoreCase("creditscore")){
		System.out.print("What would you like your new credit score to be?" + "\n>");
		String change = input.nextLine();
		int creditScore = Integer.parseInt(change);
		newUser.updateCreditScore(newUser.username, creditScore);
	}
	else {
		System.out.println("ERROR: Invalid edit type!");
	}
	system.updateAccount(newUser);
	return system;
	}

	/**
	 * Allows a  property manager account to edit a property listing they own, or their account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem editManager(PSystem system){
		Scanner input = new Scanner(System.in);
		System.out.print("Would you like to edit your account or one of your properties? " +
				"(account/property)" + "\n>");
		String editChoice = input.next();

		if(editChoice.equalsIgnoreCase("property"))
		{
			System.out.print("What property would you like to edit?" + "\n>");
			input = new Scanner(System.in);
			String propertyTitle = input.nextLine();

			System.out.print("What attribute of " + propertyTitle + " would you like to edit? " +
					"(title/description/address/capacity/rent/unit)" + "\n>");
			editChoice = input.next();

			if(editChoice.equalsIgnoreCase("title")){
				System.out.print("What would you like the new title to be?" + "\n>");
				input = new Scanner(System.in);
				String change = input.nextLine();
				system.editProperty(propertyTitle, change, EditType.TITLE);
			}
			else if(editChoice.equalsIgnoreCase("description")){
				System.out.print("What would you like the new description to be?" + "\n>");
				input = new Scanner(System.in);
				String change = input.nextLine();
				system.editProperty(propertyTitle, change, EditType.DESCRIPTION);
			}
			else if(editChoice.equalsIgnoreCase("address")){
				System.out.print("What would you like the new address to be?" + "\n>");
				input = new Scanner(System.in);
				String change = input.nextLine();
				system.editProperty(propertyTitle, change, EditType.ADDRESS);
			}
			else if(editChoice.equalsIgnoreCase("capacity")){
				System.out.print("What would you like the new maximum capacity to be?" + "\n>");
				input = new Scanner(System.in);
				String change = input.nextLine();
				system.editProperty(propertyTitle, change, EditType.CAPACITY);
			}
			else if(editChoice.equalsIgnoreCase("rent")){
				System.out.print("What would you like the new base rent to be?" + "\n>$");
				input = new Scanner(System.in);
				String change = input.nextLine();
				system.editProperty(propertyTitle, change, EditType.RENT);
			}
			else if(editChoice.equalsIgnoreCase("Unit")){
				System.out.print("What is the address modifier of the Unit you would like to " +
						"edit?" + "\n>");
				input = new Scanner(System.in);
				String addressModifier = input.nextLine();
				input = new Scanner(System.in);

				System.out.print("What attribute of this unit would you like to change? " +
						"(address/capacity)" + "\n>");
				editChoice = input.next();

				if(editChoice.equalsIgnoreCase("address")){
					System.out.print("What would you like the new address modifier to be?" + "\n>");
					input = new Scanner(System.in);
					String change = input.nextLine();
					system.editUnit(propertyTitle, addressModifier, change, EditType.ADDRESS);
				}
				else if(editChoice.equalsIgnoreCase("capacity")){
					System.out.print("What would you like the new maximum capacity to be?" + "\n>");
					input = new Scanner(System.in);
					String change = input.nextLine();
					system.editUnit(propertyTitle, addressModifier, change, EditType.CAPACITY);
				}
				else{
					System.out.println("ERROR: Invalid edit type!");
				}
			}
			else{
				System.out.println("ERROR: Invalid edit type!");
			}
		}
		else if(editChoice.equalsIgnoreCase("account"))
		{
			System.out.print("What would you like to edit? " +
					"(name/dateofbirth/address/email/phonenumber/username/password)" + "\n>");	
			input = new Scanner(System.in);
			String edit = input.nextLine();
			PropertyManager newManager = system.getManager();
			if(edit.equalsIgnoreCase("name")){
				System.out.print("What would you like your new name to be?" + "\n>");
				String name = input.nextLine();
				newManager.updateName(newManager.username, name);
			}
			else if(edit.equalsIgnoreCase("dateofbirth")){
				System.out.print("What would you like your new date of birth to be?" + "\n>");
				String dateOfBirth = input.nextLine();
				newManager.updateDateOfBirth(newManager.username, dateOfBirth);
			}
			else if(edit.equalsIgnoreCase("address")){
				System.out.print("What would you like your new address to be?" + "\n>");
				String homeAddress = input.nextLine();
				newManager.updateHomeAddress(newManager.username, homeAddress);
			}
			else if(edit.equalsIgnoreCase("email")){
				System.out.print("What would you like your new email to be?" + "\n>");
				String email = input.nextLine();
				newManager.updateEmail(newManager.username, email);
			}
			else if(edit.equalsIgnoreCase("phonenumber")){
				System.out.print("What would you like your new phone number to be?" + "\n>");
				String phoneNumber = input.nextLine();
				newManager.updatePhoneNumber(newManager.username, phoneNumber);

			}
			else if(edit.equalsIgnoreCase("username")){
				System.out.print("What would you like your new username to be?" + "\n>");
				String username = input.nextLine();
				newManager.updateUsername(newManager.username, username);
			}
			
			else if(edit.equalsIgnoreCase("password")){
				System.out.print("What would you like your new password to be?" + "\n>");
				String password = input.nextLine();
				newManager.updatePassword(newManager.username, password);
				system.updateAccount(newManager);
			}
			else 
			{
				System.out.println("ERROR: Invalid edit type!");
			}
			system.updateAccount(newManager);
		}
		else 
		{
			System.out.println("ERROR: Invalid input!");
		}

		return system;
	}

	/**
	 * Adds a disability to an existing student account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem addDisability(PSystem system) {
		Scanner input = new Scanner(System.in);
		
		// Inputting information
		System.out.print("Please enter a disability to add." + "\n>");		
		String disability = input.nextLine();
		
		//Removes disability
		system.addDisability(disability);
		return system;
	}

	/**
	 * Removes a disability from an existing student account
	 * @param system The main PSystem object
	 * @return The main PSystem object
	 */
	private static PSystem removeDisability(PSystem system) {
		Scanner input = new Scanner(System.in);
		
		// Inputting information
		System.out.print("Please enter a disability to remove." + "\n>");
		String disability = input.nextLine();
		
		//Removes disability
		system.removeDisability(disability);
		return system;
	}
}