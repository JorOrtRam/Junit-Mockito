package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 18/07/2022
 */
class AddCreateMock1Test {

    private Add add;
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        validNumber = Mockito.mock(ValidNumber.class);
        add = new Add(validNumber);
    }

    @Test
    void addTest(){
         add.add(3,2);
         Mockito.verify(validNumber).check(3);
    }

}