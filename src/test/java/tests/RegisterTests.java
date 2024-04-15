package tests;

import dataProviders.RegisterAndLoginCVSDataProviders;
import dataProviders.RegisterCVSDataProviders;
import objectModels.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class RegisterTests extends BaseTest {
    RegisterPage registrationPage;
    HomePage homePage;

    @Test(dataProvider = "registerAndLoginData", dataProviderClass = RegisterAndLoginCVSDataProviders.class, groups = {"smoke"})
    public void validRegistrationTest(RegisterModel newUser) {
        try {
            registrationPage = new RegisterPage(driver);
            registerUser(newUser);
            registrationPage.verifyRegistration();
            registrationPage.clickContinue();
            homePage = new HomePage(driver);
            homePage.waitForPageToLoad();
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    @Test(dataProvider = "errorsRegistrationData", dataProviderClass = RegisterCVSDataProviders.class, groups = {"regression"})
    public void registrationErrorsTest(RegisterModel errorsRegistration) {
        try {
            registrationPage = new RegisterPage(driver);
            registerUser(errorsRegistration);
            if (errorsRegistration.getAccount().getFirstName().isEmpty()) {
                Assert.assertEquals(registrationPage.getErrorText("FirstName"), errorsRegistration.getFirstNameError());
            }
            if (errorsRegistration.getAccount().getLastName().isEmpty()) {
                Assert.assertEquals(registrationPage.getErrorText("LastName"), errorsRegistration.getLastNameError());
            }
            if (!errorsRegistration.getAccount().getEmail().contains("@")) {
                Assert.assertEquals(registrationPage.getErrorText("Email"), errorsRegistration.getEmailError());
            }
            if (errorsRegistration.getAccount().getPassword().length() < 6) {
                Assert.assertEquals(registrationPage.getErrorText("Password"), errorsRegistration.getPasswordError());
            }
            if (errorsRegistration.getAccount().getConfirmPassword().isEmpty() || (errorsRegistration.getAccount().getPassword().length() > 6 && !errorsRegistration.getAccount().getConfirmPassword().equals(errorsRegistration.getAccount().getPassword()))) {
                Assert.assertEquals(registrationPage.getErrorText("ConfirmPassword"), errorsRegistration.getConfirmPasswordError());
            }
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }
}






