package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @AfterMethod
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }

    @Test
    public void loginSuccess() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthentificationScreen(driver)
                .fillEmail("alimych65@gmail.com")
                .fillPassword("Yv030665@")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("alimych65@gmail.com")
                        .password("Yv030665@")
                        .build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);

    }

    @Test
    public void loginSuccessModel2() {
        Assert.assertTrue(
                new AuthentificationScreen(driver)
                        .fillLoginRegistrationForm(Auth.builder()
                                .email("alimych65@gmail.com")
                                .password("Yv030665@")
                                .build())
                        .submitLogin()
                        .isActivityTitleDisplayed("Contact list"));
    }

}
