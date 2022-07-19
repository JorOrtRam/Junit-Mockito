package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 18/07/2022
 */
class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    void setUp() {
        validNumber = new ValidNumber();
        System.out.println("setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
        validNumber = null;
    }

    @Nested
    class checkValidNumber {
        @Test
        void checkTest() {
            assertTrue(validNumber.check(5));
        }

        @Test
        void checkNegativeTest() {
            assertFalse(validNumber.check(-5));
        }

        @Test
        void checkStringTest() {
            assertFalse((validNumber.check("test")));
        }
    }

    @Nested
    class checkZeroNumber {
        @Test
        void checkZeroTest() {
            assertTrue(validNumber.checkZero(-57));
        }

        @Test
        void checkZeroStringTest() {
            assertFalse((validNumber.checkZero("test")));
        }

        @Test
        void checkZero0Test() {
            assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
        }
    }

    @Test
    void doubleToIntTest() {
        assertEquals(9, validNumber.doubleToInt(9.999));
    }

    @Test
    void doubleToIntErrorTest() {
        assertNotEquals(9, validNumber.doubleToInt("9.999"));
    }


}