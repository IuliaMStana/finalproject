package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='Email']")
    private WebElement email;
    @FindBy(xpath = "//input[@id='Password']")
    private WebElement password;
    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'button-1') and contains(@class, 'login-button')]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }
    @Override
    public void waitForPageToLoad() {
        email = wait.until(ExpectedConditions.
                visibilityOf(email));
        password = wait.until(ExpectedConditions.
                visibilityOf(password));
        loginButton = wait.until(ExpectedConditions.
                visibilityOf(loginButton));
    }

    public void login(String emailInput, String passwordInput) {
        System.out.println("Enter email: " + email);
        enterEmail(emailInput);
        System.out.println("Enter password: " + password);
        enterPassword(passwordInput);
        clickLoginButton();
    }

    public void enterEmail(String emailInput) {
        email.clear();
        email.sendKeys(emailInput);
    }

    public void enterPassword(String passwordInput) {
        password.clear();
        password.sendKeys(passwordInput);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@id='Email-error']")));
        return driver.findElement(By.xpath("//span[@id='Email-error']")).getText();
    }

    public String getErrorTextForUnsuccessfulLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class='message-error validation-summary-errors']")));
        return driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']")).getText().replace("\n", " ");
    }
}
