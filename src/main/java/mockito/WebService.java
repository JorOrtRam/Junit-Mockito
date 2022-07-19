package mockito;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 19/07/2022
 */
public class WebService {

    public void login(String user, String password, Callback callback){
        if(checkLogin(user, password)){
            callback.onSuccess("Usuario correcto");
        } else {
            callback.onFail("ERROR");
        }
    }

    public boolean checkLogin(String user, String password) {
        return user.equals("Alberto") && password.equals("1234");
    }

}
