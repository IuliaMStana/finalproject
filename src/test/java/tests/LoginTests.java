package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import database.Database;
import objectModels.LoginModel;
import objectModels.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class LoginTests extends BaseTest {
    RegisterModel registerModel;
    LoginPage loginPage;
    HomePage homePage;
    RegistrationPage registrationPage;

    @DataProvider(name = "positiveLoginData")
    public Iterator<Object[]> csvPositiveCollection() throws IOException, CsvException {
        Collection<Object[]> dp = new ArrayList<>();
        File file = new File("src//test//resources//Data//positiveLoginData.csv");
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
            RegisterModel positiveLm = new RegisterModel(line[genderPoz], line[firstNamePoz],
                    line[lastNamePoz], line[datePoz], line[emailPoz], line[companyPoz], isSelected, line[passwordPoz],
                    line[confirmPasswordPoz]);
            dp.add(new Object[]{positiveLm});
        }
        return dp.iterator();
    }

    @DataProvider(name = "errorLoginData")
    public Iterator<Object[]> csvErrorCollection() throws IOException, CsvException {
        Collection<Object[]> dp = new ArrayList<>();
        File file = new File("src//test//resources//Data//errorLoginData.csv");
        Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
        //csv reader
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        //we have header on csv, in this case we will drop first line
        for (int i = 1; i < csvData.size(); i++) {
            String[] line = csvData.get(i);

            // Read data for RegisterModel
            String gender = line[0];
            String firstName = line[1];
            String lastName = line[2];
            String date = line[3];
            String email = line[4];
            String company = line[5];
            boolean isSelected = Boolean.parseBoolean(line[6]);
            String password = line[7];
            String confirmPassword = line[8];
            String firstNameError = "";
            String lastNameError = "";
            String emailError = "";
            String passwordError = "";
            String confirmPasswordError = "";

            RegisterModel registerModel = new RegisterModel(gender, firstName, lastName, date, email, company,
                    isSelected, password, confirmPassword, firstNameError, lastNameError, emailError, passwordError,
                    confirmPasswordError);

            // Read data for LoginModel
            String loginEmail = line[4];
            String loginPassword = line[7];
            String errorWrongPassword = line[9];
            String errorInvalidAcc = line[10];

            LoginModel loginError = new LoginModel(loginEmail, loginPassword, null, null,
                    errorWrongPassword, errorInvalidAcc);

            // Add both models to the data provider
            dp.add(new Object[]{registerModel, loginError});
        }
        return dp.iterator();
    }

//    @DataProvider(name = "sqlDP")
//    public Iterator<Object[]> sqlDpCollection() throws Exception {
//        Collection<Object[]> dp = new ArrayList<>();
//        // define DB connection details -> to do -> we need to move this info inside config files in future
//        String dbHostname = "localhost";
//        String dbUser = "root";
//        String dbPassword = "";
//        String dbSchema = "finalproject";
//        String dbPort = "3306";
//        // db connection
//        Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort + "/" +
//                dbSchema, dbUser, dbPassword);
//
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM loginerrorsusername;");
//        while (resultSet.next()) {
//            LoginModel lm = new LoginModel(resultSet.getString("email"),
//                    resultSet.getString("password"), resultSet.getString("emptyEmailError"),
//                    resultSet.getString("wrongEmailError"));
//
//            dp.add(new Object[]{lm});
//        }
//        return dp.iterator();
//    }
//@DataProvider(name = "sqlDP")
//public Iterator<Object[]> sqlDpCollection() throws Exception {
//    Database database = new Database();
//    return database.sqlDpCollection();
//}



    @Test(dataProvider = "positiveLoginData", groups = {"smoke"})
    public void validLoginTest(RegisterModel positiveLm) {
        try {
            driver.get(baseUrl);
            homePage = new HomePage(driver);
            registrationPage = new RegistrationPage(driver);
            homePage.waitForPageToLoad();
            homePage.goToRegister();
            registrationPage.waitForPageToLoad();
            register(positiveLm);
            registrationPage.clickContinue();
            loginPage = new LoginPage(driver);
            homePage.goToLogin();
            loginPage.waitForPageToLoad();
            loginWithRegisteredUser(positiveLm);
            Assert.assertTrue(homePage.isLogoutLinkDisplayed(), "Logout link is not displayed after login");
            Assert.assertTrue(homePage.isMyAccountLinkDisplayed(), "My account link is not displayed after login");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    @Test(dataProvider ="sqlDP", dataProviderClass = Database.class,groups = {"regression"})
    public void validateErrorsUsernameTest(LoginModel lm) {
        try {
            driver.get(baseUrl);
            homePage = new HomePage(driver);
            loginPage = new LoginPage(driver);
            homePage.waitForPageToLoad();
            homePage.goToLogin();
            loginPage.login(lm.getAccount().getEmail(), lm.getAccount().getPassword());
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

    @Test(dataProvider = "errorLoginData",groups = {"regression"} )
    public void validateErrorsPasswordTest(RegisterModel registerModel, LoginModel loginModel) {
        try {
            driver.get(baseUrl);
            homePage = new HomePage(driver);
            homePage.waitForPageToLoad();
            homePage.goToRegister();
            registrationPage = new RegistrationPage(driver);
            registrationPage.waitForPageToLoad();
            register(registerModel);
            registrationPage.clickContinue();
            homePage.waitForPageToLoad();
            loginPage = new LoginPage(driver);
            homePage.goToLogin();
            loginPage.waitForPageToLoad();
            String email = loginModel.getAccount().getEmail();
            String password = loginModel.getAccount().getPassword();
            String wrongPassword = password + "a";
            String wrongEmail = email + "a";
            loginPage.login(email, wrongPassword);
            Assert.assertEquals(loginPage.getErrorTextForUnsuccessfulLogin(), loginModel.getErrorWrongPassword());
            loginPage.login(wrongEmail, password);
            Assert.assertEquals(loginPage.getErrorTextForUnsuccessfulLogin(), loginModel.getErrorInvalidAcc());
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    // Method to login with a registered user
     protected void loginWithRegisteredUser(RegisterModel registerModel) {
         String email = registerModel.getAccount().getEmail();
         String password = registerModel.getAccount().getPassword();

         // Create a LoginModel using the email and password
         LoginModel loginModel = new LoginModel(email, password);
         loginPage.login(loginModel.getAccount().getEmail(), loginModel.getAccount().getPassword());
         }
    }




















