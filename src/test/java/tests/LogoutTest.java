package tests;

import dataProviders.RegisterAndLoginCVSDataProviders;
import objectModels.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import pageObjects.RegisterPage;

public class LogoutTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    RegisterPage registrationPage;
    LogoutPage logoutPage;


    @Test(dataProvider = "registerAndLoginData", dataProviderClass = RegisterAndLoginCVSDataProviders.class, groups = {"smoke"})
    public void validLogoutTest(RegisterModel newUser) {
        try {
            registrationPage = new RegisterPage(driver);
            homePage = new HomePage(driver);
            registerUser(newUser);
            registrationPage.clickContinue();
            loginPage = new LoginPage(driver);
            loginWithRegisteredUser(newUser);
            logoutPage = new LogoutPage(driver);
            logoutPage.waitForPageToLoad();
            logoutPage.clickLogoutButton();
            Assert.assertTrue(homePage.isLoginLinkDisplayed(), "Login link is not displayed after logout");
            Assert.assertTrue(homePage.isRegistrationLinkDisplayed(), "Register link is not displayed after logout");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }
}



