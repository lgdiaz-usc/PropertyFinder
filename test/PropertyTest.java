package PropertyFinder.test;

import PropertyFinder.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    private Property property;

    @BeforeEach
    void setUp(){
        property = new Property("bill", "bill\'s house", "its bill\'s house", "123 street st.", 2, 5);
    }

   @Test
    void testAverageRatingOfZero() {
        int average = property.getAverageRating();
        Assertions.assertTrue(average == 0);
    }

    @Test
    void testAverageRatingOfPositiveNumbers() {
        property.addReview(10, "is good", "very good", "manguy");
        property.addReview(2, "not so good", "is bad", "guyman");
        property.addReview(3, "slightly ok", "not good", "ganmuy");
        int average = property.getAverageRating();
        Assertions.assertTrue(average == 5);
    }
    
    @Test
	void testAverageRatingOfNegativeNumbers() {
		property.addReview(-6, "Cool place ot live.", "Great. Great", "steverogers");
		property.addReview(-7, "Cool place ot live.", "Great. Great", "tonystark");
		property.addReview(-8, "Cool place ot live.", "Great. Great", "thorodinson");
		int average = property.getAverageRating();
		Assertions.assertTrue(average == -7);
	}

    @Test
    void isAvailable() {
        boolean hasSpace = property.isAvailable();
        assertTrue(hasSpace);
    }

    @Test
    void isNotAvailable() {
        property.addRenter("renter 1", "bill");
        property.addRenter("renter 2", "bill");
        boolean hasSpace = property.isAvailable();
        assertFalse(hasSpace);
    }

    @Test
    void isManager() {
        boolean manager = property.isManager("bill");
        assertTrue(manager);
    }

    @Test
    void isNotManager() {
        boolean manager = property.isManager("not bill");
        assertFalse(manager);
    }

    @Test
    void isRenter() {
        property.addRenter("bingus", "bill");
        boolean renter = property.isRenter("bingus");
        assertTrue(renter);
    }

    @Test
    void isNotRenter() {
        property.addRenter("bingus", "bill");
        boolean renter = property.isRenter("not bingus");
        assertFalse(renter);
    }
}
