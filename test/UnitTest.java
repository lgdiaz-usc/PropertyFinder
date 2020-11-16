package PropertyFinder.test;

import PropertyFinder.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    private Unit unit;
    @BeforeEach
    void setUp() {
        unit = new Unit("#4a", 4);
    }
    
    @Test
    void testAverageRatingOfZero() {
        int average = unit.getAverageRating();
        Assertions.assertTrue(average == 0);
    }

    @Test
    void testAverageRatingOfPositiveNumbers() {
        unit.addReview(10, "is good", "very good", "manguy");
        unit.addReview(2, "not so good", "is bad", "guyman");
        unit.addReview(3, "slightly ok", "not good", "ganmuy");
        int average = unit.getAverageRating();
        Assertions.assertTrue(average == 5);
    }
    
    @Test
    void testAverageRatingOfNegativeNumbers() {
    	unit.addReview(-6, "is good", "very good", "manguy");
        unit.addReview(-7, "not so good", "is bad", "guyman");
        unit.addReview(-8, "slightly ok", "not good", "ganmuy");
        int average = unit.getAverageRating();
        Assertions.assertTrue(average == -7);
    }

    @Test
    void isAvailable() {
        boolean hasSpace = unit.isAvailable();
        assertTrue(hasSpace);
    }

    @Test
    void isNotAvailable() {
        unit.addUnitRenter("renter 1");
        unit.addUnitRenter("renter 2");
        unit.addUnitRenter("renter 3");
        unit.addUnitRenter("renter 4");
        boolean hasSpace = unit.isAvailable();
        assertFalse(hasSpace);
    }

    @Test
    void testCapacity(){
        unit.addUnitRenter("renter 1");
        unit.addUnitRenter("renter 2");
        unit.addUnitRenter("renter 3");
        unit.addUnitRenter("renter 4");
        unit.addUnitRenter("renter 5");
        int renterSize = unit.renters.size();
        assertFalse(renterSize == 5);
    }
}
