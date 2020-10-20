package PropertyFinder;

/**
 * Allows the user to interact with the system
 */
public class Driver {
    public static void main(String[] args){
        PSystem system = new PSystem();
        Account account = new Account("", "", "", "", "", "",
                "");
        User user = new User("", "", "", "", "", "",
                "", "");
        PropertyManager manager = new PropertyManager("", "", "", "", "", "",
                "");
        Property property = new Property("", "", "", "",
        0, 0);
        Unit unit = new Unit("", 0);
        Review review = new Review(0, "", "");
        Message message = new Message("", "");
        ReviewType revT = ReviewType.UNIT;
        EditType editT = EditType.RENT;

        System.out.println("Hooray! It compiles!");
    }
}
