package tests;
import org.testng.annotations.Test;
import pageObjects.SearchPage;


public class SearchTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void validSearchTest() {
        try {
            SearchPage searchPage=new SearchPage(driver);
            navigateToSearchPage();
            searchPage.searchProduct("Apple");
            searchPage.verifyResults("Apple");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }
    @Test(groups = {"regression"})
    public void validateNoResultsSearchMessage() {
        try {
            SearchPage searchPage=new SearchPage(driver);
            navigateToSearchPage();
            searchPage.searchProduct("cat");
            searchPage.verifyNoResultsText();
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }

    //testul nu e general valabil.Majoritatea produselor listate dupa cautare
    // nu contin in titlu cuvantul dupa care am cautat
    @Test(groups = {"regression"})
    public void advancedSearchTest() {
        try {
            SearchPage searchPage=new SearchPage(driver);
            navigateToSearchPage();
            searchPage.searchProduct("MacBook");
            searchPage.goToAdvancedSearch();
            searchPage.selectManufacturer("Apple");
            searchPage.verifyResults("MacBook");
        } catch (Throwable e) {
            takeScreenshot();
            throw e;
        }
    }
}

