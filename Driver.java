package PropertyFinder;

import java.util.Scanner;

/**
 * Allows the user to interact with the system
 */
public class Driver {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean done = false;
        PSystem system = null;

        //Choosing which JSON database to use
        System.out.println("Woukd you like to use the default database? (y/n)");
        char choice = input.next().charAt(0);
        if(choice == 'y' || choice == 'Y'){
            system = DataReader.read("data.json");
            if(system == null){
                system = new PSystem();
            }
        }
        else{
            boolean isValid = false;
            while(!isValid){
                System.out.println("Which database would you like to use? (or q for default)");
                String database = input.next();
                if(database.equals("default")){
                    system = DataReader.read("data.json");
                    if(system == null){
                        system = new PSystem();
                    }
                    isValid = true;
                }
                else {
                    system = DataReader.read(database);
                    if(system == null){
                        System.out.println("Error: That database does not exist!");
                    }
                    else{
                        isValid = true;
                    }
                }
            }
        }

        //UI Loop
        while(!done){
            System.out.print("Please enter a command:" + "\n>");
            String command = input.next();

            switch(command){
                case "help":
                    System.out.println("The available commands are:" +
                            "\nhelp - Displays available commands" +
                            "\nquit - quits the program");
                    break;
                case "quit":
                    done = true;
                    break;
                default:
                    System.out.println("\"" + command + "\" is not a valid command! Please type " +
                            "\"help\" for a list of commands");
            }

        }


        //Saves data upon completion
        System.out.println("Saving...");
        DataWriter.write(system);
        System.out.println("Goodbye!");
    }
}
