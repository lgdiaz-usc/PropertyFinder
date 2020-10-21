package PropertyFinder;

/**
 * Allows the user to interact with the system
 */
public class Driver {
    public static void main(String[] args){
        PSystem system = new PSystem();


        System.out.println("Hooray! It compiles!");

        //Saves data upon completion
        DataWriter.write(system);
    }
}
