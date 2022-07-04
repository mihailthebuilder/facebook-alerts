package facebookalerts.scraper;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.records.KeywordRecord;

public class Scraper {

    private PostCssSelector selector = new PostCssSelector();

    public Map<String, List<String>> getUserNotifications(FacebookGroupRecord facebookGroup, Instant dateTime)
            throws InterruptedException {

        WebDriver driver = this.startDriver();

        driver.get(facebookGroup.facebookUrlId());

        Map<String, List<String>> notifications = new HashMap<String, List<String>>();

        int selectorCounter = 1;

        while (true) {
            List<WebElement> queryResults = driver.findElements(
                    By.cssSelector(this.selector.createPostCssSelector(selectorCounter)));

            if (queryResults.size() == 0) {
                break;
            }

            String postText = queryResults.get(0).getText();

            for (KeywordRecord keyword : facebookGroup.keywords()) {
                if (postText.contains(keyword.keyword())) {
                    for (String email : keyword.subscribedEmailAddresses()) {
                        notifications.get(email).add(postText);
                    }
                }
            }

            ++selectorCounter;
        }

        Thread.sleep(1000);
        driver.close();

        return notifications;
    }

    protected WebDriver startDriver() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=./src/main/resources/chromeprofile", "profile-directory=Profile 1");
        options.setBinary("/usr/bin/google-chrome-beta");

        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}