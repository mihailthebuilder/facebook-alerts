package facebookalerts.scraper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Scraper {

    // String postText = queryResults.get(0).getText();

    // for (KeywordRecord keyword : facebookGroup.keywords()) {
    // if (postText.contains(keyword.keyword())) {
    // for (String email : keyword.subscribedEmailAddresses()) {
    // notifications.get(email).add(postText);
    // }
    // }
    // }

    public List<String> getAllPostsForGroup(String groupUrlId, Instant dateTime) throws InterruptedException {

        WebDriver driver = this.startDriver();
        driver.get(groupUrlId);

        List<WebElement> queryResults = driver.findElements(By.cssSelector("[data-ad-preview=\"message\"]"));

        List<String> posts = new ArrayList<>();
        for (WebElement result : queryResults) {
            posts.add(result.getText());
        }

        Thread.sleep(1000);
        driver.close();

        return posts;
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