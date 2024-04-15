package tests;

import objectModels.LoginModel;
import objectModels.RegisterModel;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import pageObjects.SearchPage;
import utils.BrowserUtils;
import utils.ConstantUtils;
import utils.GenericUtils;
import java.io.File;
import java.io.IOException;


public class BaseTest {
    protected WebDriver driver;
    private int screenshotIndex = 0;
    String  baseUrl, browser;
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registrationPage;
    String config = ConstantUtils.CONFIG_FILE;

    @BeforeMethod(groups = {"smoke", "regression"})
    public void setUp() {
        baseUrl = GenericUtils.getBaseUrl(config,
                "protocol", "hostname");
        System.out.println("Use this baseurl:" + baseUrl);
        browser = GenericUtils.getBrowser(config, "browser");
        getBrowser(browser);
        // Maximize the browser window
        driver.manage().window().maximize();
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
    protected void registerUser(RegisterModel registerModel){
        driver.get(baseUrl);
        homePage = new HomePage(driver);
        homePage.waitForPageToLoad();
        homePage.goToRegister();
        registrationPage = new RegisterPage(driver);
        registrationPage.waitForPageToLoad();
        registrationPage.register(registerModel);
    }
    protected void loginUser(LoginModel loginModel) {
        driver.get(baseUrl);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.goToLogin();
        loginPage.waitForPageToLoad();
        loginPage.login(loginModel);
    }
    protected void loginWithRegisteredUser(RegisterModel registerModel) {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.goToLogin();
        loginPage.waitForPageToLoad();
        loginPage.loginWithRegisteredUser(registerModel);
    }
    protected void navigateToSearchPage() {
        driver.get(baseUrl);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitForPageToLoad();
    }
}




