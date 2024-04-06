package pageObjects;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WishlistPage extends BasePage {
    @FindBy(xpath = "//span[@class='wishlist-qty']")
    private WebElement wishlistQuantity;

    @FindBy(xpath = "//div[@class='no-data']")
    private WebElement emptyWishlistText;

    @FindBy(xpath = "//button[@class='remove-btn' and contains(@onclick, 'removefromcart')]")
    private WebElement removeFromWishlistButton;
    @Getter
    @FindBy(xpath = "//h1[contains(text(), 'Wishlist')]")
    private WebElement titlePage;

    public WishlistPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.
                visibilityOf(wishlistQuantity));
        wait.until(ExpectedConditions.textToBePresentInElement
                (wishlistQuantity, "1"));
    }

    public String getErrorTextEmptyWishlist() {
        wait.until(ExpectedConditions.
                visibilityOf(emptyWishlistText));
        return emptyWishlistText.getText();
    }

    public int getWishlistQuantity() {
        String quantityText = wishlistQuantity.getText();
        // Extract numerical value from the quantity text
        String numericalValue = quantityText.replaceAll("[^\\d]", "");
        // Parse the numerical value into an integer
        return Integer.parseInt(numericalValue);
    }

    public void removeItem() {
        removeFromWishlistButton.click();
    }
}











