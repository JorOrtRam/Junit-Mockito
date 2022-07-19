package mockito;

import jdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 18/07/2022
 */
class AddTest {

    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;
    @Mock
    private Print print;
    @Mock
    private ArgumentCaptor<Integer> captor;
    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addTest() {
        when(validNumber.check(3)).thenReturn(false);
        boolean checkNumber = validNumber.check(3);
        assertFalse(checkNumber);

        when(validNumber.check("a")).thenReturn(false);
        boolean checkNumber1 = validNumber.check("a");
        assertFalse(checkNumber1);
    }

    @Test
    void checkMockExceptionTest() {
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero"));
        Exception exception = null;
        try{
            validNumber.checkZero(0);
        }catch (ArithmeticException e){
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    void addRealMethodTest(){
        when(validNumber.check(3)).thenCallRealMethod();
        assertTrue(validNumber.check(3));
    }

    @Test
    void addDoubleToIntThenAnswer(){
        Answer<Integer> answer = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 7;
            }
        };

        when(validNumber.doubleToInt(7.7)).thenAnswer(answer);
        assertEquals(14, add.addInt(7.7));
    }

    @Test
    void patternTest(){
        //Arrange (Before each)
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        //Act
        int result = add.add(4,5);
        //Assert
        assertEquals(9,result);
    }

    @Test
    void patternBDDTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        int result = add.add(4,5);
        //Then
        assertEquals(9,result);
    }

    @Test
    void argumentMatcherTest(){
        //Given @Mock
        given(validNumber.check(anyInt())).willReturn(true);
        given(validNumber.check(anyInt())).willReturn(true);
        //When
        int result = add.add(4,5);
        //Then
        assertEquals(9,result);
    }

    @Test
    void addPrintTest(){
        //Given @Mock
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(validNumber).check(4);
        //verify(validNumber,times(2)).check(4);
        verify(validNumber,never()).check(99);
        verify(validNumber,atLeast(1)).check(4);
        verify(validNumber,atMost(3)).check(4);

        verify(print).showMessage(9);
        verify(print,never()).showError();
    }

/*    @Test
    void captorTest(){
        //Given @Mock
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(print).showMessage(captor.capture());
        assertEquals(9, captor.getValue().intValue());
    }*/

    @Test
    void spyTest()
    {
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2,spyList.size());
    }

    @Test
    void mockTest()
    {
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        given(mockList.size()).willReturn(2);
        assertEquals(2,mockList.size());
    }
}