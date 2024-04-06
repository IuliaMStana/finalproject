package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {
    @FindBy(xpath = "//input[@id='small-searchterms']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@class='button-1 search-box-button']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='item-grid']")
    private WebElement searchResults;
    @FindBy(xpath = "//div[@class='no-result']")
    private WebElement noResultsSearchText;

    @FindBy(xpath = "//div[@class='inputs reversed']/input[@id='advs']")
    private WebElement advancedSearchLink;

    @FindBy(id = "//select[@name='mid']")
    private WebElement manufacturer;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitForPageToLoad() {
        searchBox = wait.until(ExpectedConditions.
                visibilityOf(searchBox));
        searchButton = wait.until(ExpectedConditions.
                visibilityOf(searchButton));
    }

    public void searchProduct(String productName) {
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    public void verifyResults(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchResults));
        List<WebElement> h2Elements = searchResults.findElements(By.tagName("h2"));
        for (WebElement h2Element : h2Elements) {
            Assert.assertTrue(h2Element.getText().toLowerCase().contains(productName.toLowerCase()));
            System.out.println(h2Element.getText());
        }
    }

        public void verifyNoResultsText () {
            wait.until(ExpectedConditions.visibilityOf(noResultsSearchText));
            Assert.assertTrue(noResultsSearchText.isDisplayed(), "Empty state text is not displayed");
        }

        public void goToAdvancedSearch () {
            wait.until(ExpectedConditions.visibilityOf(advancedSearchLink));
            advancedSearchLink.click();
        }

        public void selectManufacturer (String manufacturer){
            Select select = new Select(driver.findElement(By.xpath("//select[@id='mid']")));
            select.selectByVisibleText(manufacturer);
        }
    }








