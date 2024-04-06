package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LogoutPage extends BasePage {
    @FindBy(xpath = "//a[@class='ico-logout']")
    private WebElement logoutButton;

    public LogoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitForPageToLoad() {
        logoutButton = wait.until(ExpectedConditions.
                visibilityOf(logoutButton));
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    }

