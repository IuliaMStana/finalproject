package pageObjects;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShoppingCartPage extends BasePage{
    @FindBy(xpath = "//span[@class='cart-qty']")
    private WebElement shoppingCartQuantity;
    @FindBy(xpath = "//button[@class='button-1 cart-button' and text()='Go to cart']")
    private WebElement goToCartButton;
    @Getter
    @FindBy(xpath = "//div[@class='page-title']/h1[text()='Shopping cart']")
    private WebElement pageTitle;
    @FindBy(xpath = "//div[@class='no-data']")
    private WebElement emptyShoppingCartText;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getEmptyShoppingCartText() {
        wait.until(ExpectedConditions.
                visibilityOf(emptyShoppingCartText));
        return emptyShoppingCartText.getText();
    }

    public int getShoppingCartQuantity() {
        String quantityText = shoppingCartQuantity.getText();
        // Extract numerical value from the quantity text
        String numericalValue = quantityText.replaceAll("[^\\d]", "");
        // Parse the numerical value into an integer
        return Integer.parseInt(numericalValue);
    }

    @Override
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.
                visibilityOf(shoppingCartQuantity));
        wait.until(ExpectedConditions.textToBePresentInElement
                (shoppingCartQuantity, "1"));
    }
}
