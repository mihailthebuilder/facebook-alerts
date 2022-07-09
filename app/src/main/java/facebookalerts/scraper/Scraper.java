package facebookalerts.scraper;

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

    public List<String> getAllPostsForGroup() throws InterruptedException {

        // it's very hard to load Facebook posts in succession with Selenium as...
        // 1. not all posts are shown when going to the page, you have to scroll down
        // 2. once you scroll down, the posts at the top won't be visible

        List<String> allGroupPosts = new ArrayList<String>();

        for (int counter = 0; counter < 10; counter++) {
            waitForContentToLoad();

            List<WebElement> webElementPostsFromCurrentView = this.driver
                    .findElements(By.cssSelector("[data-ad-preview=\"message\"]"));

            for (WebElement webElementPost : webElementPostsFromCurrentView) {
                String postText = webElementPost.getText();

                if (postText.length() > 3 && !allGroupPosts.contains(postText)) {
                    allGroupPosts.add(postText);
                }
            }

            goToBottomOfPage();
        }

        return allGroupPosts;
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

    public void goToGroupSite(String groupUrl) {
        this.driver.get(groupUrl);
    }

    protected void waitForContentToLoad() throws InterruptedException {
        Thread.sleep(3000);
    }

    protected void goToBottomOfPage() {
        this.driver.findElement(By.tagName("body")).sendKeys(Keys.END);
    }
}