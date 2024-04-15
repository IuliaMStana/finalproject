package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.UtilityPage;
import pageObjects.WishlistPage;

public class WishlistTests extends BaseTest {
    HomePage homePage;
    WishlistPage wishlistPage;
    ProductPage productPage;

    @BeforeMethod(groups = {"smoke", "regression"})
    public void setUp() {
        super.setUp();
        driver.get(baseUrl);
        homePage = new HomePage(driver);
        homePage.waitForPageToLoad();
    }


    @Test(groups = {"regression"})
    public void validateEmptyWishlistTest() {
        try {
            wishlistPage = new WishlistPage(driver);
            homePage.goToWishList();
            String emptyWishlistText = wishlistPage.getErrorTextEmptyWishlist();
            String expectedEmptyWishlistText = "The wishlist is empty!";
            Assert.assertEquals(emptyWishlistText, expectedEmptyWishlistText);
            Assert.assertTrue(wishlistPage.getTitlePage().isDisplayed(), "Wishlist title is not displayed");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    @Test(groups = {"smoke"})
    public void addToWishlistTest() {
        try {
            wishlistPage = new WishlistPage(driver);
            int initialWishlistQuantity = wishlistPage.getWishlistQuantity();
            homePage.clickProductLink();
            productPage = new ProductPage(driver);
            String productName = productPage.getProductDetailsTitle().getText();
            productPage.addToWishlistButton();
            wishlistPage.waitForPageToLoad();
            int updatedWishlistQuantity = wishlistPage.getWishlistQuantity();
            String addToWishlistText = productPage.getTextAddToWishlist();
            String expectedAddToWishlistMessage = "The product has been added to your wishlist";
            Assert.assertEquals(addToWishlistText, expectedAddToWishlistMessage);
            productPage.closeBanner();
            homePage.goToWishList();
            Assert.assertEquals(updatedWishlistQuantity, initialWishlistQuantity + 1, "Quantity did not increase after adding the product to the wishlist");
            UtilityPage utilityPage = new UtilityPage(driver);
            Assert.assertTrue(utilityPage.getProductTableRows("product").contains(productName), "Product was not found in wishlist table");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    @Test(groups = {"regression"})
    public void removeFromWishlistTest() {
        try {
            wishlistPage = new WishlistPage(driver);
            homePage.clickProductLink();
            productPage = new ProductPage(driver);
            productPage.addToWishlistButton();
            wishlistPage.waitForPageToLoad();
            int updatedWishlistQuantity = wishlistPage.getWishlistQuantity();
            productPage.closeBanner();
            homePage.goToWishList();
            wishlistPage.removeItem();
            String removeWishlistText = wishlistPage.getErrorTextEmptyWishlist();
            String expectedRemoveFromWishlistText = "The wishlist is empty!";
            Assert.assertEquals(removeWishlistText, expectedRemoveFromWishlistText, "the product was not removed");
            Assert.assertEquals(updatedWishlistQuantity - 1, wishlistPage.getWishlistQuantity(), "Quantity did not decrease after removing the product from the wishlist");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }
}


