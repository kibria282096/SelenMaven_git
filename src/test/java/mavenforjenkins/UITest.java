package mavenforjenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UITest {

    WebDriver driver; // Declare WebDriver at the class level

    @Parameters("Browser")
    @Test
    public void startBrowser(String browserName) {
        System.out.println("Parameter value is " + browserName);

        if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Aristo Computers\\Downloads\\chromedriver-win64\\chromedriver.exe");
 // Update with your ChromeDriver path
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--headless");
            opt.addArguments("--no-sandbox");
            opt.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(opt);
        } else if (browserName.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", "path/to/edgedriver"); // Update with your EdgeDriver path
            driver = new EdgeDriver();
        }

        // Ensure driver is initialized before calling manage() method
        if (driver != null) {
            driver.manage().window().maximize();
            driver.get("https://opensource-demo.orangehrmlive.com/");
            Assert.assertTrue(driver.getTitle().contains("Orange"), "Title does not match");
            driver.quit();
        } else {
            System.out.println("Browser not recognized or not initialized");
        }
    }
}
