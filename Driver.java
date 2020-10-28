package PropertyFinder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Allows the user to interact with the system
 */
public class Driver {
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
				System.out.println("The available commands are:" + "\nhelp - Displays available commands"
						+ "\nquit - Quits the program" + "\nlogin - Login into account"
						+ "\nregister - Creates a new account"
						+ "\nsearch - Searches for accounts and property listings");
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

	public static PSystem displayUserMenu(PSystem system) {
		boolean done = false;
		Scanner input = new Scanner(System.in);
		System.out.println("*****STUDENT MENU*****");

		// UI Loop for USER commands
		while (!done) {
			System.out.print("Please enter a command:" + "\n>");
			String command = input.next();

			switch (command) {
			case "help":
				System.out.println("The available commands are:" + "\nhelp - Displays available commands"
						+ "\nlogout - Log out of account" + "\nsearch - Searches for accounts and property listings");
				break;
			case "logout":
				system = logout(system);
				done = true;
				break;
			case "search":
				search(system);
				break;
			default:
				System.out.println(
						"\"" + command + "\" is not a valid command! Please type " + "\"help\" for a list of commands");
			}
		}

		return system;
	}

	public static PSystem displayPMMenu(PSystem system) {
		boolean done = false;
		Scanner input = new Scanner(System.in);
		System.out.println("*****PROPERTY MANAGER MENU*****");

		// UI Loop for MANAGER commands
		while (!done) {
			System.out.println("");
			System.out.print("Please enter a command:" + "\n>");
			String command = input.next();

			switch (command) {
			case "help":
				System.out.println("The available commands are:" + "\nhelp - Displays available commands"
						+ "\nlogout - Log out of account"  
						+ "\nadd - Adds a property, unit, rentor or unit rentor" 
						+ "\nremove - Removes a property, unit, renotr, or unit rentor"
						+ "\nsearch - Searches for accounts and property listings");
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
			default:
				System.out.println(
						"\"" + command + "\" is not a valid command! Please type " + "\"help\" for a list of commands");
			}
		}

		return system;
	}

	public static PSystem createAccount(PSystem system) {
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

	public static int login(PSystem system) {
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

	public static PSystem logout(PSystem system) {
		// Logout of Account
		system.logout();
		System.out.println("You are logged out.");

		// Return to the main menu
		return system;
	}

	public static void search(PSystem system) {
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
					System.out.println(user.toString(null));
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
					System.out.println(manager.toString(null));
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
					System.out.println(property.toString());
				}
			}
		} else {
			System.out.println("ERROR: Invalid search type!");
		}
	}
	
	private static PSystem add(PSystem system) {
		Scanner input = new Scanner(System.in);
		int choice;
		//Inputting information
		System.out.print("What would you like to add? (Select a number)." + 
						"\n1: Property" + 
						"\n2: Unit"	+ 
						"\n3: Renter" + 
						"\n4: Unit Renter" + 
						"\n>");
		choice = input.nextInt();
		if(choice == 1)	{
			system = addProperty(system);
		}
		else if(choice == 2) {
			system = addUnit(system);
		}
		else if(choice == 3) {
			system = addRenter(system);
		}	
		else if(choice == 4) {
			system = addUnitRenter(system);
		}
		else {
			System.out.println("ERROR: Invalid add type");
		}
		return system;
	}
	
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

	private static PSystem addUnit(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.print("Please enter property name." + "\n>");
		String propertyName = input.nextLine();
		System.out.print("Please enter unit." + "\n>");
		String addressModifier = input.nextLine();
		System.out.print("Please enter unit capacity." + "\n>");
		int capacity = input.nextInt();

		// Adds Unit
		system.addUnit(propertyName, addressModifier, capacity);
		return system;
	}

	private static PSystem addRenter(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.println("Please enter property name." + "\n>");
		String propertyName = input.nextLine();
		System.out.println("Please enter renter's username." + "\n>");
		String renterName = input.nextLine();

		// Adds Renter
		system.addRenter(propertyName, renterName);
		return system;
	}

	private static PSystem addUnitRenter(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		//System.out.print("Please enter property name." + "\n>");
		//String propertyName = input.nextLine();
		//System.out.print("Please enter unit." + "\n>");
		//String addressModifier = input.nextLine();
		//System.out.print("Please enter renter's username." + "\n>");
		//String renterName = input.nextLine();

		// Adds Renter
		//system.addUnitRenter(propertyName, addressModifier, renterName);
		System.out.println("Can't add unit rentor yet.");
		return system;
	}

	private static PSystem remove(PSystem system) {
		Scanner input = new Scanner(System.in);
		int choice;
		//Inputting information
		System.out.print("What would you like to remove? (Select a number)." + 
						"\n1: Property" + 
						"\n2: Unit"	+ 
						"\n3: Renter" + 
						"\n4: Unit Renter" + 
						"\n>");
		choice = input.nextInt();
		if(choice == 1)	{
			system = removeProperty(system);
		}
		else if(choice == 2) {
			system = removeUnit(system);
		}
		else if(choice == 3) {
			system = removeRenter(system);
		}	
		else if(choice == 4) {
			system = removeUnitRenter(system);
		}
		else {
			System.out.println("ERROR: Invalid remove type");
		}
		return system;
	}
	
	private static PSystem removeProperty(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.print("Enter the property name" + "\n>");
		String propertyName = input.nextLine();

		// Removes property listing
		system.removeProperty(propertyName);
		return system;
	}

	private static PSystem removeUnit(PSystem system) {
		Scanner input = new Scanner(System.in);
		
		// Inputting information
		System.out.println("Can't remove unit yet.");
		return system;
	}

	private static PSystem removeRenter(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.println("Can't remove rentor yet.");
		return system;
	}

	private static PSystem removeUnitRenter(PSystem system) {
		Scanner input = new Scanner(System.in);

		// Inputting information
		System.out.println("Can't remove unit rentor yet.");
		return system;
	}
}