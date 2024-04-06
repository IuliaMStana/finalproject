package pageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UtilityPage extends BasePage{

    @FindBy(xpath = "//table[@class='cart']/tbody")
    private WebElement productsTable;
    public UtilityPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.
                visibilityOf(productsTable));
    }

    public List<String> getProductTableRows(String column) {
        List<String> productTexts = new ArrayList<>();
        List<WebElement> rows = productsTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            WebElement productColumn = row.findElement(By.className(column));
            String productText = productColumn.getText();
            productTexts.add(productText);
        }
        return productTexts;
    }
}
