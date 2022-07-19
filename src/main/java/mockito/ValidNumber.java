package mockito;

import org.junit.jupiter.api.Test;
import sun.net.TelnetInputStream;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 18/07/2022
 */
public class ValidNumber {

    public ValidNumber() {
    }

    public boolean check(Object o) {
        if (o instanceof Integer) {
            return (Integer) o < 10 && (Integer) o >= 0;
        } else {
            return false;
        }
    }

    public boolean checkZero(Object o) {
        if (o instanceof Integer) {
            if ((Integer) o == 0) {
                throw new ArithmeticException("No podemos aceptar cero");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public int doubleToInt(Object o) {
        if (o instanceof Double) {
            return ((Double) o).intValue();
        } else {
            return 0;
        }
    }

}
