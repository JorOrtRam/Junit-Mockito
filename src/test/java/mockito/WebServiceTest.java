package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 19/07/2022
 */
class WebServiceTest {

    private WebService webService;
    @Mock
    private Callback callback;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webService = new WebService();
    }

    @AfterEach
    void tearDown() {
        webService = null;
    }

    @Test
    void checkLoginTest() {
        assertTrue(webService.checkLogin("Alberto", "1234"));
    }

    @Test
    void checkLoginErrorTest() {
        assertFalse(webService.checkLogin("Pepe", "1234"));
    }

    @Test
    void loginTest() {
        webService.login("Alberto", "1234", callback);
        verify(callback).onSuccess("Usuario correcto");
    }

    @Test
    void loginErrorTest() {
        webService.login("Pepe", "1234", callback);
        verify(callback).onFail("ERROR");
    }
}