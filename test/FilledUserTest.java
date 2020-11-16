package PropertyFinder.test;

import PropertyFinder.User;
import org.junit.jupiter.api.*;

class FilledUserTest
{
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Jboy", "passwprd", "Jimbob Smith", "1/1/1",
                "123 house st.", "jimbob@a.a", "123-456-7890", "N123456789");
    }

    @Test
    void authenticateTrue() {
        boolean usernameCorrect = user.authenticate("Jboy");
        Assertions.assertTrue(usernameCorrect);
    }

    @Test
    void authenticateFalse() {
        boolean usernameCorrect = user.authenticate("not Jboy");
        Assertions.assertFalse(usernameCorrect);
    }
    
    @Test
    void testAverageRatingOfZero(){
        int average = user.getAverageRating();
        Assertions.assertTrue(average == 0);
    }

    @Test
    void testAverageRatingOfPositiveNumbers(){
        user.addReview(10, "is good", "very good", "manguy");
        user.addReview(2, "not so good", "is bad", "guyman");
        user.addReview(3, "slightly ok", "not good", "ganmuy");
        int average = user.getAverageRating();
        Assertions.assertTrue(average == 5);
    }
    
    @Test
    void testAverageRatingOfNegativeNumbers() {
    	user.addReview(-6, "really ok", "just ok", "jill");
        user.addReview(-7, "terrible", "is bad", "jake");
        user.addReview(-8, "alright", "alright", "james");
		int average = user.getAverageRating();
		Assertions.assertTrue(average == -7);
    }
}
