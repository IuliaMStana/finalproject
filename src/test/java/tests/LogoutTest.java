package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import objectModels.LoginModel;
import objectModels.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import testNG.LogoutDataModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import pageObjects.RegistrationPage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class LogoutTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    RegistrationPage registrationPage;
    LogoutPage logoutPage;

    @DataProvider(name = "logout")
    public Iterator<Object[]> csvLogoutCollection() throws IOException, CsvException {
        Collection<Object[]> dp = new ArrayList<>();
        File file = new File("src//test//resources//Data//logoutData.csv");
        Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
        //csv reader
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        //we create legend
        int genderPoz = 0, firstNamePoz = 1, lastNamePoz = 2, datePoz = 3, emailPoz = 4, companyPoz = 5,
                isSelectedPoz = 6, passwordPoz = 7, confirmPasswordPoz = 8;
        //we have header on csv, in this case we will drop first line
        for (int i = 1; i < csvData.size(); i++) {
            String[] line = csvData.get(i);
            boolean isSelected = Boolean.parseBoolean(line[isSelectedPoz]);
            RegisterModel user = new RegisterModel(line[genderPoz], line[firstNamePoz], line[lastNamePoz],
                    line[datePoz], line[emailPoz], line[companyPoz], isSelected, line[passwordPoz],
                    line[confirmPasswordPoz]);
            // Read data for LoginModel
            String loginEmail = line[4];
            String loginPassword = line[7];

            LoginModel registeredUser = new LoginModel(loginEmail, loginPassword);
            dp.add(new Object[]{user, registeredUser});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "logout", groups = {"smoke"})
    public void validLogoutTest(RegisterModel user, LoginModel registeredUser) {
        try {
            driver.get(baseUrl);
            homePage = new HomePage(driver);
            registrationPage = new RegistrationPage(driver);
            homePage.waitForPageToLoad();
            homePage.goToRegister();
            registrationPage.waitForPageToLoad();
            register(user);
            registrationPage.clickContinue();
            homePage.waitForPageToLoad();
            loginPage = new LoginPage(driver);
            homePage.goToLogin();
            loginPage.waitForPageToLoad();
            loginPage.login(registeredUser.getAccount().getEmail(), registeredUser.getAccount().getPassword());
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



