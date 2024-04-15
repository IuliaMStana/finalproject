package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class='ico-register']")
    private WebElement registrationLink;

    @FindBy(xpath="//a[@class='ico-login']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[@class='ico-account' and text()='My account']")
    private WebElement myAccountLink;
    @FindBy(xpath = "//a[@class='ico-logout']")
    private WebElement logoutLink;
    @FindBy(xpath = "//a[@class='ico-wishlist']")
    private WebElement wishlistLink;
    @FindBy(xpath = "//a[@class='ico-cart']")
    private WebElement shoppingCartLink;
    @FindBy(xpath = "//a[text()='HTC One M8 Android L 5.0 Lollipop']")
    private WebElement productLink;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitForPageToLoad() {
        registrationLink = wait.until(ExpectedConditions.
                visibilityOf(registrationLink));
        loginLink = wait.until(ExpectedConditions.
                visibilityOf(loginLink));
        wishlistLink = wait.until(ExpectedConditions.
                visibilityOf(wishlistLink));
        productLink = wait.until(ExpectedConditions.
                visibilityOf(productLink));
        shoppingCartLink = wait.until(ExpectedConditions.
                visibilityOf(shoppingCartLink));

    }

    public void goToRegister() {
        registrationLink.click();
    }

    public void goToLogin() {
        loginLink.click();
    }

    public void goToWishList() {
        wishlistLink.click();
    }

    public void clickProductLink() {
        productLink.click();
    }

    public void goToShoppingCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCartLink).perform();
        shoppingCartLink.click();
    }

    public boolean isLogoutLinkDisplayed() {
        return logoutLink.isDisplayed();
    }

    public boolean isMyAccountLinkDisplayed() {
        return myAccountLink.isDisplayed();
    }

    public boolean isLoginLinkDisplayed() {
        return loginLink.isDisplayed();
    }
    public boolean isRegistrationLinkDisplayed() {
        return registrationLink.isDisplayed();
    }
}




