package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class ShoppingCartTests extends BaseTest {
    HomePage homePage;

    @Test(groups = {"regression"})
    public void validateEmptyShoppingCartMessage() {
        try {
            driver.get(baseUrl);
            homePage = new HomePage(driver);
            homePage.waitForPageToLoad();
            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
            homePage.goToShoppingCart();
            String emptyShoppingCartText = shoppingCartPage.getEmptyShoppingCartText();
            String expectedEmptyShoppingCartText = "Your Shopping Cart is empty!";
            Assert.assertEquals(emptyShoppingCartText, expectedEmptyShoppingCartText);
            Assert.assertTrue(shoppingCartPage.getPageTitle().isDisplayed(), "Shopping Cart title is not displayed");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    @Test(groups = {"smoke"})
    public void addToShoppingCart() {
        try {
            driver.get(baseUrl);
            homePage = new HomePage(driver);
            homePage.waitForPageToLoad();
            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
            int initialShoppingCartQuantity = shoppingCartPage.getShoppingCartQuantity();
            homePage.clickProductLink();
            ProductPage productPage = new ProductPage(driver);
            String productDetailsName = productPage.getProductDetailsTitle().getText();
            productPage.addToShoppingCartButton();
            String addToShoppingCartText = productPage.getAddToShoppingCartText();
            productPage.closeBanner();
            shoppingCartPage.waitForPageToLoad();
            int updatedShoppingCartQuantity = shoppingCartPage.getShoppingCartQuantity();
            String expectedShoppingCartMessage = "The product has been added to your shopping cart";
            Assert.assertEquals(addToShoppingCartText, expectedShoppingCartMessage);
            homePage.goToShoppingCart();
            Assert.assertEquals(updatedShoppingCartQuantity, initialShoppingCartQuantity + 1, "Quantity did not increase after adding the product to the shopping cart");
            UtilityPage utilityPage = new UtilityPage(driver);
            Assert.assertTrue(utilityPage.getProductTableRows("product").contains(productDetailsName), "Product was not found in shopping cart table");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }
}
