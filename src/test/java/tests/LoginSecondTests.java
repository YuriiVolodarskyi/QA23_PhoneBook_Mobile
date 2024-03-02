package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;

public class LoginSecondTests extends AppiumConfig {

    @Test
    public void loginSuccess() {
        new AuthentificationScreen(driver)
                .fillEmail("alimych65@gmail.com")
                .fillPassword("Yv030665@")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginSuccessModel() {

        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("alimych65@gmail.com")
                        .password("Yv030665@")
                        .build())
                .submitLogin()
                .isAccountOpened()
                .logout();
    }
    @Test
    public void loginWrongEmail() {
        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("alimych65gmail.com")
                        .password("Yv030665@")
                        .build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }

}
