package facebookalerts.scraper;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import facebookalerts.browserdriver.BrowserDriver;

public class Scraper {

    private BrowserDriver browserDriver;

    public Scraper(BrowserDriver driver) {
        this.browserDriver = driver;
    }

    public List<String> getAllPostsForGroup() throws InterruptedException {

        // it's very hard to load Facebook posts in succession with Selenium as...
        // 1. not all posts are shown when going to the page, you have to scroll down
        // 2. once you scroll down, the posts at the top won't be visible

        List<String> allGroupPosts = new ArrayList<String>();

        for (int counter = 0; counter < 10; counter++) {
            waitForContentToLoad();

            List<WebElement> webElementPostsFromCurrentView = this.browserDriver.get()
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

    public void goToGroupSite(String groupUrl) {
        this.browserDriver.get().get(groupUrl);
    }

    protected void waitForContentToLoad() throws InterruptedException {
        Thread.sleep(3000);
    }

    protected void goToBottomOfPage() {
        this.browserDriver.get().findElement(By.tagName("body")).sendKeys(Keys.END);
    }
}