package facebookalerts.scraper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Scraper {

    private WebDriver driver;

    public List<String> getAllPostsForGroup(String groupSite, Instant dateTime) throws InterruptedException {

        this.driver.get(groupSite);

        this.loadMoreContentOnPage();

        List<WebElement> queryResults = this.driver.findElements(By.cssSelector("[data-ad-preview=\"message\"]"));

        List<String> posts = new ArrayList<>();
        for (WebElement result : queryResults) {
            posts.add(result.getText());
        }

        return posts;
    }

    public void start() {
        System.setProperty("webdriver.chrome.driver",
                "/home/mmarian/dev/facebook-alerts/app/src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/home/mmarian/dev/facebook-alerts/app/src/main/resources/chromeprofile",
                "profile-directory=Profile 1");
        options.setBinary("/usr/bin/google-chrome-beta");

        this.driver = new ChromeDriver(options);
    }

    public void close() {
        this.driver.close();
    }

    protected void loadMoreContentOnPage() throws InterruptedException {
        for (int counter = 0; counter < 5; counter++) {
            this.driver.findElement(By.tagName("body")).sendKeys(Keys.END);
            Thread.sleep(2000);
        }
    }
}