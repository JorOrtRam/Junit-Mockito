package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 18/07/2022
 */
class AddTCreateMock2est {

    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void add() {
        add.add(3,2);
        Mockito.verify(validNumber).check(3);
    }
}