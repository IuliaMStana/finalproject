package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserUtils {

    public static WebDriver setUpBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case ("chrome"): {
                return new ChromeDriver();
            }
            case ("firefox"): {
                return new FirefoxDriver();
            }
            case ("edge"): {
                return new EdgeDriver();
            }
            default: {
                System.out.println("Unsupported browser. We will switch default to chrome");
                return new ChromeDriver();
            }
        }
    }
}
