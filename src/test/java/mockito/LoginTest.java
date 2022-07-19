package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 19/07/2022
 */
class LoginTest {

    @InjectMocks
    private Login login;

    @Mock
    private WebService webService;

    @Captor
    private ArgumentCaptor<Callback> callbackArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doLoginTest() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String user = (String) invocationOnMock.getArguments()[0];
                assertEquals("Alberto", user);
                String password = (String) invocationOnMock.getArguments()[1];
                assertEquals("1234", password);
                Callback callback = (Callback) invocationOnMock.getArguments()[2];
                if (user.equals("Alberto")) {
                    callback.onSuccess("OK");
                } else {
                    callback.onFail("ERROR");
                }
                return null;
            }
        }).when(webService).login(anyString(), anyString(), any(Callback.class));

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
        assertTrue(login.isLogin);
    }

    @Test
    void doLoginCaptorTest() {
        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), callbackArgumentCaptor.capture());
        assertFalse(login.isLogin);

        Callback callback = callbackArgumentCaptor.getValue();
        callback.onSuccess("OK");
        assertTrue(login.isLogin);
    }
}
