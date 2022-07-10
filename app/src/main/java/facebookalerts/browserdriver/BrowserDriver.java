package facebookalerts.browserdriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class BrowserDriver {

    private WebDriver driver;

    public void start() {
        System.setProperty("webdriver.chrome.driver",
                "/home/mmarian/dev/facebook-alerts/app/src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/home/mmarian/dev/facebook-alerts/app/src/main/resources/chromeprofile",
                "profile-directory=Profile 1");
        options.setBinary("/usr/bin/google-chrome-beta");

        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void close() {
        this.driver.close();
    }

    public void goToBottomOfPage() {
        this.driver.findElement(By.tagName("body")).sendKeys(Keys.END);
    }

    public void goToWebsite(String url) {
        this.driver.get(url);
    }

    public List<String> getAllElementsAsTextByCssSelector(String selector) {
        List<String> textList = new ArrayList<String>();

        List<WebElement> webElements = this.driver
                .findElements(By.cssSelector(selector));

        for (WebElement element : webElements) {
            textList.add(element.getText());
        }

        return textList;
    }

    public WebElement findElementByCssSelector(String selector) {
        return this.driver.findElement(By.cssSelector(selector));
    }

    public void sendKeys(String keys) {
        Actions actions = new Actions(this.driver);
        actions.sendKeys(keys);
    }
}
