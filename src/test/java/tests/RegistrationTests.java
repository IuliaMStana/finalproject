package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import objectModels.RegisterModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class RegistrationTests extends BaseTest {
    RegistrationPage registrationPage;
    HomePage homePage;

    @DataProvider(name = "registrationData")
    public Iterator<Object[]> csvPositiveRegistrationCollection() throws IOException, CsvException {
        Collection<Object[]> dp = new ArrayList<>();
        File file = new File("src//test//resources//Data//registrationData.cvs");
        Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
        //csv reader
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        //we create legend
        int genderPoz = 0, firstNamePoz = 1, lastNamePoz = 2, datePoz = 3, emailPoz = 4, companyPoz = 5,
                isSelectedPoz = 6, passwordPoz = 7, confirmPasswordPoz = 8,
                firstNameErrorPoz = 9, lastNameErrorPoz = 10, emailErrorPoz = 11, passwordErrorPoz = 12, confirmPasswordErrorPoz = 13;
        //we have header on csv, in this case we will drop first line
        for (int i = 1; i < csvData.size(); i++) {
            String[] line = csvData.get(i);
            boolean isSelected = Boolean.parseBoolean(line[isSelectedPoz]);
            RegisterModel newUser = new RegisterModel(line[genderPoz], line[firstNamePoz],
                    line[lastNamePoz], line[datePoz], line[emailPoz], line[companyPoz], isSelected, line[passwordPoz],
                    line[confirmPasswordPoz], line[firstNameErrorPoz], line[lastNameErrorPoz], line[emailErrorPoz],
                    line[passwordErrorPoz], line[confirmPasswordErrorPoz]);
            dp.add(new Object[]{newUser});
        }
        return dp.iterator();
    }

    @DataProvider(name = "errorsRegistrationData")
    public Iterator<Object[]> csvErrorsRegistrationCollection() throws IOException, CsvException {
        Collection<Object[]> dp = new ArrayList<>();
        File file = new File("src//test//resources//Data/errorsRegistrationData.csv");
        Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
        //csv reader
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        //we create legend
        int genderPoz = 0, firstNamePoz = 1, lastNamePoz = 2, datePoz = 3, emailPoz = 4, companyPoz = 5,
                isSelectedPoz = 6, passwordPoz = 7, confirmPasswordPoz = 8, firstNameErrorPoz = 9,
                lastNameErrorPoz = 10, emailErrorPoz = 11, passwordErrorPoz = 12, confirmPasswordErrorPoz = 13;
        //we have header on csv, in this case we will drop first line
        for (int i = 1; i < csvData.size(); i++) {
            String[] line = csvData.get(i);
            boolean isSelected = Boolean.parseBoolean(line[isSelectedPoz]);
            RegisterModel errorsRegistration = new RegisterModel(line[genderPoz], line[firstNamePoz],
                    line[lastNamePoz], line[datePoz], line[emailPoz], line[companyPoz], isSelected, line[passwordPoz],
                    line[confirmPasswordPoz], line[firstNameErrorPoz], line[lastNameErrorPoz], line[emailErrorPoz],
                    line[passwordErrorPoz], line[confirmPasswordErrorPoz]);
            dp.add(new Object[]{errorsRegistration});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "registrationData", groups = {"smoke"})
    public void validRegistrationTest(RegisterModel newUser) {
        try {
            driver.get(baseUrl);
            homePage = new HomePage(driver);
            registrationPage = new RegistrationPage(driver);
            homePage.waitForPageToLoad();
            homePage.goToRegister();
            registrationPage.waitForPageToLoad();
            register(newUser);
            registrationPage.verifyRegistration();
            registrationPage.clickContinue();
            homePage.waitForPageToLoad();
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    @Test(dataProvider = "errorsRegistrationData", groups = {"regression"})
    public void registrationErrorsTest(RegisterModel errorsRegistration) {
        try {
            driver.get(baseUrl);
            homePage = new HomePage(driver);
            registrationPage = new RegistrationPage(driver);
            homePage.waitForPageToLoad();
            homePage.goToRegister();
            registrationPage.waitForPageToLoad();
            register(errorsRegistration);
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







