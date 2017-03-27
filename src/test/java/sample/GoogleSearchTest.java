package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by hofa on 27.03.2017.
 */
public class GoogleSearchTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyFoxmindedIsPresentInSearchResult() {
        String searchQuery = "Foxminded";
        String expectedUrl = "foxminded.com.ua/";

        driver.get("https://www.google.com/");
        WebElement searchInput = driver.findElement(By.cssSelector("input[name=\"q\"]"));
        searchInput.sendKeys(searchQuery);

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ires")));

        List<WebElement> searchResults = driver.findElements(By.cssSelector("div.rc cite"));

        boolean resultFound = false;
        for (WebElement serchResult : searchResults) {
            if (serchResult.getText().equals(expectedUrl)) {
                resultFound = true;
                break;
            }
        }
        Assert.assertTrue(resultFound, "URL not found in google search result");
    }
}
