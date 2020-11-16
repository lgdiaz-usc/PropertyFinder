package PropertyFinder.test;

import PropertyFinder.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmptyUserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("", "", "", "", "", "", "", "");
    }

    @Test
    void authenticateTrue() {
        boolean usernameCorrect = user.authenticate("");
        Assertions.assertTrue(usernameCorrect);
    }

    @Test
    void authenticateFalse() {
        boolean usernameCorrect = user.authenticate("ekjnjsnkjds");
        Assertions.assertFalse(usernameCorrect);
    }

    @Test
    void testAverageRatingOF0(){
        int average = user.getAverageRating();
        Assertions.assertTrue(average == 0);
    }

    @Test
    void testAverageRatingOf5(){
        user.addReview(10, "is good", "very good", "manguy");
        user.addReview(2, "not so good", "is bad", "guyman");
        user.addReview(3, "slightly ok", "not good", "ganmuy");
        int average =user.getAverageRating();
        Assertions.assertTrue(average == 5);
    }
}