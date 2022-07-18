package junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jorge Ortiz for Academia Microservices - UnitTests on 15/07/2022
 */
class CalculatorTest {

    private Calculator calculator;
    private Calculator calculatorNull;
    private static Calculator calculatorStatic;

    @BeforeAll
    static void beforeAllTest() {
        calculatorStatic = new Calculator();
        System.out.println("@BeforeAll -> beforeAllTest()");
    }

    @AfterAll
    static void afterAllTest() {
        calculatorStatic = null;
        System.out.println("@AfterAll -> afterAllTest()");
    }

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        System.out.println("@BeforeEach -> setUp()");
    }

    @AfterEach
    public void tearDown() {
        calculator = null;
        System.out.println("@AfterEach -> tearDown()");
    }

    @Test
    void calculatorNotNullTest() {
        assertNotNull(calculatorStatic);
        assertNotNull(calculator, "Calculator debe ser not null / estar instanciado");
        System.out.println("@Test -> calculator NotNull Test()");
    }

    @Test
    void calculatorNullTest() {
        assertNull(calculatorNull);
        System.out.println("@Test -> calculator Null Test()");
    }

    @Test
    void addAssertTest() {
        //1.- SetUp
        Calculator calculatorAdd = new Calculator();
        int resultadoEsperado = 30;
        int resultadoActual;

        //2.- Action
        resultadoActual = calculatorAdd.add(10, 20);

        //3.- Assert
        assertEquals(resultadoEsperado, resultadoActual);
        System.out.println("La operación se ejecuto correctamente");
    }

    @Test
    void addTest() {
        assertEquals(30, calculator.add(10, 20));
        System.out.println("La operación se ejecuto correctamente, pero en una sola linea");
    }

    @Test
    void assertTypesTest() {
        assertTrue(1 == 1);
        assertFalse(1 == 2);

        assertNull(calculatorNull);
        assertNotNull(calculator);

        Calculator calculator1 = new Calculator();
        Calculator calculator2 = new Calculator();
        Calculator calculator3 = calculator1;

        assertSame(calculator1, calculator3);
        assertNotSame(calculator1, calculator2);

        assertEquals("Alberto", "Alberto");
        assertNotEquals("Alberto", "alberto", "ha fallado nuestro metodo String 'alberto'");

        assertEquals(1, 1.4, 0.5);
        assertNotEquals(1, 1.6, 0.5);
    }

    @Test
    void addValidInputValidExpectedTest() {
        assertEquals(11, calculator.add(7, 4));
    }

    @Test
    void subtractValidInputValidExpectedTest() {
        assertEquals(11, calculator.subtract(15, 4));
    }

    @Test
    void subtractValidInputValidNegativeResultExpectedTest() {
        assertEquals(-10, calculator.subtract(10, 20));
    }

    @Test
    void divideValidInputValidResultExpectedTest() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Disabled("Por que siempre dara fallo")
    @Test
    void divideInValidInputTest() {
        fail("Fallo detectado manualmente - No se puede dividir por cero");
        calculator.divide(10, 0);
    }

    @Test
    void divideInValidInputExpectedExceptionTest() {
        assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0), "No se puede dividir por cero");
    }

    @DisplayName("Metodo Dividir -> Funciona")
    @Test
    void divideValidInputValidResultExpectedNameTest() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    void addAssertAllTest() {
        assertAll(
                () -> assertEquals(30, calculator.add(11, 19)),
                () -> assertEquals(20, calculator.add(10, 10)),
                () -> assertEquals(2, calculator.add(1, 1))
        );
    }

    @Nested
    class AddTest {
        @Test
        void addPositiveTest() {
            assertEquals(30, calculator.add(15, 15));
        }

        @Test
        void addNegativeTest() {
            assertEquals(-30, calculator.add(-15, -15));
        }

        @Test
        void addZeroTest() {
            assertEquals(0, calculator.add(15, -15));
        }
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("addProvidedData")
    void addParameterizedTest(int a, int b, int sum) {
        assertEquals(sum, calculator.add(a, b));
    }

    private static Stream<Arguments> addProvidedData() {
        return Stream.of(
                Arguments.of(6, 2, 8),
                Arguments.of(-6, -2, -8),
                Arguments.of(6, -2, 4),
                Arguments.of(-6, 2, -4),
                Arguments.of(6, 0, 6)
        );
    }

    @Test
    void timeOut(){
        assertTimeout(Duration.ofMillis(5000),()->{
            calculator.longTaskOperation();
        });
    }
}