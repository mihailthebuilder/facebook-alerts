package facebookalerts.scraper;

import java.util.ArrayList;
import java.util.List;

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

            List<String> postsFromCurrentView = this.browserDriver
                    .getAllElementsAsTextByCssSelector("[data-ad-preview=\"message\"]");

            for (String post : postsFromCurrentView) {
                if (post.length() > 3 && !allGroupPosts.contains(post)) {
                    allGroupPosts.add(post);
                }
            }

            this.browserDriver.goToBottomOfPage();
        }

        return allGroupPosts;
    }

    private void waitForContentToLoad() throws InterruptedException {
        Thread.sleep(3000);
    }
}