package tests;

import objectModels.RegisterModel;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.RegistrationPage;
import utils.BrowserUtils;
import utils.ConstantUtils;
import utils.GenericUtils;
import java.io.File;
import java.io.IOException;


public class BaseTest {
    protected WebDriver driver;
    private int screenshotIndex = 0;
    String config = ConstantUtils.CONFIG_FILE;
    String  baseUrl,dbHostname, dbUser,  dbPassword,  dbSchema, dbPort, browser;


    @BeforeMethod(groups = {"smoke", "regression"})
    public void setUp() {
        baseUrl = GenericUtils.getBaseUrl(config,
                "protocol", "hostname");
        System.out.println("Use this baseurl:" + baseUrl);
        dbHostname = GenericUtils.getDbHostname(config, "dbHostname");
        dbUser = GenericUtils.getDbUser(config, "dbUser");
        dbPassword = GenericUtils.getDbPassword(config, "dbPassword");
        dbSchema = GenericUtils.getDbSchema(config, "dbSchema");
        dbPort = GenericUtils.getDbPort(config, "dbPort");
        //getBrowser("chrome");
        browser = GenericUtils.getBrowser(config, "browser");
        getBrowser(browser);
    }

    public void getBrowser(String browserName) {
        driver = BrowserUtils.setUpBrowser(browserName);
    }

        public void closeBrowserAtEnd () {
            if (driver != null) {
                System.out.println("Browser was closed");
                driver.quit();
            }
        }
    protected void register(RegisterModel registerModel) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.register(registerModel.getAccount().getGender(), registerModel.getAccount().getFirstName(),
                registerModel.getAccount().getLastName(), registerModel.getAccount().getDate(),
                registerModel.getAccount().getEmail(), registerModel.getAccount().getCompany(),
                registerModel.getAccount().isSelected(), registerModel.getAccount().getPassword(),
                registerModel.getAccount().getConfirmPassword());
    }
        @AfterMethod(groups = {"smoke", "regression"})
        public void cleanUpAfterTest () {
            closeBrowserAtEnd();
        }

    protected void takeScreenshot() {
        File screenshotFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        final String fileName = ConstantUtils.SCREENSHOT_FILE +
                screenshotIndex + ".png";
        File finalFile =
                new File(fileName);
        try {
            FileUtils.copyFile(screenshotFile, finalFile);
            Reporter.log("<img src='screenshot" +
                    screenshotIndex + ".png' width='400' height='400'>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        screenshotIndex++;
    }
}



