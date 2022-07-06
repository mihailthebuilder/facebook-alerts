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

    private WebDriver driver;

    public Scraper() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=./src/main/resources/chromeprofile", "profile-directory=Profile 1");
        options.setBinary("/usr/bin/google-chrome-beta");

        this.driver = new ChromeDriver(options);

    }

    public List<String> getAllPostsForGroup(String groupSite, Instant dateTime) throws InterruptedException {

        this.driver.get(groupSite);

        List<WebElement> queryResults = this.driver.findElements(By.cssSelector("[data-ad-preview=\"message\"]"));

        List<String> posts = new ArrayList<>();
        for (WebElement result : queryResults) {
            posts.add(result.getText());
        }

        Thread.sleep(1000);
        return posts;
    }

    public void close() {
        this.driver.close();
    }
}