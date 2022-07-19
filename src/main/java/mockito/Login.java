package mockito;

import sun.rmi.runtime.Log;

/**
 * Created by Jorge Ortiz for Academia Microservicios - UnitTest on 19/07/2022
 */
public class Login {

    public WebService webService;
    public boolean isLogin;

    public Login(WebService webService) {
        this.webService = webService;
        isLogin = false;
    }

    public void doLogin(){
        webService.login("Alberto", "1234", new Callback() {
            @Override
            public void onSuccess(String response) {
                System.out.println(response);
                isLogin = true;
            }

            @Override
            public void onFail(String error) {
                System.out.println(error);
                isLogin = false;
            }
        });
    }

}
