package mockito;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 19/07/2022
 */
public interface Callback {

    void onSuccess(String response);
    void onFail(String error);

}
