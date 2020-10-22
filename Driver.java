package PropertyFinder;

import java.util.ArrayList;

/**
 * Allows the user to interact with the system
 */
public class Driver {
    public static void main(String[] args){
        PSystem system = DataReader.read("test.json");

        //PSystem system = DataReader.read("test.json");
        System.out.println("Hooray! It compiles!");

        //Saves data upon completion
        DataWriter.write(system);
    }
}
