package junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 18/07/2022
 */
class TemperatureCalculatorTest {
    private TemperatureCalculator temperatureCalculator;

    @BeforeEach
    void setUp() {
        temperatureCalculator = new TemperatureCalculator();
        System.out.println("setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
        temperatureCalculator = null;
    }

    @Test
    void toFahrenheit() {
        assertEquals( 9.4,-temperatureCalculator.toFahrenheit(-23),0.5);
    }
}