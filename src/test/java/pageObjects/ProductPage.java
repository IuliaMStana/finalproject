package pageObjects;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//button[@id='add-to-wishlist-button-18']")
    private WebElement addToWishlistButton;
    @FindBy(xpath = "//p[@class='content']")
    private WebElement addToWishlistText;
    @FindBy(xpath = "//span[@class='close' and @title='Close']")
    private WebElement closeButton;
    @FindBy(xpath = "//button[@id='add-to-cart-button-18']")
    private WebElement addToShoppingCartButton;
    @FindBy(xpath = "//p[@class='content']")
    private WebElement addToShoppingCartText;

    @Getter
    @FindBy(xpath = "//div[@class='product-name']/h1")
    private WebElement productDetailsTitle;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitForPageToLoad() {
        addToWishlistButton = wait.until(ExpectedConditions.
                visibilityOf(addToWishlistButton));
        addToShoppingCartButton = wait.until(ExpectedConditions.
                visibilityOf(addToShoppingCartButton));
    }

    public void addToWishlistButton() {
        addToWishlistButton.click();
    }

    public String getTextAddToWishlist() {
        return addToWishlistText.getText();
    }

    public void addToShoppingCartButton() {
        addToShoppingCartButton.click();
    }

    public String getAddToShoppingCartText() {
        return addToShoppingCartText.getText();
    }

    public void closeBanner() {
        closeButton.click();
    }
}

