package tests;
import dataProviders.LoginCVSDataProviders;
import dataProviders.DBDataProviders;
import dataProviders.RegisterAndLoginCVSDataProviders;
import objectModels.LoginModel;
import objectModels.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import java.util.*;


public class LoginTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    RegisterPage registrationPage;

    @Test(dataProvider = "registerAndLoginData", dataProviderClass = RegisterAndLoginCVSDataProviders.class, groups = {"smoke"})
    public void validLoginTest(RegisterModel newUser) {
        try {
            registrationPage = new RegisterPage(driver);
            registerUser(newUser);
            registrationPage.clickContinue();
            loginPage = new LoginPage(driver);
            loginWithRegisteredUser(newUser);
            homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isLogoutLinkDisplayed(), "Logout link is not displayed after login");
            Assert.assertTrue(homePage.isMyAccountLinkDisplayed(), "My account link is not displayed after login");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }
    @Test(dataProvider = "login_errors_sql", dataProviderClass = DBDataProviders.class, groups = {"regression"})
    public void validateErrorsUsernameTest(LoginModel lm) {
        try {
            loginPage = new LoginPage(driver);
            loginUser(lm);
            if (Objects.equals(lm.getAccount().getEmail(), "")) {
                Assert.assertEquals(loginPage.getErrorText(), lm.getEmptyEmailError());
            }
            if (!lm.getWrongEmailError().contains("@") && !lm.getWrongEmailError().isEmpty()) {
                Assert.assertEquals(loginPage.getErrorText(), lm.getWrongEmailError());
            }
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    @Test(dataProvider = "errorLoginData", dataProviderClass = LoginCVSDataProviders.class, groups = {"regression"})
    public void validateErrorsPasswordTest(RegisterModel registerModel, LoginModel loginError) {
        try {
            registerUser(registerModel);
            loginPage = new LoginPage(driver);
            homePage = new HomePage(driver);
            homePage.goToLogin();
            loginPage.waitForPageToLoad();
            String email = registerModel.getAccount().getEmail();
            String password = registerModel.getAccount().getPassword();
            String wrongEmail = email + "a";
            String wrongPassword = password + "a";
            loginPage.login(email, wrongPassword);
            Assert.assertEquals(loginPage.getErrorTextForUnsuccessfulLogin(), loginError.getErrorWrongPassword());
            loginPage.login(wrongEmail, password);
            Assert.assertEquals(loginPage.getErrorTextForUnsuccessfulLogin(), loginError.getErrorInvalidAcc());
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }
}





















